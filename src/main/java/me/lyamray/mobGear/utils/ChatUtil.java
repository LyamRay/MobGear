package me.lyamray.mobGear.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ChatUtil {

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
