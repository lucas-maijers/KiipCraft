/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.events.listener.TriathlonSelections;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public class TriathlonUI {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    public static boolean areHorsesSpawned = false;
    private static ConfigManager cfgm = ConfigManager.getManager();
    private static boolean elytraGiven = false;

    private static Set<Player> triathlonPlayers = new HashSet<>();


    public static void initialize() {
        inventory_name = Utils.chat("&6&lTriathlon Controls");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory triathlonGUI(Player p) {
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

        Utils.createItemLore(inv, Material.ELYTRA, 1, 11, "&6&lElytras", "&7Geeft iedereen bij Triathlon een Elyta!");

        if (!areHorsesSpawned) {
            Utils.createItemLore(inv, Material.HORSE_SPAWN_EGG, 1, 12, "&6&lSpawn Paarden", "&7Spawnt een paard voor de Triathlon");
        } else {
            Utils.createItemLore(inv, Material.DIAMOND_SWORD, 1, 12, "&6&lKill Paarden", "&7Vermoord de paarden!");
        }

        Utils.createItemLore(inv, Material.COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar het Colosseum");


        Utils.createItem(inv, Material.ARROW, 1, 22, "Terug");
        Utils.createItem(inv, Material.BARRIER, 1, 23, "&cSluiten");


        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lElytras"))) {
            
            if (!elytraGiven) {
                for (Entity e : p.getNearbyEntities(50, 50, 50)) {
                    if (e instanceof Player) {
                        Player plr = ((Player) e).getPlayer();

                        assert plr != null;
                        plr.getInventory().setChestplate(elytra());
                        triathlonPlayers.add(plr);
                    }
                }

                for (Player plr : Bukkit.getOnlinePlayers()) {
                    if (plr.hasPermission("kiipcraft.events")) {
                        plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d%s &7heeft iedereen bij de Triathlon een Elytra gegeven!", p.getName())));
                    }
                }
                elytraGiven = true;
                p.openInventory(triathlonGUI(p));
                return;
            } else {
                for (Player plr : triathlonPlayers) {
                    plr.getInventory().setChestplate(new ItemStack(Material.AIR));
                    triathlonPlayers.remove(plr);
                }
                for (Player plr : Bukkit.getOnlinePlayers()) {
                    if (plr.hasPermission("kiipcraft.events")) {
                        plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d%s &7heeft iedereen bij de Triathlon zijn Elytra weggehaald!", p.getName())));
                    }
                }
                elytraGiven = false;
                p.openInventory(triathlonGUI(p));
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lSpawn Paarden"))) {
            p.sendMessage(Utils.prefix + Utils.chat("Typ in de chat hoeveel paarden je wilt spawnen."));

            TriathlonSelections.spawnHorse.add(p.getName());
            p.closeInventory();
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lKill Paarden"))) {
            TriathlonSelections.removeHorses(p);
            p.closeInventory();
        }


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            ConfigurationSection cs = cfgm.getEventsCFG().getConfigurationSection("Events.Triathlon.Teleport");

            assert cs != null;
            p.teleport(new Location(p.getWorld(), cs.getDouble("X"), cs.getDouble("Y"), cs.getDouble("Z")));
            p.sendMessage(Utils.prefix + Utils.chat("Je teleporteert naar de Triathlon"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.sendMessage(Utils.prefix + Utils.chat("Je keert terug naar het &3Events Menu&7!"));
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase((Utils.chat("&cSluiten")))) {
            p.closeInventory();
        }
    }

    private static ItemStack elytra() {
        ItemStack i = new ItemStack(Material.ELYTRA);
        ItemMeta meta = i.getItemMeta();

        assert meta != null;
        meta.setDisplayName(Utils.chat("&7[&cEVENTS&7] &3Elytra"));

        meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);

        i.setItemMeta(meta);
        return i;
    }
}
