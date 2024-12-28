package me.lyamray.mobGear.inventories;

import me.lyamray.mobGear.utils.ChatUtil;
import me.lyamray.mobGear.utils.MobGearUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class ForgeMaceMenu extends Menu {
    private static final int BOTTOM_ROW_START = 27;

    private static final int EXIT_SLOT = 31;

    private static final int[] MACE_SLOT = new int[] { 0, 13 };

    private final String firstLore;

    private final String effectLore;

    private final String armorPiece;

    private final String url;

    private final String nameHead;

    private final String loreHead;

    private final String color;

    public ForgeMaceMenu(String firstLore, String effectLore, String url, String armorPiece, String nameHead, String loreHead, String hexColor) {
        setTitle("&bForge your Mob Gear!");
        setSize(Integer.valueOf(36));
        this.firstLore = firstLore;
        this.effectLore = effectLore;
        this.armorPiece = armorPiece;
        this.url = url;
        this.nameHead = nameHead;
        this.loreHead = loreHead;
        this.color = hexColor;
    }

    public ItemStack getItemAt(int slot) {
        if (slot >= 27)
            return (slot == 31) ?
                    ItemCreator.of(
                            CompMaterial.BARRIER, "&cClose", new String[0]).lore(new String[] { "&7(&c&l&cClick here &7to close this &cmenu&7!" }).make() :
                    ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, " ", new String[0]).make();
        for (int maceSlot : MACE_SLOT) {
            if (slot == maceSlot)
                return getArmorSlots(maceSlot);
        }
        return ItemCreator.of((slot % 2 == 0) ? CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE : CompMaterial.GRAY_STAINED_GLASS_PANE, " ", new String[0]).make();
    }

    private ItemStack getArmorSlots(int slot) {
        switch (slot) {
            case 0:

            case 13:

        }
        return null;
    }

    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        switch (slot) {
            case 31:
                player.sendMessage(ChatUtil.color("&7(&c&l&7You &cclosed &7the forge menu."));
                player.closeInventory();
                break;
            case 13:
                if (MobGearUtils.hasEnoughHeads(player, this.url, this.nameHead, this.loreHead, 32)) {
                    craftMobGear(player);
                } else {
                    player.sendMessage(ChatUtil.color("&7(&c&l&7You don't have enough &cEnhanced Heads&7!"));
                }
                player.closeInventory();
                break;
            case 0:
                player.sendMessage("You opened the information page!");
                break;
        }
    }

    private void craftMobGear(Player player) {
        ItemStack mobGear = MobGearUtils.createMobGear(this.armorPiece, this.firstLore, this.effectLore, this.color);
        if (mobGear != null) {
            if (player.getInventory().addItem(new ItemStack[] { mobGear }).isEmpty()) {
                MobGearUtils.removeEnhancedHeads(player, this.url, this.nameHead, this.loreHead, 32);
                player.sendMessage(ChatUtil.color("&7(&b&l&7You have successfully crafted &b" + this.armorPiece + "&7!"));
            } else {
                player.sendMessage(ChatUtil.color("&7(&c&l&7Your inventory is &cfull&7. &7Please make &cspace&7!"));
            }
        } else {
            player.sendMessage(ChatUtil.color("&cError: Invalid armor piece material."));
        }
    }
}
