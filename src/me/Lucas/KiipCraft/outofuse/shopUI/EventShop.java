/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.outofuse.shopUI;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Set;

import static me.Lucas.KiipCraft.outofuse.utils.ShopItems.*;
import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.Material.*;

public class EventShop {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static Main plugin;
    private static Set<String> demoKiipPakje1 = new HashSet<>();
    private static Set<String> demoKiipPakje2 = new HashSet<>();

    private static Set<String> heiligKiipPakje1 = new HashSet<>();
    private static Set<String> heiligKiipPakje2 = new HashSet<>();

    public EventShop(Main plugin) {
        EventShop.plugin = plugin;
    }


    public static void initialize() {
        inventory_name = Utils.chat("&c&lKiipCraft &3&lEvent Shop");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory eventShop(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        if (ShopAdmin.adminShop.contains(p.getName())) {
            for (int i = 1; i < 10; i++) {
                Utils.createItem(inv, RED_STAINED_GLASS_PANE, 1, i, " ");
            }

            for (int j = 19; j < 22 + 1; j++) {
                Utils.createItem(inv, RED_STAINED_GLASS_PANE, 1, j, " ");
            }
            for (int k = 24; k < inv_rows + 1; k++) {
                Utils.createItem(inv, RED_STAINED_GLASS_PANE, 1, k, " ");
            }

            Utils.createItem(inv, RED_STAINED_GLASS_PANE, 1, 10, " ");
            Utils.createItem(inv, RED_STAINED_GLASS_PANE, 1, 18, " ");
        } else {
            for (int i = 1; i < 10; i++) {
                Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
            }

            for (int j = 19; j < 22 + 1; j++) {
                Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
            }
            for (int k = 24; k < inv_rows + 1; k++) {
                Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
            }

            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        }


        if (!(ShopAdmin.adminShop.contains(p.getName()))) {
            Utils.createItemLore(inv, EGG, 1, 11, "&6&lHeilige Kiippen Pakje", "&7Geeft je het Heilige Kiippen Pakje", " ", "&5Kost &6&l5 KiipEuro's");
            Utils.createItemLore(inv, EGG, 1, 12, "&4&lDemonische Kiippen Pakje", "&7Geeft je het Demonische Kiippen Pakje", " ", "&5Kost &6&l5 KiipEuro's");
        } else {
            Utils.createItemLore(inv, EGG, 1, 11, "&6&lHeilige Kiippen Pakje", "&7Geeft je het Heilige Kiippen Pakje", " ", "&5Klik om te spawnen");
            Utils.createItemLore(inv, EGG, 1, 12, "&4&lDemonische Kiippen Pakje", "&7Geeft je het Demonische Kiippen Pakje", " ", "&5Klik om te spawnen");
        }

        Utils.createItemLore(inv, PUMPKIN, 1, 14, "&6&lHalloween Pakjes", "&7Opent de Shop met alle Halloween pakjes.");
        Utils.createItemLore(inv, SNOWBALL, 1, 15, "&6&lKerst Pakjes", "&7Opent de shop met alle Kesrt pakjes.");

        if (p.hasPermission("kiipcraft.admin") || p.getName().equals("Thunderkookie15")) {
            Utils.createItemLore(inv, COMMAND_BLOCK, 1, 17, "&6&lAdmin Panel", "&7Opent de Shop Admin");
        } else if (inv.contains(COMMAND_BLOCK)) {
            inv.remove(COMMAND_BLOCK);
        }

        Utils.createItem(inv, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        BukkitScheduler scheduler = getScheduler();

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lHeilige Kiippen Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(kiipHelm(), kiipChest(), kiipLegs(), kiipBoots());
            } else {
                if (!(heiligKiipPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (heiligKiipPakje1.contains(p.getName())) {
                    heiligKiipPakje2.add(p.getName());
                }

                if (heiligKiipPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipEuroCost(), 5)) {
                        p.getInventory().removeItem(kiipEuroCost());

                        p.getInventory().addItem(kiipHelm(), kiipChest(), kiipLegs(), kiipBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipEuro's§7!");
                        p.closeInventory();
                    }
                    heiligKiipPakje1.remove(p.getName());
                    heiligKiipPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                heiligKiipPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> heiligKiipPakje1.remove(p.getName()), 20 * 10);
            }
        }
        // Demonische Pakje
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&4&lDemonische Kiippen Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(demoKiipHelm(), demoKiipChest(), demoKiipLegs(), demoKiipBoots());
            } else {
                if (!(demoKiipPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (demoKiipPakje1.contains(p.getName())) {
                    demoKiipPakje2.add(p.getName());
                }

                if (demoKiipPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipEuroCost(), 5)) {
                        p.getInventory().removeItem(kiipEuroCost());

                        p.getInventory().addItem(demoKiipHelm(), demoKiipChest(), demoKiipLegs(), demoKiipBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipEuro's§7!");
                        p.closeInventory();
                    }
                    demoKiipPakje1.remove(p.getName());
                    demoKiipPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                demoKiipPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> demoKiipPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lKerst Pakjes"))) {
            p.sendMessage(prefix + "Je opent de §6§lKerst Shop§7...");
            p.openInventory(KerstShop.kerstShop(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lAdmin Panel"))) {
            p.sendMessage(prefix + "Je opent de §6§lAdmin Panel§7...");
            p.openInventory(ShopAdmin.shopAdmin(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lHalloween Pakjes"))) {
            p.sendMessage(prefix + "Je opent de §6§lHalloween Shop§7...");
            p.openInventory(HalloweenShop.halloweenShop(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }
}
