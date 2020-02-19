/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.guis;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitScheduler;

import static me.Lucas.KiipCraft.admintool.guis.AdminToolPlayersGUI.plrData;
import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Material.*;

public class AdminToolPlayerSettings {

    public static Inventory inv;
    public static String inv_name;
    private static Main plugin;
    private static int row_now = 6;
    public static int inv_rows = row_now * 9;
    private static boolean hasClickedKill = false;
    private static boolean hasClickedClear = false;

    public AdminToolPlayerSettings(Main plugin) {
        AdminToolPlayerSettings.plugin = plugin;
    }

    public static void initialize() {
        inv_name = Utils.chat("&6&l" + plrData.getName() + " Settings");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory playerSettings(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);
        inv.clear();
        inv.setContents(plrData.getInventory().getContents());

        for (int i = 37; i < 46; i++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        Utils.createItemLore(inv, PAPER, 1, 46, "&4&lInformatie",
                "&6&lSpeler: &d" + plrData.getName(),
                " ",
                "&c&lHealth: &c" + plrData.getHealth(),
                "&6&lHunger: &6" + plrData.getFoodLevel(),
                "&a&lXP Level: &a" + plrData.getLevel(),
                " ",
                "&b&lX: &d" + plrData.getLocation().getX(),
                "&b&lY: &d" + plrData.getLocation().getY(),
                "&b&lZ: &d" + plrData.getLocation().getZ(),
                "&b&lWereld: &1" + plrData.getWorld().getName());

        Utils.createItemLore(inv, ENDER_CHEST, 1, 47, "&5&lEnder Chest", "&7Opent de Ender Chest van: &6" + plrData.getName() + "&7!");

        Utils.createItemLore(inv, RED_MUSHROOM, 1, 48, "&a&lHeal", "&7Zet de speler op vol HP en volle hunger balk!");
        Utils.createItemLore(inv, DIAMOND_SWORD, 1, 51, "&4&lKILL", "&7Vermoord de speler!", " ", "&4&lWAARSCHUWING: JE MOET 2x KLIKKEN!");
        Utils.createItemLore(inv, HOPPER, 1, 52, "&4&lCLEAR INVENTORY", "&7Cleart de inventaris van de speler", " ", "&4&lWAARSCHUWING: JE MOET 2x KLIKKEN!");

        Utils.createItemLore(inv, COMPASS, 1, 54, "&6&lTeleport", "&7Teleporteer naar de Speler!");


        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        BukkitScheduler scheduler = Bukkit.getScheduler();

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&5&lEnder Chest"))) {
            p.openInventory(plrData.getEnderChest());
            p.sendMessage(prefix + Utils.chat("Je opent de &5&lEnder Chest &7van &d" + plrData.getName() + "&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lHeal"))) {
            plrData.setHealth(20);
            plrData.setFoodLevel(20);
            plrData.setFireTicks(0);

            for (PotionEffect eff : plrData.getActivePotionEffects()) {
                plrData.removePotionEffect(eff.getType());
            }

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage")) {
                    plr.sendMessage(prefix + Utils.chat("&b&l" + p.getName() + "&7 heeft &d&l" + plrData.getName() + " &agenezen&7!"));
                }
            }
            p.openInventory(AdminToolPlayerSettings.playerSettings(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&4&lKILL"))) {
            if (!hasClickedKill) {
                p.sendMessage(prefix + Utils.chat("klik binnen &c&l5 seconden &7nog een keer als je &d " + plrData.getName() + " &7wilt &4doodmaken7!"));
            }

            if (hasClickedKill) {
                plrData.setHealth(0);

                for (Player plr : Bukkit.getOnlinePlayers()) {
                    if (plr.hasPermission("kiipcraft.infomessage")) {
                        plr.sendMessage(prefix + Utils.chat("&b&l" + p.getName() + "&7 heeft &d&l" + plrData.getName() + " &4gekilled&7!"));
                    }
                }
                scheduler.cancelTasks(plugin);
                hasClickedKill = false;
                return;
            }

            hasClickedKill = true;

            scheduler.scheduleSyncDelayedTask(plugin, () -> hasClickedKill = false, 20 * 5);
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&4&lCLEAR INVENTORY"))) {
            if (!hasClickedClear) {
                p.sendMessage(prefix + Utils.chat("klik binnen &c&l5 seconden &7nog een keer als je de inventaris van &d " + plrData.getName() + " &7wilt &4clearen&7!"));
            }

            if (hasClickedClear) {
                plrData.getInventory().clear();

                for (Player plr : Bukkit.getOnlinePlayers()) {
                    if (plr.hasPermission("kiipcraft.infomessage")) {
                        plr.sendMessage(prefix + Utils.chat("&b&l" + p.getName() + "&7 heeft &d&l" + plrData.getName() + " &7zijn inventaris &4gecleared&7!"));
                    }
                }
                scheduler.cancelTasks(plugin);
                p.openInventory(AdminToolPlayerSettings.playerSettings(p));
                hasClickedClear = false;
                return;
            }

            hasClickedClear = true;

            scheduler.scheduleSyncDelayedTask(plugin, () -> hasClickedClear = false, 20 * 5);
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            p.sendMessage(prefix + Utils.chat("Teleporteren naar &6&l" + plrData.getName() + "&7!"));
            p.teleport(plrData.getLocation());
        }
    }
}
