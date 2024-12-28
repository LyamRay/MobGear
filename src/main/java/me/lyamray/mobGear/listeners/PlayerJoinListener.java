package me.lyamray.mobGear.listeners;

import me.lyamray.mobGear.MobGear;
import me.lyamray.mobGear.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("op"))
            return;

        Bukkit.getScheduler().runTaskLater(MobGear.getInstance(), () -> {
            player.sendMessage(ChatUtil.color("&7(&l&bHey! I see you are a &bserver operator&7. Don't forget to"));
            player.sendMessage(ChatUtil.color("&7(&l&bcheck out &b/mobgear help &7for all the &bcommands&7!"));
        }, 20L);
    }
}
