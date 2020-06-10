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

import java.util.ArrayList;
import java.util.List;

public class OrbCommand extends SubCommand {

    private static final List<String> types = new ArrayList<>();
    private final Main plugin;

    public OrbCommand(Main plugin) {
        this.plugin = plugin;

        types.add("fire");
        types.add("water");
        types.add("air");
        types.add("earth");
        types.add("lightning");
        types.add("light");
        types.add("darkness");
        types.add("life");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.storyline")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + "Je moet de naam van een Orb invullen.");
                p.sendMessage(Utils.prefix + Utils.chat("Geldige namen zijn: fire, water, air, earth, lightning, light, darkness, life"));
                return;
            }
            switch (args[1].toLowerCase()) {
                case "fire":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &4&lFire &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.fireOrb());
                    return;
                case "water":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &1&lWater &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.waterOrb());
                    return;
                case "air":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &l&fAir &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.airOrb());
                    return;
                case "earth":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &6&lEarth &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.earthOrb());
                    return;
                case "lightning":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &b&lLight&e&lning &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lightningOrb());
                    return;
                case "light":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &e&lLight &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lightOrb());
                    return;
                case "darkness":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt de &5&lDark&0&lness &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.darknessOrb());
                    return;
                case "life":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt &a&lLife &c&lOrb&7!"));
                    p.getInventory().addItem(OrbItems.lifeOrb());
                    return;
                default:
                    p.sendMessage(Utils.prefix + "Onbekende Orbnaam.");
            }
        } else {
            p.sendMessage(Utils.noPermission);
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

    @Override
    public List<String> getArguments(Player player, String[] args) {
        List<String> tabComplete = new ArrayList<>(types);
        List<String> completionList = new ArrayList<>();

        if (args.length == 2) {
            if (!args[0].equals("")) {
                for (String s : types) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return tabComplete;
        }
        return null;
    }
}
