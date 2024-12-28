package me.lyamray.mobGear.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

public final class SkullUtil {
    @Generated
    private SkullUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ItemStack getSkull(String url) {
        URL urlObject;
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        UUID uuid = UUID.nameUUIDFromBytes(url.getBytes());
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(uuid);
        PlayerTextures playerTextures = playerProfile.getTextures();
        try {
            urlObject = URI.create(url).toURL();
        } catch (MalformedURLException exception) {
            throw new RuntimeException("This URL is incorrect!", exception);
        }
        playerTextures.setSkin(urlObject);
        playerProfile.setTextures(playerTextures);
        skullMeta.setOwnerProfile(playerProfile);
        skull.setItemMeta((ItemMeta)skullMeta);
        return skull;
    }
}
