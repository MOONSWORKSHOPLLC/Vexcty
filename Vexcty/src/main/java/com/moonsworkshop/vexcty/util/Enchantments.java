package com.moonsworkshop.vexcty.util;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * From EssentialsX for ease of use
 */
public class Enchantments {
    private static final Map<String, Enchantment> enchantments = new HashMap<>();
    private static final Map<String, Enchantment> aliasEnchantments = new HashMap<>();

    static {
        enchantments.put("alldamage", Enchantment.DAMAGE_ALL);
        aliasEnchantments.put("alldmg", Enchantment.DAMAGE_ALL);
        enchantments.put("sharpness", Enchantment.DAMAGE_ALL);
        aliasEnchantments.put("sharp", Enchantment.DAMAGE_ALL);
        aliasEnchantments.put("dal", Enchantment.DAMAGE_ALL);

        enchantments.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
        enchantments.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
        aliasEnchantments.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
        aliasEnchantments.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
        aliasEnchantments.put("dar", Enchantment.DAMAGE_ARTHROPODS);

        enchantments.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
        enchantments.put("smite", Enchantment.DAMAGE_UNDEAD);
        aliasEnchantments.put("du", Enchantment.DAMAGE_UNDEAD);

        enchantments.put("digspeed", Enchantment.DIG_SPEED);
        enchantments.put("efficiency", Enchantment.DIG_SPEED);
        aliasEnchantments.put("minespeed", Enchantment.DIG_SPEED);
        aliasEnchantments.put("cutspeed", Enchantment.DIG_SPEED);
        aliasEnchantments.put("ds", Enchantment.DIG_SPEED);
        aliasEnchantments.put("eff", Enchantment.DIG_SPEED);

        enchantments.put("durability", Enchantment.DURABILITY);
        aliasEnchantments.put("dura", Enchantment.DURABILITY);
        enchantments.put("unbreaking", Enchantment.DURABILITY);
        aliasEnchantments.put("d", Enchantment.DURABILITY);

        enchantments.put("thorns", Enchantment.THORNS);
        enchantments.put("highcrit", Enchantment.THORNS);
        aliasEnchantments.put("thorn", Enchantment.THORNS);
        aliasEnchantments.put("highercrit", Enchantment.THORNS);
        aliasEnchantments.put("t", Enchantment.THORNS);

        enchantments.put("fireaspect", Enchantment.FIRE_ASPECT);
        enchantments.put("fire", Enchantment.FIRE_ASPECT);
        aliasEnchantments.put("meleefire", Enchantment.FIRE_ASPECT);
        aliasEnchantments.put("meleeflame", Enchantment.FIRE_ASPECT);
        aliasEnchantments.put("fa", Enchantment.FIRE_ASPECT);

        enchantments.put("knockback", Enchantment.KNOCKBACK);
        aliasEnchantments.put("kback", Enchantment.KNOCKBACK);
        aliasEnchantments.put("kb", Enchantment.KNOCKBACK);
        aliasEnchantments.put("k", Enchantment.KNOCKBACK);

        aliasEnchantments.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
        enchantments.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
        aliasEnchantments.put("fort", Enchantment.LOOT_BONUS_BLOCKS);
        aliasEnchantments.put("lbb", Enchantment.LOOT_BONUS_BLOCKS);

        aliasEnchantments.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
        enchantments.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
        enchantments.put("looting", Enchantment.LOOT_BONUS_MOBS);
        aliasEnchantments.put("lbm", Enchantment.LOOT_BONUS_MOBS);

        aliasEnchantments.put("oxygen", Enchantment.OXYGEN);
        enchantments.put("respiration", Enchantment.OXYGEN);
        aliasEnchantments.put("breathing", Enchantment.OXYGEN);
        enchantments.put("breath", Enchantment.OXYGEN);
        aliasEnchantments.put("o", Enchantment.OXYGEN);

        enchantments.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
        aliasEnchantments.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchantments.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
        aliasEnchantments.put("p", Enchantment.PROTECTION_ENVIRONMENTAL);

        aliasEnchantments.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
        aliasEnchantments.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
        aliasEnchantments.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
        aliasEnchantments.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
        aliasEnchantments.put("bprotection", Enchantment.PROTECTION_EXPLOSIONS);
        aliasEnchantments.put("bprotect", Enchantment.PROTECTION_EXPLOSIONS);
        enchantments.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
        aliasEnchantments.put("pe", Enchantment.PROTECTION_EXPLOSIONS);

        aliasEnchantments.put("fallprotection", Enchantment.PROTECTION_FALL);
        enchantments.put("fallprot", Enchantment.PROTECTION_FALL);
        enchantments.put("featherfall", Enchantment.PROTECTION_FALL);
        aliasEnchantments.put("featherfalling", Enchantment.PROTECTION_FALL);
        aliasEnchantments.put("pfa", Enchantment.PROTECTION_FALL);

        aliasEnchantments.put("fireprotection", Enchantment.PROTECTION_FIRE);
        aliasEnchantments.put("flameprotection", Enchantment.PROTECTION_FIRE);
        enchantments.put("fireprotect", Enchantment.PROTECTION_FIRE);
        aliasEnchantments.put("flameprotect", Enchantment.PROTECTION_FIRE);
        enchantments.put("fireprot", Enchantment.PROTECTION_FIRE);
        aliasEnchantments.put("flameprot", Enchantment.PROTECTION_FIRE);
        aliasEnchantments.put("pf", Enchantment.PROTECTION_FIRE);

        enchantments.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
        enchantments.put("projprot", Enchantment.PROTECTION_PROJECTILE);
        aliasEnchantments.put("pp", Enchantment.PROTECTION_PROJECTILE);

        enchantments.put("silktouch", Enchantment.SILK_TOUCH);
        aliasEnchantments.put("softtouch", Enchantment.SILK_TOUCH);
        aliasEnchantments.put("st", Enchantment.SILK_TOUCH);

        enchantments.put("waterworker", Enchantment.WATER_WORKER);
        enchantments.put("aquaaffinity", Enchantment.WATER_WORKER);
        aliasEnchantments.put("watermine", Enchantment.WATER_WORKER);
        aliasEnchantments.put("ww", Enchantment.WATER_WORKER);

        aliasEnchantments.put("firearrow", Enchantment.ARROW_FIRE);
        enchantments.put("flame", Enchantment.ARROW_FIRE);
        enchantments.put("flamearrow", Enchantment.ARROW_FIRE);
        aliasEnchantments.put("af", Enchantment.ARROW_FIRE);

        enchantments.put("arrowdamage", Enchantment.ARROW_DAMAGE);
        enchantments.put("power", Enchantment.ARROW_DAMAGE);
        aliasEnchantments.put("arrowpower", Enchantment.ARROW_DAMAGE);
        aliasEnchantments.put("ad", Enchantment.ARROW_DAMAGE);

        enchantments.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
        aliasEnchantments.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
        enchantments.put("punch", Enchantment.ARROW_KNOCKBACK);
        aliasEnchantments.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);
        aliasEnchantments.put("ak", Enchantment.ARROW_KNOCKBACK);

