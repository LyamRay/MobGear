package me.lyamray.mobGear.listeners;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class PlayerChangeArmorListener implements Listener {

    private static final int HELMET_SLOT = 39;
    private static final int BOOTS_SLOT = 36;

    private static final Map<String, PotionEffect[]> ARMOR_EFFECTS = new HashMap<>();
    static {
        int inf = -1;
        ARMOR_EFFECTS.put("Blaze", new PotionEffect[]{
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, inf, 0, false, false),
                new PotionEffect(PotionEffectType.SPEED, inf, 0, false, false),
                new PotionEffect(PotionEffectType.RESISTANCE, inf, 1, false, false)
        });
        ARMOR_EFFECTS.put("Iron", new PotionEffect[]{
                new PotionEffect(PotionEffectType.SPEED, inf, 0, false, false),
                new PotionEffect(PotionEffectType.REGENERATION, inf, 1, false, false),
                new PotionEffect(PotionEffectType.STRENGTH, inf, 2, false, false)
        });
        ARMOR_EFFECTS.put("Slime", new PotionEffect[]{
                new PotionEffect(PotionEffectType.SLOWNESS, inf, 0, false, false),
                new PotionEffect(PotionEffectType.JUMP_BOOST, inf, 0, false, false),
                new PotionEffect(PotionEffectType.JUMP_BOOST, inf, 1, false, false)
        });
        ARMOR_EFFECTS.put("Silverfish", new PotionEffect[]{
                new PotionEffect(PotionEffectType.SPEED, inf, 0, false, false),
                new PotionEffect(PotionEffectType.STRENGTH, inf, 0, false, false),
                new PotionEffect(PotionEffectType.HEALTH_BOOST, inf, 3, false, false)
        });
        ARMOR_EFFECTS.put("Chicken", new PotionEffect[]{
                new PotionEffect(PotionEffectType.SLOW_FALLING, inf, 0, false, false),
                new PotionEffect(PotionEffectType.SPEED, inf, 0, false, false),
                new PotionEffect(PotionEffectType.STRENGTH, inf, 1, false, false)
        });
    }

    @EventHandler
    public void onArmorChange(PlayerInventorySlotChangeEvent event) {
        Player player = event.getPlayer();
        int slot = event.getSlot();
        if (slot < BOOTS_SLOT || slot > HELMET_SLOT) return;

        ItemStack oldItem = event.getOldItemStack();
        ItemStack newItem = event.getNewItemStack();
        String oldGearType = getGearType(oldItem);
        String newGearType = getGearType(newItem);

        if (oldGearType != null && !oldGearType.equals(newGearType)) {
            removeEffectsForGear(player, oldGearType);
        }

        applyEffectsBasedOnGear(player);
    }

    private void applyEffectsBasedOnGear(Player player) {
        Map<String, Integer> wornGearTypesCount = calculateWornGearTypes(player);
        for (Map.Entry<String, Integer> entry : wornGearTypesCount.entrySet()) {
            String gearType = entry.getKey();
            int piecesWorn = entry.getValue();

            if (ARMOR_EFFECTS.containsKey(gearType)) {
                PotionEffect[] effects = ARMOR_EFFECTS.get(gearType);
                for (int i = 0; i < Math.min(piecesWorn, effects.length); i++) {
                    player.addPotionEffect(effects[i]);
                }
            }
        }
    }

    private void removeEffectsForGear(Player player, String gearType) {
        if (ARMOR_EFFECTS.containsKey(gearType)) {
            PotionEffect[] effects = ARMOR_EFFECTS.get(gearType);
            for (PotionEffect effect : effects) {
                player.removePotionEffect(effect.getType());
            }
        }
    }

    private Map<String, Integer> calculateWornGearTypes(Player player) {
        Map<String, Integer> wornGearTypesCount = new HashMap<>();
        for (ItemStack item : player.getInventory().getArmorContents()) {
            String gearType = getGearType(item);
            if (gearType != null) {
                wornGearTypesCount.merge(gearType, 1, Integer::sum);
            }
        }
        return wornGearTypesCount;
    }

    private String getGearType(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta.getLore() == null) return null;
        return meta.getLore().stream()
                .filter(lore -> lore.contains("Set Effects"))
                .map(lore -> lore.split(" ")[0].trim())
                .map(ChatColor::stripColor)
                .findFirst()
                .orElse(null);
    }
}
