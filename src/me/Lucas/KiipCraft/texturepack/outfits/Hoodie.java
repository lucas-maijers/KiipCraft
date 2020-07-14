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

public class Hoodie {

    public static ItemStack hoodie() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Kiipcraft Hoodie &7is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Kiipcraft Hoodie"));

        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

}
