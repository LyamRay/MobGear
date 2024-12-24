package me.lyamray.mobGear.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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

    public ItemBuilder clearLore() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setLore(new ArrayList<>());
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setLoreLine(int index, String loreLine) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<String> currentLore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

            while (currentLore.size() <= index) {
                currentLore.add("");
            }

            currentLore.set(index, loreLine);
            meta.setLore(currentLore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder addLoreLine(String loreLine) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<String> currentLore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            assert currentLore != null;
            currentLore.add(loreLine);
            meta.setLore(currentLore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }
    public ItemBuilder addLoreLines(String loreBlock) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<String> currentLore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            assert currentLore != null;
            Collections.addAll(currentLore, loreBlock.split("\n"));
            meta.setLore(currentLore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }
    public ItemBuilder setLeatherArmorColor(String hexColor) {
        try {
            Color color = Color.fromRGB(
                    Integer.parseInt(hexColor.substring(1, 3), 16),
                    Integer.parseInt(hexColor.substring(3, 5), 16),
                    Integer.parseInt(hexColor.substring(5, 7), 16)
            );

            LeatherArmorMeta im = (LeatherArmorMeta) itemStack.getItemMeta();
            if (im != null) {
                im.setColor(color);
                itemStack.setItemMeta(im);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | ClassCastException e) {
            e.printStackTrace();
        }
        return this;
    }
    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }
    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = itemStack.getItemMeta();
        im.addEnchant(ench, level, true);
        itemStack.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addUnsafeEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setGlowing(boolean toggle) {
        if (toggle) {
            addUnsafeEnchantment(Enchantment.LUCK_OF_THE_SEA, 1);
            setItemFlag(ItemFlag.HIDE_ENCHANTS);
            return this;
        }
        ItemMeta im = itemStack.getItemMeta();
        itemStack.removeEnchantment(Enchantment.LUCK_OF_THE_SEA);
        im.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(im);
        return this;
    }
    public ItemBuilder setItemFlag(ItemFlag itemFlag) {
        ItemMeta im = itemStack.getItemMeta();
        im.addItemFlags(itemFlag);
        itemStack.setItemMeta(im);
        return this;
    }
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        itemStack.addUnsafeEnchantment(ench, level);
        return this;
    }
    public ItemBuilder setInfinityDurability() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setUnbreakable(true);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemStack toItemStack() {
        return itemStack.clone();
    }
}
