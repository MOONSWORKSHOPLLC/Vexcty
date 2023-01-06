package com.moonsworkshop.vexcty;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.moonsworkshop.vexcty.afk.AFKManager;
import com.moonsworkshop.vexcty.afk.MovementChecker;
import com.moonsworkshop.vexcty.backup.BackupUtil;
import com.moonsworkshop.vexcty.backup.CronUtil;
import com.moonsworkshop.vexcty.commands.*;
import com.moonsworkshop.vexcty.lobby.Cosmetics;
import com.moonsworkshop.vexcty.lobby.listener.*;
import com.moonsworkshop.vexcty.lobby.misc.ActionbarScheduler;
import com.moonsworkshop.vexcty.lobby.misc.HiderType;
import com.moonsworkshop.vexcty.events.PlayerListener;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.lang.LangCommand;
import com.moonsworkshop.vexcty.lang.LangManager;
import com.moonsworkshop.vexcty.maintenance.MaintenanceCommand;
import com.moonsworkshop.vexcty.maintenance.MaintenanceListener;
import com.moonsworkshop.vexcty.maintenance.MaintenanceManager;
import com.moonsworkshop.vexcty.managers.*;
import com.moonsworkshop.vexcty.messages.MessageCommand;
import com.moonsworkshop.vexcty.messages.ReplyCommand;
import com.moonsworkshop.vexcty.rank.*;
import com.moonsworkshop.vexcty.runnables.FrozenInventoryRunnable;
import com.moonsworkshop.vexcty.scoreboard.Board;
import com.moonsworkshop.vexcty.user.UserManager;
import com.moonsworkshop.vexcty.util.CC;
import com.moonsworkshop.vexcty.util.Config;
import com.moonsworkshop.vexcty.util.MessageHandler;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Getter
public class Vexcty extends JavaPlugin implements Listener, CommandExecutor {

