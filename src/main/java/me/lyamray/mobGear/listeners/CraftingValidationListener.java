package me.lyamray.mobGear.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftingValidationListener implements Listener {

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        for (ItemStack item : inventory.getMatrix()) {
            if (isInvalidEnhancedHead(item)) {
                inventory.setResult(null);
                return;
            }
        }
    }

    private boolean isInvalidEnhancedHead(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return false;
        if (item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasDisplayName()) {
            String displayName = item.getItemMeta().getDisplayName();
            return displayName.contains("Enhanced") && displayName.contains("Head") && item.getAmount() < 32;
        }
        return false;
    }
}