        aliasEnchantments.put("infinitearrows", Enchantment.ARROW_INFINITE);
        enchantments.put("infarrows", Enchantment.ARROW_INFINITE);
        enchantments.put("infinity", Enchantment.ARROW_INFINITE);
        aliasEnchantments.put("infinite", Enchantment.ARROW_INFINITE);
        aliasEnchantments.put("unlimited", Enchantment.ARROW_INFINITE);
        aliasEnchantments.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
        aliasEnchantments.put("ai", Enchantment.ARROW_INFINITE);

        enchantments.put("luck", Enchantment.LUCK);
        aliasEnchantments.put("luckofsea", Enchantment.LUCK);
        aliasEnchantments.put("luckofseas", Enchantment.LUCK);
        aliasEnchantments.put("rodluck", Enchantment.LUCK);

        enchantments.put("lure", Enchantment.LURE);
        aliasEnchantments.put("rodlure", Enchantment.LURE);

        // 1.8
        try {
            Enchantment depthStrider = Enchantment.getByName("DEPTH_STRIDER");
            if (depthStrider != null) {
                enchantments.put("depthstrider", depthStrider);
                aliasEnchantments.put("depth", depthStrider);
                aliasEnchantments.put("strider", depthStrider);
            }
        } catch (IllegalArgumentException ignored) {}

