package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Material.*;


public class MainEventsUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 4 * 9;
    public static int singleinvrow = 10;

    public static void initialize() {
        inventory_name = Utils.chat("&3&lEvents GUI");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory mainGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        // Glass
        for (int i = 1; i < singleinvrow; i++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 28; j < 32; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }

        for (int k = 33; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 19, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 27, " ");

        // AnvilDrop
        Utils.createItemLore(inv, ANVIL, 1, 11, "&6&lAnvil Drop", "&7Anvil Drop Controls!");

        // Colloseum
        Utils.createItemLore(inv, DIAMOND_SWORD, 1, 12, "&6&lColosseum", "&7Colosseum Controls!");

        // Spleef
        Utils.createItemLore(inv, DIAMOND_SHOVEL, 1, 13, "&6&lSpleef", "&7Spleef Controls!");

        // The Quiz
        Utils.createItemLore(inv, WRITABLE_BOOK, 1, 14, "&6&lThe Quiz", "&7The Quiz Controls!");

        // Vloer is Lava
        Utils.createItemLore(inv, LAVA_BUCKET, 1, 15, "&6&lDe vloer is lava", "&7De vloer is lava Controls!");

        // Build Battle
        Utils.createItemLore(inv, CRAFTING_TABLE, 1, 16, "&6&lBuild Battle", "&7Build Battle Controls!");

        // Close GUI
        Utils.createItem(inv, BARRIER, 1, 32, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        // Anvil Drop
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lAnvil Drop"))) {
            p.sendMessage(prefix + "Je opent de §6§lAnvil Drop Controls§7...");
            p.openInventory(AnvilDropUI.anvilDropGUI(p));
        }
        // Colosseum
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lColosseum"))) {
            p.sendMessage(prefix + "Je opent de §6§lColosseum Controls§7...");
            p.openInventory(ColosseumUI.colosseumGUI(p));
        }
        // Spleef
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lSpleef"))) {
            p.sendMessage(prefix + "Je opent de §6§lSpleef Controls§7...");
            p.openInventory((SpleefUI.spleefGUI(p)));
        }
        // The Quiz
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lThe Quiz"))) {
            p.sendMessage(prefix + "Je opent de §6§lQuiz Controls§7...");
            p.openInventory(TheQuizUI.theQuizUI(p));
        }
        // Vloer is lava
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lDe vloer is lava"))) {
            p.sendMessage(prefix + "Je opent de §6§lVloer is Lava Controls§7...");
            p.openInventory(VloerIsLavaUI.vloerislavaGUI(p));
        }
        // Build Battle
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lBuild Battle"))) {
            p.sendMessage(prefix + "Je opent de §6§lBuild Battle Controls§7...");
            p.openInventory(BuildBattleUI.buildBattleGUI(p));
        }

        // Close GUI
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
