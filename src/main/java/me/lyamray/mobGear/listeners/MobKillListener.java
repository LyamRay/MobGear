package me.lyamray.mobGear.listeners;

import me.lyamray.mobGear.MobGear;
import me.lyamray.mobGear.utils.ChatUtil;
import me.lyamray.mobGear.utils.ItemStacks;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobKillListener implements Listener {


    @EventHandler
    public void killListener(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (event.getEntity().getKiller() == null) {
            MobGear.getInstance().getLogger().info("This was not a player that killed this mob! *DEBUG");
            return;
        }

        Player player = event.getEntity().getKiller().getPlayer();
        String url1 = "";
        String name = "";
        double dropChance = 0.2;
        switch (event.getEntity().getType()) {
            case BLAZE -> {
                url1 = "http://textures.minecraft.net/texture/b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0";
                name = "&6Blaze";
                dropChance = 0.4;
            }
            case SILVERFISH -> {
                url1 = "http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540";
                name = "&3Silverfish";
                dropChance = 1;
            }
            case IRON_GOLEM -> {
                url1 = "http://textures.minecraft.net/texture/da6e0429ccaabb6f5f0c5d513c795bed6d80fce72f57f4bc3a616aee23e12572";
                name = "&8Iron Golem";
                dropChance = 1;
            }
        }
        skullLogic(entity, player, url1, dropChance, name);
    }

    private void skullLogic(Entity entity, Player player, String url, double chance, String name) {
        if (Math.random() < chance) {
            String defName = name + " Head";
            String lore1 = "&7Use this head to craft a &bEnhanced &7version!";
            short amount = 1;
            ItemStack skull = ItemStacks.entityHead(url, defName, lore1, amount);
            entity.getWorld().dropItemNaturally(entity.getLocation(), skull);
            String entityType = entity.getType().toString().toLowerCase();
            player.sendMessage(ChatUtil.color("&7Good! You killed a &b" + entityType + " &7and it dropped their head!"));
        }
    }
}