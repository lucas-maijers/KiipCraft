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

public class HoneySuit {

    public static ItemStack honeyHelmet() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Honey Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7De volledige honey set heeft een speciale kracht!"));
        lore.add(" ");
        lore.add(" ");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geen fall damage!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Honey Helmet"));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack honeyChestplate() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Honey Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7De volledige honey set heeft een speciale kracht!"));
        lore.add(" ");
        lore.add(" ");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geen fall damage!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Honey Chestplate"));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack honeyLeggings() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Honey Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7De volledige honey set heeft een speciale kracht!"));
        lore.add(" ");
        lore.add(" ");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geen fall damage!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Honey Leggings"));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack honeyBoots() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Honey Boots&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7De volledige honey set heeft een speciale kracht!"));
        lore.add(" ");
        lore.add(" ");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geen fall damage!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Honey Boots"));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        i.setItemMeta(meta);
        return i;
    }

}
