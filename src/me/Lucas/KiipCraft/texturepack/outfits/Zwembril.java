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

public class Zwembril {

    public static ItemStack zwembril() {
        ItemStack i = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6Zwembril&7 is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));
        lore.add("");
        lore.add(Utils.chat("&6&lITEM BONUS:"));
        lore.add(Utils.chat("  &9Geeft je &1Night Vision&9 al zit je onderwater!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&6Zwembril"));
        meta.setUnbreakable(true);
        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}
