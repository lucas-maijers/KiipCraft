package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.*;

public class VloerIsLavaUI {

    public static Inventory invLava;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static Main plugin;
    private static boolean chunks = false;
    private static boolean pvp = false;
    private static int timer = 5;

    private static int countdown = 5;

    public VloerIsLavaUI(Main plugin) {
        VloerIsLavaUI.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = Utils.chat("&6&lVloer is Lava Controls");

        invLava = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory vloerislavaGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invLava, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invLava, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invLava, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invLava, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invLava, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        if (!pvp) {
            Utils.createItemLore(invLava, DIAMOND_SWORD, 1, 11, "&a&lPvP aan", "&7Zet de Vloer Is Lava PvP aan!");
        } else {
            Utils.createItemLore(invLava, SHIELD, 1, 11, "&c&lPvP uit", "&7Zet de Vloer Is Lava PvP uit!");
        }

        if (!chunks) {
            Utils.createItemLore(invLava, GREEN_WOOL, 1, 12, "&a&lChunks aan", "&7Zet Chunks op spawn aan!");
        } else {
            Utils.createItemLore(invLava, RED_WOOL, 1, 12, "&c&lChunks uit", "&7Zet Chunks op spawn uit!");
        }

        Utils.createItemLore(invLava, LAVA_BUCKET, 1, 13, "&6&lPlaats Lava", "&7Plaatst de Lava");

        Utils.createItemLore(invLava, RED_CONCRETE, 1, 14, "&4&lStop Event", "&7ALLEEN OP KLIKKEN INDIEN HET EVENT BUGGED");

        Utils.createItemLore(invLava, COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar De Vloer Is Lava");

        Utils.createItem(invLava, ARROW, 1, 22, "Terug");
        Utils.createItem(invLava, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(invLava.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        BukkitScheduler scheduler = getScheduler();

        // PvP
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lPvP aan"))) {
            dispatchCommand(getConsoleSender(), "rg flag -w Survival pvp pvp allow");
            pvp = true;
            p.openInventory(VloerIsLavaUI.vloerislavaGUI(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lVloer Is Lava PvP §aaangezet§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lPvP uit"))) {
            dispatchCommand(getConsoleSender(), "rg flag -w Survival pvp pvp deny");
            pvp = false;
            p.openInventory(VloerIsLavaUI.vloerislavaGUI(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lVloer Is Lava PvP §cuitgezet§7!");
                }
            }
        }
        /*                                               */

        // Chunks
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lChunks aan"))) {
            dispatchCommand(getConsoleSender(), "rg flag -w Survival spawn chunk-unload deny");
            chunks = true;
            p.openInventory(VloerIsLavaUI.vloerislavaGUI(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lChunks §aaangezet§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lChunks uit"))) {
            dispatchCommand(getConsoleSender(), "rg flag -w Survival spawn chunk-unload allow");
            chunks = false;
            p.openInventory(VloerIsLavaUI.vloerislavaGUI(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lChunks §cuitgezet§7!");
                }
            }
        }
        /*                                               */

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lPlaats Lava"))) {
            int randomRotsen = (int) Math.floor(Math.random() * 3) + 1;

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de Vloer van §6§lLava §7gemaakt!");
                }
            }

            countdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                if (timer == 5) {
                    for (Player plr : getOnlinePlayers()) {
                        plr.sendMessage("§6§lDe vloer is lava!");
                    }
                }

                for (Player plr : Bukkit.getOnlinePlayers()) {
                    plr.sendMessage("§6§l" + timer);
                }

                if (timer == 3) {
                    switch (randomRotsen) {
                        case 1:
                            dispatchCommand(getConsoleSender(), "clone -1421 35 -3407 -1400 37 -3380 -1577 192 -3241 masked normal");
                            break;
                        case 2:
                            dispatchCommand(getConsoleSender(), "clone -1415 41 -3437 -1394 43 -3410 -1577 192 -3241 masked normal");
                            break;
                        case 3:
                            dispatchCommand(getConsoleSender(), "clone -1421 50 -3408 -1400 52 -3381 -1577 192 -3241 masked normal");
                            break;
                    }
                }

                if (timer == 0) {
                    dispatchCommand(getConsoleSender(), "fill -1556 193 -3242 -1578 194 -3215 lava replace air");
                    countdown = 5;
                    timer = 5;
                    getScheduler().cancelTasks(plugin);
                    scheduler.scheduleSyncDelayedTask(plugin, () -> {
                        dispatchCommand(getConsoleSender(), "fill -1556 193 -3242 -1578 194 -3215 air replace lava");
                        dispatchCommand(getConsoleSender(), "clone -1416 43 -3399 -1394 45 -3374 -1578 192 -3242 replace normal");
                    }, 20 * 15);
                } else {
                    timer--;
                    countdown--;
                }
            }, 0L, 20L);
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&4&lStop Event"))) {
            getScheduler().cancelTasks(plugin);
            timer = 5;
            countdown = 5;
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            p.teleport(new Location(Bukkit.getWorld("Survival"), -1553, 198, -3226));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.sendMessage(prefix + "Je keert terug naar de §3§lEvents Admin§7...");
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
