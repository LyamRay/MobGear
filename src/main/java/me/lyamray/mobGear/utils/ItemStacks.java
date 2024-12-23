package me.lyamray.mobGear.utils;


import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class ItemStacks {

    public ItemStack entityHead (String url, String name, String lore1) {
        ItemStack head = SkullUtil.getSkull(url);
        return new ItemBuilder(head)
                .setName(ChatUtil.color(name))
                .setLoreLine(0, ChatUtil.color(lore1))
                .toItemStack();
    }
}
