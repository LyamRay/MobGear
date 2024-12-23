package me.lyamray.mobGear.recipes;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.lyamray.mobGear.MobGear;
import me.lyamray.mobGear.utils.ItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.ShapedRecipe;

@UtilityClass
public class CustomEnhancedRecipes {
    private final short amount = 1;

    public void registerHeadRecipes() {
        for (EnhancedHeadType headType : EnhancedHeadType.values()) {
            createEnhancedHeadRecipe(headType);
        }
    }

    private void createEnhancedHeadRecipe(EnhancedHeadType headType) {

        ItemStack enhancedHead = ItemStacks.entityHead(
                headType.getTextureUrl(),
                headType.getEnhancedName(),
                headType.getEnhancedLore(), amount);

        ShapedRecipe recipe = new ShapedRecipe(
                new NamespacedKey(MobGear.getInstance(), headType.getKey()), enhancedHead);
        recipe.shape(
                "AAA",
                "ABA",
                "AAA");

        ItemStack baseHead = ItemStacks.entityHead(
                headType.getTextureUrl(),
                headType.getDefaultName(),
                headType.getDefaultLore(), amount);

        recipe.setIngredient('A', new ExactChoice(baseHead));
        recipe.setIngredient('B', Material.DIAMOND_BLOCK);

        Bukkit.addRecipe(recipe);
    }

    @Getter
    public enum EnhancedHeadType {
        BLAZE(
                "http://textures.minecraft.net/texture/b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0",
                "&6Enhanced Blaze Head",
                "&7Use this &bEnhanced &7head to craft &bBlaze Gear&7!",
                "&6Blaze Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedBlazeHead"
        ),
        SILVERFISH(
                "http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540",
                "&3Enhanced Silverfish Head",
                "&7Use this &bEnhanced &7head to craft &bSilverfish Gear&7!",
                "&3Silverfish Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedSilverfishHead"
        ),
        IRONGOLEM(
                "http://textures.minecraft.net/texture/da6e0429ccaabb6f5f0c5d513c795bed6d80fce72f57f4bc3a616aee23e12572",
                "&8Enhanced Iron Golem Head",
                "&7Use this &bEnhanced &7head to craft &bIron Golem Gear&7!",
                "&8Iron Golem Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedIronGolemHead"
        );
        private final String textureUrl;
        private final String enhancedName;
        private final String enhancedLore;
        private final String defaultName;
        private final String defaultLore;
        private final String key;

        EnhancedHeadType(String textureUrl, String enhancedName, String enhancedLore, String defaultName, String defaultLore, String key) {
            this.textureUrl = textureUrl;
            this.enhancedName = enhancedName;
            this.enhancedLore = enhancedLore;
            this.defaultName = defaultName;
            this.defaultLore = defaultLore;
            this.key = key;
        }

    }
}
