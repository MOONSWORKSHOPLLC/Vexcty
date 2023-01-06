package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.ConfigMessages;
import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.util.ItemDatabase;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GiveCommand implements CommandExecutor {

    private Vexcty vexcty;

    public GiveCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if (player.hasPermission("f2erg.vexcty.admin")) {
            String itemString = "name";
            int amount = 0;

            String[] split = itemString.split(":");

            ItemStack item = ItemDatabase.getItemByAlias(split[0]);

            System.out.println(item);
            System.out.println(split[0]);

            if(item == null) {
                try {
                    item = new ItemStack(Integer.parseInt(split[0]));
                } catch(NumberFormatException ignored) { }
            }

            if(item != null) {
                try {
                    if(split.length > 1) item.setDurability(Short.parseShort(split[1]));
                } catch(NumberFormatException ignored) { }

                int leftOverAmount = 0;

                item.setAmount(amount); // get the amount

                HashMap<Integer, ItemStack> leftOver = player.getInventory().addItem(item);

                for(ItemStack leftOverStack : leftOver.values()) {
                    leftOverAmount += leftOverStack.getAmount();
                }

                player.sendMessage(ConfigMessages.get("give").replace("{count}", String.valueOf(amount - leftOverAmount)).replace("{item}",
                        item.getType().name().toLowerCase().replace('_', ' ')));
            } else {
                player.sendMessage(ConfigMessages.get("give-noitem"));
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

        return false;
    }
}
