/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.suits;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class LightningSuit {

    public static ItemStack lightningHelmet() {
        ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &bLight&ening Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller sprinten!"));
        lore.add(Utils.chat("  &9Immuun voor bliksem"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&bLight&ening Helmet"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack lightningChestplate() {
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &bLight&ening Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller sprinten!"));
        lore.add(Utils.chat("  &9Immuun voor bliksem"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&bLight&ening Chestplate"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack lightningLeggings() {
        ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &bLight&ening Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller sprinten!"));
        lore.add(Utils.chat("  &9Immuun voor bliksem"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&bLight&ening Leggings"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack lightningBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &bLight&ening Boots&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller sprinten!"));
        lore.add(Utils.chat("  &9Immuun voor bliksem"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&bLight&ening Boots"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
