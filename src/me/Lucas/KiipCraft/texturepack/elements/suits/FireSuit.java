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

public class FireSuit {

    public static ItemStack fireHelmet() {
        ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cFire Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Immuun voor vuur!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cFire Helmet"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack fireChestplate() {
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cFire Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Immuun voor vuur!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cFire Chestplate"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack fireLeggings() {
        ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cFire Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Immuun voor vuur!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cFire Leggings"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack fireBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cFire Boots&7 zjin een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Immuun voor vuur!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cFire Boots"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
