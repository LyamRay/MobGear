package me.lyamray.mobGear.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

@UtilityClass
public class SkullUtil {
    // Thanks JensieFlensie for this code! X
    public ItemStack getSkull(String url) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        UUID uuid = UUID.nameUUIDFromBytes(url.getBytes());

        PlayerProfile playerProfile = Bukkit.createPlayerProfile(uuid);
        PlayerTextures playerTextures = playerProfile.getTextures();

        URL urlObject;
        try {
            urlObject = URI.create(url).toURL();
        } catch (MalformedURLException exception) {
            throw new RuntimeException("This URL is incorrect!", exception);
        }

        playerTextures.setSkin(urlObject);
        playerProfile.setTextures(playerTextures);

        skullMeta.setOwnerProfile(playerProfile);
        skull.setItemMeta(skullMeta);

        return skull;
    }
}
