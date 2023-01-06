package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    private Vexcty vexcty;

    public HelpCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) { // if the lang is en
            player.sendMessage(CC.YELLOW + "Have a server bug or recommendations? Visit vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
            player.sendMessage(CC.YELLOW + "هل لديك خطأ في الخادم أو توصيات؟ قم بزيارة vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
            player.sendMessage(CC.YELLOW + "Vous avez un bogue de serveur ou des recommandations ? Visitez vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
            player.sendMessage(CC.YELLOW + "Hai un bug del server o consigli? Visita vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
            player.sendMessage(CC.YELLOW + "サーバーのバグや推奨事項がありますか? vexcty.com/help.html にアクセスしてください。");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
            player.sendMessage(CC.YELLOW + "Have a server bug or recommendations? Visit vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
            player.sendMessage(CC.YELLOW + "ਕੀ ਕੋਈ ਸਰਵਰ ਬੱਗ ਜਾਂ ਸਿਫ਼ਾਰਸ਼ਾਂ ਹਨ? vexcty.com/help.html 'ਤੇ ਜਾਓ।");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
            player.sendMessage(CC.YELLOW + "Есть ошибка сервера или рекомендации? Посетите vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
            player.sendMessage(CC.YELLOW + "¿Tiene un error de servidor o recomendaciones? Visite vexcty.com/help.html.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
            player.sendMessage(CC.YELLOW + "Bir sunucu hatası veya önerileriniz mi var? vexcty.com/help.html adresini ziyaret edin.");
        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
            player.sendMessage(CC.YELLOW + "سرور کی خرابی یا سفارشات ہیں؟ vexcty.com/help.html ملاحظہ کریں۔");
        }

        return false;
    }
}
