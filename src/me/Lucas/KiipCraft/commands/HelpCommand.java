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

import java.util.List;

public class HelpCommand extends SubCommand {
    private final Main plugin;

    public HelpCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public void onCommand(Player p, String[] args) {
        p.sendMessage(Utils.prefix + Utils.chat("&c&lKiipcraft Commands:"));
        p.sendMessage(" ");
        // De Commands voor de Nons
        p.sendMessage(Utils.chat(" &7- &3/kiipcraft help&7: Laat dit bericht zien."));
        p.sendMessage(Utils.chat(" &7- &3/kiipcraft servertour&7: Maakt een warp aan voor de servertour op jouw huidige locatie."));
        p.sendMessage(Utils.chat(" "));
        // Al heb je Donateurs Permissies
        if (p.hasPermission("kiipcraft.donator")) {
            p.sendMessage(Utils.chat(" &c&lDonateurs Sectie:"));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft bottlexp <levels>&7: Stopt het ingevoerde aantal levels in een flesje."));
            p.sendMessage(" ");
        }
        // Staff Permissies
        if (p.hasPermission("kiipcraft.staff")) {
            p.sendMessage(Utils.chat(" &c&lStaff Sectie:"));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons help&7: Laat een lijst met alle dungeon commands zien."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft events help&7: Laat een lijst met alle event commands zien."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft servertour menu&7: Opent het servertour menu."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft shard <naam>&7: Geeft je de shard van de ingevoerde soort."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft orb <naam>&7: Geeft je de orb van de ingevoerde soort."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft admintool&7: Geeft je de admintool."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft update&7: Checkt de plugin voor updates."));
            p.sendMessage(Utils.chat(" &7- &3/kiipcraft fix&7: Fixt herhalende loops in de plugin."));
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

    @Override
    public List<String> getArguments(Player player, String[] args) {
        return null;
    }
}
