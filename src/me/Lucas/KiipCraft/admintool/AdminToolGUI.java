package me.Lucas.KiipCraft.admintool;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.ui.MainEventsUI;
import me.Lucas.KiipCraft.servertour.ServerTourRequestsGUI;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Material.*;

public class AdminToolGUI {

    public static Inventory inv;
    public static String inv_name;
    private static Main plugin;
    private static int row_now = 4;
    public static int inv_rows = row_now * 9;

    public AdminToolGUI(Main plugin) {
        AdminToolGUI.plugin = plugin;
    }


    public static void initialize() {
        inv_name = Utils.chat("&4&lAdmintool Menu");


        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory adminToolGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int i = 1; i < 10; i++) {
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

        Utils.createItemLore(inv, FIREWORK_ROCKET, 1, 11, "&6&lEvents Menu", "&7Opent het Events Menu!");
        Utils.createItemLore(inv, WRITABLE_BOOK, 1, 12, "&6&lServer Tour Menu", "&7Opent het Servertour menu!");
        Utils.createItemHead(inv, adminHead(p), 1, 13, "&6&lSpelers Menu", "&7Opent een lijst met alle Online Spelers!");
        Utils.createItemLore(inv, PRISMARINE_SHARD, 1, 14, "&c&lShards", "&7Opent het Menu met alle Shards!");

        Utils.createItem(inv, BARRIER, 1, 32, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lEvents Menu"))) {
            p.sendMessage(prefix + Utils.chat("Je opent het &6&lEvents Menu&7!"));
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lServer Tour Menu"))) {
            p.sendMessage(prefix + Utils.chat("Je opent het &6&lServertour Menu&7!"));
            p.openInventory(ServerTourRequestsGUI.serverTourUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lSpelers Menu"))) {
            p.sendMessage(prefix + Utils.chat("Je opent het &6&lSpelers Menu&7!"));
            p.openInventory(AdminToolPlayersGUI.spelersMenu(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShards"))) {
            p.sendMessage(prefix + Utils.chat("Je opent het &c&lShards Menu&7!"));
            p.openInventory(AdminToolShardsMenu.shardsMenu(p));
        }


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }

    private static ItemStack adminHead(Player p) {
        ItemStack adminHead = new ItemStack(PLAYER_HEAD);
        SkullMeta adminHeadM = (SkullMeta) adminHead.getItemMeta();

        assert adminHeadM != null;
        adminHeadM.setOwningPlayer(p);
        adminHead.setItemMeta(adminHeadM);
        return adminHead;
    }
}
