/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.events.listener.GeluksGraversSelections;
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

public class GeluksGraversUI {

    public static Inventory inv;

    private static ConfigManager cfgm = ConfigManager.getManager();
    public static String inventory_name;

    private static boolean toolsGegeven = false;

    private static Set<Player> geluksGraversPlayers = new HashSet<>();
    public static int inv_rows = 3 * 9;

    public static void initialize() {
        inventory_name = Utils.chat("&6&lGeluks Gravers Controls");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory geluksGraversGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, Material.BLACK_STAINED_GLASS_PANE, 1, 18, " ");


        if (!toolsGegeven) {
            Utils.createItemLore(inv, Material.IRON_PICKAXE, 1, 11, "&6&lTools", "&7Geeft iedereen bij de Geluks Gravers de Tools!");
        } else {
            Utils.createItemLore(inv, Material.IRON_PICKAXE, 1, 11, "&6&lTools", "&7Neemt de Geluks Gravers Tools af bij iedereen!");
        }
        Utils.createItemLore(inv, Material.BRICKS, 1, 12, "&6&lReset Pilaren", "&7Reset de pilaren van de Geluks Gravers");

        Utils.createItemLore(inv, Material.COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar Geluks Gravers");

        Utils.createItem(inv, Material.ARROW, 1, 22, "Terug");
        Utils.createItem(inv, Material.BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lReset Pilaren"))) {
            GeluksGraversSelections.resetPillars(p);

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.events")) {
                    plr.sendMessage(Utils.prefix + Utils.chat(String.format("De &cGeluks Gravers Pilaren&7 zijn gereset door &d%s&7!", p.getName())));
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTools"))) {
            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.events")) {
                    if (!toolsGegeven) {
                        plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d%s&7 heeft iedereen de &b&lGeluks Gravers Tools &7gegeven!", p.getName())));
                    } else {
                        plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d%s&7 heeft iedereen de &b&lGeluks Gravers Tools &7afgenomen!", p.getName())));
                    }
                }
            }

            if (!toolsGegeven) {
                for (Entity e : p.getNearbyEntities(50, 50, 50)) {
                    if (e instanceof Player) {
                        Player plr = ((Player) e).getPlayer();

                        plr.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                        plr.getInventory().addItem(new ItemStack(Material.IRON_SHOVEL));
                        plr.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                        geluksGraversPlayers.add(plr);
                    }
                }
                toolsGegeven = true;
                p.openInventory(geluksGraversGUI(p));
                return;
            } else {
                for (Player plr : geluksGraversPlayers) {
                    plr.getInventory().remove(Material.IRON_PICKAXE);
                    plr.getInventory().remove(Material.IRON_SHOVEL);
                    plr.getInventory().remove(Material.IRON_AXE);
                    geluksGraversPlayers.remove(plr);
                }
                toolsGegeven = false;
                p.openInventory(geluksGraversGUI(p));
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            ConfigurationSection cs = cfgm.getEventsCFG().getConfigurationSection("Events.BuildBattle.Teleport");

            assert cs != null;
            p.teleport(new Location(p.getWorld(), cs.getDouble("X"), cs.getDouble("Y"), cs.getDouble("Z")));
            p.sendMessage(Utils.prefix + Utils.chat("Je teleporteert naar &cGeluks Gravers&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase((Utils.chat("&cSluiten")))) {
            p.closeInventory();
        }
    }
}
