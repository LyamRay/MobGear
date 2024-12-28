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
    private static final int[] SPAWN_EGG_SLOTS = new int[]{0, 11, 15, 22, 29, 33};

    public ForgeMainMenu() {
        this.setTitle("&b⚒ Forge &7here your &bskulls&7!");
        this.setSize(54);
    }

    @Override
    public ItemStack getItemAt(int slot) {
        if (slot >= 45) {
            if (slot == 49) {
                return ItemCreator.of(CompMaterial.BARRIER, "&cClose").lore("&7(&c&l✘&7) &cClick here &7to close this &cmenu&7!").make();
            }
            return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, " ", new String[0]).make();
        }
        for (int eggSlot : SPAWN_EGG_SLOTS) {
            if (slot != eggSlot) continue;
            return this.getSpawnEggItem(eggSlot);
        }
        return slot % 2 == 0 ? ItemCreator.of(CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, " ", new String[0]).make() : ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, " ", new String[0]).make();
    }

    private ItemStack getSpawnEggItem(int slot) {
        return switch (slot) {
            case 0 -> ItemCreator.of(CompMaterial.KNOWLEDGE_BOOK, "&bInformation Book").lore("&7(&b&l❕&7) &bClick here &7to get all the &binformation &7about &bMob Gear&7!").make();
            case 11 -> ItemCreator.of(CompMaterial.IRON_GOLEM_SPAWN_EGG,
                    "&8Iron Golem Mob Gear").lore("&7(&b&l❕&7) &bClick here &7to create &8Iron Golem Mob Gear&7!").make();
            case 15 -> ItemCreator.of(CompMaterial.BLAZE_SPAWN_EGG,
                    "&6Blaze Mob Gear").lore("&7(&b&l❕&7) &bClick here &7to create &6Blaze Mob Gear&7!").make();
            case 22 -> ItemCreator.of(CompMaterial.SILVERFISH_SPAWN_EGG,
                    "&3Silverfish Mob Gear").lore("&7(&b&l❕&7) &bClick here &7to create &3Silverfish Mob Gear&7!").make();
            case 29 -> ItemCreator.of(CompMaterial.CHICKEN_SPAWN_EGG,
                    "&cChicken Mob Gear").lore("&7(&b&l❕&7) &bClick here &7to create &cChicken Mob Gear&7!").make();
            case 33 -> ItemCreator.of(CompMaterial.SLIME_SPAWN_EGG,
                    "&aSlime Mob Gear").lore("&7(&b&l❕&7) &bClick here &7to create &aSlime Mob Gear&7!").make();
            default -> null;
        };
    }

    @Override
    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        if (slot == 49) {
            player.sendMessage(ChatUtil.color("&7(&c&l❕&7) &7You &cclosed &7the forge menu."));
            player.closeInventory();
            return;
        }
        for (int eggSlot : SPAWN_EGG_SLOTS) {
            if (slot != eggSlot) continue;
            switch (eggSlot) {
                case 11: {
                    new ForgeIronGolemMenu().displayTo(player);
                    break;
                }
                case 15: {
                    new ForgeBlazeMenu().displayTo(player);
                    break;
                }
                case 22: {
                    new ForgeSilverfishMenu().displayTo(player);
                    break;
                }
                case 29: {
                    new ForgeChickenMenu().displayTo(player);
                    break;
                }
                case 33: {
                    new ForgeSlimeMenu().displayTo(player);
                    break;
                }
            }
            return;
        }
    }
}