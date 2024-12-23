package me.lyamray.mobGear.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    // Clears all existing lore
    public ItemBuilder clearLore() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setLore(new ArrayList<>()); // Clears the lore
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    // Set a specific lore line at a given index
    public ItemBuilder setLoreLine(int index, String loreLine) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<String> currentLore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

            // Ensure the lore list has enough space
            while (currentLore.size() <= index) {
                currentLore.add(""); // Add empty strings until the desired index is reached
            }

            currentLore.set(index, loreLine);  // Set the lore at the specific index
            meta.setLore(currentLore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    // Add a new lore line at the end of the lore list
    public ItemBuilder addLoreLine(String loreLine) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<String> currentLore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            currentLore.add(loreLine);
            meta.setLore(currentLore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemStack toItemStack() {
        return itemStack.clone();  // Return a clone to prevent external modifications
    }
}