    public static File file = new File("plugins/Vexcty", "config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static File fileLocations = new File("plugins/Vexcty", "locations.yml");
    public static FileConfiguration cfgL = YamlConfiguration.loadConfiguration(fileLocations);

    public static File fileMessages = new File("plugins/Vexcty", "messages.yml");
    public static FileConfiguration cfgM = YamlConfiguration.loadConfiguration(fileMessages);

    public static File fileSounds = new File("plugins/Vexcty", "sounds.yml");
    public static FileConfiguration cfgS = YamlConfiguration.loadConfiguration(fileSounds);

    public static ArrayList<String> actionbarMessages = new ArrayList<>();
    public static ArrayList<Player> build = new ArrayList<>();
    public static ArrayList<Player> fly = new ArrayList<>();
    public static ArrayList<Player> shield = new ArrayList<>();
    public static ArrayList<Player> silentLobby = new ArrayList<>();
    public static HashMap<Player, ItemStack[]> buildInventory = new HashMap<>();
    public static HashMap<Player, HiderType> playerHider = new HashMap<>();

    public static boolean globalMute = false;
    public static boolean updateAvailable = false;
    public static boolean placeholderApi = false;

    public static boolean multiWorld_mode;

    public static ArrayList<World> lobbyWorlds = new ArrayList<>();

    public static ActionbarScheduler scheduler;

    public static HashMap<String, ErrorType> errors = new HashMap<>();

    private AFKManager afkManager;

    public static boolean update;
    private Date now = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    private HashMap<String, Boolean> hiddenPlayers = new HashMap<String, Boolean>();
    private HashMap<String, Integer> violations = new HashMap<String, Integer>();

    AtomicBoolean isInBackup = new AtomicBoolean(false);
    AtomicBoolean isInUpload = new AtomicBoolean(false);

    // config options
    String crontask, backupFormat, backupDateFormat;
    File backupPath;
    int maxBackups;
    boolean onlyBackupIfPlayersWereOn;
    boolean deleteAfterUpload;
    int compressionLevel;

    String ftpType, ftpHost, ftpUser, ftpPass, ftpPath, sftpPrivateKeyPath, sftpPrivateKeyPassword;
    int ftpPort;
    boolean ftpEnable, useSftpKeyAuth;

    boolean backupPluginJars, backupPluginConfs;
    List<String> filesToIgnore;
    List<File> ignoredFiles = new ArrayList<>();

    BukkitTask bukkitCronTask = null;

    // track if players were on
    AtomicBoolean playersWereOnSinceLastBackup = new AtomicBoolean(false);

    private HashMap<UUID, UUID> recentMessages; // used for the private messages

    private static final int CONFIGURATION_ERROR = 1;

    private static Vexcty plugin;

    private Vexcty vexcty;

    private static Logger log = Logger.getLogger("Minecraft");

    public static MessageHandler messageHandler;

    private RankManager rankManager; // rank manager
    private NametagManager nametagManager; // name tag manager
    private Perks perks; // perks manager

    private LangManager langManager; // lang manager

    private MaintenanceManager maintenanceManager; // maintenance manager

    @Getter
    private static Vexcty instance;

    public PlayerManager playerManager; // player manager
    public ItemManager itemManager; // item manager
    public ChatManager chatManager; // chat manager
    public ReportManager reportManager; // report manager
    public InventoryManager inventoryManager; // inventory manager

    private UserManager userManager; // user manager

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection players;

    World world;

    public static Vexcty instancee;

    private final Map<UUID, Board> boards = new HashMap<>();

    public static Vexcty getPlugin() {
        return JavaPlugin.getPlugin(Vexcty.class);
    }

    private ConcurrentHashMap<String, Integer> listServers;
    private ConcurrentHashMap<String, Short> serverPorts;

    public void loadPlugin() {
        ignoredFiles.clear();

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        try {
            getConfig().load(getDataFolder() + "/config.yml");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        // register events
        getServer().getPluginManager().registerEvents(this, this);

        // load config data
        crontask = getConfig().getString("crontask");
        backupFormat = getConfig().getString("backup-format");
        backupDateFormat = getConfig().getString("backup-date-format");
        backupPath = new File(getConfig().getString("backup-path"));
        maxBackups = getConfig().getInt("max-backups");
        onlyBackupIfPlayersWereOn = getConfig().getBoolean("only-backup-if-players-were-on");
        deleteAfterUpload = getConfig().getBoolean("delete-after-upload");
        compressionLevel = getConfig().getInt("compression-level");
        if (!getConfig().contains("compression-level") || compressionLevel > 9 || compressionLevel < 0) {
            if (compressionLevel > 9 || compressionLevel < 0) {
                getLogger().warning("Invalid compression level set! Must be between 0-9. Defaulting to 4.");
            }
            compressionLevel = 4;
        }

        ftpEnable = getConfig().getBoolean("ftp.enable");
        ftpType = getConfig().getString("ftp.type");
        ftpHost = getConfig().getString("ftp.host");
        ftpPort = getConfig().getInt("ftp.port");
        ftpUser = getConfig().getString("ftp.user");
        ftpPass = getConfig().getString("ftp.pass");
        useSftpKeyAuth = getConfig().getBoolean("ftp.use-key-auth");
        sftpPrivateKeyPath = getConfig().getString("ftp.private-key");
        sftpPrivateKeyPassword = getConfig().getString("ftp.private-key-password");
        ftpPath = getConfig().getString("ftp.path");
        backupPluginJars = getConfig().getBoolean("backup.pluginjars");
        backupPluginConfs = getConfig().getBoolean("backup.pluginconfs");
        filesToIgnore = getConfig().getStringList("backup.ignore");
        for (String s : filesToIgnore) {
            ignoredFiles.add(new File(s));
        }

        // make sure backup location exists
        if (!backupPath.exists())
            backupPath.mkdir();

        // stop cron task if it is running
        if (bukkitCronTask != null)
            bukkitCronTask.cancel();

        // start cron task
        CronUtil.checkCron();
        bukkitCronTask = Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (CronUtil.run()) {
                if (isInBackup.get()) {
                    getLogger().warning("A backup was scheduled to happen now, but a backup was detected to be in progress. Skipping...");
                } else if (onlyBackupIfPlayersWereOn && !playersWereOnSinceLastBackup.get()) {
                    getLogger().info("No players were detected to have joined since the last backup or server start, skipping backup...");
                } else {
                    BackupUtil.doBackup(true);

                    if (Bukkit.getServer().getOnlinePlayers().size() == 0) {
                        playersWereOnSinceLastBackup.set(false);
                    }
                }
            }
        }, 20, 20);
    }

