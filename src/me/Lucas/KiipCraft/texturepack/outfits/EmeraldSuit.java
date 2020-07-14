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

public class EmeraldSuit {

    public static ItemStack emeraldHelmet() {
        ItemStack i = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &aEmerald Helmet &7is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&aEmerald Helmet"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack emeraldChestplate() {
        ItemStack i = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &aEmerald Chestplate &7is een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&aEmerald Chestplate"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack emeraldLeggings() {
        ItemStack i = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &aEmerald Leggings &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&aEmerald Leggings"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack emeraldBoots() {
        ItemStack i = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta meta = i.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &aEmerald Boots &7zijn een speciaal item in &cKiipCraft&7!"));
        lore.add(Utils.chat("&7Dit item is alleen te verkrijgen via een officieel &cKiipCraft Event&7!"));

        assert meta != null;
        meta.setDisplayName(Utils.chat("&aEmerald Boots"));
        meta.setUnbreakable(true);
        meta.setLore(lore);

        i.setItemMeta(meta);
        return i;
    }
}
