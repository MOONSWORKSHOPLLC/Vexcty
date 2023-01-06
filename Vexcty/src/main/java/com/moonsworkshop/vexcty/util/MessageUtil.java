package com.moonsworkshop.vexcty.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtil {

    public static String MUST_PLAYER = CC.RED + "You must be a player to execute this command.";
    public static String NO_PERMISSION = CC.RED + "You don't have the permission to execute this command.";
    public static String NULL_DATA = CC.RED + "You player data hasn't loaded correctly, please disconnect and reconnect, contact an administrator if the problem percist.";
    public static String TARGET_NULL = CC.RED + "%s is currently offline.";

}
