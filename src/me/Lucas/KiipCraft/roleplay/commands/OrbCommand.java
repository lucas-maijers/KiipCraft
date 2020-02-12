/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.roleplay.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class OrbCommand implements CommandExecutor {

    private Main plugin;

    public OrbCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("orb").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Alleen spelers kunnen dit commando gebruiken!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.orb")) {
            if (args.length == 0) {
                p.sendMessage(prefix + "Je moet de naam van een Orb invullen.");
                p.sendMessage(prefix + Utils.chat("Geldige namen zijn: fire, water, air, earth, lightning, light, darkness, life"));
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "fire":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &4&lFire &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.fireOrb());
                    return true;
                case "water":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &1&lWater &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.waterOrb());
                    return true;
                case "air":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &l&fAir &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.airOrb());
                    return true;
                case "earth":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &6&lEarth &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.earthOrb());
                    return true;
                case "lightning":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &b&lLight&e&lning &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lightningOrb());
                    return true;
                case "light":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &e&lLight &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lightOrb());
                    return true;
                case "darkness":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &5&lDark&0&lness &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.darknessOrb());
                    return true;
                case "life":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt &a&lLife &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lifeorb());
                    return true;
                default:
                    p.sendMessage(prefix + "Onbekende Orbnaam.");
                    return true;
            }
        } else {
            p.sendMessage(noPermission);
        }
        return false;
    }
}
