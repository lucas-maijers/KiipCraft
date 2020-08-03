/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.texturepack.elements.suits.DarknessSuit;
import org.bukkit.entity.Player;

import java.util.List;

public class OutfitCommand extends SubCommand {

    private final Main plugin;

    public OutfitCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (player.hasPermission("kiipcraft.staff")) {
            player.getInventory().addItem(DarknessSuit.darknessHelmet(), DarknessSuit.darknessChestplate(), DarknessSuit.darknessLeggings(), DarknessSuit.darknessBoots());
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.outfit;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        return null;
    }
}
