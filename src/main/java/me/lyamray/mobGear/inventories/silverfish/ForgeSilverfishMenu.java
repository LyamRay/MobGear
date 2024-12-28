package me.lyamray.mobGear.inventories.silverfish;

import java.util.HashMap;
import me.lyamray.mobGear.inventories.ForgeMaceMenu;
import me.lyamray.mobGear.utils.ChatUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

public class ForgeSilverfishMenu extends Menu {
    private static final int[] ARMOR_SLOTS = new int[]{0, 11, 12, 14, 15};
    private final String effectLore = """
            
            &bSilverfish Set Effects:
            &31 Piece &8⋙ &7Speed I
            &32 Pieces &8⋙ &7Strenght I
            &33 Pieces &8⋙ &7Health Boost IV
            """;

    public ForgeSilverfishMenu() {
        this.setTitle("&b⚒ Silverfish Mob Gear!");
        this.setSize(36);
    }

    @Override
    public ItemStack getItemAt(int slot) {
        if (slot >= 27) {
            if (slot == 31) {
                return ItemCreator.of(CompMaterial.BARRIER,
                        "&cClose").lore("&7(&c&l✘&7) &cClick here &7to close this &cmenu&7!").make();
            }
            return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, " ").make();
        }
        for (int armorSlot : ARMOR_SLOTS) {
            if (slot != armorSlot) continue;
            return this.getArmorSlots(armorSlot);
        }
        return slot % 2 == 0 ? ItemCreator.of(CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, " ")
                .make() : ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, " ").make();
    }

    private ItemStack getArmorSlots(int slot) {
        String priceLore = "\n&bPrice:\n&7Each piece costs &b32 Enhanced Silverfish Skulls&7!\n";
        String firstLore = "\n&7(&b&l❕&7) &bClick here &7to &bcraft &7this piece of &bMob Gear&7!\n\n";
        return switch (slot) {
            case 0 -> ItemCreator.of(CompMaterial.KNOWLEDGE_BOOK,
                    "&bInformation Book").
                    lore("&7(&b&l❕&7) &bClick here &7to get all the &binformation &7about &bMob Gear&7!").make();
            case 11 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_HELMET,
                    "&cLegendary Silverfish Helmet", firstLore, effectLore, priceLore)).make();
            case 12 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_CHESTPLATE,
                    "&cLegendary Silverfish Chestplate", firstLore, effectLore, priceLore)).make();
            case 14 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_LEGGINGS,
                    "&cLegendary Silverfish Leggings", firstLore, effectLore, priceLore)).make();
            case 15 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_BOOTS,
                    "&cLegendary Silverfish Boots", firstLore, effectLore, priceLore)).make();
            default -> null;
        };
    }

    @Override
    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        if (slot == 31) {
            player.sendMessage(ChatUtil.color("&7(&c&l❕&7) &7You &cclosed &7the forge menu."));
            player.closeInventory();
            return;
        }
        String nameHead = "&3Enhanced Silverfish Head";
        String loreHead = "&7Use this &bEnhanced &7head to craft &bSilverfish Gear&7!";
        String url = "http://textures.minecraft.net/texture/da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540";
        String firstLore = "\n&7(&b&l❕&7) &7This &bunique piece &7of &bMob Gear &7is forged\n with the &bskulls &7of an &bSilverfish&7!\n";
        String hexColor = "#CECECE";
        HashMap<Integer, String> armorPieceMap = new HashMap<>();
        armorPieceMap.put(11, "Legendary Silverfish Helmet");
        armorPieceMap.put(12, "Legendary Silverfish Chestplate");
        armorPieceMap.put(14, "Legendary Silverfish Leggings");
        armorPieceMap.put(15, "Legendary Silverfish Boots");
        if (armorPieceMap.containsKey(slot)) {
            String armorPiece = armorPieceMap.get(slot);
            new ForgeMaceMenu(firstLore, effectLore, url, armorPiece, nameHead, loreHead, hexColor).displayTo(player);
        }
    }

    private ItemCreator createItemStack(ItemCreator itemCreator) {
        itemCreator.enchant(Enchantment.PROTECTION, 10)
                .unbreakable(true).glow(false).hideTags(false)
                .color(CompColor.fromName("#7D44A9"));
        return itemCreator;
    }
}
