/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.storyline.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.storyline.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class OrbCommand extends SubCommand {

    private Main plugin;

    public OrbCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.storyline")) {
            if (args.length == 1) {
                p.sendMessage(prefix + "Je moet de naam van een Orb invullen.");
                p.sendMessage(prefix + Utils.chat("Geldige namen zijn: fire, water, air, earth, lightning, light, darkness, life"));
                return;
            }
            switch (args[1].toLowerCase()) {
                case "fire":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &4&lFire &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.fireOrb());
                    return;
                case "water":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &1&lWater &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.waterOrb());
                    return;
                case "air":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &l&fAir &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.airOrb());
                    return;
                case "earth":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &6&lEarth &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.earthOrb());
                    return;
                case "lightning":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &b&lLight&e&lning &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lightningOrb());
                    return;
                case "light":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &e&lLight &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lightOrb());
                    return;
                case "darkness":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &5&lDark&0&lness &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.darknessOrb());
                    return;
                case "life":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt &a&lLife &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lifeOrb());
                    return;
                default:
                    p.sendMessage(prefix + "Onbekende Orbnaam.");
                    return;
            }
        } else {
            p.sendMessage(noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.orb;
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
