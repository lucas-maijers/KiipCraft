/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.ui.MainEventsUI;
import me.Lucas.KiipCraft.managers.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class GUICommand extends SubCommand {

    private Main plugin;

    public GUICommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        return null;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.events")) {
            p.sendMessage(prefix + "Bezig met het openen van de §3§lEventsAdmin GUI§7...");
            p.openInventory(MainEventsUI.mainGUI(p));
            return;
        } else {
            p.sendMessage(noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.eventsadmin;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[]{"events"};
    }

}
