package me.lyamray.mobGear.inventories.chicken;

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

public class ForgeChickenMenu extends Menu {
    private static final int BOTTOM_ROW_START = 27;

    private static final int EXIT_SLOT = 31;

    private static final int[] ARMOR_SLOTS = new int[] { 0, 11, 12, 14, 15 };

    private final String effectLore = "\n&bBlaze Chicken Set Effects:\n&31 Piece &8&7Slow Falling \n&32 Pieces &8&7Speed I \n&33 Pieces &8&7Strength II \n";

    public ForgeChickenMenu() {
        setTitle("&bChicken Mob Gear!");
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
        String priceLore = "\n&bPrice:\n&7Each piece costs &b32 Enhanced Chicken Skulls&7!\n";
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
        String nameHead = "&cEnhanced Chicken Head";
        String loreHead = "&7Use this &bEnhanced &7head to craft &bChicken Gear&7!";
        String url = "http://textures.minecraft.net/texture/598f6f7e0d2dd1d68e9fa0a486c6a22fef481dca7c281d554cf600836a03187f";
        String firstLore = "\n&bThis special Mob Gear piece is forged with the skulls of a Chicken!\n";
        String hexColor = "#C26162";
        Map<Integer, String> armorPieceMap = new HashMap<>();
        armorPieceMap.put(11, "Legendary Chicken Helmet");
        armorPieceMap.put(12, "Legendary Chicken Chestplate");
        armorPieceMap.put(14, "Legendary Chicken Leggings");
        armorPieceMap.put(15, "Legendary Chicken Boots");
        if (armorPieceMap.containsKey(slot)) {
            String armorPiece = armorPieceMap.get(slot);
            (new ForgeMaceMenu(firstLore, "\n&bBlaze Chicken Set Effects:\n&31 Piece &8&7Slow Falling \n&32 Pieces &8&7Speed I \n&33 Pieces &8&7Strength II \n", url, armorPiece, nameHead, loreHead, hexColor)).displayTo(player);
        }
    }

    private ItemCreator createItemStack(ItemCreator itemCreator) {
        itemCreator
                .enchant(Enchantment.PROTECTION, 10)
                .unbreakable(true)
                .glow(false)
                .hideTags(false)
                .color(CompColor.fromName("#C26162"));
        itemCreator.hideTags(false);
        return itemCreator;
    }
}
