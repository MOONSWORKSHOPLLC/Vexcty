package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.player.PlayerData;
import com.moonsworkshop.vexcty.player.PlayerState;
import com.moonsworkshop.vexcty.util.CC;
import com.moonsworkshop.vexcty.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand extends Command {

    private Vexcty vexcty;

    public FreezeCommand(Vexcty vexcty) {
        super("vexcty");
        this.vexcty = vexcty;
    }

    public FreezeCommand() {
        super("freeze");
        this.vexcty = Vexcty.getInstance();
        setUsage(CC.RED + "Usage: /freeze <player>");
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

        if(args.length == 0 || args.length >= 2) {
            player.sendMessage(getUsage());
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]); // get the player

        if(target == null) { // if the player is offline
            player.sendMessage(MessageUtil.TARGET_NULL.replace("%s", args[0]));
            return false;
        }

        PlayerData targetData = vexcty.getPlayerManager().getPlayerData(target);
        PlayerState targetState = targetData.getPlayerState();

        targetData.setPlayerState(targetState == PlayerState.FROZEN ? PlayerState.PLAYING : PlayerState.FROZEN);
        player.sendMessage(CC.PRIMARY + "You've successfully " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen ") + CC.SECONDARY + target.getName());
        target.sendMessage(CC.PRIMARY + "You've been " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen"));
        target.closeInventory();

        return false;
    }

}
