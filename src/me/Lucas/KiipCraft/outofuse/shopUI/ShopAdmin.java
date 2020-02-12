/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.outofuse.shopUI;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Material.*;

public class ShopAdmin {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;

    public static Set<String> adminShop = new HashSet<>();

    public static void initialize() {
        inventory_name = Utils.chat("&c&lKiipCraft &3&lShop Admin Panel");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory shopAdmin(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        Utils.createItemLore(inv, IRON_NUGGET, 1, 11, "&6&l&oKiipEuro", "&7Geeft je &6&lKiipEuro's&7!");
        Utils.createItemLore(inv, IRON_NUGGET, 1, 12, "&6&l&oKiipDollar", "&7Geeft je &6&lKiipDollars&7!");
        Utils.createItemLore(inv, IRON_NUGGET, 1, 13, "&6&l&oKiipse Frank", "&7Geeft je &6&lKiipse Frank&7!");

        if (!(ShopAdmin.adminShop.contains(p.getName()))) {
            Utils.createItemLore(inv, CHAIN_COMMAND_BLOCK, 1, 17, "&a&lAdmin Mode", "&7Zet de Shop Admin Mode aan.", "&7Je hoeft dan geen KiipEuro's te betalen om pakjes te kopen.");
        } else {
            Utils.createItemLore(inv, COMMAND_BLOCK, 1, 17, "&c&lAdmin Mode", "&7Zet de Shop Admin Mode Uit", "&7Je moet dan wel weer KiipEuro's betalen om pakjes te krijgen.");
        }

        Utils.createItem(inv, ARROW, 1, 22, "Terug");
        Utils.createItem(inv, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&l&oKiipEuro"))) {
            p.sendMessage(prefix + "Je hebt §6§lKiipEuro's §7ingespawned!");
            p.getInventory().addItem(Utils.kiipEuroAdmin());
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&l&oKiipDollar"))) {
            p.sendMessage(prefix + "Je hebt §6§lKiipDollars §7ingespawned!");
            p.getInventory().addItem(Utils.kiipDollar());
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&l&oKiipse Frank"))) {
            p.sendMessage(prefix + "Je hebt §6§lKiipse Franken §7ingespawned!");
            p.getInventory().addItem(Utils.kiipFrank());
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lAdmin Mode"))) {
            adminShop.add(p.getName());
            p.sendMessage(prefix + "Je hebt §6§lAdmin Mode §aaangezet§7, je kan nu alles in de §3§lEvent Shop§7 gratis verkrijgen!");
            p.openInventory(ShopAdmin.shopAdmin(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.admin") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §6§lAdmin Mode §aaangezet§f!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lAdmin Mode"))) {
            adminShop.remove(p.getName());
            p.sendMessage(prefix + "Je hebt §6§lAdmin Mode §cuitgezet§7, je moet nu weer betalen om pakjes te verkrijgen!");
            p.openInventory(ShopAdmin.shopAdmin(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.admin") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §6§lAdmin Mode §cuitgezet§f!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.sendMessage(prefix + "Je keert terug naar de §3§lEvents Shop§7...");
            p.openInventory(EventShop.eventShop(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
