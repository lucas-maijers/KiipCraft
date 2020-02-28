/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.items;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DungeonItems {

    public static ItemStack diamondKey() {
        ItemStack dKey = new ItemStack(Material.PAPER);
        ItemMeta dKeyM = dKey.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7Een &b&lDiamond &c&lDungeon Key &7kan een dungeon deur met een &b&lDiamond &7slot openen."));
        lore.add(Utils.chat("&7Na het gebruiken zal de sleutel verdwijnen."));
        lore.add(Utils.chat("&7Dit is een Officieel KiipCraft item."));

        assert dKeyM != null;
        dKeyM.setDisplayName(Utils.chat("&b&lDiamond &c&lKey"));
        dKeyM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        dKeyM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dKeyM.setLore(lore);

        dKey.setItemMeta(dKeyM);
        return dKey;
    }

    public static ItemStack emeraldKey() {
        ItemStack dKey = new ItemStack(Material.PAPER);
        ItemMeta dKeyM = dKey.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7Een &a&lEmerald &c&lDungeon Key &7kan een dungeon deur met een &a&lEmerald &7slot openen."));
        lore.add(Utils.chat("&7Na het gebruiken zal de sleutel verdwijnen."));
        lore.add(Utils.chat("&7Dit is een Officieel KiipCraft item."));

        assert dKeyM != null;
        dKeyM.setDisplayName(Utils.chat("&a&lEmerald &c&lKey"));
        dKeyM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        dKeyM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dKeyM.setLore(lore);

        dKey.setItemMeta(dKeyM);
        return dKey;
    }

    public static ItemStack goldKey() {
        ItemStack dKey = new ItemStack(Material.PAPER);
        ItemMeta dKeyM = dKey.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7Een &6&lGold &c&lDungeon Key &7kan een dungeon deur met een &6&lGolden &7slot openen."));
        lore.add(Utils.chat("&7Na het gebruiken zal de sleutel verdwijnen."));
        lore.add(Utils.chat("&7Dit is een Officieel KiipCraft item."));

        assert dKeyM != null;
        dKeyM.setDisplayName(Utils.chat("&6&lGold &c&lKey"));
        dKeyM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        dKeyM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dKeyM.setLore(lore);

        dKey.setItemMeta(dKeyM);
        return dKey;
    }

    public static ItemStack dungeonGateTool() {
        ItemStack gateTool = new ItemStack(Material.BLAZE_ROD);
        ItemMeta gateToolM = gateTool.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De Dungeon Gate Tool om deuren voor dungeons mee aan te wijzen!"));
        lore.add(Utils.chat("&7Met de tool kan je een dungeon gate maken!"));
        lore.add(Utils.chat("&7Om te beginnen met een dungeon gate te maken doe je /dungeons createdungeon!"));

        assert gateToolM != null;
        gateToolM.setDisplayName(Utils.chat("&c&lDungeon Gate Tool"));
        gateToolM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        gateToolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        gateToolM.setLore(lore);

        gateTool.setItemMeta(gateToolM);
        return gateTool;
    }
}
