/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.suits;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class EarthSuit {

    public static ItemStack earthHelmet() {
        ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Earth Helmet&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lARMOR BONUS:"));
        lore.add(Utils.chat("  &94x sterker dan standaard diamant!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Earth Helmet"));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack earthChestplate() {
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Earth Chestplate&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lARMOR BONUS:"));
        lore.add(Utils.chat("  &94x sterker dan standaard diamant!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Earth Chestplate"));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack earthLeggings() {
        ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Earth Leggings&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lARMOR BONUS:"));
        lore.add(Utils.chat("  &94x sterker dan standaard diamant!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Earth Leggings"));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack earthBoots() {
        ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Earth Boots&7 zijn een speciaal item in &cKiipCraft&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lARMOR BONUS:"));
        lore.add(Utils.chat("  &94x sterker dan standaard diamant!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Earth Boots"));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        meta.setUnbreakable(true);

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
