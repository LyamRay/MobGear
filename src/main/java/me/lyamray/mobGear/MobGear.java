package me.lyamray.mobGear;

import lombok.Getter;
import me.lyamray.mobGear.listeners.CraftingValidationListener;
import me.lyamray.mobGear.listeners.MobKillListener;
import me.lyamray.mobGear.recipes.CustomEnhancedRecipes;
import me.lyamray.mobGear.recipes.CustomMobGearRecipes;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobGear extends JavaPlugin {

    @Getter
    private static MobGear instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        CustomEnhancedRecipes.registerRecipes();
        CustomMobGearRecipes.createMobGear();
        Bukkit.getPluginManager().registerEvents(new MobKillListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingValidationListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
