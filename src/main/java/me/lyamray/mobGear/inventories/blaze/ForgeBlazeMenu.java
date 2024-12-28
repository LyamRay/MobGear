package me.lyamray.mobGear.inventories.blaze;

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

public class ForgeBlazeMenu extends Menu {
    private static final int BOTTOM_ROW_START = 27;

    private static final int EXIT_SLOT = 31;

    private static final int[] ARMOR_SLOTS = new int[] { 0, 11, 12, 14, 15 };

    private final String effectLore = "\n&bBlaze Set Effects:\n&31 Piece &8&7Fire Resistance I\n&32 Pieces &8&7Speed I\n&33 Pieces &8&7Resistance II\n";

    public ForgeBlazeMenu() {
        setTitle("&bBlaze Mob Gear!");
        setSize(36);
    }

    public ItemStack getItemAt(int slot) {
        if (slot >= 27) {
            if (slot == 31)
                return ItemCreator.of(CompMaterial.BARRIER, "&cClose", new String[0])
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
        String priceLore = "\n&bPrice:\n&7Each piece costs &b32 Enhanced Blaze Skulls&7!\n";
        String firstLore = "\n&7(&b&l&bClick here &7to &bcraft &7this piece of &bMob Gear&7!\n\n";
        switch (slot) {
            case 0:

            case 11:

            case 12:

            case 14:

            case 15:

        }
        return

                null;
    }

    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        if (slot == 31) {
            player.sendMessage(ChatUtil.color("&7(&c&l&7You &cclosed &7the forge menu."));
            player.closeInventory();
            return;
        }
        String nameHead = "&6Enhanced Blaze Head";
        String loreHead = "&7Use this &bEnhanced &7head to craft &bBlaze Gear&7!";
        String url = "http://textures.minecraft.net/texture/b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0";
        String firstLore = "\n&bThis special Mob Gear piece is forged with the skulls of a Blaze!\n";
        String hexColor = "#FFDE59";
        Map<Integer, String> armorPieceMap = new HashMap<>();
        armorPieceMap.put(11, "Legendary Blaze Helmet");
        armorPieceMap.put(12, "Legendary Blaze Chestplate");
        armorPieceMap.put(14, "Legendary Blaze Leggings");
        armorPieceMap.put(15, "Legendary Blaze Boots");
        if (armorPieceMap.containsKey(slot)) {
            String armorPiece = armorPieceMap.get(slot);
            (new ForgeMaceMenu(firstLore, "\n&bBlaze Set Effects:\n&31 Piece &8&7Fire Resistance I\n&32 Pieces &8&7Speed I\n&33 Pieces &8&7Resistance II\n", url, armorPiece, nameHead, loreHead, hexColor)).displayTo(player);
        }
    }

    private ItemCreator createItemStack(ItemCreator itemCreator) {
        itemCreator
                .enchant(Enchantment.PROTECTION, 10)
                .unbreakable(true)
                .glow(false)
                .hideTags(false)
                .color(CompColor.fromName("#FFDE59"));
        itemCreator.hideTags(false);
        return itemCreator;
    }
}
