/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.fragments;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Fragments {

    public static ItemStack fireFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cFire Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &cFire Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cFire Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack airFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &fAir Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &fAir Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&fAir Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack waterFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &1Water Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &1Water Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&1Water Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack earthFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Earth Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &6Earth Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Earth Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack lightningFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &bLight&ening Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &bLight&ening Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&bLight&ening Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack lightFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &eLight Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &eLight Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&eLight Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack lifeFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &aLife Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &aLife Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&aLife Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack darknessFragment() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Dark&0ness &5Fragments &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Deze &aFragments&7 kunnen alleen in &9Dungeons&7 gevonden worden!"));
        lore.add("");
        lore.add(Utils.chat("&7Deze &aFragments&7 worden gebruikt om de &5Dark&0ness &5Suit&7 te craften!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Dark&0ness &5Fragment"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }
}
