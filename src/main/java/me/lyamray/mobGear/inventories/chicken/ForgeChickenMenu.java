package me.lyamray.mobGear.inventories.chicken;

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

public class ForgeChickenMenu extends Menu {
    private static final int EXIT_SLOT = 31;
    private static final int[] ARMOR_SLOTS = new int[]{0, 11, 12, 14, 15};
    private final String effectLore = """
            
            &bChicken Set Effects:
            &31 Piece ⋙ &7Slow Falling
            &32 Pieces ⋙ &7Speed I
            &33 Pieces ⋙ &7Strength II
            """;

    public ForgeChickenMenu() {
        this.setTitle("&b⚒ Chicken Mob Gear!");
        this.setSize(36);
    }

    @Override
    public ItemStack getItemAt(int slot) {
        if (slot >= 27) {
            if (slot == EXIT_SLOT) {
                return ItemCreator.of(CompMaterial.BARRIER, "&cClose")
                        .lore("&7(&c&l✘&7) &cClick here &7to close this &cmenu&7!").make();
            }
            return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, " ").make();
        }
        for (int armorSlot : ARMOR_SLOTS) {
            if (slot == armorSlot) {
                return this.getArmorSlots(armorSlot);
            }
        }
        return slot % 2 == 0
                ? ItemCreator.of(CompMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, " ").make()
                : ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, " ").make();
    }

    private ItemStack getArmorSlots(int slot) {
        String priceLore = "\n&bPrice:\n&7Each piece costs &b32 Enhanced Chicken Skulls&7!\n";
        String firstLore = "\n&7(&b&l❕&7) &bClick here &7to &bcraft &7this piece of &bMob Gear&7!\n\n";
        return switch (slot) {
            case 0 -> ItemCreator.of(CompMaterial.KNOWLEDGE_BOOK, "&bInformation Book")
                        .lore("&7(&b&l❕&7) &bClick here &7to get all the &binformation &7about &bMob Gear&7!")
                        .make();
            case 11 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_HELMET,
                        "&cLegendary Chicken Helmet", firstLore, effectLore, priceLore)).make();
            case 12 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_CHESTPLATE,
                        "&cLegendary Chicken Chestplate", firstLore, effectLore, priceLore)).make();
            case 14 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_LEGGINGS,
                        "&cLegendary Chicken Leggings", firstLore, effectLore, priceLore)).make();
            case 15 -> createItemStack(ItemCreator.of(CompMaterial.LEATHER_BOOTS,
                        "&cLegendary Chicken Boots", firstLore, effectLore, priceLore)).make();
            default -> null;
        };
    }

    @Override
    protected void onMenuClick(Player player, int slot, ItemStack clicked) {
        if (slot == EXIT_SLOT) {
            player.sendMessage(ChatUtil.color("&7(&c&l❕&7) &7You &cclosed &7the forge menu."));
            player.closeInventory();
            return;
        }

        String nameHead = "&cEnhanced Chicken Head";
        String loreHead = "&7Use this &bEnhanced &7head to craft &bChicken Gear&7!";
        String url = "http://textures.minecraft.net/texture/598f6f7e0d2dd1d68e9fa0a486c6a22fef481dca7c281d554cf600836a03187f";
        String firstLore = "\n&7(&b&l❕&7) &7This &bunique piece &7of &bMob Gear &7is forged\n with the &bskulls &7of an &bChicken&7!\n";
        String hexColor = "#C26162";

        HashMap<Integer, String> armorPieceMap = new HashMap<>();
        armorPieceMap.put(11, "Legendary Chicken Helmet");
        armorPieceMap.put(12, "Legendary Chicken Chestplate");
        armorPieceMap.put(14, "Legendary Chicken Leggings");
        armorPieceMap.put(15, "Legendary Chicken Boots");

        if (armorPieceMap.containsKey(slot)) {
            String armorPiece = armorPieceMap.get(slot);
            new ForgeMaceMenu(firstLore, effectLore, url, armorPiece, nameHead, loreHead, hexColor).displayTo(player);
        }
    }

    private ItemCreator createItemStack(ItemCreator itemCreator) {
        itemCreator.enchant(Enchantment.PROTECTION, 10)
                .unbreakable(true).glow(false).hideTags(false)
                .color(CompColor.fromName("#C26162"));
        return itemCreator;
    }
}
