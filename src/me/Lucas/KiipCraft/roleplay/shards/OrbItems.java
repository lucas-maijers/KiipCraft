package me.Lucas.KiipCraft.roleplay.shards;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class OrbItems {

    public static ItemStack fireOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &4&lFire &c&lOrb &7heeft de kracht om vuurballen naar waar dan ook te sturen!"));
        lore.add(Utils.chat("&7Deze krachten zijn uiterst gevaarlijk!"));
        lore.add(Utils.chat("&7Als ze in verkeerde handen vallen zou de wereld verdoemd zijn!"));

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&4&lFire &c&lOrb"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shardMeta.setLore(lore);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack waterOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&1&lWater &c&lOrb"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack airOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&f&lAir &c&lOrb"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack earthOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &6&lEarth &c&lOrb &7heeft de kracht om mensen compleet vast te zetten!"));
        lore.add(Utils.chat("&7Deze krachten zijn uiterst gevaarlijk!"));
        lore.add(Utils.chat("&7Als ze in verkeerde handen vallen zou de wereld verdoemd zijn!"));

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&6&lEarth &c&lOrb"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shardMeta.setLore(lore);

        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack lightningOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7De &b&lLight&e&lning &c&lOrb &7heeft de kracht om bliksem naar waar dan ook te sturen!"));
        lore.add(Utils.chat("&7Deze krachten zijn uiterst gevaarlijk!"));
        lore.add(Utils.chat("&7Als ze in verkeerde handen vallen zou de wereld verdoemd zijn!"));

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&b&lLight&e&lning &c&lOrb"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shardMeta.setLore(lore);

        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack lightOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&e&lLight &c&lOrb"));
        shardMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack darknessOrb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&5&lDark&0&lness &c&lOrb"));

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }

    public static ItemStack lifeorb() {
        ItemStack shard = new ItemStack(Material.ENDER_EYE);
        ItemMeta shardMeta = shard.getItemMeta();

        assert shardMeta != null;
        shardMeta.setDisplayName(Utils.chat("&a&lLife &c&lOrb"));

        shardMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        shard.setItemMeta(shardMeta);
        return shard;
    }
}
