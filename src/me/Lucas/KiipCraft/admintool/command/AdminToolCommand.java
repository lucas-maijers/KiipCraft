/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.*;

public class AdminToolCommand extends SubCommand {

    private Main plugin;

    public AdminToolCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.admintool") && !(p.getInventory().contains(adminTool()))) {
            p.sendMessage(prefix + chat("Je hebt zojuist de &4&lAdmin Tool&7 ontvangen!"));
            p.getInventory().addItem(adminTool());
            return;
        } else if (p.hasPermission("kiipcraft.admintool") && p.getInventory().contains(adminTool())) {
            p.sendMessage(prefix + chat("Je hebt zojuist je &4&lAdmin Tool&7 verwijderd!"));
            p.getInventory().remove(adminTool());
            return;
        } else {
            p.sendMessage(noPermission);
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
}
