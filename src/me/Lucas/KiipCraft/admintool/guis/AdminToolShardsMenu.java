/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.guis;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.storyline.shards.ShardItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.*;

public class AdminToolShardsMenu {

    public static Inventory inv;
    public static String inv_name;
    private static Main plugin;
    private static int row_now = 4;
    public static int inv_rows = row_now * 9;

    public AdminToolShardsMenu(Main plugin) {
        AdminToolShardsMenu.plugin = plugin;
    }

    public static void initialize() {
        inv_name = Utils.chat("&c&lShards Menu");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory shardsMenu(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 28; j < 31; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }

        for (int k = 33; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 18, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 19, " ");
        Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, 27, " ");

        Utils.createItemHead(inv, shard(), 1, 11, "&c&lShard of &4&lFire", "&7Geeft je de Fire Shard!");
        Utils.createItemHead(inv, shard(), 1, 12, "&c&lShard of &1&lWater", "&7Geeft je de Water Shard!");
        Utils.createItemHead(inv, shard(), 1, 13, "&c&lShard of &f&lAir", "&7Geeft je de Air Shard!");
        Utils.createItemHead(inv, shard(), 1, 14, "&c&lShard of &6&lEarth", "&7Geeft je de Earth Shard!");
        Utils.createItemHead(inv, shard(), 1, 15, "&c&lShard of &b&lLight&e&lning", "&7Geeft je de Lightning Shard!");
        Utils.createItemHead(inv, shard(), 1, 16, "&c&lShard of &e&lLight", "&7Geeft je de Light Shard!");
        Utils.createItemHead(inv, shard(), 1, 17, "&c&lShard of &5&lDark&0&lness", "&7Geeft je de Darkness Shard!");
        Utils.createItemHead(inv, shard(), 1, 20, "&c&lShard of &a&lLife", "&7Geeft je de Life Shard!");

        Utils.createItem(inv, ARROW, 1, 31, "Terug");
        Utils.createItem(inv, BARRIER, 1, 32, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &4&lFire"))) {
            p.getInventory().addItem(ShardItems.fireShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &4&lFire&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &1&lWater"))) {
            p.getInventory().addItem(ShardItems.waterShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &1&lWater&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &f&lAir"))) {
            p.getInventory().addItem(ShardItems.airShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &f&lAir&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &6&lEarth"))) {
            p.getInventory().addItem(ShardItems.earthShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &6&lEarth&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &b&lLight&e&lning"))) {
            p.getInventory().addItem(ShardItems.lightningShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &b&lLight&e&lning&7!"));
        }
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &e&lLight"))) {
            p.getInventory().addItem(ShardItems.lightShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &e&lLight&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &5&lDark&0&lness"))) {
            p.getInventory().addItem(ShardItems.darknessShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &5&lDark&0&lness&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lShard of &a&lLife"))) {
            p.getInventory().addItem(ShardItems.lifeShard());
            p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &c&lShard of &a&lLife&7!"));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(AdminToolGUI.adminToolGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }

    public static ItemStack shard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }
}
