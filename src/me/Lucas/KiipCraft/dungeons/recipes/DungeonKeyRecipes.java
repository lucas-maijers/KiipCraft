/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.recipes;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.items.DungeonItems;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import static org.bukkit.Material.*;

public class DungeonKeyRecipes {

    private Main plugin;

    public DungeonKeyRecipes(Main plugin) {
        this.plugin = plugin;
        goldKey();
        diamondKey();
        emeraldKey();
    }

    private void goldKey() {
        NamespacedKey goldDungeonKey = new NamespacedKey(plugin, "gold_dungeon_key");
        ShapedRecipe goldKeyRecipe = new ShapedRecipe(goldDungeonKey, DungeonItems.goldKey());

        goldKeyRecipe.shape("II ", "IG ", "  I");
        goldKeyRecipe.setIngredient('I', IRON_INGOT);
        goldKeyRecipe.setIngredient('G', GOLD_INGOT);

        Bukkit.addRecipe(goldKeyRecipe);
    }

    private void diamondKey() {
        NamespacedKey diamondDungeonKey = new NamespacedKey(plugin, "diamond_dungeon_key");
        ShapedRecipe diamondKeyRecipe = new ShapedRecipe(diamondDungeonKey, DungeonItems.diamondKey());

        diamondKeyRecipe.shape("II ", "ID ", "  I");
        diamondKeyRecipe.setIngredient('I', IRON_INGOT);
        diamondKeyRecipe.setIngredient('D', DIAMOND);

        Bukkit.addRecipe(diamondKeyRecipe);
    }

    private void emeraldKey() {
        NamespacedKey emeraldDungeonKey = new NamespacedKey(plugin, "emerald_dungeon_key");
        ShapedRecipe emeraldKeyRecipe = new ShapedRecipe(emeraldDungeonKey, DungeonItems.emeraldKey());

        emeraldKeyRecipe.shape("II ", "IE ", "  I");
        emeraldKeyRecipe.setIngredient('I', IRON_INGOT);
        emeraldKeyRecipe.setIngredient('E', EMERALD);

        Bukkit.addRecipe(emeraldKeyRecipe);
    }

}
