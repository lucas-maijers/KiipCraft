/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.*;

public class BuildBattleUI {

    public static Inventory invBuildBattle;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static boolean bouwen = false;
    private static boolean muur = true;

    public static void initialize() {
        inventory_name = Utils.chat("&6&lBuild Battle Controls");

        invBuildBattle = createInventory(null, inv_rows);
    }

    public static Inventory buildBattleGUI(Player p) {
        Inventory toReturn = createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invBuildBattle, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invBuildBattle, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invBuildBattle, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invBuildBattle, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invBuildBattle, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        if (!muur) {
            Utils.createItemLore(invBuildBattle, BRICKS, 1, 11, "&a&lPlaats muur", "&7Plaatst de muur bij Build Battle!", "  ", "&7We Will Build A Wall!");
        } else {
            Utils.createItemLore(invBuildBattle, IRON_PICKAXE, 1, 11, "&c&lVerwijder muur", "&7Verwijderd de muur bij Build Battle!");
        }

        Utils.createItemLore(invBuildBattle, TNT, 1, 12, "&6&lReset bouwgebied", "&7Reset het bouwgebied weer naar een vlakke status!");

        Utils.createItemLore(invBuildBattle, COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar Build Battle");

        Utils.createItem(invBuildBattle, ARROW, 1, 22, "Terug");
        Utils.createItem(invBuildBattle, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(invBuildBattle.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lVerwijder muur"))) {
            muur = false;
            p.openInventory(BuildBattleUI.buildBattleGUI(p));
        }


        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lPlaats muur"))) {
            dispatchCommand(getConsoleSender(), "clone -1389 51 -3403 -1389 35 -3379 -1382 167 -3335 replace force");
            muur = true;
            p.openInventory(BuildBattleUI.buildBattleGUI(p));

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lMuur§7 bij Build Battle §ageplaatst§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lReset bouwgebied"))) {
            dispatchCommand(getConsoleSender(), "clone -1405 36 -3360 -1379 50 -3335 -1395 166 -3335 replace");

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft het §6§lBouwgebied §cgereset§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {

        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.sendMessage(prefix + "Je keert terug naar de §3§lEvents Admin§f...");
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
