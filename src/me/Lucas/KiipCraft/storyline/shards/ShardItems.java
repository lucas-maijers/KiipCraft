/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.storyline.shards;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShardItems {

    public static ItemStack fireShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &4&lFire"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack waterShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &1&lWater"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack airShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &f&lAir"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack earthShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &6&lEarth"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack lightningShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &b&lLight&e&lning"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack lightShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &e&lLight"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack darknessShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &5&lDark&0&lness"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack lifeShard() {
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&c&lShard of &a&lLife"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

}
