/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.events.listener.SpleefSelections;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class SpleefUI {

    public static Inventory invSpleef;

    private static ConfigManager cfgm = ConfigManager.getManager();
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static boolean schepGegeven = false;

    private static Set<Player> spleefPlayers = new HashSet<>();

    public static void initialize() {
        inventory_name = Utils.chat("&6&lSpleef Controls");

        invSpleef = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory spleefGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invSpleef, Material.BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invSpleef, Material.BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invSpleef, Material.BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invSpleef, Material.BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invSpleef, Material.BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        Utils.createItemLore(invSpleef, Material.RED_WOOL, 1, 11, "&c&lReset SpleefArea", "&7Reset de Spleef Area!");

        if (!schepGegeven) {
            Utils.createItemLore(invSpleef, Material.DIAMOND_SHOVEL, 1, 12, "&b&lSpleef Schep", "&7Geeft iedereen een SpleefSchep!");
        } else {
            Utils.createItemLore(invSpleef, Material.DIAMOND_SHOVEL, 1, 12, "&b&lSpleef Schep", "&7Verwijderd de SpleefSchep uit iedereen zijn Inventory!");
        }

        Utils.createItemLore(invSpleef, Material.COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar Spleef!");

        Utils.createItem(invSpleef, Material.ARROW, 1, 22, "Terug");
        Utils.createItem(invSpleef, Material.BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(invSpleef.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&b&lSpleef Schep"))) {
            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.events")) {
                    if (!schepGegeven) {
                        plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d&l%s&7 heeft iedereen een &b&lSpleef Schep &7gegeven!", p.getName())));
                    } else {
                        plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d&l%s&7 heeft iedereen zijn &b&lSpleef Schep &7afgenomen!", p.getName())));
                    }
                }
            }

            if (!schepGegeven) {
                for (Entity e : p.getNearbyEntities(50, 50, 50)) {
                    if (e instanceof Player) {
                        Player plr = ((Player) e).getPlayer();

                        plr.getInventory().addItem(Utils.spleefSchep());
                        spleefPlayers.add(plr);
                    }
                }
                schepGegeven = true;
                p.openInventory(spleefGUI(p));
                return;
            } else {
                for (Player plr : spleefPlayers) {
                    if (plr.getInventory().contains(Utils.spleefSchep())) {
                        plr.getInventory().remove(Utils.spleefSchep());
                        spleefPlayers.remove(plr);
                    }
                }
                schepGegeven = false;
                p.openInventory(spleefGUI(p));
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lReset SpleefArea"))) {
            SpleefSelections.regenerateCircle(p);

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.events")) {
                    plr.sendMessage(Utils.prefix + Utils.chat(String.format("De &cSpleef Area &7is gereset door &d&l%s&7!", p.getName())));
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            ConfigurationSection cs = cfgm.getEventsCFG().getConfigurationSection("Events.Spleef.Teleport");

            assert cs != null;
            p.teleport(new Location(p.getWorld(), cs.getDouble("X"), cs.getDouble("Y"), cs.getDouble("Z")));
            p.sendMessage(Utils.prefix + Utils.chat("Je teleporteert naar de &cSpleef&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }

    }
}
