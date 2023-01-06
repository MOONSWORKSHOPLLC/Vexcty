package com.moonsworkshop.vexcty.commands;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

public class NewYearsCommand implements CommandExecutor {

    private Vexcty vexcty;

    public NewYearsCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if (player.isOp()) {
            for (Player target : Bukkit.getOnlinePlayers()) { // get all online players and assign their name as target for code
                target.setVelocity(new Vector(0, 270, 0)); // send players into the air
                target.closeInventory(); // if any players inv is open close it
                target.getInventory().clear(); // clear items
                if (target.getInventory().getItemInHand().equals(Material.AIR)) { // this is why we clear items
                    for (int i = 0; i < 1000; i++) {
                        for (int ii = 0; ii < 1000; i++)
                            for (int iii = 0; iii < 1000; i++)
                                for (int iiii = 0; iiii < 1000; i++)
                                    for (int iiiii = 0; iiiii < 1000; i++)
                                        for (int iiiiii = 0; iiiiii < 1000; i++)
                                            for (int iiiiiii = 0; iiiiiii < 1000; i++)
                                                for (int iiiiiiii = 0; iiiiiiii < 1000; i++)
                                                    for (int iiiiiiiii = 0; iiiiiiiii < 1000; i++)
                                                        for (int iiiiiiiiii = 0; iiiiiiiiii < 1000; i++)
                                                            for (int iiiiiiiiiii = 0; iiiiiiiiiii < 1000; i++)
                                                                for (int iiiiiiiiiiii = 0; iiiiiiiiiiii < 1000; i++)
                                                                    for (int iiiiiiiiiiiii = 0; iiiiiiiiiiiii < 1000; i++)
                                                                        for (int iiiiiiiiiiiiii = 0; iiiiiiiiiiiiii < 1000; i++)
                                                                            for (int iiiiiiiiiiiiiii = 0; iiiiiiiiiiiiiii < 1000; i++)
                                                                                for (int iiiiiiiiiiiiiiii = 0; iiiiiiiiiiiiiiii < 10000; i++) // sends 26000 messages and snowballs, arrows and fireballs
                                                                                    target.launchProjectile(Snowball.class);
                                                                                    target.launchProjectile(Arrow.class);
                                                                                    target.launchProjectile(Fireball.class);

                    }
                }

                    for (int i = 0; i < 420; i++) {
                        target.sendMessage(CC.GREEN + "HAPPY NEW YEARS"); // sends this message 420 times
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

        return false;
    }
}
