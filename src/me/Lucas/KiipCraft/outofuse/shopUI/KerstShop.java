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
import static me.Lucas.KiipCraft.utils.Utils.*;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.Material.*;

public class KerstShop {

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static Main plugin;
    private static final Set<String> grinchPakje1 = new HashSet<>();
    private static final Set<String> grinchPakje2 = new HashSet<>();

    private static final Set<String> elfPakje1 = new HashSet<>();
    private static final Set<String> elfPakje2 = new HashSet<>();

    private static final Set<String> spPakje1 = new HashSet<>();
    private static final Set<String> spPakje2 = new HashSet<>();

    private static final Set<String> rdPakje1 = new HashSet<>();
    private static final Set<String> rdPakje2 = new HashSet<>();

    private static final Set<String> scPakje1 = new HashSet<>();
    private static final Set<String> scPakje2 = new HashSet<>();

    public KerstShop(Main plugin) {
        KerstShop.plugin = plugin;
    }

    public static void initialize() {
        inventory_name = chat("&c&lKiipCraft &3&lKerst Shop");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory kerstShop(Player p) {
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
            createItemHead(inv, grinchMasker(), 1, 11, "&6&lGrinch Pakje", "&7Geeft je het Grinch Pakje", " ", "&5Kost 5 &6&lKiipse Franken");
            createItemHead(inv, elfMasker(), 1, 12, "&6&lElf Pakje", "&7Geeft je het Elf Pakje", " ", "&5Kost 5 &6&lKiipse Franken");
            createItemHead(inv, spMasker(), 1, 13, "&6&lSneeuwpop Pakje", "&7Geeft je het Sneeuwpop Pakje", " ", "&5Kost 5 &6&lKiipse Franken");
            createItemHead(inv, rdMasker(), 1, 14, "&6&lRudolf Pakje", "&7Geeft je het Rudolf Pakje", " ", "&5Kost 5 &6&lKiipse Franken");
            createItemHead(inv, santaMask(), 1, 15, "&6&lSanta Pakje", "&7Geeft je het Santa Pakje", " ", "&5Kost 5 &6&lKiipse Franken");
        } else {
            createItemHead(inv, grinchMasker(), 1, 11, "&6&lGrinch Pakje", "&7Geeft je het Grinch Pakje", " ", "&5Klik om te spawnen");
            createItemHead(inv, elfMasker(), 1, 12, "&6&lElf Pakje", "&7Geeft je het Elf Pakje", " ", "&5Klik om te spawnen");
            createItemHead(inv, spMasker(), 1, 13, "&6&lSneeuwpop Pakje", "&7Geeft je het Sneeuwpop Pakje", " ", "&5Klik om te spawnen");
            createItemHead(inv, rdMasker(), 1, 14, "&6&lRudolf Pakje", "&7Geeft je het Rudolf Pakje", " ", "&5Klik om te spawnen");
            createItemHead(inv, santaMask(), 1, 15, "&6&lSanta Pakje", "&7Geeft je het Santa Pakje", " ", "&5Klik om te spawnen");
        }

        Utils.createItem(inv, ARROW, 1, 22, "Terug");
        Utils.createItem(inv, BARRIER, 1, 23, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        BukkitScheduler scheduler = getScheduler();

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lGrinch Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(grinchHelm(), grinchChest(), grinchLegs(), grinchBoots());
            } else {
                if (!(grinchPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (grinchPakje1.contains(p.getName())) {
                    grinchPakje2.add(p.getName());
                }

                if (grinchPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipFrankCost(), 5)) {
                        p.getInventory().removeItem(kiipFrankCost());

                        p.getInventory().addItem(grinchHelm(), grinchChest(), grinchLegs(), grinchBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipse Franken§7!");
                        p.closeInventory();
                    }
                    grinchPakje1.remove(p.getName());
                    grinchPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                grinchPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> grinchPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lElf Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(elfHelmet(), elfChest(), elfLegs(), elfBoots());
            } else {
                if (!(elfPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (elfPakje1.contains(p.getName())) {
                    elfPakje2.add(p.getName());
                }

                if (elfPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipFrankCost(), 5)) {
                        p.getInventory().removeItem(kiipFrankCost());

                        p.getInventory().addItem(elfHelmet(), elfChest(), elfLegs(), elfBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipse Franken§7!");
                        p.closeInventory();
                    }
                    elfPakje1.remove(p.getName());
                    elfPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                elfPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> elfPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lSneeuwpop Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(sneeuwpopHelm(), sneeuwpopChest(), sneeuwpopLegs(), sneeuwpopBoots());
            } else {
                if (!(spPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (spPakje1.contains(p.getName())) {
                    spPakje2.add(p.getName());
                }

                if (spPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipFrankCost(), 5)) {
                        p.getInventory().removeItem(kiipFrankCost());

                        p.getInventory().addItem(sneeuwpopHelm(), sneeuwpopChest(), sneeuwpopLegs(), sneeuwpopBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipse Franken§7!");
                        p.closeInventory();
                    }
                    spPakje1.remove(p.getName());
                    spPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                spPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> spPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lSanta Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(santaHelmet(), santaChest(), santaLegs(), santaBoots());
            } else {
                if (!(scPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (scPakje1.contains(p.getName())) {
                    scPakje2.add(p.getName());
                }

                if (scPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipFrankCost(), 5)) {
                        p.getInventory().removeItem(kiipFrankCost());

                        p.getInventory().addItem(santaHelmet(), santaChest(), santaLegs(), santaBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipse Franken§7!");
                        p.closeInventory();
                    }
                    scPakje1.remove(p.getName());
                    scPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                scPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> scPakje1.remove(p.getName()), 20 * 10);
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(chat("&6&lRudolf Pakje"))) {
            if (ShopAdmin.adminShop.contains(p.getName())) {
                p.getInventory().addItem(rudolfHelmet(), rudolfChest(), rudolfLegs(), rudolfBoots());
            } else {
                if (!(rdPakje1.contains(p.getName()))) {
                    p.sendMessage(prefix + "Klik nog een keer al wil je dit pakje kopen.");
                }

                if (rdPakje1.contains(p.getName())) {
                    rdPakje2.add(p.getName());
                }

                if (rdPakje2.contains(p.getName())) {
                    if (p.getInventory().containsAtLeast(kiipFrankCost(), 5)) {
                        p.getInventory().removeItem(kiipFrankCost());

                        p.getInventory().addItem(rudolfHelmet(), rudolfChest(), rudolfLegs(), rudolfBoots());

                        p.closeInventory();
                        p.sendMessage(prefix + "Veel plezier met je nieuwe outfit!");
                    } else {
                        p.sendMessage(prefix + "Helaas, je hebt niet genoeg §6§lKiipse Franken§7!");
                        p.closeInventory();
                    }
                    rdPakje1.remove(p.getName());
                    rdPakje2.remove(p.getName());
                    scheduler.cancelTasks(plugin);
                    return;
                }
                rdPakje1.add(p.getName());

                scheduler.scheduleSyncDelayedTask(plugin, () -> rdPakje1.remove(p.getName()), 20 * 10);
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
