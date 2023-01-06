package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.player.PlayerData;
import com.moonsworkshop.vexcty.player.PlayerState;
import com.moonsworkshop.vexcty.util.CC;
import com.moonsworkshop.vexcty.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ModerationCommand extends Command {

    private Vexcty plugin;

    private Vexcty vexcty;

    public ModerationCommand(Vexcty vexcty) {
        super("vexcty");
        this.vexcty = vexcty;
    }

    public ModerationCommand() {
        super("moderation");
        this.plugin = Vexcty.getInstance();
        setAliases(Arrays.asList("mod", "staff"));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.MUST_PLAYER);
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("moderation.staff")) {
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
            return false;
        }

        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("reports")) {
                plugin.getInventoryManager().setupReportInventory();
                player.openInventory(plugin.getInventoryManager().getReportInventory().getCurrentPage());
            }
            return false;
        }

        PlayerData data = plugin.getPlayerManager().getPlayerData(player);

        if(data == null) { // if the data is null
            player.sendMessage(MessageUtil.NULL_DATA);
            return false;
        }

        if(data.getPlayerState() == PlayerState.MODERATOR) {
            data.out();
            player.sendMessage(CC.RED + "You successfully left moderation mode.");
            return false;
        }

        data.in();
        player.sendMessage(CC.GREEN + "You successfully entered moderation mode.");

        return false;
    }

}
