package me.lyamray.mobGear.recipes;

import lombok.experimental.UtilityClass;
import me.lyamray.mobGear.MobGear;
import me.lyamray.mobGear.utils.ItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

@UtilityClass
public class CustomMobGearRecipes {

    public void registerMobGear() {
        registerMobGear("silverfish", "&3Enhanced Silverfish Head",
                "http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540",
                "&7Use this &bEnhanced &7head to craft &bSilverfish Gear&7!",
                ItemStacks.silverfishHelmet(), ItemStacks.silverfishChestplate(),
                ItemStacks.silverfishLeggings(), ItemStacks.silverfishBoots());

        registerMobGear("blaze", "&6Enhanced Blaze Head",
                "http://textures.minecraft.net/texture/b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0",
                "&7Use this &bEnhanced &7head to craft &bBlaze Gear&7!",
                ItemStacks.blazeHelmet(), ItemStacks.blazeChestplate(),
                ItemStacks.blazeLeggings(), ItemStacks.blazeBoots());

        registerMobGear("irongolem", "&8Enhanced Iron Golem Head",
                "http://textures.minecraft.net/texture/da6e0429ccaabb6f5f0c5d513c795bed6d80fce72f57f4bc3a616aee23e12572",
                "&7Use this &bEnhanced &7head to craft &bIron Golem Gear&7!",
                ItemStacks.irongolemHelmet(), ItemStacks.irongolemChestplate(),
                ItemStacks.irongolemLeggings(), ItemStacks.irongolemBoots());
    }

    private void registerMobGear(String key, String displayName, String url, String lore1,
                                 ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        String[][] patterns = {
                {"   ", "AAA", "A A"}, // Helmet
                {"A A", "AAA", "AAA"}, // Chestplate
                {"AAA", "A A", "A A"}, // Leggings
                {"   ", "A A", "A A"}  // Boots
        };

        ItemStack[] gearItems = {helmet, chestplate, leggings, boots};

        for (int i = 0; i < gearItems.length; i++) {
            String partKey = key + getGearPartName(i);
            registerRecipe(partKey, displayName, url, lore1, gearItems[i], patterns[i]);
        }
    }

    private void registerRecipe(String key, String displayName, String url, String lore1, ItemStack result, String[] shape) {
        ItemStack enhancedHead = ItemStacks.entityHead(url, displayName, lore1, (short) 32);

        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(MobGear.getInstance(), key), result);
        shapedRecipe.shape(shape);
        shapedRecipe.setIngredient('A', new RecipeChoice.ExactChoice(enhancedHead));

        Bukkit.addRecipe(shapedRecipe);
    }

    private String getGearPartName(int index) {
        return switch (index) {
            case 0 -> "Helmet";
            case 1 -> "Chestplate";
            case 2 -> "Leggings";
            case 3 -> "Boots";
            default -> throw new IllegalArgumentException("Invalid gear part index");
        };
    }
}
