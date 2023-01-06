package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.rank.PlayerRank;
import com.moonsworkshop.vexcty.util.ActionBar;
import com.moonsworkshop.vexcty.util.CC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
;
import java.util.*;
import java.util.List;

public class StreamingModeCommand implements CommandExecutor, Listener {

    public static ArrayList<Player> nicked = new ArrayList<Player>();
    public static HashMap<Player, String> nickedRanks = new HashMap<Player, String>();

    private List<UUID> streamers = new ArrayList<>();

    private Vexcty vexcty;

    public StreamingModeCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.YOUTUBE ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.OWNER ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.ADMIN ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MOD ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.DEVELOPER ||
                    vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.BUILDER) { // check for ranks instead of perms

                if (!streamers.contains(player.getUniqueId())) { // if the player is not in the Map then add them
                    streamers.add(player.getUniqueId()); // add them
                } else { // if they are already in the Map remove them
                    streamers.remove(player.getUniqueId()); // remove them
                }

                for (Player target : Bukkit.getOnlinePlayers()) { // get all the players on the server
                    if (vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.OWNER ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.ADMIN ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MOD ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.DEVELOPER ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.BUILDER) { // When a staff member vanishes, Owner, Admin, Mod, Dev and Builders will all be able to see you
                        player.showPlayer(target);
                    }
                }

                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (!(vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.OWNER ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.ADMIN ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MOD ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.DEVELOPER ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.BUILDER ||
                            vexcty.getRankManager().getRank(player.getUniqueId()) == PlayerRank.YOUTUBE)) { // if they dont have Owner, Admin, Mod, Dev, Builder or Youtube, hide the vanished player
                        player.hidePlayer(target);
                    }
                }

                if (args.length == 1) {
                    if (args[0].length() > 16) { // if the username is more than 16 charaters
                        if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                            player.sendMessage(CC.RED + "Username cannot be more than 16 characters.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                            player.sendMessage(CC.RED + "لا يمكن أن يكون اسم المستخدم أكثر من 16 حرفًا.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                            player.sendMessage(CC.RED + "Le nom d'utilisateur ne peut pas comporter plus de 16 caractères.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                            player.sendMessage(CC.RED + "Il nome utente non può contenere più di 16 caratteri.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                            player.sendMessage(CC.RED + "ユーザー名は 16 文字を超えることはできません。");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                            player.sendMessage(CC.RED + "Username non plus quam XVI ingenia.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                            player.sendMessage(CC.RED + "ਵਰਤੋਂਕਾਰ ਨਾਂ 16 ਅੱਖਰਾਂ ਤੋਂ ਵੱਧ ਨਹੀਂ ਹੋ ਸਕਦਾ।");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                            player.sendMessage(CC.RED + "Имя пользователя не может быть длиннее 16 символов.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                            player.sendMessage(CC.RED + "El nombre de usuario no puede tener más de 16 caracteres.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                            player.sendMessage(CC.RED + "Kullanıcı adı 16 karakterden fazla olamaz.");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                            player.sendMessage(CC.RED + "صارف نام 16 حروف سے زیادہ نہیں ہو سکتا۔");
                        }
                        return true;
                    }

                    String prf; // set different ranks for example if a player does /streamindmode <nick> God,
                    // make their nicked rank to god and when they leave, remove them.
                    if (args[1].equalsIgnoreCase("GOD")) {
                        prf = "§9[GOD] ";
                    } else if (args[1].equalsIgnoreCase("LEGEND")) {
                        prf = "§b[LEGEND] ";
                    } else if (args[1].equalsIgnoreCase("DONOR")) {
                        prf = "§9[DONOR] ";
                    } else if (args[1].equalsIgnoreCase("MEMBER")) {
                        prf = "§e[MEMBER] ";
                    } else { // if the player types a invalid Rank
                        if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                            player.sendMessage(ChatColor.RED + "Invalid rank! Valid Ranks are \n" + "- GOD \n" + "- LEGEND \n" + "- DONOR \n" + "- MEMBER");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                            player.sendMessage(CC.RED + "غلط درجہ! درست درجات ہیں \n - خدا \n - لیجنڈ \n - ڈونر \n - ممبر");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                            player.sendMessage(CC.RED + "Classement invalide! Les rangs valides sont \n - DIEU \n - LÉGENDE \n - DONATEUR \n - MEMBRE");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                            player.sendMessage(CC.RED + "Grado non valido! I gradi validi sono \n - DIO \n - LEGGENDA \n - DONATORE \n - MEMBRO");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                            player.sendMessage(CC.RED + "ランクが無効です。有効なランクは \n - GOD \n - LEGEND \n - ドナー \n - メンバーです");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                            player.sendMessage(CC.RED + "Irritum gradum! Valid Ranks are \n - GOD \n - TRADITIO \n - DONATOR \n - MEMBER");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                            player.sendMessage(CC.RED + "ਅਵੈਧ ਦਰਜਾ! ਵੈਧ ਦਰਜੇ ਹਨ \n - ਰੱਬ \n - ਦੰਤਕਥਾ \n - ਦਾਨੀ \n - ਮੈਂਬਰ");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                            player.sendMessage(CC.RED + "Неверный ранг! Действительные звания: \n - БОГ \n - ЛЕГЕНДА \n - ДОНОР \n - ЧЛЕН");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                            player.sendMessage(CC.RED + "¡Rango no válido! Los rangos válidos son \n - DIOS \n - LEYENDA \n - DONANTE \n - MIEMBRO");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                            player.sendMessage(CC.RED + "Geçersiz rütbe! Geçerli Dereceler \n - GOD \n - LEGEND \n - DONOR \n - ÜYE'dir");
                        } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                            player.sendMessage(CC.RED + "غلط درجہ! درست درجات ہیں \n - خدا \n - لیجنڈ \n - ڈونر \n - ممبر");
                        }
                        return true;
                    }

                    player.setDisplayName(args[0]); // set their Name to their nicked name
                    player.setPlayerListName(prf + args[0]); // add the rank and the nickname to the player

                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) { // hotbar stuff
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "YOU ARE CURRENTLY VANISHED AND NICKED");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "لقد تم تغليظك وتضرر في الوقت الحالي");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "VOUS ÊTES ACTUELLEMENT VASINIÉ ET NICKÉ");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "SEI ATTUALMENTE VASINISHED E NICKED");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "あなたは現在、ヴァシニッシュとニックが付けられています");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "TE IAM VASINISHED et nicked");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "ਤੁਸੀਂ ਵਰਤਮਾਨ ਵਿੱਚ ਵੈਸਿਨਿਸ਼ਡ ਅਤੇ ਨਿਕੰਮੇ ਹੋਏ ਹੋ");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "ВЫ В НАСТОЯЩЕЕ ВРЕМЯ ВАСИНИРОВАЛИСЬ И ПОНЯЛИСЬ");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "USTED ESTÁ ACTUALMENTE VASINADO Y MALLADO");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "ŞU ANDA VAZİNE VE NİKELİSİNİZ");
                        actionBar.sendToPlayer(player);
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                        ActionBar actionBar = new ActionBar(ChatColor.RED + "آپ فی الحال vasinised اور niccked ہیں");
                        actionBar.sendToPlayer(player);
                    }

                    nickedRanks.put(player, prf); // add the player to the List


                    nicked.add(player); // add the player to the HashMap


                } else { // if they get the args wrong
                    if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
                        player.sendMessage(CC.RED + "Invalid Usage! Please use /streamingmode> <nickname> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
                        player.sendMessage(CC.RED + "استخدام غير صالح! الرجاء استخدام / streamingmode> <nickname> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
                        player.sendMessage(CC.RED + "Utilisation invalide! Veuillez utiliser /streamingmode> <nickname> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
                        player.sendMessage(CC.RED + "Utilizzo non valido! Si prega di utilizzare /streamingmode> <nickname> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
                        player.sendMessage(CC.RED + "使用方法が無効です! /streamingmode> <nickname> <prf> を使用してください。");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
                        player.sendMessage(CC.RED + "Invalida Ritus! Quaeso utere /streamingmode> <cognomen> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
                        player.sendMessage(CC.RED + "ਅਵੈਧ ਵਰਤੋਂ! ਕਿਰਪਾ ਕਰਕੇ /streamingmode> <nickname> <prf> ਦੀ ਵਰਤੋਂ ਕਰੋ।");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
                        player.sendMessage(CC.RED + "Недопустимое использование! Пожалуйста, используйте /streamingmode> <псевдоним> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
                        player.sendMessage(CC.RED + "¡Uso no válido! Utilice /streamingmode> <apodo> <prf>.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
                        player.sendMessage(CC.RED + "Geçersiz Kullanım! Lütfen /streamingmode> <nickname> <prf> kullanın.");
                    } else if (vexcty.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
                        player.sendMessage(CC.RED + "غلط استعمال! براہ کرم /streamingmode> <nickname> <prf> استعمال کریں۔");
                    }
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

        return true;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) { // remove their rank when they leave
        nicked.remove(e.getPlayer());
        nickedRanks.remove(e.getPlayer());
    }

}
