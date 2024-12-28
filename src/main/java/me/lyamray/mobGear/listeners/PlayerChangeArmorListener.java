package me.lyamray.mobGear.listeners;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChangeArmorListener implements Listener {
    @EventHandler
    public void test(PlayerInventorySlotChangeEvent event) {
        event.getPlayer().sendMessage("it worked!");
    }
}
