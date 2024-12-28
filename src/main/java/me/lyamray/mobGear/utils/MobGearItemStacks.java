package me.lyamray.mobGear.utils;

import lombok.Generated;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public final class MobGearItemStacks {
    @Generated
    private MobGearItemStacks() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ItemStack leatherHelmet(String name, String lore, Enchantment enchantment, int enchantment_level, boolean glow, boolean unbreakable, Color color) {
        ItemStack test = new ItemStack(Material.LEATHER_HELMET, 1);
        return ItemCreator.of(CompMaterial.fromItem(test))
                .name(name)
                .lore(new String[] { lore }).glow(glow)
                .enchant(enchantment, enchantment_level)
                .unbreakable(unbreakable)
                .color(CompColor.fromColor(color))
                .make();
    }

    public static ItemStack leatherChestplate(String name, String lore, Enchantment enchantment, int enchantment_level, boolean glow, boolean unbreakable, Color color) {
        ItemStack test = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        return ItemCreator.of(CompMaterial.fromItem(test))
                .name(name)
                .lore(new String[] { lore }).glow(glow)
                .enchant(enchantment, enchantment_level)
                .unbreakable(unbreakable)
                .color(CompColor.fromColor(color))
                .make();
    }

    public static ItemStack leatherLeggings(String name, String lore, Enchantment enchantment, int enchantment_level, boolean glow, boolean unbreakable, Color color) {
        ItemStack test = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        return ItemCreator.of(CompMaterial.fromItem(test))
                .name(name)
                .lore(new String[] { lore }).glow(glow)
                .enchant(enchantment, enchantment_level)
                .unbreakable(unbreakable)
                .color(CompColor.fromColor(color))
                .make();
    }

    public static ItemStack leatherBoots(String name, String lore, Enchantment enchantment, int enchantment_level, boolean glow, boolean unbreakable, Color color) {
        ItemStack test = new ItemStack(Material.LEATHER_BOOTS, 1);
        return ItemCreator.of(CompMaterial.fromItem(test))
                .name(name)
                .lore(new String[] { lore }).glow(glow)
                .enchant(enchantment, enchantment_level)
                .unbreakable(unbreakable)
                .color(CompColor.fromColor(color))
                .make();
    }
}
