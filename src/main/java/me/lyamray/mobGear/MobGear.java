package me.lyamray.mobGear;

import lombok.Getter;
import me.lyamray.mobGear.listeners.ForgeBlockListener;
import me.lyamray.mobGear.listeners.MobKillListener;
import me.lyamray.mobGear.listeners.PlayerChangeArmorListener;
import me.lyamray.mobGear.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class MobGear extends SimplePlugin {
    @Getter
    private static MobGear instance;

    public void onPluginStart() {
        instance = this;
        //CustomEnhancedRecipes.registerHeadRecipes();
        Bukkit.getPluginManager().registerEvents(new MobKillListener(),this);
        Bukkit.getPluginManager().registerEvents(new ForgeBlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChangeArmorListener(), this);
    }

    public void onPluginStop() {}
}
