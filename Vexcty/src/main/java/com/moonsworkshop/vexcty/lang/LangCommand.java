package com.moonsworkshop.vexcty.lang;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.util.CC;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LangCommand implements CommandExecutor {

    private Vexcty vexcty;

    public LangCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 0) {
                for (Lang lang : Lang.values()) {
                    if (args[0].equalsIgnoreCase("ar") || args[0].equalsIgnoreCase("arabic") || args[0].equalsIgnoreCase("عربي")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.AR);
                        player.sendMessage(CC.GREEN + "لقد قمت بتغيير لغتك إلى العربية.");
                    } else if (args[0].equalsIgnoreCase("en") || args[0].equalsIgnoreCase("english")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.EN);
                        player.sendMessage(CC.GREEN + "You have changed your language to English.");
                    } else if (args[0].equalsIgnoreCase("fr") || args[0].equalsIgnoreCase("french") || args[0].equalsIgnoreCase("Français")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.FR);
                        player.sendMessage(CC.GREEN + "Vous avez changé votre langue en français.");
                    } else if (args[0].equalsIgnoreCase("ur") || args[0].equalsIgnoreCase("urdu") || args[0].equalsIgnoreCase("اردو")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.UR);
                        player.sendMessage(CC.GREEN + "آپ نے اپنی زبان کو اردو میں بدل دیا ہے۔");
                    } else if (args[0].equalsIgnoreCase("it") || args[0].equalsIgnoreCase("italian") || args[0].equalsIgnoreCase("Italiano")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.IT);
                        player.sendMessage(CC.GREEN + "Hai cambiato la tua lingua in italiano.");
                    } else if (args[0].equalsIgnoreCase("pa") || args[0].equalsIgnoreCase("punjabi") || args[0].equalsIgnoreCase("ਪੰਜਾਬੀ")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.PA);
                        player.sendMessage(CC.GREEN + "ਤੁਸੀਂ ਆਪਣੀ ਭਾਸ਼ਾ ਪੰਜਾਬ ਬਦਲ ਲਈ ਹੈ।");
                    } else if (args[0].equalsIgnoreCase("la") || args[0].equalsIgnoreCase("latin") || args[0].equalsIgnoreCase("Latinus")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.LA);
                        player.sendMessage(CC.GREEN + "Linguam tuam in Latinam mutasti.");
                    } else if (args[0].equalsIgnoreCase("es") || args[0].equalsIgnoreCase("spanish") || args[0].equalsIgnoreCase("Español")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.ES);
                        player.sendMessage(CC.GREEN + "Has cambiado tu idioma a español.");
                    } else if (args[0].equalsIgnoreCase("ja") || args[0].equalsIgnoreCase("japanese") || args[0].equalsIgnoreCase("日本")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.JA);
                        player.sendMessage(CC.GREEN + "言語を日本語に変更しました。");
                    } else if (args[0].equalsIgnoreCase("ru") || args[0].equalsIgnoreCase("russian") || args[0].equalsIgnoreCase("Русский")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.RU);
                        player.sendMessage(CC.GREEN + "Вы сменили язык на русский.");
                    } else if (args[0].equalsIgnoreCase("tr") || args[0].equalsIgnoreCase("turkey") || args[0].equalsIgnoreCase("Türk")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.TR);
                        player.sendMessage(CC.GREEN + "Dilinizi Türkçe olarak değiştirmişsiniz.");
                    } else if (args[0].equalsIgnoreCase("Amharic") || args[0].equalsIgnoreCase("አማርኛ")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.AM);
                        player.sendMessage(CC.GREEN + "ቋንቋህን ወደ አማርኛ ቀይረሃል።");
                    } else if (args[0].equalsIgnoreCase("chineses") || args[0].equalsIgnoreCase("简体中文")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.ZH);
                        player.sendMessage(CC.GREEN + "您已将语言更改为简体中文。");
                    } else if (args[0].equalsIgnoreCase("chineset") || args[0].equalsIgnoreCase("中國傳統的")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.ZHHK);
                        player.sendMessage(CC.GREEN + "您已將語言更改為繁體中文。");
                    } else if (args[0].equalsIgnoreCase("czech") || args[0].equalsIgnoreCase("čeština")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.CS);
                        player.sendMessage(CC.GREEN + "Změnili jste jazyk na češtinu.");
                    } else if (args[0].equalsIgnoreCase("german") || args[0].equalsIgnoreCase("Deutch")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.DE);
                        player.sendMessage(CC.GREEN + "Sie haben Ihre Sprache auf Deutsch umgestellt.");
                    } else if (args[0].equalsIgnoreCase("finnish") || args[0].equalsIgnoreCase("Suomalainen")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.FI);
                        player.sendMessage(CC.GREEN + "Olet vaihtanut kielesi suomeksi.");
                    } else if (args[0].equalsIgnoreCase("portuguese") || args[0].equalsIgnoreCase("Português")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.PT);
                        player.sendMessage(CC.GREEN + "Você mudou seu idioma para português.");
                    } else if (args[0].equalsIgnoreCase("polish") || args[0].equalsIgnoreCase("Polski")) {
                        vexcty.getLangManager().setLang(player.getUniqueId(), Lang.PL);
                        player.sendMessage(CC.GREEN + "Zmieniłeś język na polski.");
                    } else if (args[0].equalsIgnoreCase("list")) {
                        player.sendMessage(CC.GREEN + "The available languages are \n" +
                                "- EN (English/English) \n" +
                                "- AR (Arabic/عربي) \n" +
                                "- FR (French/Français) \n" +
                                "- IT (Italian/Italiano) \n" +
                                "- JA (Japanese/日本) \n" +
                                "- LA (Latin/Latinus) \n" +
                                "- PA (Punjab/ਪੰਜਾਬੀ) \n" +
                                "- RU (Russian/Русский) \n" +
                                "- ES (Spanish/Español) \n" +
                                "- TR (Turkish/Türk) \n" +
                                "- UR (Urdu/اردو) \n" +
                                "- AM (Amharic/አማርኛ) \n" +
                                "- ZH (Chinese Simplified/简体中文) \n" +
                                "- ZHHK (Chinese Traditional/中國傳統的) \n" +
                                "- CS (Czech/čeština) \n" +
                                "- DE (German/Deutch) \n" +
                                "- FI (Finnish/Soumalainen) \n" +
                                "- PT (Portuguese/Português) \n" +
                                "- PL (Polish/Polski)");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Please use /lang <language code>.");
            }
        }

        return false;
    }
}