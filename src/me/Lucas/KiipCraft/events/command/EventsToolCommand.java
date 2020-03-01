/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

import static me.Lucas.KiipCraft.utils.Utils.*;

public class EventsToolCommand extends SubCommand {

    private Main plugin;

    public EventsToolCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.events")) {
            if (!(p.getInventory().contains(eventsTool()))) {
                p.sendMessage(prefix + "Je hebt de §3§lEvents Tool §7gekregen.");
                p.getInventory().addItem(eventsTool());
                return;
            } else if (p.getInventory().contains(eventsTool())) {
                p.sendMessage(prefix + "Je hebt de §3§lEvents Tool §7uit je inventaris verwijderd.");
                p.getInventory().removeItem(eventsTool());
                return;
            }
        } else {
            p.sendMessage(noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.eventstool;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
