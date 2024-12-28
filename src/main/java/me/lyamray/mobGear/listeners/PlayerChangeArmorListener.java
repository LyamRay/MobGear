package me.lyamray.mobGear.listeners;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.Material;

import java.util.List;

public class PlayerChangeArmorListener implements Listener {

    private static final int HELMET_SLOT = 39;
    private static final int CHESTPLATE_SLOT = 38;
    private static final int LEGGINGS_SLOT = 37;
    private static final int BOOTS_SLOT = 36;

    @EventHandler
    public void onArmorChange(PlayerInventorySlotChangeEvent event) {
        Player player = event.getPlayer();
        ItemStack oldItem = event.getOldItemStack();
        ItemStack newItem = event.getNewItemStack();

        int slot = event.getSlot();
        if (slot < BOOTS_SLOT || slot > HELMET_SLOT) {
            return;
        }

        String oldGearType = getGearType(oldItem);
        String newGearType = getGearType(newItem);
        String gearPiece = getArmorPiece(slot);

        ActionType actionType = determineActionType(oldItem, newItem);

        if (actionType != null) {
            handleArmorAction(player, gearPiece, oldGearType, newGearType, actionType, newItem);
        }
    }

    private ActionType determineActionType(ItemStack oldItem, ItemStack newItem) {
        return switch (ItemActionState.from(oldItem, newItem)) {
            case EQUIP -> ActionType.EQUIP;
            case CHANGE -> ActionType.CHANGE;
            case UNEQUIP -> ActionType.UNEQUIP;
            case null -> null;
        };
    }

    private void handleArmorAction(Player player, String gearPiece, String oldGearType, String newGearType, ActionType actionType, ItemStack item) {
        if (gearPiece != null) {
            String message = switch (actionType) {
                case EQUIP -> "You have equipped a " + gearPiece + " of " + newGearType + " gear.";
                case CHANGE -> "You have changed from a " + gearPiece + " of " + oldGearType + " gear to a " + gearPiece + " of " + newGearType + " gear.";
                case UNEQUIP -> "You have unequipped a " + gearPiece + " of " + oldGearType + " gear.";
            };
            player.sendMessage(message);

            List<String> setEffectsLore = getSetEffectsLore(item);
            if (setEffectsLore != null) {
                for (String lore : setEffectsLore) {
                    player.sendMessage(lore);
                }
            }
        }
    }

    private List<String> getSetEffectsLore(ItemStack item) {
        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getLore() == null) return null;

        List<String> lore = meta.getLore();
        boolean setEffectsFound = false;
        int count = 0;
        List<String> setEffects = new java.util.ArrayList<>();

        for (String line : lore) {
            if (line.contains("Set Effects")) {
                setEffectsFound = true;
                continue;
            }
            if (setEffectsFound && count < 3) {
                setEffects.add(line);
                count++;
            }
            if (count == 3) {
                break;
            }
        }

        return setEffects.isEmpty() ? null : setEffects;
    }

    private String getGearType(ItemStack item) {
        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getLore() == null) return null;

        for (String lore : meta.getLore()) {
            if (lore.contains("Set Effects")) {
                return lore.split(" ")[0].trim();
            }
        }
        return null;
    }

    private String getArmorPiece(int slot) {
        return switch (slot) {
            case HELMET_SLOT -> "Helmet";
            case CHESTPLATE_SLOT -> "Chestplate";
            case LEGGINGS_SLOT -> "Leggings";
            case BOOTS_SLOT -> "Boots";
            default -> null;
        };
    }

    private enum ActionType {
        EQUIP, CHANGE, UNEQUIP
    }

    private enum ItemActionState {
        EQUIP,
        CHANGE,
        UNEQUIP;

        static ItemActionState from(ItemStack oldItem, ItemStack newItem) {
            if (oldItem.getType() == Material.AIR && newItem.getType() != Material.AIR) {
                return EQUIP;
            } else if (oldItem.getType() != Material.AIR && newItem.getType() != Material.AIR && !oldItem.isSimilar(newItem)) {
                return CHANGE;
            } else if (oldItem.getType() != Material.AIR && newItem.getType() == Material.AIR) {
                return UNEQUIP;
            }
            return null;
        }
    }
}
