package com.moonsworkshop.vexcty.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Config
{
    public static boolean GENERATE_RANDOM, ALPHANUMERIC, REQUIRED_CHAT, REQUIRED_CMD;
    public static int CAPTCHA_LENGTH;
    public static List<?> WORD_LIST;
    public static String FIRSTJOIN_MESSAGE, CAPTCHA_COMPLETED, CAPTCHA_FAILED;

    public static void loadConfig(Plugin plugin)
    {
        FileConfiguration config = plugin.getConfig();

        config.options().copyDefaults(true);
        plugin.saveConfig();

        GENERATE_RANDOM = config.getBoolean("captcha.randomGeneration");
        ALPHANUMERIC = config.getBoolean("captcha.alphanumric");
        CAPTCHA_LENGTH = config.getInt("captcha.length");
        WORD_LIST = config.getList("captcha.wordList");

        REQUIRED_CHAT = config.getBoolean("captha-required.chat");
        REQUIRED_CMD = config.getBoolean("captha-required.command");

        FIRSTJOIN_MESSAGE = config.getString("message.firstJoin");
        CAPTCHA_COMPLETED = config.getString("message.captchaCompleted");
        CAPTCHA_FAILED = config.getString("message.captchaFailed");

        plugin.saveConfig();
    }
}