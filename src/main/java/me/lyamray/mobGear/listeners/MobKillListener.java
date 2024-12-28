package me.lyamray.mobGear.listeners;

import me.lyamray.mobGear.utils.ChatUtil;
import me.lyamray.mobGear.utils.ItemStacks;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobKillListener implements Listener {
    @EventHandler
    public void killListener(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (event.getEntity().getKiller() == null)
            return;
        Player player = event.getEntity().getKiller().getPlayer();
        String url1 = "";
        String name = "";
        double dropChance = 0.2D;
        switch (event.getEntity().getType()) {
            case BLAZE:
                url1 = "http://textures.minecraft.net/texture/b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0";
                name = "&6Blaze";
                dropChance = 0.4D;
                break;
            case SILVERFISH:
                url1 = "http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540";
                name = "&3Silverfish";
                dropChance = 1.0D;
                break;
            case IRON_GOLEM:
                url1 = "http://textures.minecraft.net/texture/da6e0429ccaabb6f5f0c5d513c795bed6d80fce72f57f4bc3a616aee23e12572";
                name = "&8Iron Golem";
                dropChance = 1.0D;
                break;
            case SLIME:
                url1 = "http://textures.minecraft.net/texture/c7d29dbf3d98213ec2fb0ca25da74779e57bd0c1234268f828a3ec9869e15a9c";
                name = "&aSlime";
                dropChance = 1.0D;
                break;
            case CHICKEN:
                url1 = "http://textures.minecraft.net/texture/598f6f7e0d2dd1d68e9fa0a486c6a22fef481dca7c281d554cf600836a03187f";
                name = "&cChicken";
                dropChance = 1.0D;
                break;
        }
        skullLogic(livingEntity, player, url1, dropChance, name);
    }

    private void skullLogic(Entity entity, Player player, String url, double chance, String name) {
        if (Math.random() < chance) {
            String defName = name + " Head";
            String lore1 = "&7Use this head to craft a &bEnhanced &7version!";
            ItemStack skull = ItemStacks.entityHead(url, defName, lore1);
            entity.getWorld().dropItemNaturally(entity.getLocation(), skull);
            String entityType = entity.getType().toString().toLowerCase();
            player.sendMessage(ChatUtil.color("&7Good! You killed a &b" + entityType + " &7and it dropped their head!"));
        }
    }
}
