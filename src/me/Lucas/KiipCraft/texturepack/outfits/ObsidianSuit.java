/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.outfits;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ObsidianSuit {

    public static ItemStack obsidianHelmet() {
        ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Obsidian Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Obsidian Helmet"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack obsidianChestplate() {
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Obsidian Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Obsidian Chestplate"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack obsidianLeggings() {
        ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Obsidian Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Obsidian Leggings"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack obsidianBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Obsidian Boots&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Obsidian Boots"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

}
