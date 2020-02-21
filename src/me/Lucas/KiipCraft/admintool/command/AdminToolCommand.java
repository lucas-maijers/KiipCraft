/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.*;

public class AdminToolCommand implements CommandExecutor {

    private Main plugin;

    public AdminToolCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("admintool").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.consoleMessage);
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.admintool") && !(p.getInventory().contains(adminTool()))) {
            p.sendMessage(prefix + chat("Je hebt zojuist de &4&lAdmin Tool&7 ontvangen!"));
            p.getInventory().addItem(adminTool());
            return true;
        } else if (p.hasPermission("kiipcraft.admintool") && p.getInventory().contains(adminTool())) {
            p.sendMessage(prefix + chat("Je hebt zojuist je &4&lAdmin Tool&7 verwijderd!"));
            p.getInventory().remove(adminTool());
            return true;
        } else {
            p.sendMessage(noPermission);
        }

        return false;
    }
}
