package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.ConfigMessages;
import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {

    private Vexcty vexcty;

    public RenameCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String displayName = "display name";

        Player player = (Player) commandSender;

        ItemStack item = player.getItemInHand();

        if (player.hasPermission("f2erg.vexcty.admin")) {
            if(item == null || item.getType() == Material.AIR) {
                player.sendMessage(ConfigMessages.get("rename-noitem")); // if the player does not have a item in their hand
                return true;
            }

            displayName = ChatColor.translateAlternateColorCodes('&', displayName);

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(displayName);
            item.setItemMeta(meta);

            player.setItemInHand(item);

            player.sendMessage(ConfigMessages.get("renamed").replace("{item}", item.getType().name().toLowerCase().replace('_', ' '))
                    .replace("{name}", displayName));
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

        return false;
    }
}
