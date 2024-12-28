package me.lyamray.mobGear.listeners;

import java.util.ArrayList;
import java.util.UUID;
import me.lyamray.mobGear.MobGear;
import me.lyamray.mobGear.inventories.ForgeMainMenu;
import me.lyamray.mobGear.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ForgeBlockListener
        implements Listener {
    private final ArrayList<UUID> interacting = new ArrayList<>();

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        if (!this.isValidInteraction(event, block)) {
            return;
        }
        if (this.interacting.contains(player.getUniqueId())) {
            player.sendMessage(ChatUtil.color("&7(&c&l✘&7) You have &calready interacted&7!"));
            return;
        }
        this.interacting.add(player.getUniqueId());
        Bukkit.getScheduler().runTaskLater(MobGear.getInstance(), () -> {
            new ForgeMainMenu().displayTo(player);
            this.interacting.remove(player.getUniqueId());
        }, 15L);
        player.sendMessage(ChatUtil.color("&7(&b&l❕&7) &bBefore &7you click on anything. &bRead &7it first!"));
    }

    private boolean isValidInteraction(PlayerInteractEvent event, Block block) {
        Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.OFF_HAND || event.getAction() != Action.LEFT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return false;
        }
        if (block == null || block.getType() != Material.SCULK_SHRIEKER) {
            return false;
        }
        if (player.getGameMode() != GameMode.SURVIVAL) {
            player.sendMessage(ChatUtil.color("&7(&c&l✘&7) You need to be in &cgamemode survival &7to use the forge!"));
            return false;
        }
        return true;
    }
}