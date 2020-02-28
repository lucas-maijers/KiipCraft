/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {
    private Main plugin;

    public HelpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        p.sendMessage("§7-=+ §6§lKiipCraft Commands §7+=-");
        p.sendMessage(" ");
        // De Commands voor de Nons
        p.sendMessage("§7- §3/kiipcraft §7| §fLaat dit menu zien.");
        p.sendMessage("§7- §3/shop §7| §fOpent de Events shop.");
        p.sendMessage("§7- §3/eventshop §7| §fZie /shop.");
        // Al heb je Donateurs Permissies
        if (p.hasPermission("kiipcraft.help.donator")) {
            p.sendMessage("§7- §3/bottlexp [levels] §7| §fStopt je XP in een flesje.");
        }
        // Staff Permissies
        if (p.hasPermission("kiipcraft.help.all")) {
            p.sendMessage("§7- §3/events §7| §fOpent het Events menu.");
            p.sendMessage("§7- §3/eventsadmin §7| §fZie /events.");
            p.sendMessage("§7- §3/eventstool §7| §fGeeft je de Events Tool.");
            p.sendMessage("§7- §3/kiipeuro [aantal] §7| §fGeeft je KiipEuro's.");
            p.sendMessage("§7- §3/kiipcraftupdate §7 §fChecked de plugin voor updates.");
        }
        p.sendMessage(" ");
    }

    @Override
    public String name() {
        return plugin.cmdMngr.help;
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
