/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminToolCommand extends SubCommand {

    private final Main plugin;

    public AdminToolCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.staff")) {
            if (!(p.getInventory().contains(Utils.adminTool()))) {
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt zojuist de &4&lAdmin Tool&7 ontvangen!"));
                p.getInventory().addItem(Utils.adminTool());
            } else if (p.getInventory().contains(Utils.adminTool())) {
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt zojuist je &4&lAdmin Tool&7 verwijderd!"));
                p.getInventory().remove(Utils.adminTool());
            } else {
                p.sendMessage(Utils.noPermission);
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.admintool;
    }

    @Override
    public String info() {
        return "";
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
