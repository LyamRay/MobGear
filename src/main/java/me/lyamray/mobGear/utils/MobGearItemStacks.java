package me.lyamray.mobGear.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class MobGearItemStacks {

    private ItemStack createBlazeArmorPiece(Material material, String name) {
        return new ItemBuilder(new ItemStack(material))
                .setName(ChatUtil.color(name))
                .setLoreLine(0, "")

                .addLoreLines(ChatUtil.color("""
                &7This &bLegendary &7piece of equipment is forged
                &7from the &bskulls &7of a deadly &bBlaze&7.
                
                &bSet Effects:
                &31 Piece &8> &7Fire Resistance
                &32 Pieces &8> &7Resistance I
                &33 Pieces &8> &7Speed I
                """))

                .setLeatherArmorColor("#FFECA1")
                .addEnchant(Enchantment.PROTECTION, 10)
                .setGlowing(false)
                .setInfinityDurability()
                .toItemStack();
    }

    public ItemStack blazeHelmet() {
        return createBlazeArmorPiece(Material.LEATHER_HELMET, "&cLegendary Blaze Helmet");
    }

    public ItemStack blazeChestplate() {
        return createBlazeArmorPiece(Material.LEATHER_CHESTPLATE, "&cLegendary Blaze Chestplate");
    }

    public ItemStack blazeLeggings() {
        return createBlazeArmorPiece(Material.LEATHER_LEGGINGS, "&cLegendary Blaze Leggings");
    }

    public ItemStack blazeBoots() {
        return createBlazeArmorPiece(Material.LEATHER_BOOTS, "&cLegendary Blaze Boots");
    }






    public ItemStack silverfishHelmet () {
        return new ItemBuilder(new ItemStack(Material.LEATHER_HELMET))
                .toItemStack();

    }

    public static ItemStack silverfishChestplate() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_CHESTPLATE))
                .toItemStack();
    }

    public static ItemStack silverfishLeggings() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_LEGGINGS))
                .toItemStack();
    }

    public static ItemStack silverfishBoots() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS))
                .toItemStack();
    }

    public static ItemStack irongolemHelmet() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_HELMET))
                .toItemStack();
    }

    public static ItemStack irongolemChestplate() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_CHESTPLATE))
                .toItemStack();
    }

    public static ItemStack irongolemLeggings() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_LEGGINGS))
                .toItemStack();
    }

    public static ItemStack irongolemBoots() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS))
            .toItemStack();
    }
}
