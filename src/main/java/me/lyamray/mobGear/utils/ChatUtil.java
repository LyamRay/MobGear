package me.lyamray.mobGear.utils;

import lombok.Generated;
import org.bukkit.ChatColor;

public final class ChatUtil {
    @Generated
    private ChatUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
