package com.moonsworkshop.vexcty.backup;

import com.moonsworkshop.vexcty.Vexcty;
import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackupUtil {

    // delete old backups (when limit reached)
    private static void checkMaxBackups() {
        if (Vexcty.getPlugin().getMaxBackups() <= 0) return;

        int backups = 0;
        SortedMap<Long, File> m = new TreeMap<>(); // oldest files to newest

        for (File f : Vexcty.getPlugin().getBackupPath().listFiles()) {
            if (f.getName().endsWith(".zip")) {
                backups++;
                m.put(f.lastModified(), f);
            }
        }

        // delete old backups
        while (backups-- >= Vexcty.getPlugin().getMaxBackups()) {
            m.get(m.firstKey()).delete();
            m.remove(m.firstKey());
        }
    }

    // actually do the backup
    // run async please
    public static void doBackup(boolean uploadToServer) {
        List<File> tempIgnore = new ArrayList<>();
        Vexcty.getPlugin().getLogger().info("Starting backup...");

        // do not backup when plugin is disabled
        if (!Vexcty.getPlugin().isEnabled()) {
            Vexcty.getPlugin().getLogger().warning("Unable to start a backup, because the plugin is disabled by the server!");
            return;
        }

        // prevent other processes from backing up at the same time
        Vexcty.getPlugin().getIsInBackup().set(true);

        File currentWorkingDirectory = new File(Paths.get(".").toAbsolutePath().normalize().toString());

        try {
            // find plugin data to ignore
            for (File f : new File("plugins").listFiles()) {
                if ((!Vexcty.getPlugin().isBackupPluginJars() && f.getName().endsWith(".jar")) || (!Vexcty.getPlugin().isBackupPluginConfs() && f.isDirectory())) {
                    tempIgnore.add(f);
                    Vexcty.getPlugin().getIgnoredFiles().add(f);
                }
            }

            // delete old backups
            checkMaxBackups();

            // zip
            SimpleDateFormat formatter = new SimpleDateFormat(Vexcty.getPlugin().getBackupDateFormat());
            String fileName = Vexcty.getPlugin().getBackupFormat().replace("{DATE}", formatter.format(new Date()));
            FileOutputStream fos = new FileOutputStream(Vexcty.getPlugin().getBackupPath() + "/" + fileName + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // set zip compression level
            zipOut.setLevel(Vexcty.getPlugin().getCompressionLevel());

            Set<String> visitedFiles = new HashSet<>();

            // backup worlds first
            for (World w : Bukkit.getWorlds()) {
                File worldFolder = w.getWorldFolder();

                String worldPath = Paths.get(currentWorkingDirectory.toURI()).relativize(Paths.get(worldFolder.toURI())).toString();
                if (worldPath.endsWith("/.")) {// 1.16 world folders end with /. for some reason
                    worldPath = worldPath.substring(0, worldPath.length() - 2);
                    worldFolder = new File(worldPath);
                }

                // check if world is in ignored list
                boolean skip = false;
                for (File f : Vexcty.getPlugin().getIgnoredFiles()) {
                    if (f.getCanonicalPath().equals(worldFolder.getCanonicalPath())) {
                        skip = true;
                        break;
                    }
                }
                if (skip) continue;

                // manually trigger world save (needs to be run sync)
                AtomicBoolean saved = new AtomicBoolean(false);
                Bukkit.getScheduler().runTask(Vexcty.getPlugin(), () -> {
                    w.save();
                    saved.set(true);
                });

                // wait until world save is finished
                while (!saved.get()) Thread.sleep(500);

                w.setAutoSave(false); // make sure autosave doesn't screw everything over

                Vexcty.getPlugin().getLogger().info("Backing up world " + w.getName() + " " + worldPath + "...");
                zipFile(worldFolder, worldPath, zipOut, visitedFiles);

                w.setAutoSave(true);

                // ignore in dfs
                tempIgnore.add(worldFolder);
                Vexcty.getPlugin().getIgnoredFiles().add(worldFolder);
            }

            // dfs all other files
            Vexcty.getPlugin().getLogger().info("Backing up other files...");
            zipFile(currentWorkingDirectory, "", zipOut, visitedFiles);
            zipOut.close();
            fos.close();

            // upload to ftp/sftp
            if (uploadToServer && Vexcty.getPlugin().isFtpEnable()) {
                uploadTask(Vexcty.getPlugin().getBackupPath() + "/" + fileName + ".zip", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (World w : Bukkit.getWorlds()) {
                w.setAutoSave(true);
            }
            // restore tempignore
            for (File f : tempIgnore) {
                Vexcty.getPlugin().getIgnoredFiles().remove(f);
            }

            // unlock
            Vexcty.getPlugin().getIsInBackup().set(false);
        }
        Vexcty.getPlugin().getLogger().info("Local backup complete!");
    }

    public static void testUpload() {
        try {
            File temp = new File(Vexcty.getPlugin().getDataFolder() + "/uploadtest.txt");
            temp.createNewFile();
            uploadTask(temp.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
            Vexcty.getPlugin().getLogger().warning("Error creating temporary file.");
        }
    }

    private static void uploadTask(String fileName, boolean testing) {
        if (Vexcty.getPlugin().getIsInUpload().get()) {
            Vexcty.getPlugin().getLogger().warning("A upload was scheduled to happen now, but an upload was detected to be in progress. Skipping...");
            return;
        }

        boolean isSFTP = Vexcty.getPlugin().getFtpType().equals("sftp"), isFTP = Vexcty.getPlugin().getFtpType().equals("ftp");
        if (!isSFTP && !isFTP) {
            Vexcty.getPlugin().getLogger().warning("Invalid upload type specified (only ftp/sftp accepted). Skipping upload...");
            return;
        }

        Vexcty.getPlugin().getLogger().info(String.format("Starting upload of %s to %s server...", fileName, isSFTP ? "SFTP" : "FTP"));
        Bukkit.getScheduler().runTaskAsynchronously(Vexcty.getPlugin(), () -> {
            try {
                Vexcty.getPlugin().getIsInUpload().set(true);

                File f = new File(fileName);
                if (isSFTP) {
                    uploadSFTP(f, testing);
                } else {
                    uploadFTP(f, testing);
                }

                // delete testing file
                if (testing) {
                    f.delete();
                    Vexcty.getPlugin().getLogger().info("Test upload successful!");
                } else {
                    Vexcty.getPlugin().getLogger().info("Upload of " + fileName + " has succeeded!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Vexcty.getPlugin().getLogger().info("Upload of " + fileName + " has failed.");
            } finally {
                Vexcty.getPlugin().getIsInUpload().set(false);
            }
        });
    }

    private static void uploadSFTP(File f, boolean testing) throws JSchException, SftpException {
        JSch jsch = new JSch();

        // ssh key auth if enabled
        if (Vexcty.getPlugin().isUseSftpKeyAuth()) {
            if (Vexcty.getPlugin().getSftpPrivateKeyPassword().equals("")) {
                jsch.addIdentity(Vexcty.getPlugin().getSftpPrivateKeyPath());
            } else {
                jsch.addIdentity(Vexcty.getPlugin().getSftpPrivateKeyPath(), Vexcty.getPlugin().getSftpPrivateKeyPassword());
            }
        }

        Session session = jsch.getSession(Vexcty.getPlugin().getFtpUser(), Vexcty.getPlugin().getFtpHost(), Vexcty.getPlugin().getFtpPort());
        // password auth if using password
        if (!Vexcty.getPlugin().isUseSftpKeyAuth()) {
            session.setPassword(Vexcty.getPlugin().getFtpPass());
        }
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(f.getAbsolutePath(), Vexcty.getPlugin().getFtpPath());

        if (testing) {
            // delete testing file
            sftpChannel.rm(Vexcty.getPlugin().getFtpPath() + "/" + f.getName());
        }

        sftpChannel.exit();
        session.disconnect();

        if (!testing) {
            deleteAfterUpload(f);
        }
    }

    private static void uploadFTP(File f, boolean testing) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setAutodetectUTF8(true);
        try (FileInputStream fio = new FileInputStream(f)) {
            ftpClient.setDataTimeout(180 * 1000);
            ftpClient.setConnectTimeout(180 * 1000);
            ftpClient.setDefaultTimeout(180 * 1000);
            ftpClient.setControlKeepAliveTimeout(60);

            ftpClient.connect(Vexcty.getPlugin().getFtpHost(), Vexcty.getPlugin().getFtpPort());
            ftpClient.enterLocalPassiveMode();

            ftpClient.login(Vexcty.getPlugin().getFtpUser(), Vexcty.getPlugin().getFtpPass());
            ftpClient.setUseEPSVwithIPv4(true);

            ftpClient.changeWorkingDirectory(Vexcty.getPlugin().getFtpPath());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(1024 * 1024 * 16);

            if (ftpClient.storeFile(f.getName(), fio)) {
                if (testing) {
                    // delete testing file
                    ftpClient.deleteFile(f.getName());
                } else {
                    deleteAfterUpload(f);
                }
            } else {
                // ensure that an error message is printed if the file cannot be stored
                throw new IOException();
            }
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteAfterUpload(File f) {
        if (Vexcty.getPlugin().isDeleteAfterUpload()) {
            Bukkit.getScheduler().runTaskAsynchronously(Vexcty.getPlugin(), () -> {
                if (f.delete()) {
                    Vexcty.getPlugin().getLogger().info("Successfully deleted " + f.getName() + " after upload.");
                } else {
                    Vexcty.getPlugin().getLogger().warning("Unable to delete " + f.getName() + " after upload.");
                }
            });
        }
    }

    // recursively compress files and directories
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut, Set<String> visitedFiles) throws IOException {
        // ignore if this file has been visited before
        if (visitedFiles.contains(fileToZip.getCanonicalPath())) return;

        // return if it is ignored file
        for (File f : Vexcty.getPlugin().getIgnoredFiles()) {
            if (f.getCanonicalPath().equals(fileToZip.getCanonicalPath())) return;
        }

        // fix windows archivers not being able to see files because they don't support / (root) for zip files
        if (fileName.startsWith("/") || fileName.startsWith("\\")) {
            fileName = fileName.substring(1);
        }
        // make sure there won't be a "." folder
        if (fileName.startsWith("./") || fileName.startsWith(".\\")) {
            fileName = fileName.substring(2);
        }
        // truncate \. on windows (from the end of folder names)
        if (fileName.endsWith("/.") || fileName.endsWith("\\.")) {
            fileName = fileName.substring(0, fileName.length()-2);
        }

        visitedFiles.add(fileToZip.getCanonicalPath());

        if (fileToZip.isDirectory()) { // if it's a directory, recursively search
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut, visitedFiles);
            }
        } else { // if it's a file, store
            try {
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            } catch (IOException e) {
                Vexcty.getPlugin().getLogger().warning("Error while backing up file " + fileName + ", backup will ignore this file: " + e.getMessage());
            }
        }
    }
}
