package me.lyamray.mobGear.inventories.silverfish;

import java.util.HashMap;
import java.util.Map;
import me.lyamray.mobGear.inventories.ForgeMaceMenu;
import me.lyamray.mobGear.utils.ChatUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public class ForgeSilverfishMenu extends Menu {

    private static final int[] ARMOR_SLOTS = new int[] { 0, 11, 12, 14, 15 };

    public ForgeSilverfishMenu() {
        setTitle("&bSilverfish Mob Gear!");
        setSize(36);
    }

    public ItemStack getItemAt(int slot) {
        if (slot >= 27) {
            if (slot == 31)
                return ItemCreator.of(CompMaterial.BARRIER, "&cClose")
                        .lore(new String[] { "&7(&c&l&cClick here &7to close this &cmenu&7!" }).make();
            return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, " ", new String[0]).make();
        }
        for (int armorSlot : ARMOR_SLOTS) {
            if (slot == armorSlot)
                return getArmorSlots(armorSlot);
        }
        return (slot % 2 == 0) ?
                ItemCreator.of(CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, " ", new String[0]).make() :
                ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, " ", new String[0]).make();
    }

    private ItemStack getArmorSlots(int slot) {
        String priceLore = "\n&bPrice:\n&7Each piece costs &b32 Enhanced Silverfish Skulls&7!\n";
        String firstLore = "\n&7(&b&l&bClick here &7to &bcraft &7this piece of &bMob Gear&7!\n\n";
        String effectLore = "\n&bSilverfish Set Effects:\n&31 Piece &8&7Speed I\n&32 Pieces &8&7Strenght I\n&33 Pieces &8&7Health Boost IV\n";
        String fullLore = firstLore + effectLore + priceLore;

        ItemCreator itemCreator;

        switch (slot) {
            case 0:
                itemCreator = ItemCreator.of(CompMaterial.LEATHER_HELMET, "&bSilverfish Helmet", fullLore);
                break;

            case 11:
                itemCreator = ItemCreator.of(CompMaterial.LEATHER_CHESTPLATE, "&bSilverfish Chestplate", fullLore);
                break;

            case 12:
                itemCreator = ItemCreator.of(CompMaterial.LEATHER_LEGGINGS, "&bSilverfish Leggings", fullLore);
                break;

            case 14:
                itemCreator = ItemCreator.of(CompMaterial.LEATHER_BOOTS, "&bSilverfish Boots", fullLore);
                break;

            default:
                return null;
        }

        return createItemStack(itemCreator).make();
    }


    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        if (slot == 31) {
            player.sendMessage(ChatUtil.color("&7(&c&l&7You &cclosed &7the forge menu."));
            player.closeInventory();
            return;
        }
        String nameHead = "&3Enhanced Silverfish Head";
        String loreHead = "&7Use this &bEnhanced &7head to craft &bSilverfish Gear&7!";
        String url = "http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540";
        String firstLore = "\n&bThis special Mob Gear piece is forged with the skulls of a Silverfish!\n";
        String hexColor = "#CECECE";
        Map<Integer, String> armorPieceMap = new HashMap<>();
        armorPieceMap.put(11, "Legendary Silverfish Helmet");
        armorPieceMap.put(12, "Legendary Silverfish Chestplate");
        armorPieceMap.put(14, "Legendary Silverfish Leggings");
        armorPieceMap.put(15, "Legendary Silverfish Boots");
        if (armorPieceMap.containsKey(slot)) {
            String armorPiece = armorPieceMap.get(slot);
            (new ForgeMaceMenu(firstLore, "\n&bSilverfish Set Effects:\n&31 Piece &8&7Speed I\n&32 Pieces &8&7Strenght I\n&33 Pieces &8&7Health Boost IV\n", url, armorPiece, nameHead, loreHead, hexColor)).displayTo(player);
        }
    }

    private ItemCreator createItemStack(ItemCreator itemCreator) {
        itemCreator
                .enchant(Enchantment.PROTECTION, 10)
                .unbreakable(true)
                .glow(false)
                .hideTags(false)
                .color(CompColor.fromName("#7D44A9"));
        itemCreator.hideTags(false);
        return itemCreator;
    }
}