        // 1.9
        try {
            Enchantment frostWalker = Enchantment.getByName("FROST_WALKER");
            if (frostWalker != null) {
                enchantments.put("frostwalker", frostWalker);
                aliasEnchantments.put("frost", frostWalker);
                aliasEnchantments.put("walker", frostWalker);
            }

            Enchantment mending = Enchantment.getByName("MENDING");
            if (mending != null) {
                enchantments.put("mending", mending);
            }
        } catch (IllegalArgumentException ignored) {}

        // 1.11
        try {
            Enchantment bindingCurse = Enchantment.getByName("BINDING_CURSE");
            if (bindingCurse != null) {
                enchantments.put("bindingcurse", bindingCurse);
                aliasEnchantments.put("bindcurse", bindingCurse);
                aliasEnchantments.put("binding", bindingCurse);
                aliasEnchantments.put("bind", bindingCurse);
            }
            Enchantment vanishingCurse = Enchantment.getByName("VANISHING_CURSE");
            if (vanishingCurse != null) {
                enchantments.put("vanishingcurse", vanishingCurse);
                aliasEnchantments.put("vanishcurse", vanishingCurse);
                aliasEnchantments.put("vanishing", vanishingCurse);
                aliasEnchantments.put("vanish", vanishingCurse);
            }
            Enchantment sweeping = Enchantment.getByName("SWEEPING_EDGE");
            if (sweeping != null) {
                enchantments.put("sweepingedge", sweeping);
                aliasEnchantments.put("sweepedge", sweeping);
                aliasEnchantments.put("sweeping", sweeping);
            }
        } catch (IllegalArgumentException ignored) {}


        try { // 1.13
            Enchantment loyalty = Enchantment.getByName("LOYALTY");
            if (loyalty != null) {
                enchantments.put("loyalty", loyalty);
                aliasEnchantments.put("loyal", loyalty);
                aliasEnchantments.put("return", loyalty);
            }
            Enchantment impaling = Enchantment.getByName("IMPALING");
            if (impaling != null) {
                enchantments.put("impaling", impaling);
                aliasEnchantments.put("impale", impaling);
                aliasEnchantments.put("oceandamage", impaling);
                aliasEnchantments.put("oceandmg", impaling);
            }
            Enchantment riptide = Enchantment.getByName("RIPTIDE");
            if (riptide != null) {
                enchantments.put("riptide", riptide);
                aliasEnchantments.put("rip", riptide);
                aliasEnchantments.put("tide", riptide);
                aliasEnchantments.put("launch", riptide);
            }
            Enchantment channelling = Enchantment.getByName("CHANNELING");
            if (channelling != null) {
                enchantments.put("channelling", channelling);
                aliasEnchantments.put("chanelling", channelling);
                aliasEnchantments.put("channeling", channelling);
                aliasEnchantments.put("chaneling", channelling);
                aliasEnchantments.put("channel", channelling);
            }
        } catch (IllegalArgumentException ignored) {}


        try { // 1.14
            Enchantment multishot = Enchantment.getByName("MULTISHOT");
            if (multishot != null) {
                enchantments.put("multishot", multishot);
                aliasEnchantments.put("tripleshot", multishot);
            }
            Enchantment quickCharge = Enchantment.getByName("QUICK_CHARGE");
            if (quickCharge != null) {
                enchantments.put("quickcharge", quickCharge);
                aliasEnchantments.put("quickdraw", quickCharge);
                aliasEnchantments.put("fastcharge", quickCharge);
                aliasEnchantments.put("fastdraw", quickCharge);
            }
            Enchantment piercing = Enchantment.getByName("PIERCING");
            if (piercing != null) {
                enchantments.put("piercing", piercing);
            }
        } catch (IllegalArgumentException ignored) {}
    }

    public static Enchantment getByName(String name) {
        Enchantment enchantment = Enchantment.getByName(name.toUpperCase());

        if (enchantment == null) {
            enchantment = enchantments.get(name.toLowerCase(Locale.ENGLISH));
        }

        if (enchantment == null) {
            enchantment = aliasEnchantments.get(name.toLowerCase(Locale.ENGLISH));
        }

        return enchantment;
    }

    public static Set<Map.Entry<String, Enchantment>> entrySet() {
        return enchantments.entrySet();
    }

    public static Set<String> keySet() {
        return enchantments.keySet();
    }
}
