/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.managers;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommand {

    public SubCommand() {
    }

    public abstract void onCommand(Player player, String[] args);

    public abstract String name();

    public abstract String info();

    public abstract String[] aliases();

    public abstract List<String> getArguments(Player player, String[] args);
}
