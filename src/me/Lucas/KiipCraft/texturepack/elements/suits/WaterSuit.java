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

public class WaterSuit {

    public static ItemStack waterHelmet() {
        ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &1Water Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller zwemmen en onbeperkt onderwater!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&1Water Helmet"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack waterChestplate() {
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &1Water Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller zwemmen en onbeperkt onderwater!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&1Water Chestplate"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack waterLeggings() {
        ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &1Water Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller zwemmen en onbeperkt onderwater!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&1Water Leggings"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack waterBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &1Water Boots&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Sneller zwemmen en onbeperkt onderwater!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&1Water Boots"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

}
