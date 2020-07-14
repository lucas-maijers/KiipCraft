/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.outfits;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class Wandelschoenen {

    public static ItemStack wandelschoenen() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Wandelschoenen &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lITEM BONUS:"));
        lore.add(Utils.chat("  &9Geeft je een &f20% &9speedboost!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Wandelschoenen"));
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", .2, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.FEET));

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
