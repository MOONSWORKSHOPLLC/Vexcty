package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.util.CC;
import com.moonsworkshop.vexcty.util.MessageUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand extends Command {

    private Vexcty vexcty;

    public ReportCommand(Vexcty vexcty) {
        super("vexcty");
        this.vexcty = vexcty;
    }

    private Vexcty plugin;

    public ReportCommand() {
        super("report"); // same
        this.plugin = Vexcty.getInstance();
        setUsage(CC.RED + "Usage: /report <player> <reason>"); // usage
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.MUST_PLAYER);
            return false;
        }

        Player player = (Player) sender;

        if(args.length != 2) {
            player.sendMessage(getUsage());
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            player.sendMessage(MessageUtil.TARGET_NULL.replace("%s", args[0]));
            return false;
        }

        if(args.length == 2 && args[1].length() > 32) {
            if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                player.sendMessage(CC.RED + "Please enter a short and clear reason.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                player.sendMessage(CC.RED + "الرجاء إدخال سبب قصير وواضح.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                player.sendMessage(CC.RED + "Veuillez saisir une raison courte et claire.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                player.sendMessage(CC.RED + "Inserisci un motivo breve e chiaro.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                player.sendMessage(CC.RED + "短く明確な理由を入力してください。");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                player.sendMessage(CC.RED + "Quaeso, brevem et apertam rationem ini.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                player.sendMessage(CC.RED + "ਕਿਰਪਾ ਕਰਕੇ ਇੱਕ ਛੋਟਾ ਅਤੇ ਸਪਸ਼ਟ ਕਾਰਨ ਦਾਖਲ ਕਰੋ।");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                player.sendMessage(CC.RED + "Пожалуйста, введите короткую и четкую причину.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                player.sendMessage(CC.RED + "Introduzca un motivo breve y claro.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                player.sendMessage(CC.RED + "Lütfen kısa ve net bir neden girin.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                player.sendMessage(CC.RED +"براہ کرم ایک مختصر اور واضح وجہ درج کریں۔");
            }
            return false;
        }

        if(target == player) {
            if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                player.sendMessage(CC.RED + "You can not report yourself.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                player.sendMessage(CC.RED + "لا يمكنك الإبلاغ عن نفسك.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                player.sendMessage(CC.RED + "Vous ne pouvez pas vous signaler.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                player.sendMessage(CC.RED + "Non puoi denunciare te stesso.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                player.sendMessage(CC.RED + "自分自身を報告することはできません。");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                player.sendMessage(CC.RED + "Te ipsum referre non potes.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                player.sendMessage(CC.RED + "ਤੁਸੀਂ ਆਪਣੇ ਆਪ ਨੂੰ ਰਿਪੋਰਟ ਨਹੀਂ ਕਰ ਸਕਦੇ.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                player.sendMessage(CC.RED + "Вы не можете сообщить о себе.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                player.sendMessage(CC.RED + "No puedes denunciarte a ti mismo.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                player.sendMessage(CC.RED + "Kendinizi ihbar edemezsiniz.");
            } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                player.sendMessage(CC.RED +"آپ خود رپورٹ نہیں کر سکتے۔");
            }
            return false;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(" ");
        }

        plugin.getReportManager().createReport(target, player, sb.toString());
        if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
            player.sendMessage(CC.GREEN + "Your report has been submitted.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
            player.sendMessage(CC.GREEN + "وقد قدم التقرير الخاص بك.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
            player.sendMessage(CC.GREEN + "Votre rapport a été soumis.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
            player.sendMessage(CC.GREEN + "La tua segnalazione è stata inviata.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
            player.sendMessage(CC.GREEN + "レポートが送信されました。");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
            player.sendMessage(CC.GREEN + "Tua fama est summitto.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
            player.sendMessage(CC.GREEN + "ਤੁਹਾਡੀ ਰਿਪੋਰਟ ਦਰਜ ਕਰ ਦਿੱਤੀ ਗਈ ਹੈ।");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
            player.sendMessage(CC.GREEN + "Ваш отчет отправлен.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
            player.sendMessage(CC.GREEN + "Su informe ha sido enviado.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
            player.sendMessage(CC.GREEN + "Raporunuz gönderildi.");
        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
            player.sendMessage(CC.GREEN +"آپ کی رپورٹ جمع کر دی گئی ہے۔");
        }
        plugin.getServer().getOnlinePlayers().stream().filter(players -> players.hasPermission("moderation.staff")).forEach(players -> {
            TextComponent reportMessage = new TextComponent(CC.SECONDARY + player.getName() + CC.PRIMARY + " has reported " + CC.SECONDARY + target.getName() + CC.PRIMARY + " for " + CC.SECONDARY + sb.toString());
            reportMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.I_GRAY + "Click to teleport onto " + target.getName()).create()));
            reportMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
            players.spigot().sendMessage(reportMessage);
        });

        return false;
    }

}