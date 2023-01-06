package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class AddVelocityToAllCommand implements CommandExecutor {

    private Vexcty vexcty;

    public AddVelocityToAllCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("f2erg.vexcty.admin")) {
                if (args.length == 0) {
                    for (Player target : Bukkit.getOnlinePlayers()) { // get all players
                        if (args[0].equals(0)) {
                            target.setVelocity(new Vector(0, 1, 0));
                        } else if (args[0].equals(2)) {
                            target.setVelocity(new Vector(0, 2, 0));
                        } else if (args[0].equals(3)) {
                            target.setVelocity(new Vector(0, 3, 0));
                        } else if (args[0].equals(4)) {
                            target.setVelocity(new Vector(0, 4, 0));
                        } else if (args[0].equals(5)) {
                            target.setVelocity(new Vector(0, 5, 0));
                        } else if (args[0].equals(6)) {
                            target.setVelocity(new Vector(0, 6, 0));
                        } else if (args[0].equals(7)) {
                            target.setVelocity(new Vector(0, 7, 0));
                        } else if (args[0].equals(8)) {
                            target.setVelocity(new Vector(0, 8, 0));
                        } else if (args[0].equals(9)) {
                            target.setVelocity(new Vector(0, 9, 0));
                        } else if (args[0].equals(10)) {
                            target.setVelocity(new Vector(0, 10, 0));
                        }
                    }

                    player.sendMessage(CC.GREEN + "Congrats, no but actually why tho?");
                } else {
                    player.sendMessage(CC.RED + "Invalid Usage! Please use /addvelocitytoall <power>");
                }


            } else {
                if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                    player.sendMessage(ChatColor.RED + "ليس لديك إذن لاستخدام هذا الأمر.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                    player.sendMessage(ChatColor.RED + "Vous n'êtes pas autorisé à utiliser cette commande.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                    player.sendMessage(ChatColor.RED + "Non hai l'autorizzazione per usare questo comando.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                    player.sendMessage(ChatColor.RED + "このコマンドを使用する権限がありません。");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                    player.sendMessage(ChatColor.RED + "Hoc imperio uti non licebit.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                    player.sendMessage(ChatColor.RED + "ਤੁਹਾਨੂੰ ਇਹ ਕਮਾਂਡ ਵਰਤਣ ਦੀ ਇਜਾਜ਼ਤ ਨਹੀਂ ਹੈ।");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                    player.sendMessage(ChatColor.RED + "У вас нет разрешения на использование этой команды.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                    player.sendMessage(ChatColor.RED + "No tiene permiso para usar este comando.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                    player.sendMessage(ChatColor.RED + "Bu komutu kullanma izniniz yok.");
                } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                    player.sendMessage(ChatColor.RED + "آپ کو یہ کمانڈ استعمال کرنے کی اجازت نہیں ہے۔");
                }
            }
        }

        return false;
    }
}
