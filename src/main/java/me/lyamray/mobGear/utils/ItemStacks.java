package me.lyamray.mobGear.utils;

import lombok.Generated;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;

public final class ItemStacks {
    @Generated
    private ItemStacks() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ItemStack entityHead(String url, String name, String lore1) {
        ItemStack head = SkullUtil.getSkull(url);
        return ItemCreator.of(head)
                .name(name)
                .lore(new String[] { lore1 }).make();
    }
}
