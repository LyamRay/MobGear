package me.lyamray.mobGear;

import lombok.Getter;
import me.lyamray.mobGear.listeners.ForgeBlockListener;
import me.lyamray.mobGear.listeners.MobKillListener;
import me.lyamray.mobGear.recipes.CustomEnhancedRecipes;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobGear extends JavaPlugin {

    @Getter
    private static MobGear instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        CustomEnhancedRecipes.registerHeadRecipes();
        Bukkit.getPluginManager().registerEvents(new MobKillListener(), this);
        Bukkit.getPluginManager().registerEvents(new ForgeBlockListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
