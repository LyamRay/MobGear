package me.lyamray.mobGear.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ChatUtil {

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String stripColor(String input) {
        if (input == null) {
            return null;
        }
        return ChatColor.stripColor(input);
    }
}