    @Override
    public void onEnable() {

        String packageName = getClass().getPackage().getName();

        for (Class<?> clazz : new Reflections(packageName + ".events")
                .getSubTypesOf(Listener.class)
        ) {
            try {
                Listener listener = (Listener) clazz
                        .getDeclaredConstructor()
                        .newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        // a lot of mongo stuff

        client = new MongoClient();
        database = client.getDatabase("moonsworkshop");
        players = database.getCollection("players");

        Player player = null;

        Document document = new Document();
        document.put("uuid", player.getUniqueId());
        document.put("rank", getRankManager().getRank(player.getUniqueId()));
        document.put("name", player.getName());

        players.insertOne(document);

        loadPlugin();

        saveDefaultConfig();

        this.createFiles();
        this.loadFiles();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            log.info("PlaceholderAPI was found. Connected.");
            placeholderApi = true;
        }

        if (cfg.getBoolean("actionbar.enabled")) {
            actionbarMessages.addAll(Vexcty.cfg.getStringList("actionbar.messages"));
            scheduler = new ActionbarScheduler(actionbarMessages);
            scheduler.start();
        }

        multiWorld_mode = Vexcty.cfg.getBoolean("multiworld_mode.enabled");

        for (World world : Bukkit.getWorlds()) {
            if (Vexcty.cfg.getStringList("lobby_worlds").contains(world.getName())) {
                lobbyWorlds.add(world);
            }
        }

        this.prepareLobbyWorlds();

        this.registerCommands();
        this.registerListener();

        Cosmetics.startBalloonTask();

        recentMessages = new HashMap<>();

        System.out.println("Plugin started...");

        getServer().getPluginManager().registerEvents(this, this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Board board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 60);

        this.listServers = new ConcurrentHashMap<String, Integer>();
        this.serverPorts = new ConcurrentHashMap<String, Short>();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this, this), this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);

