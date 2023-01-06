package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class PunishCommand implements CommandExecutor {

    private ArrayList b1 = new ArrayList();
    private ArrayList b2 = new ArrayList();
    private ArrayList bfinal = new ArrayList();

    Calendar cal = Calendar.getInstance();


    // for vexcty in arabic: فيكستي

    private Vexcty vexcty;

    public PunishCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("f2erg.vexcty.admin")) {

                if (args.length == 3) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = (Player) Bukkit.getOfflinePlayer(args[0]);

                        if (args[1].equalsIgnoreCase("-k")) {
                            switch (args[2].toLowerCase()) {
                                case "sb" :
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        target.kickPlayer(CC.RED + "You have been kicked for suspicious behaviour. \n" +
                                                "Please contact a staff member if this repeatedly happens.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        target.kickPlayer(CC.RED + "لقد تم طردك بسبب السلوك المشبوه \n" +
                                                "يرجى الاتصال بأحد الموظفين إذا حدث هذا بشكل متكرر.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        target.kickPlayer(CC.RED + "Vous avez été expulsé pour comportement suspect. \n" +
                                                "Veuillez contacter un membre du personnel si cela se produit à plusieurs reprises.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        target.kickPlayer(CC.RED + "Sei stato preso a calci per comportamento sospetto. \n" +
                                                "Si prega di contattare un membro del personale se ciò accade ripetutamente.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        target.kickPlayer(CC.RED + "あなたは疑わしい行動のためにキックされました. \n" +
                                                "繰り返し発生する場合は、スタッフまでご連絡ください。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        target.kickPlayer(CC.RED + "Tu enim suspectum mores calce. \n" +
                                                "Quaeso tange virgam membrum si hoc saepe fit.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ ਸ਼ੱਕੀ ਵਿਵਹਾਰ ਲਈ ਮਾਰਿਆ ਗਿਆ ਹੈ। \n" +
                                                "ਜੇਕਰ ਅਜਿਹਾ ਵਾਰ-ਵਾਰ ਹੁੰਦਾ ਹੈ ਤਾਂ ਕਿਰਪਾ ਕਰਕੇ ਕਿਸੇ ਸਟਾਫ਼ ਮੈਂਬਰ ਨਾਲ ਸੰਪਰਕ ਕਰੋ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        target.kickPlayer(CC.RED + "Вас выгнали за подозрительное поведение. \n" +
                                                "Пожалуйста, свяжитесь с сотрудником, если это повторяется.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        target.kickPlayer(CC.RED + "Ha sido expulsado por comportamiento sospechoso. \n" +
                                                "Comuníquese con un miembro del personal si esto sucede repetidamente.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        target.kickPlayer(CC.RED + "Şüpheli davranıştan dolayı tekmelendiniz. \n" +
                                                "Bu tekrar tekrar olursa lütfen bir personel ile iletişime geçin.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        target.kickPlayer(CC.RED + "آپ کو مشکوک رویے کی وجہ سے مارا گیا ہے۔ \n" +
                                                "اگر ایسا بار بار ہوتا ہے تو براہ کرم عملے کے کسی رکن سے رابطہ کریں۔");
                                    }
                                    break;
                                case "eg":
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        target.kickPlayer(CC.RED + "You have been kicked for exploiting/glitching the network,\n" +
                                                "This is your only warning.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        target.kickPlayer(CC.RED + "لقد تم طردك لاستغلالك / خلل في الشبكة ،\n" +
                                                "هذا هو انذارك الاخير.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        target.kickPlayer(CC.RED + "Vous avez été kické pour avoir exploité/glitché le réseau,\n" +
                                                "Ceci est votre seul avertissement.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        target.kickPlayer(CC.RED + "Sei stato espulso per aver sfruttato/glitchato la rete,\n" +
                                                "Questo è il tuo unico avvertimento.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        target.kickPlayer(CC.RED + "あなたはネットワークの悪用/グリッチのためにキックされました,\n" +
                                                "これが唯一の警告です。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        target.kickPlayer(CC.RED + "Calce estis ad opprimendum/retis glitching,\n" +
                                                "Hoc solum admoneo.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ ਨੈੱਟਵਰਕ ਦਾ ਸ਼ੋਸ਼ਣ/ਗਲਚ ਕਰਨ ਲਈ ਮਾਰਿਆ ਗਿਆ ਹੈ,\n" +
                                                "ਇਹ ਤੁਹਾਡੀ ਸਿਰਫ ਚੇਤਾਵਨੀ ਹੈ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        target.kickPlayer(CC.RED + "Вас выгнали за использование/глюк в сети,\n" +
                                                "Это ваше единственное предупреждение.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        target.kickPlayer(CC.RED + "Ha sido expulsado por explotar/perturbar la red,\n" +
                                                "Esta es su única advertencia.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        target.kickPlayer(CC.RED + "Ağı sömürmek/hata yapmak için atıldınız,\n" +
                                                "Bu senin tek uyarın.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        target.kickPlayer(CC.RED + "آپ کو نیٹ ورک کا استحصال/غلطی کرنے پر لات مار دی گئی ہے،\n" +
                                                "یہ آپ کی واحد وارننگ ہے۔");
                                    }
                                    break;
                                default:
                                    player.sendMessage(CC.RED + "Invalid Usage! Please use /punish <player> -k \n" +
                                            "- sb (Suspicious behaviour) \n" +
                                            "- eg (Exploiting the network)");

                            }


                        } else if (args[1].equalsIgnoreCase("-b")) {
                            switch (args[2].toLowerCase()) { // get ban types
                                case "in":
                                    cal.add(Calendar.DAY_OF_WEEK, 14); // 2 weeks
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty. \n" + CC.GRAY +
                                                "Reason: " + CC.WHITE + "Having an inappropriate name.", null, null);
                                        target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "\n لقد تم حظرك من فيكستي " + CC.GRAY +
                                                "سبب: " + CC.WHITE + "الحصول على اسم غير مناسب.", null, null);
                                        target.kickPlayer(CC.RED + "لقد تم حظرك من فيكستي");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vous avez été banni de Vexcty. \n" + CC.GRAY +
                                                "Raison: " + CC.WHITE + "Avoir un nom inapproprié.", null, null);
                                        target.kickPlayer(CC.RED + "Vous avez été banni de Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Sei stato bandito da Vexcty. \n" + CC.GRAY +
                                                "Motivo: " + CC.WHITE + "Avere un nome inappropriato.", null, null);
                                        target.kickPlayer(CC.RED + "Sei stato bandito da Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "あなたは ベクティ から追放されました。 \n" + CC.GRAY +
                                                "理由: " + CC.WHITE + "不適切な名前を持つ。", null, null);
                                        target.kickPlayer(CC.RED + "あなたは ベクティ から追放されました。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "interdictum a Vexcty. \n" + CC.GRAY +
                                                "ratio: " + CC.WHITE + "Nomen habens indebitum.", null, null);
                                        target.kickPlayer(CC.RED + "interdictum a Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "ਤੁਹਾਨੂੰ ਵੇਕਸੀਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ। \n" + CC.GRAY +
                                                "ਕਾਰਨ: " + CC.WHITE + "ਇੱਕ ਅਣਉਚਿਤ ਨਾਮ ਹੋਣਾ.", null, null);
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ ਵੇਕਸੀਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Вы были забанены в Вексти. \n" + CC.GRAY +
                                                "Причина: " + CC.WHITE + "Иметь неподходящее имя.", null, null);
                                        target.kickPlayer(CC.RED + "Вы были забанены в Вексти.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Has sido expulsado de vejatorio. \n" + CC.GRAY +
                                                "Razón: " + CC.WHITE + "Tener un nombre inadecuado.", null, null);
                                        target.kickPlayer(CC.RED + "Has sido expulsado de vejatorio.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vexcty'den yasaklandınız. \n" + CC.GRAY +
                                                "Sebep: " + CC.WHITE + "Uygunsuz bir isme sahip olmak.", null, null);
                                        target.kickPlayer(CC.RED + "Vexcty'den yasaklandınız.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے۔ \n" + CC.GRAY +
                                                "وجہ: " + CC.WHITE + "نامناسب نام رکھنا۔", null, null);
                                        target.kickPlayer(CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے۔");
                                    }
                                    break;

                                case "hac":
                                    cal.add(Calendar.HOUR, 720); // idk figure it out im not tryna do this im just making comments
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty for 30 days. \n" + CC.GRAY +
                                                "Reason: " + CC.WHITE + "Hacking and giving other players and bots and unfair advantage", null, null);
                                        target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "\n لقد تم حظرك من فيكستي لمدة ٣٠ يومًا" + CC.GRAY +
                                                "سبب: " + CC.WHITE + "القرصنة ومنح لاعبين آخرين وروبوتات ومزايا غير عادلة.", null, null);
                                        target.kickPlayer(CC.RED + "لقد تم حظرك من فيكستي");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vous avez été banni de Vexcty \n" + CC.GRAY +
                                                "Raison: " + CC.WHITE + "Piratage et donner aux autres joueurs et bots et avantage injuste pendant 30 jours.", null, null);
                                        target.kickPlayer(CC.RED + "Vous avez été banni de Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Sei stato bandito da Vexcty. \n" + CC.GRAY +
                                                "Motivo: " + CC.WHITE + "Hackerare e dare ad altri giocatori e bot un vantaggio sleale per 30 giorni.", null, null);
                                        target.kickPlayer(CC.RED + "Sei stato bandito da Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "あなたは ベクティ から追放されました。30日間\n" + CC.GRAY +
                                                "理由: " + CC.WHITE + "他のプレイヤーやボットをハッキングし、不当に有利にする行為。", null, null);
                                        target.kickPlayer(CC.RED + "あなたは ベクティ から追放されました。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "interdictum a Vexcty pro XXX diebus. \n" + CC.GRAY +
                                                "ratio: " + CC.WHITE + "Caesim et alios lusores et automata et iniquum commodum.", null, null);
                                        target.kickPlayer(CC.RED + "interdictum a Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "ਤੁਹਾਨੂੰ ੩੦ ਦਿਨਾਂ ਲਈ ਵੈਕਸਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।\n" + CC.GRAY +
                                                "ਕਾਰਨ: " + CC.WHITE + "ਹੈਕਿੰਗ ਅਤੇ ਹੋਰ ਖਿਡਾਰੀਆਂ ਅਤੇ ਬੋਟਸ ਨੂੰ ਦੇਣਾ ਅਤੇ ਅਨੁਚਿਤ ਫਾਇਦਾ।", null, null);
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ ਵੈਕਸਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Вы были забанены в Вексти на тридцать дней. \n" + CC.GRAY +
                                                "Причина: " + CC.WHITE + "Взлом и предоставление другим игрокам и ботам несправедливого преимущества.", null, null);
                                        target.kickPlayer(CC.RED + "Вы были забанены в Вексти. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Has sido expulsado de vejatorio durante treinta días. \n" + CC.GRAY +
                                                "Razón: " + CC.WHITE + "Hackear y dar a otros jugadores y bots una ventaja injusta.", null, null);
                                        target.kickPlayer(CC.RED + "Has sido expulsado de vejatorio. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vexcty'den yasaklandınız otuz gün boyunca.\n" + CC.GRAY +
                                                "Sebep: " + CC.WHITE + "Diğer oyunculara ve botlara saldırı ve haksız avantaj sağlama.", null, null);
                                        target.kickPlayer(CC.RED + "Vexcty'den yasaklandınız.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے ٣٠ دنوں کے لئے.\n" + CC.GRAY +
                                                "وجہ: " + CC.WHITE + "ہیکنگ اور دوسرے کھلاڑیوں اور بوٹس کو دینا اور غیر منصفانہ فائدہ.", null, null);
                                        target.kickPlayer(CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے۔");
                                    }
                                    break;
                                case "p":
                                    cal.add(Calendar.MONTH, 3); // 3 months (90 days)
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty for 90 days.\n" + CC.GRAY +
                                                "Reason: " + CC.WHITE + "Inappropriate language that can harm others.", null, null);
                                        target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "\n لقد تم حظرك من فيكستي لمدة ۹۰ يومًا " + CC.GRAY +
                                                "سبب: " + CC.WHITE + "لغة غير لائقة يمكن أن تؤذي الآخرين.", null, null);
                                        target.kickPlayer(CC.RED + "لقد تم حظرك من فيكستي");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vous avez été banni de Vexcty pendant quatre-vingt-dix jours. \n" + CC.GRAY +
                                                "Raison: " + CC.WHITE + "Langage inapproprié qui peut nuire aux autres.", null, null);
                                        target.kickPlayer(CC.RED + "Vous avez été banni de Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Sei stato bandito da Vexcty per novanta giorni. \n" + CC.GRAY +
                                                "Motivo: " + CC.WHITE + "Linguaggio inappropriato che può danneggiare gli altri.", null, null);
                                        target.kickPlayer(CC.RED + "Sei stato bandito da Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "あなたは ベクティ から追放されました。九十日間\n" + CC.GRAY +
                                                "理由: " + CC.WHITE + "他人を傷つける可能性のある不適切な言葉。", null, null);
                                        target.kickPlayer(CC.RED + "あなたは ベクティ から追放されました。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "interdictum a Vexcty pro XC diebus. \n" + CC.GRAY +
                                                "ratio: " + CC.WHITE + "Lingua aliena, quae aliis nocere potest.", null, null);
                                        target.kickPlayer(CC.RED + "interdictum a Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "ਤੁਹਾਨੂੰ ਵੇਕਸੀਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ ਨੱਬੇ ਦਿਨਾਂ ਲਈ।\n" + CC.GRAY +
                                                "ਕਾਰਨ: " + CC.WHITE + "ਅਣਉਚਿਤ ਭਾਸ਼ਾ ਜੋ ਦੂਜਿਆਂ ਨੂੰ ਨੁਕਸਾਨ ਪਹੁੰਚਾ ਸਕਦੀ ਹੈ।", null, null);
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ ਵੇਕਸੀਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Вы были забанены в Вексти на девяносто дней. \n" + CC.GRAY +
                                                "Причина: " + CC.WHITE + "Неуместный язык, который может навредить другим.", null, null);
                                        target.kickPlayer(CC.RED + "Вы были забанены в Вексти. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Has sido expulsado de vejatorio durante 90 días. \n" + CC.GRAY +
                                                "Razón: " + CC.WHITE + "Lenguaje inapropiado que puede dañar a otros.", null, null);
                                        target.kickPlayer(CC.RED + "Has sido expulsado de vejatorio. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vexcty'den yasaklandınız noventa gün boyunca.\n" + CC.GRAY +
                                                "Sebep: " + CC.WHITE + "Başkalarına zarar verebilecek uygunsuz dil.", null, null);
                                        target.kickPlayer(CC.RED + "Vexcty'den yasaklandınız.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + " آپ کو ویکسٹی سے منع کر دیا گیا ہے ۹۰ دنوں کے لئے.\n" + CC.GRAY +
                                                "وجہ: " + CC.WHITE + "نامناسب زبان جو دوسروں کو نقصان پہنچا سکتی ہے۔", null, null);
                                        target.kickPlayer(CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے۔");
                                    }
                                    break;
                                case "tto":
                                    cal.add(Calendar.MONTH, 3); // 3 months (90 days)
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty for 90 days. \n" + CC.GRAY +
                                                "Reason: " + CC.WHITE + "Threats to other people on the network.", null, null);
                                        target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "\n لقد تم حظرك من فيكستي لمدة ۹۰ يومًا  " + CC.GRAY +
                                                "سبب: " + CC.WHITE + "تهديدات لأشخاص آخرين على الشبكة.", null, null);
                                        target.kickPlayer(CC.RED + "لقد تم حظرك من فيكستي");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vous avez été banni de Vexcty pendant quatre-vingt-dix jours. \n" + CC.GRAY +
                                                "Raison: " + CC.WHITE + "Menaces envers d'autres personnes sur le réseau.", null, null);
                                        target.kickPlayer(CC.RED + "Vous avez été banni de Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Sei stato bandito da Vexcty per novanta giorni. \n" + CC.GRAY +
                                                "Motivo: " + CC.WHITE + "Minacce ad altre persone sulla rete.", null, null);
                                        target.kickPlayer(CC.RED + "Sei stato bandito da Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "あなたは ベクティ から追放されました。九十日間\n" + CC.GRAY +
                                                "理由: " + CC.WHITE + "ネットワーク上の他の人々への脅威。", null, null);
                                        target.kickPlayer(CC.RED + "あなたは Vexcty から追放されました。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "interdictum a Vexcty pro XC diebus. \n" + CC.GRAY +
                                                "ratio: " + CC.WHITE + "Minas aliis hominibus in ornatum.", null, null);
                                        target.kickPlayer(CC.RED + "interdictum a Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "ਤੁਹਾਨੂੰ ਵੇਕਸੀਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ ਨੱਬੇ ਦਿਨਾਂ ਲਈ।\n" + CC.GRAY +
                                                "ਕਾਰਨ: " + CC.WHITE + "ਨੈੱਟਵਰਕ 'ਤੇ ਹੋਰ ਲੋਕਾਂ ਨੂੰ ਧਮਕੀਆਂ।", null, null);
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ Vexcty ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Вы были забанены в Вексти на девяносто дней. \n" + CC.GRAY +
                                                "Причина: " + CC.WHITE + "Угрозы другим людям в сети.", null, null);
                                        target.kickPlayer(CC.RED + "Вы были забанены в Вексти. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Has sido expulsado de vejatorio durante 90 días. \n" + CC.GRAY +
                                                "Razón: " + CC.WHITE + "Amenazas a otras personas en la red.", null, null);
                                        target.kickPlayer(CC.RED + "Has sido expulsado de Vexcty. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vexcty'den yasaklandınız noventa gün boyunca. \n" + CC.GRAY +
                                                "Sebep: " + CC.WHITE + "Ağdaki diğer kişilere yönelik tehditler.", null, null);
                                        target.kickPlayer(CC.RED + "Vexcty'den yasaklandınız.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے ۹۰ دنوں کے لئے.\n" + CC.GRAY +
                                                "وجہ: " + CC.WHITE + "نیٹ ورک پر دوسرے لوگوں کو دھمکیاں۔", null, null);
                                        target.kickPlayer(CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے۔");
                                    }
                                    break;
                                case "en":
                                    cal.add(Calendar.MONTH, 1); // <<
                                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty.\n" + CC.GRAY +
                                                "Reason: " + CC.WHITE + "Exploiting/Glitching the server.", null, null);
                                        target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "\n لقد تم حظرك من فيكستي " + CC.GRAY +
                                                "سبب: " + CC.WHITE + "استغلال / خلل الخادم.", null, null);
                                        target.kickPlayer(CC.RED + "لقد تم حظرك من فيكستي");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vous avez été banni de Vexcty \n" + CC.GRAY +
                                                "Raison: " + CC.WHITE + "Exploiter/Glitcher le serveur.", null, null);
                                        target.kickPlayer(CC.RED + "Vous avez été banni de Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Sei stato bandito da Vexcty. \n" + CC.GRAY +
                                                "Motivo: " + CC.WHITE + "Sfruttamento/glitch del server.", null, null);
                                        target.kickPlayer(CC.RED + "Sei stato bandito da Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "あなたは ベクティ から追放されました。\n" + CC.GRAY +
                                                "理由: " + CC.WHITE + "サーバーの悪用/グリッチ。", null, null);
                                        target.kickPlayer(CC.RED + "あなたは Vexcty から追放されました。");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "interdictum a Vexcty. \n" + CC.GRAY +
                                                "ratio: " + CC.WHITE + "Abutitur / Glitching calculonis servi.", null, null);
                                        target.kickPlayer(CC.RED + "interdictum a Vexcty.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "ਤੁਹਾਨੂੰ ਵੇਕਸੀਟੀ ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।\n" + CC.GRAY +
                                                "ਕਾਰਨ: " + CC.WHITE + "ਸਰਵਰ ਦਾ ਸ਼ੋਸ਼ਣ/ਗਲਚ ਕਰਨਾ।", null, null);
                                        target.kickPlayer(CC.RED + "ਤੁਹਾਨੂੰ Vexcty ਤੋਂ ਪਾਬੰਦੀ ਲਗਾਈ ਗਈ ਹੈ।");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Вы были забанены в Вексти. \n" + CC.GRAY +
                                                "Причина: " + CC.WHITE + "Эксплуатация/сбой сервера.", null, null);
                                        target.kickPlayer(CC.RED + "Вы были забанены в Вексти. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Has sido expulsado de vejatorio. \n" + CC.GRAY +
                                                "Razón: " + CC.WHITE + "Explotación/Glitching del servidor.", null, null);
                                        target.kickPlayer(CC.RED + "Has sido expulsado de Vexcty. ");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "Vexcty'den yasaklandınız.\n" + CC.GRAY +
                                                "Sebep: " + CC.WHITE + "Sunucuyu Sömürmek/Hata Yapmak.", null, null);
                                        target.kickPlayer(CC.RED + "Vexcty'den yasaklandınız.");
                                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے.\n" + CC.GRAY +
                                                "وجہ: " + CC.WHITE + "سرور کا استحصال/غلطی کرنا۔", null, null);
                                        target.kickPlayer(CC.RED + "آپ کو ویکسٹی سے منع کر دیا گیا ہے۔");
                                    }

                                    break;
                                default:
                                    player.sendMessage(CC.RED + "Invalid Usage! Please use /ban <player> \n" +
                                        "- hac (Hacks and cheats) \n" +
                                        "- p (Profanity eg: YoU sHoUlD cOoMiT sUcIdE) \n" +
                                        "- tto (Threats to others eg: ddosing, doxing, harassing, blackmailing etc) \n" +
                                        "- en (Exploiting and glitching the server) \n" +
                                        "- in (Inappropriate Name)");
                            }
                        } else {
                            player.sendMessage(CC.RED + args[1] + " is not a valid argument, arguments are -k, -b or -tb");
                        }
                    }

                } else {
                    player.sendMessage(CC.RED + "Invalid Usage! Please use /punish <player> -b|-k <REASON>");
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
