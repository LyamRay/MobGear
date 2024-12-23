package me.lyamray.mobGear;

import lombok.Getter;
import me.lyamray.mobGear.listeners.MobKillListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobGear extends JavaPlugin {

    @Getter
    private static MobGear instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getPluginManager().registerEvents(new MobKillListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