                    out.writeUTF("PlayerCount");
                    out.writeUTF("ALL");
                    getServer().sendPluginMessage(Vexcty.getPlugin(), "BungeeCord", b.toByteArray());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);

                    out.writeUTF("PlayerCount");
                    out.writeUTF("Kitpvp");
                    getServer().sendPluginMessage(Vexcty.getPlugin(), "BungeeCord", b.toByteArray());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);

                    out.writeUTF("PlayerCount");
                    out.writeUTF("HCF");
                    getServer().sendPluginMessage(Vexcty.getPlugin(), "BungeeCord", b.toByteArray());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);

                    out.writeUTF("PlayerCount");
                    getServer().sendPluginMessage(Vexcty.getPlugin(), "BungeeCord", b.toByteArray());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 20L, 100);


        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MovementChecker(this.afkManager), 0L, 600L);

        this.afkManager = new AFKManager();

        instancee = this;

        plugin = this;

        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        log.info("loading configuration and message file...");
        Config.loadConfig(this);
        if (!Config.REQUIRED_CHAT && !Config.REQUIRED_CMD) {
            log.severe("Config Error: You must require a captcha on commands, chat, or both!");
            this.disablePlugin(CONFIGURATION_ERROR);
        }
        log.info("Configuration file has successfully loaded!");
        messageHandler = new MessageHandler(this);
        log.info("Messages file has succesfully loaded!");

        getServer().getPluginManager().registerEvents(this, this);

        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);
        perks = new Perks(this);

        langManager = new LangManager(this);

        maintenanceManager = new MaintenanceManager(this);
        // Why you using maintenance stuff?
        // Every other server is using it, why not implement it?
        // Bruh
        // Easter egg #1

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

        instance = this;


        Bukkit.getPluginManager().registerEvents(this, this);

        registerCommands();
        registerConfig();
        startRunnables();

        Perks.perks();

        // register listeners that are not in src/main/java/com/f2erg/vexcty/events

        Bukkit.getPluginManager().registerEvents(new Perks(this), this);
        Bukkit.getPluginManager().registerEvents(new MaintenanceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this, this), this);
        Bukkit.getPluginManager().registerEvents(new StreamingModeCommand(this), this);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Board board = new Board(player);

        if (getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
            board.updateTitle(CC.RED + "Vexcty Network");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
            board.updateTitle(CC.RED + "شبكة فيكستي");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
            board.updateTitle(CC.RED + "Réseau Vexcty");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
            board.updateTitle(CC.RED + "Rete Vexcty");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
            board.updateTitle(CC.RED + "ベクティ ネットワーク");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
            board.updateTitle(CC.RED + "Vexcty Network");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
            board.updateTitle(CC.RED + "ਵੇਕਸੀਟੀ ਨੈੱਟਵਰਕ");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
            board.updateTitle(CC.RED + "Сеть Вексти");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
            board.updateTitle(CC.RED + "red vejatorio");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
            board.updateTitle(CC.RED + "Vexty Ağı");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
            board.updateTitle(CC.RED + "ویکسیٹی نیٹ ورک");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AM)) {
            board.updateTitle(CC.RED + "Vexcty አውታረ መረብ");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZH)) {
            board.updateTitle(CC.RED + "Vexcty网络");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZHHK)) {
            board.updateTitle(CC.RED + "Vexcty網絡");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.CS)) {
            board.updateTitle(CC.RED + "Síť Vexcty");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.DE)) {
            board.updateTitle(CC.RED + "Vexcty-Netzwerk");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FI)) {
            board.updateTitle(CC.RED + "Vexcty verkko");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
            board.updateTitle(CC.RED + "Rede Vexcty");
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PL)) {
            board.updateTitle(CC.RED + "Sieć Vexcty");
        }


        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        Board board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(Board board) {

        Player player = null;

        if (getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Players: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rank: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "اللاعبين: " + getServer().getOnlinePlayers().size(),
                    "",
                    "مرتبة: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Joueurs: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rang: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Giocatori: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rango: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "プレイヤー: " + getServer().getOnlinePlayers().size(),
                    "",
                    "ランク: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Histriones: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rank: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "ਖਿਡਾਰੀ: " + getServer().getOnlinePlayers().size(),
                    "",
                    "ਰੈਂਕ: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Игроки: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Классифицировать: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Jugadores: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rango: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "oyuncular: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rütbe: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "کھلاڑی: " + getServer().getOnlinePlayers().size(),
                    "",
                    "رینک: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AM)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "ተጫዋቾች: " + getServer().getOnlinePlayers().size(),
                    "",
                    "ደረጃ: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZH)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "球员: " + getServer().getOnlinePlayers().size(),
                    "",
                    "秩: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZHHK)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "球员: " + getServer().getOnlinePlayers().size(),
                    "",
                    "秩: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.CS)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Hráči: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Hodnost: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.DE)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Spieler: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rang: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FI)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Pelaajat: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Sijoitus: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Jogadoras: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Classificação: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Gracze: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Ranga: " + getRankManager().getRank(player.getUniqueId()).getDisplay(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        }

        // You dont have to translate the server
        // i just thought i would get more players because it is international
        // Why
        // Easter egg #3
    }


    private void startRunnables() {
        this.getServer().getScheduler().runTaskTimer(this, new FrozenInventoryRunnable(), 20L, 20L);
    }

    private void registerConfigs() {
        saveDefaultConfig();
    }

    public void registerListener() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new HangingBreakByEntityListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new LeavesDecayListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerArmorStandManipulateListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerBucketEmptyListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerBucketFillListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerChangedWorldListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerFishListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerItemConsumeListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerItemDamageListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerItemHeldListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerPickupItemListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new PlayerUnleashEntityListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new SignChangeListener(), Vexcty.getInstance());
        Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), Vexcty.getInstance());
    }


    public void registerCommands() {
        Arrays.asList(
                new FreezeCommand(),
                new ChatCommand(),
                new ReportCommand()).forEach(command -> this.bukkitCommand(getName(), command));
        getCommand("kaboom").setExecutor(new KaboomCommand(this));
        getCommand("gm").setExecutor(new GamemodeCommand(this));
        getCommand("gamemode").setExecutor(new GamemodeCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("kickall").setExecutor(new KickAllCommand(this));
        getCommand("i").setExecutor(new GiveCommand(this));
        getCommand("speed").setExecutor(new SpeedCommand(this));
        getCommand("rename").setExecutor(new RenameCommand(this));
        getCommand("ping").setExecutor(new PingCommand(this));
        getCommand("openinv").setExecutor(new OpenInvCommand(this));
        getCommand("enchant").setExecutor(new EnchantCommand(this));
        getCommand("addvelocitytoplayer").setExecutor(new AddVelocityToPlayerCommand(this));
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("backup").setExecutor(this);
        getCommand("sucide").setExecutor(new SucideCommand(this));
        getCommand("language").setExecutor(new LangCommand(this));
        getCommand("help").setExecutor(new HelpCommand(this));
        getCommand("addvelocitytoall").setExecutor(new AddVelocityToAllCommand(this));
        getCommand("thiscommandisfortestingpurposesandifyourgamecrashesitsnotourproblem").setExecutor(new ThisCommandIsForTestingPurposesAndIfYourGameCrashesItsNotOurProblem());
        getCommand("newyears").setExecutor(new NewYearsCommand(this));
        getCommand("setmaintenance").setExecutor(new MaintenanceCommand(this));
        getCommand("streamingmode").setExecutor(new StreamingModeCommand(this));
        getCommand("trololol").setExecutor(new TrololololCommand(this));
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("giveadmin").setExecutor(new UnoReverseCommand());
        getCommand("skull").setExecutor(new SkullCommand());
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        // congrats if you somehow found this Easter egg #2
    public void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void bukkitCommand(String prefix, Command command) {
        MinecraftServer.getServer().server.getCommandMap().register(prefix, command);
    }

    private void registerManagers() {
        this.playerManager = new PlayerManager();
        this.itemManager = new ItemManager();
        this.chatManager = new ChatManager();
        this.reportManager = new ReportManager();
        this.inventoryManager = new InventoryManager();
    }

    public void disablePlugin(int reason) {
        if (reason == CONFIGURATION_ERROR) log.info("Disabling due to Configuration Error!");
        this.getServer().getPluginManager().disablePlugin(this);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }


    @Override
    public void onDisable() {

        for (Player players : Bukkit.getOnlinePlayers()) {
            if (Cosmetics.balloons.containsKey(players)) {
                Cosmetics.balloons.get(players).remove();
            }
        }

        this.chatManager.saveRestrictedPlayers();

        if (isInBackup.get() || isInUpload.get()) {
            getLogger().info("Any running tasks (uploads or backups) will now be cancelled due to the server shutdown.");
        }
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void prepareLobbyWorlds() {
        for (World world : multiWorld_mode ? Vexcty.lobbyWorlds : Bukkit.getWorlds()) {
            String weatherType = Vexcty.cfg.getString("weather.weather_type").toUpperCase();
            switch (weatherType) {
                case ("CLEAR"):
                    world.setStorm(false);
                    world.setThundering(false);
                    break;
                case ("RAIN"):
                    world.setStorm(false);
                    world.setThundering(false);
                    break;
                case ("THUNDER"):
                    world.setStorm(false);
                    world.setThundering(false);
                    break;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "Do /backup help for help!");
            return true;
        }

        if (!sender.hasPermission("f2erg.vexcty.admin")) {
            sender.sendMessage(CC.RED + "You do not have permission to use this command.");
        }

        switch (args[0]) {
            case "help":
                sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.GRAY + "/backup backup - Starts a backup of the server.");
                sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.GRAY + "/backup backuplocal - Starts a backup of the server, but does not upload to FTP/SFTP.");
                sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.GRAY + "/backup list - Lists the backups in the folder.");
                sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.GRAY + "/backup stats - Shows disk space.");
                sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.GRAY + "/backup testupload - Test uploading a file to FTP/SFTP without creating a backup.");
                sender.sendMessage(ChatColor.AQUA + "> " + ChatColor.GRAY + "/backup reload - Reloads the plugin settings from the config.");
                break;
            case "backup":
                if (isInBackup.get()) {
                    sender.sendMessage(ChatColor.RED + "A backup is currently in progress!");
                } else {
                    sender.sendMessage(ChatColor.GRAY + "Starting backup (check console logs for details)...");
                    Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
                        BackupUtil.doBackup(true);
                        if (sender instanceof Player) {
                            sender.sendMessage(ChatColor.GRAY + "Finished local backup!");
                        }
                    });
                }
                break;
            case "backuplocal":
                if (isInBackup.get()) {
                    sender.sendMessage(ChatColor.RED + "A backup is currently in progress!");
                } else {
                    sender.sendMessage(ChatColor.GRAY + "Starting backup (check console logs for details)...");
                    Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
                        BackupUtil.doBackup(false);
                        sender.sendMessage(ChatColor.GRAY + "Finished!");
                    });
                }
                break;
            case "list":
                sender.sendMessage(ChatColor.AQUA + "Local Backups:");
                for (File f : getPlugin().backupPath.listFiles()) {
                    sender.sendMessage(ChatColor.GRAY + "- " + f.getName());
                }
                break;
            case "stats":
                sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "=====" + ChatColor.RESET + ChatColor.DARK_AQUA + " Disk Stats " + ChatColor.RESET + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "=====");
                sender.sendMessage(ChatColor.AQUA + "Total size: " + ChatColor.GRAY + (getPlugin().getBackupPath().getTotalSpace() / 1024 / 1024 / 1024) + "GB");
                sender.sendMessage(ChatColor.AQUA + "Space usable: " + ChatColor.GRAY + (getPlugin().getBackupPath().getUsableSpace() / 1024 / 1024 / 1024) + "GB");
                sender.sendMessage(ChatColor.AQUA + "Space free: " + ChatColor.GRAY + (getPlugin().getBackupPath().getFreeSpace() / 1024 / 1024 / 1024) + "GB");
                break;
            case "testupload":
                sender.sendMessage(ChatColor.GRAY + "Starting upload test...");
                if (sender instanceof Player) {
                    sender.sendMessage(ChatColor.AQUA + "Please check the console for the upload status!");
                }
                BackupUtil.testUpload();
                break;
            case "reload":
                sender.sendMessage(ChatColor.GRAY + "Starting plugin reload...");
                loadPlugin();
                sender.sendMessage(ChatColor.GRAY + "Reloaded eBackup!");
                break;
            default:
                sender.sendMessage(ChatColor.AQUA + "Do /backup help for help!");
                break;
        }
        return true;
    }

    public void logCheat(String message) {
        logToFile(message, "cheat.txt");
    }

    public void logAction(String message) {
        logToFile(message, "action.txt");
    }

    public void logPlayerLogin(String message) {
        logToFile(message, "login.txt");
    }

    public void logToFile(String message, String filename) {
        try {
            File dataFolder = getDataFolder();
            if(!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            File saveTo = new File(getDataFolder(), filename);
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("[" + format.format(now) + "] " + message);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFiles() {
        if (!Vexcty.fileLocations.exists() | !Vexcty.fileMessages.exists() | !Vexcty.fileSounds.exists()) {
            Vexcty.getInstance().getLogger().info("One or more files were not found. Creating..");
            if (!Vexcty.fileLocations.exists()) {
                Vexcty.fileLocations.getParentFile().mkdirs();
                try {
                    fileLocations.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!Vexcty.fileMessages.exists()) {
                Vexcty.fileMessages.getParentFile().mkdirs();
                Vexcty.getInstance().saveResource("messages.yml", false);
            }
            if (!Vexcty.fileSounds.exists()) {
                Vexcty.fileMessages.getParentFile().mkdirs();
                Vexcty.getInstance().saveResource("sounds.yml", false);
            }
        }
    }

    public void loadFiles() {
        try {
            Vexcty.getInstance().getLogger().info("Loading files..");
            Vexcty.cfg.load(Vexcty.file);
            Vexcty.cfgL.load(Vexcty.fileLocations);
            Vexcty.cfgM.load(Vexcty.fileMessages);
            Vexcty.cfgS.load(Vexcty.fileSounds);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile(File file, FileConfiguration cfg) {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String GO_BACK_SKULL_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6L"
            + "y90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzY"
            + "jJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==";

    public static void playSound(Player player, Location location, String path) {
        try {
            if (Vexcty.cfgS.getBoolean(path + ".enabled")) {
                player.playSound(location,
                        Sound.valueOf(Vexcty.cfgS.getString(path + ".sound")),
                        Vexcty.cfgS.getInt(path + ".volume"),
                        Vexcty.cfgS.getInt(path + ".pitch"));
            }
        } catch (Exception ex) {
            Vexcty.errors.put(path, ErrorType.SOUND);
        }
    }

    public static Material getMaterial(String materialString) {
        try {
            Material material = Material.getMaterial(Vexcty.cfg.getString(materialString));
            if (material == null) {
                Vexcty.errors.put(materialString, ErrorType.MATERIAL);
                return Material.BARRIER;
            }
            return material;
        } catch (Exception ex) {
            Vexcty.errors.put(materialString, ErrorType.MATERIAL);
            return Material.BARRIER;
        }
    }

    public static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static boolean isLegacyVersion() {
        return Integer.parseInt(getVersion().split("_")[1]) <= 12;
    }

    public static boolean isOneEightVersion() {
        return Integer.parseInt(getVersion().split("_")[1]) == 8;
    }

    public static String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Vexcty.cfg.getString(path));
    }

    public static String getPlaceholderString(Player player, String path) {
        return PlaceholderAPI.setPlaceholders(player, Vexcty.cfg.getString(path).replace("&", "§"));
    }

    public static int getInt(String path) {
        return Vexcty.cfg.getInt(path);
    }

    public static String getName(Player player) {
        return Vexcty.cfg.getBoolean("use_displaynames") ? player.getDisplayName() : player.getName();
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage("[" + getInstance().getDescription().getName() + "] " + message);
    }

    public enum ErrorType {
        SOUND, MATERIAL
    }

    public HashMap<String, Boolean> getHiddenPlayers() {
        return hiddenPlayers;
    } // get the manager to people can use vexcty.getHiddenPlayers

    public HashMap<String, Integer> getViolations() {
        return violations;
    } // get the manager to people can use vexcty.getViolations

    public RankManager getRankManager() {
        return rankManager;
    } // get the manager to people can use vexcty.getRankManager

    public NametagManager getNametagManager() {
        return nametagManager;
    } // get the manager to people can use vexcty.getNametagManager

    public Map<String, Integer> getServers() {
        return listServers;
    } // get the manager to people can use vexcty.getServers

    public Map<String, Short> getPorts() {
        return serverPorts;
    } // get the manager to people can use vexcty.getPorts

    public UserManager getProfileManager() {
        return this.userManager;
    } // get the manager to people can use vexcty.getProfileManager

    public Perks getPerks() {
        return perks;
    } // get the manager to people can use vexcty.getPerks

    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    } // get the manager to people can use vexcty.recentMessages

    public LangManager getLangManager() {
        return langManager;
    } // get the manager to people can use vexcty.getLangManager

    public MaintenanceManager getMaintenanceManager() {
        return maintenanceManager;
    } // get the manager to people can use vexcty.getMaintenaceManager

    public static Vexcty getInstance() {
        return Vexcty.instance;
    }

}