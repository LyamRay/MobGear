package me.lyamray.mobGear.inventories.mobgearforge;

import me.lyamray.mobGear.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ForgeInventory {


    public void forgeInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9*6, ChatUtil.color(
                "&7(&b&l⚒&7) &bForge &7here your &bskulls&7!"));
                player.openInventory(inventory);
        }
    }
