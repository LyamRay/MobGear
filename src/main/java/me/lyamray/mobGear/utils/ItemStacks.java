package me.lyamray.mobGear.utils;


import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class ItemStacks {

    public ItemStack entityHead (String url, String name, String lore1, short amount) {
        ItemStack head = SkullUtil.getSkull(url, amount);
        return new ItemBuilder(head)
                .setName(ChatUtil.color(name))
                .setLoreLine(0, ChatUtil.color(lore1))
                .toItemStack();
    }

    public static ItemStack blazeHelmet() {

        return new ItemBuilder(new ItemStack(Material.LEATHER_HELMET))
                .toItemStack();
    }

    public static ItemStack blazeChestplate() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_CHESTPLATE))
                .toItemStack();
    }


    public static ItemStack blazeLeggings() {
        return new ItemBuilder(new ItemStack(Material.LEATHER_LEGGINGS))
                .toItemStack();
    }

    public ItemStack blazeBoots () {
        return new ItemBuilder(new ItemStack(Material.LEATHER_BOOTS))
                .toItemStack();

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
