package me.lyamray.mobGear.inventories.slime;

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

public class ForgeSlimeMenu extends Menu {
    private static final int BOTTOM_ROW_START = 27;

    private static final int EXIT_SLOT = 31;

    private static final int[] ARMOR_SLOTS = new int[] { 0, 11, 12, 14, 15 };

    private final String effectLore = "\n&bSlime Set Effects:\n&31 Piece &8&7Slowness I\n&32 Pieces &8&7Jump Boost I\n&33 Pieces &8&7Jump Boost II\n";

    public ForgeSlimeMenu() {
        setTitle("&bSlime Mob Gear!");
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
        String priceLore = "\n&bPrice:\n&7Each piece costs &b32 Enhanced Slime Skulls&7!\n";
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
        String nameHead = "&aEnhanced Slime Head";
        String loreHead = "&7Use this &bEnhanced &7head to craft &bSlime Gear&7!";
        String url = "http://textures.minecraft.net/texture/c7d29dbf3d98213ec2fb0ca25da74779e57bd0c1234268f828a3ec9869e15a9c";
        String firstLore = "\n&bThis special Mob Gear piece is forged with the skulls of a Slime!\n";
        String hexColor = "#00FF00";
        Map<Integer, String> armorPieceMap = new HashMap<>();
        armorPieceMap.put(11, "Legendary Slime Helmet");
        armorPieceMap.put(12, "Legendary Slime Chestplate");
        armorPieceMap.put(14, "Legendary Slime Leggings");
        armorPieceMap.put(15, "Legendary Slime Boots");
        if (armorPieceMap.containsKey(slot)) {
            String armorPiece = armorPieceMap.get(slot);
            (new ForgeMaceMenu(firstLore, "\n&bSlime Set Effects:\n&31 Piece &8&7Slowness I\n&32 Pieces &8&7Jump Boost I\n&33 Pieces &8&7Jump Boost II\n", url, armorPiece, nameHead, loreHead, hexColor)).displayTo(player);
        }
    }

    private ItemCreator createItemStack(ItemCreator itemCreator) {
        itemCreator
                .enchant(Enchantment.PROTECTION, 10)
                .unbreakable(true)
                .glow(false)
                .hideTags(false)
                .color(CompColor.fromName("#00FF00"));
        itemCreator.hideTags(false);
        return itemCreator;
    }
}
