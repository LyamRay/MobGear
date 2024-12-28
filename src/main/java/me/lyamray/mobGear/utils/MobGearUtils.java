package me.lyamray.mobGear.utils;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public class MobGearUtils {
    public static boolean hasEnoughHeads(Player player, String url, String nameHead, String loreHead, int requiredAmount) {
        ItemStack requiredHead = ItemStacks.entityHead(url, nameHead, loreHead);
        return player.getInventory().containsAtLeast(requiredHead, requiredAmount);
    }

    public static void removeEnhancedHeads(Player player, String url, String nameHead, String loreHead, int requiredAmount) {
        ItemStack requiredHead = ItemStacks.entityHead(url, nameHead, loreHead);
        requiredHead.setAmount(requiredAmount);
        Map<Integer, ItemStack> leftover = player.getInventory().removeItem(new ItemStack[] { requiredHead });
        if (!leftover.isEmpty())
            player.sendMessage(ChatUtil.color("&7(&c&l&7Failed to remove all Enhanced Heads from your inventory!"));
    }

    public static ItemStack createMobGear(String armorPiece, String firstLore, String effectLore, String color) {
        try {
            String armorType = armorPiece.split(" ")[(armorPiece.split(" ")).length - 1].toUpperCase();
            CompMaterial material = CompMaterial.valueOf("LEATHER_" + armorType);
            return ItemCreator.of(material, "&c" + armorPiece, new String[0])
                    .lore(new String[] { firstLore, effectLore }).color(CompColor.fromName(color))
                    .enchant(Enchantment.PROTECTION, 10)
                    .glow(false)
                    .hideTags(false)
                    .unbreakable(true)
                    .make();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
