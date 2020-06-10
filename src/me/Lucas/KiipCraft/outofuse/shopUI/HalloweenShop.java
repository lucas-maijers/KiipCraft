/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.outofuse.shopUI;

import me.Lucas.KiipCraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Set;

import static me.Lucas.KiipCraft.outofuse.utils.ShopItems.*;
import static me.Lucas.KiipCraft.utils.Utils.*;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.Material.*;

public class HalloweenShop {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static Main plugin;
    private static final Set<String> witchPakje1 = new HashSet<>();
    private static final Set<String> witchPakje2 = new HashSet<>();

    private static final Set<String> vogelPakje1 = new HashSet<>();
    private static final Set<String> vogelPakje2 = new HashSet<>();

    private static final Set<String> skellyPakje1 = new HashSet<>();
    private static final Set<String> skellyPakje2 = new HashSet<>();

    private static final Set<String> zombiePakje1 = new HashSet<>();
    private static final Set<String> zombiePakje2 = new HashSet<>();

    public HalloweenShop(Main plugin) {
        HalloweenShop.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = chat("&c&lKiipCraft &3&lHalloween Shop");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory halloweenShop(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);

        if (ShopAdmin.adminShop.contains(p.getName())) {
            for (int i = 1; i < 10; i++) {
                createItem(inv, RED_STAINED_GLASS_PANE, 1, i, " ");
            }

            for (int j = 19; j < 21 + 1; j++) {
                createItem(inv, RED_STAINED_GLASS_PANE, 1, j, " ");
            }
            for (int k = 24; k < inv_rows + 1; k++) {
                createItem(inv, RED_STAINED_GLASS_PANE, 1, k, " ");
            }

            createItem(inv, RED_STAINED_GLASS_PANE, 1, 10, " ");
            createItem(inv, RED_STAINED_GLASS_PANE, 1, 18, " ");
        } else {
            for (int i = 1; i < 10; i++) {
                createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
            }

            for (int j = 19; j < 21 + 1; j++) {
                createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
            }
            for (int k = 24; k < inv_rows + 1; k++) {
                createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
            }

            createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
            createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        }

        if (!(ShopAdmin.adminShop.contains(p.getName()))) {
            createItemLore(inv, POTION, 1, 11, "&6&lWitch Pakje", "&7Geeft je het Heksen Pakje", " ", "&5Kost &6&l5 KiipDollars");
            createItemLore(inv, HAY_BLOCK, 1, 12, "&6&lVogelverschrikker Pakje", "&7Geeft je het Vogelverschrikker Pakje", " ", "&5Kost &6&l5 KiipDollars");
            createItemLore(inv, SKELETON_SKULL, 1, 13, "&6&lSkeleton Pakje", "&7Geeft je het Skeleton Pakje", " ", "&5Kost &6&l5 KiipDollars");
            createItemLore(inv, ZOMBIE_HEAD, 1, 14, "&6&lZombie Pakje", "&7Geeft je het Zombie Pakje", " ", "&5Kost &6&l5 KiipDollars");
        } else {
            createItemLore(inv, POTION, 1, 11, "&6&lWitch Pakje", "&7Geeft je het Heksen Pakje", " ", "&5Klik om te spawnen");
            createItemLore(inv, HAY_BLOCK, 1, 12, "&6&lVogelverschrikker Pakje", "&7Geeft je het Vogelverschrikker Pakje", " ", "&5Klik om te spawnen");
            createItemLore(inv, SKELETON_SKULL, 1, 13, "&6&lSkeleton Pakje", "&7Geeft je het Skeleton Pakje", " ", "&5Klik om te spawnen");
            createItemLore(inv, ZOMBIE_HEAD, 1, 14, "&6&lZombie Pakje", "&7Geeft je het Zombie Pakje", " ", "&5Klik om te spawnen");
        }

        createItem(inv, ARROW, 1, 22, "Terug");
        createItem(inv, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        BukkitScheduler scheduler = getScheduler();

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lWitch Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(witchHelm(), witchChest(), witchLegs(), witchBoots());
            } else {
                if (!(witchPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (witchPakje1.contains(p.getName())) {
                    witchPakje2.add(p.getName());
                }

                if (witchPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipDollarCost(), 5)) {
                        p.getInventory().removeItem(kiipDollarCost());

                        p.getInventory().addItem(witchHelm(), witchChest(), witchLegs(), witchBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipDollars§7!");
                        p.closeInventory();
                    }
                    witchPakje1.remove(p.getName());
                    witchPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                witchPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> witchPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lVogelverschrikker Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(vogelHoed(), vogelChest(), vogelLegs());
            } else {
                if (!(vogelPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (vogelPakje1.contains(p.getName())) {
                    vogelPakje2.add(p.getName());
                }

                if (vogelPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipDollarCost(), 5)) {
                        p.getInventory().removeItem(kiipDollarCost());

                        p.getInventory().addItem(vogelHoed(), vogelChest(), vogelLegs());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipDollars§7!");
                        p.closeInventory();
                    }
                    vogelPakje1.remove(p.getName());
                    vogelPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                vogelPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> vogelPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lSkeleton Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(skellyHoofd(), skellyChest(), skellyLegs(), skellyBoots());
            } else {
                if (!(skellyPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (skellyPakje1.contains(p.getName())) {
                    skellyPakje2.add(p.getName());
                }

                if (skellyPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipDollarCost(), 5)) {
                        p.getInventory().removeItem(kiipDollarCost());

                        p.getInventory().addItem(skellyHoofd(), skellyChest(), skellyLegs(), skellyBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipDollars§7!");
                        p.closeInventory();
                    }
                    skellyPakje1.remove(p.getName());
                    skellyPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                skellyPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> skellyPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lZombie Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(zombieHead(), zombieChest(), zombieLegs(), zombieBoots());
            } else {
                if (!(zombiePakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (zombiePakje1.contains(p.getName())) {
                    zombiePakje2.add(p.getName());
                }

                if (zombiePakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipDollarCost(), 5)) {
                        p.getInventory().removeItem(kiipDollarCost());

                        p.getInventory().addItem(zombieHead(), zombieChest(), zombieLegs(), zombieBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipDollars§7!");
                        p.closeInventory();
                    }
                    zombiePakje1.remove(p.getName());
                    zombiePakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                zombiePakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> zombiePakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("Terug"))) {
            p.sendMessage(prefix + "Je keert terug naar de §3§lEvents Shop§7...");
            p.openInventory(EventShop.eventShop(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&cSluiten"))) {
            p.closeInventory();
        }
    }

}
