package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.ConfigMessages;
import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class PingCommand implements CommandExecutor {

    private Vexcty vexcty;

    public PingCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("f2erg.vexcty.admin")) {
                try {
                    Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                    int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer); // get the ping

                    commandSender.sendMessage(ConfigMessages.get("ping").replace("{player}", player.getName())
                            .replace("{ping}", String.valueOf(ping)));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
                    e.printStackTrace();
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
