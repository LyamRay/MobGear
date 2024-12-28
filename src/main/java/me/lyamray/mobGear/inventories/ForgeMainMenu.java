package me.lyamray.mobGear.inventories;

import me.lyamray.mobGear.inventories.blaze.ForgeBlazeMenu;
import me.lyamray.mobGear.inventories.chicken.ForgeChickenMenu;
import me.lyamray.mobGear.inventories.irongolem.ForgeIronGolemMenu;
import me.lyamray.mobGear.inventories.silverfish.ForgeSilverfishMenu;
import me.lyamray.mobGear.inventories.slime.ForgeSlimeMenu;
import me.lyamray.mobGear.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class ForgeMainMenu extends Menu {
    private static final int BOTTOM_ROW_START = 45;

    private static final int EXIT_SLOT = 49;

    private static final int[] SPAWN_EGG_SLOTS = new int[] { 0, 11, 15, 22, 29, 33 };

    public ForgeMainMenu() {
        setTitle("&bForge &7here your &bskulls&7!");
        setSize(Integer.valueOf(54));
    }

    public ItemStack getItemAt(int slot) {
        if (slot >= 45) {
            if (slot == 49)
                return ItemCreator.of(CompMaterial.BARRIER, "&cClose", new String[0])
                        .lore(new String[] { "&7(&c&l&cClick here &7to close this &cmenu&7!" }).make();
            return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, " ", new String[0]).make();
        }
        for (int eggSlot : SPAWN_EGG_SLOTS) {
            if (slot == eggSlot)
                return getSpawnEggItem(eggSlot);
        }
        return (slot % 2 == 0) ?
                ItemCreator.of(CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, " ", new String[0]).make() :
                ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, " ", new String[0]).make();
    }

    private ItemStack getSpawnEggItem(int slot) {
        switch (slot) {
            case 0:

            case 11:

            case 15:

            case 22:

            case 29:

            case 33:

        }
        return

                null;
    }

    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        if (slot == 49) {
            player.sendMessage(ChatUtil.color("&7(&c&l&7You &cclosed &7the forge menu."));
            player.closeInventory();
            return;
        }
        for (int eggSlot : SPAWN_EGG_SLOTS) {
            if (slot == eggSlot) {
                switch (eggSlot) {
                    case 11:
                        (new ForgeIronGolemMenu()).displayTo(player);
                        break;
                    case 15:
                        (new ForgeBlazeMenu()).displayTo(player);
                        break;
                    case 22:
                        (new ForgeSilverfishMenu()).displayTo(player);
                        break;
                    case 29:
                        (new ForgeChickenMenu()).displayTo(player);
                        break;
                    case 33:
                        (new ForgeSlimeMenu()).displayTo(player);
                        break;
                }
                return;
            }
        }
    }
}
