/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.dispatchCommand;
import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Material.*;

public class AnvilDropUI {

    public static Inventory invAnvilDrop;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;

    public static void initialize() {
        inventory_name = Utils.chat("&6&lAnvil Drop Controls");

        invAnvilDrop = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory anvilDropGUI(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invAnvilDrop, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invAnvilDrop, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invAnvilDrop, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invAnvilDrop, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invAnvilDrop, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        Utils.createItemLore(invAnvilDrop, COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar Anvil Drop");

        Utils.createItemLore(invAnvilDrop, GREEN_WOOL, 1, 11, "&2&lStart", "&7Start de anvil drop");
        Utils.createItemLore(invAnvilDrop, RED_WOOL, 1, 12, "&c&lStop", "&7Stopt de anvil drop");

        Utils.createItem(invAnvilDrop, ARROW, 1, 22, "Terug");
        Utils.createItem(invAnvilDrop, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(invAnvilDrop.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            p.teleport(new Location(Bukkit.getWorld("Survival"), -1401, 181, 3255));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&2&lStart"))) {
            dispatchCommand(getConsoleSender(), "clone -1350 51 -3327 -1374 56 -3303 -1412 199 -3281 replace");
            p.closeInventory();

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lAnvil Drop §agestart§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lStop"))) {
            dispatchCommand(getConsoleSender(), "clone -1318 45 -3327 -1294 50 -3303 -1412 199 -3281");
            dispatchCommand(getConsoleSender(), "fill -1411 180 -3281 -1389 187 -3259 air replace chipped_anvil");
            p.closeInventory();

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lAnvil Drop §cgestopt§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase((Utils.chat("&cSluiten")))) {
            p.closeInventory();
        }
    }
}
