/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {
    private Main plugin;

    public HelpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        p.sendMessage(Utils.prefix + Utils.chat("&c&lKiipcraft Commands:"));
        p.sendMessage(" ");
        // De Commands voor de Nons
        p.sendMessage(Utils.chat(" &7- &3/kiipcraft help&a: Laat dit bericht zien."));
        p.sendMessage(Utils.chat(" &7- &3/kiipcraft servertour&a: Maakt een warp aan voor de servertour op jouw huidige locatie."));
        p.sendMessage(Utils.chat(" "));
        // Al heb je Donateurs Permissies
        if (p.hasPermission("kiipcraft.help.donator")) {
            p.sendMessage(Utils.chat("&c&lDonateurs Sectie:"));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft bottlexp <levels>&a: Stopt het ingevoerde aantal levels in een flesje."));
            p.sendMessage(" ");
        }
        // Staff Permissies
        if (p.hasPermission("kiipcraft.help.all")) {
            p.sendMessage(Utils.chat("&c&lStaff Sectie:"));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons help&a: Laat een lijst met alle dungeon commands zien."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft eventstool&a: Geeft je de eventstool."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft eventsadmin&a: Opent het events menu."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft servertour menu&a: Opent het servertour menu,"));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft admintool&a: Geeft je de admintool."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft update&a: Checkt de plugin voor updates."));

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
