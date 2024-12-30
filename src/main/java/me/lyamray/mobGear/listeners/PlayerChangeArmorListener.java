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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerChangeArmorListener implements Listener {
    private static final int HELMET_SLOT = 39;
    private static final int BOOTS_SLOT = 36;
    private static final int INFINITE_DURATION = -1;

    private static final Map<String, PotionEffect[]> ARMOR_EFFECTS = createArmorEffects();

    private static Map<String, PotionEffect[]> createArmorEffects() {
        Function<Object[], PotionEffect> effect = args -> 
            new PotionEffect((PotionEffectType) args[0], INFINITE_DURATION, (int) args[1], false, false);

        Map<String, Object[][]> effectsData = Map.of(
            "Blaze", new Object[][]{
                {PotionEffectType.FIRE_RESISTANCE, 0},
                {PotionEffectType.SPEED, 0},
                {PotionEffectType.RESISTANCE, 1}
            },
            "Iron", new Object[][]{
                {PotionEffectType.SPEED, 0},
                {PotionEffectType.REGENERATION, 1},
                {PotionEffectType.STRENGTH, 2}
            },
            "Slime", new Object[][]{
                {PotionEffectType.SLOWNESS, 0},
                {PotionEffectType.JUMP_BOOST, 0},
                {PotionEffectType.JUMP_BOOST, 1}
            },
            "Silverfish", new Object[][]{
                {PotionEffectType.SPEED, 0},
                {PotionEffectType.STRENGTH, 0},
                {PotionEffectType.HEALTH_BOOST, 3}
            },
            "Chicken", new Object[][]{
                {PotionEffectType.SLOW_FALLING, 0},
                {PotionEffectType.SPEED, 0},
                {PotionEffectType.STRENGTH, 1}
            }
        );

        return effectsData.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> Arrays.stream(e.getValue())
                    .map(effect)
                    .toArray(PotionEffect[]::new)
            ));
    }

    @EventHandler
    public void onArmorChange(PlayerInventorySlotChangeEvent event) {
        if (!isArmorSlot(event.getSlot())) return;

        Player player = event.getPlayer();
        String oldGearType = getGearType(event.getOldItemStack());
        String newGearType = getGearType(event.getNewItemStack());

        if (oldGearType != null && !oldGearType.equals(newGearType)) {
            removeEffectsForGear(player, oldGearType);
        }
        applyEffectsBasedOnGear(player);
    }

    private boolean isArmorSlot(int slot) {
        return slot >= BOOTS_SLOT && slot <= HELMET_SLOT;
    }

    private void applyEffectsBasedOnGear(Player player) {
        calculateWornGearTypes(player).forEach((gearType, piecesWorn) -> {
            PotionEffect[] effects = ARMOR_EFFECTS.get(gearType);
            if (effects != null) {
                Arrays.stream(effects)
                    .limit(piecesWorn)
                    .forEach(player::addPotionEffect);
            }
        });
    }

    private void removeEffectsForGear(Player player, String gearType) {
        Optional.ofNullable(ARMOR_EFFECTS.get(gearType))
            .ifPresent(effects -> Arrays.stream(effects)
                .map(PotionEffect::getType)
                .forEach(player::removePotionEffect));
    }

    private Map<String, Integer> calculateWornGearTypes(Player player) {
        return Arrays.stream(player.getInventory().getArmorContents())
            .filter(Objects::nonNull)
            .map(this::getGearType)
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    private String getGearType(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        return Optional.ofNullable(meta.getLore())
            .flatMap(lore -> lore.stream()
                .filter(line -> line.contains("Set Effects"))
                .map(line -> line.split(" ")[0].trim())
                .map(ChatColor::stripColor)
                .findFirst())
            .orElse(null);
    }
}
