/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.command;

import me.Lucas.KiipCraft.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.*;

public class EventsToolCommand implements CommandExecutor {

    private Main plugin;

    public EventsToolCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("eventstool").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Alleen spelers kunnen dit commando uitvoeren!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.eventstool")) {
            if (!(p.getInventory().contains(eventsTool()))) {
                p.sendMessage(prefix + "Je hebt de §3§lEvents Tool §7gekregen.");
                p.getInventory().addItem(eventsTool());
                return true;
            } else if (p.getInventory().contains(eventsTool())) {
                p.sendMessage(prefix + "Je hebt de §3§lEvents Tool §7uit je inventaris verwijderd.");
                p.getInventory().removeItem(eventsTool());
                return true;
            }
        } else {
            p.sendMessage(noPermission);
        }
        return false;
    }
}
