package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.*;

public class TheQuizUI {

    public static Inventory invQuiz;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static Main plugin;

    public TheQuizUI(Main plugin) {
        TheQuizUI.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = Utils.chat("&6&lThe Quiz Controls");

        invQuiz = createInventory(null, inv_rows);
    }

    public static Inventory theQuizUI(Player p) {
        Inventory toReturn = createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invQuiz, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invQuiz, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invQuiz, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invQuiz, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invQuiz, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        Utils.createItemLore(invQuiz, BLUE_WOOL, 1, 11, "&1&lBlauw", "&7Al is &1Blauw&7 het juiste antwoord!");

        Utils.createItemLore(invQuiz, RED_WOOL, 1, 12, "&c&lRood", "&7Al is &cRood&7 het juist antwoord!");

        Utils.createItemLore(invQuiz, COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar The Quiz!");

        Utils.createItem(invQuiz, ARROW, 1, 22, "Terug");
        Utils.createItem(invQuiz, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(invQuiz.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        BukkitScheduler scheduler = getServer().getScheduler();

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&1&lBlauw"))) {
            dispatchCommand(getConsoleSender(), "fill -1525 157 -3296 -1550 157 -3271 air replace red_wool");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §c§lRood §7weggehaald bij §6§lThe Quiz§7!");
                }
            }

            scheduler.scheduleSyncDelayedTask(plugin, () -> dispatchCommand(getConsoleSender(), "fill -1525 157 -3296 -1550 157 -3271 red_wool replace air"), 100);
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lRood"))) {
            dispatchCommand(getConsoleSender(), "fill -1549 157 -3273 -1524 157 -3284 air replace blue_wool");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §1§lBlauw §fweggehaald bij §6§lThe Quiz§7!");
                }
            }

            scheduler.scheduleSyncDelayedTask(plugin, () -> dispatchCommand(getConsoleSender(), "fill -1549 157 -3273 -1524 157 -3284 blue_wool replace air"), 100);
        }


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            p.teleport(new Location(getWorld("Survival"), -1554, 158, -3281));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
