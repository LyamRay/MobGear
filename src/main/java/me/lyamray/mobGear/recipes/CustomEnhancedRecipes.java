package me.lyamray.mobGear.recipes;

import lombok.Generated;
import me.lyamray.mobGear.MobGear;
import me.lyamray.mobGear.utils.ItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public final class CustomEnhancedRecipes {
    public static void registerHeadRecipes() {
        for (EnhancedHeadType headType : EnhancedHeadType.values()) {
            CustomEnhancedRecipes.createEnhancedHeadRecipe(headType);
        }
    }

    private static void createEnhancedHeadRecipe(EnhancedHeadType headType) {
        ItemStack enhancedHead = ItemStacks.entityHead(headType.getTextureUrl(), headType.getEnhancedName(), headType.getEnhancedLore());
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(MobGear.getInstance(), headType.getKey()), enhancedHead);
        recipe.shape("AAA", "ABA", "AAA");
        ItemStack baseHead = ItemStacks.entityHead(headType.getTextureUrl(), headType.getDefaultName(), headType.getDefaultLore());
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(baseHead));
        recipe.setIngredient('B', Material.DIAMOND_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    @Generated
    private CustomEnhancedRecipes() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public enum EnhancedHeadType {
        BLAZE("http://textures.minecraft.net/texture/b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0",
                "&6Enhanced Blaze Head",
                "&7Use this &bEnhanced &7head to craft &bBlaze Gear&7!",
                "&6Blaze Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedBlazeHead"),
        SILVERFISH("http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540",
                "&3Enhanced Silverfish Head",
                "&7Use this &bEnhanced &7head to craft &bSilverfish Gear&7!",
                "&3Silverfish Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedSilverfishHead"),
        IRONGOLEM("http://textures.minecraft.net/texture/da6e0429ccaabb6f5f0c5d513c795bed6d80fce72f57f4bc3a616aee23e12572",
                "&8Enhanced Iron Golem Head",
                "&7Use this &bEnhanced &7head to craft &bIron Golem Gear&7!",
                "&8Iron Golem Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedIronGolemHead"),
        SLIME("http://textures.minecraft.net/texture/c7d29dbf3d98213ec2fb0ca25da74779e57bd0c1234268f828a3ec9869e15a9c",
                "&aEnhanced Slime Head",
                "&7Use this &bEnhanced &7head to craft &bSlime Gear&7!",
                "&aSlime Head",
                "&7Use this head to craft a &bEnhanced &7version!",
                "enhancedSlimeHead"),
        CHICKEN("http://textures.minecraft.net/texture/598f6f7e0d2dd1d68e9fa0a486c6a22fef481dca7c281d554cf600836a03187f",
                "&cEnhanced Chicken Head",
                "&7Use this &bEnhanced &7head to craft &bChicken Gear&7!",
                "&cChicken Head",
                "&7Use this head to craft a &bEnhanced &7version!"
                , "enhancedChickenHead");

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

        @Generated
        public String getTextureUrl() {
            return this.textureUrl;
        }

        @Generated
        public String getEnhancedName() {
            return this.enhancedName;
        }

        @Generated
        public String getEnhancedLore() {
            return this.enhancedLore;
        }

        @Generated
        public String getDefaultName() {
            return this.defaultName;
        }

        @Generated
        public String getDefaultLore() {
            return this.defaultLore;
        }

        @Generated
        public String getKey() {
            return this.key;
        }
    }
}