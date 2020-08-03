/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.suits;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DarknessSuit {

    public static ItemStack darknessHelmet() {
        ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Dark&0ness &5Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geeft je aanvaller een Wither effect!"));
        lore.add(Utils.chat("  &9Druk 2 keer snel achter elkaar op shift om iedereen in een radius van 10 blokken te vervloeken!"));
        lore.add(Utils.chat("    &7(5 Minuten Cooldown)"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Dark&0ness &5Helmet"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        meta.addEnchant(Enchantment.THORNS, 1, true);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack darknessChestplate() {
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Dark&0ness &5Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geeft je aanvaller een Wither effect!"));
        lore.add(Utils.chat("  &9Druk 2 keer snel achter elkaar op shift om iedereen in een radius van 10 blokken te vervloeken!"));
        lore.add(Utils.chat("    &7(5 Minuten Cooldown)"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Dark&0ness &5Chestplate"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        meta.addEnchant(Enchantment.THORNS, 1, true);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack darknessLeggings() {
        ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Dark&0ness &5Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geeft je aanvaller een Wither effect!"));
        lore.add(Utils.chat("  &9Druk 2 keer snel achter elkaar op shift om iedereen in een radius van 10 blokken te vervloeken!"));
        lore.add(Utils.chat("    &7(5 Minuten Cooldown)"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Dark&0ness &5Leggings"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        meta.addEnchant(Enchantment.THORNS, 1, true);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack darknessBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &5Dark&0ness &5Boots&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lVOLLEDIGE SET BONUS:"));
        lore.add(Utils.chat("  &9Geeft je aanvaller een Wither effect!"));
        lore.add(Utils.chat("  &9Druk 2 keer snel achter elkaar op shift om iedereen in een radius van 10 blokken te vervloeken!"));
        lore.add(Utils.chat("    &7(5 Minuten Cooldown)"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&5Dark&0ness &5Boots"));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        meta.addEnchant(Enchantment.THORNS, 1, true);
        i.setItemMeta(meta);
        return i;
    }
}
