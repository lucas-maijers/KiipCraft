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

public class RubySuit {

    public static ItemStack rubyHelmet() {
        ItemStack i = new ItemStack(Material.IRON_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cRuby Helmet &7is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cRuby Helmet"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack rubyChestplate() {
        ItemStack i = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cRuby Chestplate &7is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cRuby Chestplate"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack rubyLeggings() {
        ItemStack i = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cRuby Leggings &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cRuby Leggings"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack rubyBoots() {
        ItemStack i = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &cRuby Boots &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event &7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&cRuby Boots"));
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }


}
