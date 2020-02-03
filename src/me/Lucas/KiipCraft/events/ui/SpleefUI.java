package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.*;

public class SpleefUI {

    public static Inventory invSpleef;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static boolean schepGegeven = false;

    public static void initialize() {
        inventory_name = Utils.chat("&6&lSpleef Controls");

        invSpleef = createInventory(null, inv_rows);
    }

    public static Inventory spleefGUI(Player p) {
        Inventory toReturn = createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invSpleef, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invSpleef, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invSpleef, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invSpleef, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invSpleef, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        Utils.createItemLore(invSpleef, GREEN_WOOL, 1, 11, "&2&lStart", "&7Start het Spleef Event!");
        Utils.createItemLore(invSpleef, RED_WOOL, 1, 12, "&c&lStop", "&7Stopt het Spleef Event!");

        if (!schepGegeven) {
            Utils.createItemLore(invSpleef, DIAMOND_SHOVEL, 1, 13, "&b&lSpleef Schep", "&7Geeft iedereen een SpleefSchep!");
        } else {
            Utils.createItemLore(invSpleef, DIAMOND_SHOVEL, 1, 13, "&b&lSpleef Schep", "&7Verwijderd de SpleefSchep uit iedereen zijn Inventory!");
        }

        Utils.createItemLore(invSpleef, COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar Spleef!");

        Utils.createItem(invSpleef, ARROW, 1, 22, "Terug");
        Utils.createItem(invSpleef, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(invSpleef.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&b&lSpleef Schep"))) {
            if (!schepGegeven) {
                p.sendMessage(Utils.chat(prefix + "Je hebt iedereen bij de &6&lSpleef&7 een &b&lSpleefSchep&7 gegeven!"));
            } else {
                p.sendMessage(Utils.chat(prefix + "Je hebt iedereen bij de &6&lSpleef&7 zijn &b&lSpleefSchep&7 afgenomen!"));
            }

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    if (!schepGegeven) {
                        plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft iedereen bij de §6§lSpleef §7een §b§lSpleefSchep §7gegeven!");
                    } else {
                        plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft iedereen bij de §6§lSpleef §7zijn §b§lSpleefSchep §7afgenomen!");
                    }
                }
            }

            int i = 0;
            Collection<? extends Player> totalPlayers = getOnlinePlayers();
            for (Player plr : getOnlinePlayers()) {

                if (!schepGegeven) {
                    plr.getInventory().addItem(Utils.spleefSchep());
                    i++;
                    if (i == totalPlayers.size()) {
                        schepGegeven = true;
                        p.openInventory(spleefGUI(p));
                        return;
                    }

                } else {
                    if (plr.getInventory().contains(Utils.spleefSchep())) {
                        plr.getInventory().removeItem(Utils.spleefSchep());
                    }
                    i++;
                    if (i == totalPlayers.size()) {
                        schepGegeven = false;
                        p.openInventory(spleefGUI(p));
                        return;
                    }
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&2&lStart"))) {
            p.sendMessage(prefix + "Je hebt het §6§lSpleef Event §agestart§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival spleef1 block-break allow");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft het §6§lSpleef Event §agestart§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lStop"))) {
            p.sendMessage(prefix + "Je hebt het §6§lSpleef Event §cgestopt§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival spleef1 block-break deny");
            dispatchCommand(getConsoleSender(), "clone -1375 44 -3332 -1352 44 -3309 -1408 175 -3227");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft het §6§lSpleef Event §cgestopt§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            p.teleport(new Location(getWorld("Survival"), -1411, 177, -3216));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }

    }
}
