package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KaboomCommand implements CommandExecutor {

    private Vexcty vexcty;

    public KaboomCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("hypixelkabom.kaboom")) {
            if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "You do not have permission to use this command.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "ليس لديك إذن لاستخدام هذا الأمر.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "Vous n'êtes pas autorisé à utiliser cette commande.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "Non hai l'autorizzazione per usare questo comando.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "このコマンドを使用する権限がありません。");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "Hoc imperio uti non licebit.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "ਤੁਹਾਨੂੰ ਇਹ ਕਮਾਂਡ ਵਰਤਣ ਦੀ ਇਜਾਜ਼ਤ ਨਹੀਂ ਹੈ।");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "У вас нет разрешения на использование этой команды.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "No tiene permiso para usar este comando.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "Bu komutu kullanma izniniz yok.");
            } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                player.sendMessage(org.bukkit.ChatColor.RED + "آپ کو یہ کمانڈ استعمال کرنے کی اجازت نہیں ہے۔");
            }
            return false;
        }

        if(args.length == 0) {
            for(Player pl : Bukkit.getOnlinePlayers()) { // get all the players on the server
                pl.getWorld().strikeLightningEffect(pl.getLocation());
                Vector playerVec = new Vector(0, 1, 0);
                pl.setVelocity(playerVec.multiply(5));
                player.sendMessage(ChatColor.GREEN + "Launched " + pl.getName() + "!");
            }
            return true;
        }
        else {
            // A specific player on the server
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage(ChatColor.RED + "Player not online!");
                return false;
            }

            target.getWorld().strikeLightningEffect(target.getLocation());
            Vector playerVec = new Vector(0, 1, 0);
            target.setVelocity(playerVec.multiply(5));
            player.sendMessage(ChatColor.GREEN + "Launched " + target.getName() + "!");

            return true;
        }

    }
}
