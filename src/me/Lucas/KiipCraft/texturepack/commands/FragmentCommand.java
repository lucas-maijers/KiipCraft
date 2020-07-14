/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.texturepack.elements.fragments.Fragments;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FragmentCommand extends SubCommand {

    private final Main plugin;
    private final List<String> types = new ArrayList<>();

    public FragmentCommand(Main plugin) {
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
        if (p.hasPermission("kiipcraft.staff")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + "Je moet de naam van een Fragment invullen.");
                p.sendMessage(Utils.prefix + Utils.chat("Geldige namen zijn: fire, water, air, earth, lightning, light, darkness, life"));
                return;
            }
            switch (args[1].toLowerCase()) {
                case "fire":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &4&lFire &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.fireFragment());
                    return;
                case "water":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &1&lWater &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.waterFragment());
                    return;
                case "air":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &l&fAir &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.airFragment());
                    return;
                case "earth":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &6&lEarth &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.earthFragment());
                    return;
                case "lightning":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &b&lLight&e&lning &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.lightningFragment());
                    return;
                case "light":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &e&lLight &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.lightFragment());
                    return;
                case "darkness":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &5&lDark&0&lness &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.darknessFragment());
                    return;
                case "life":
                    p.sendMessage(Utils.prefix + Utils.chat("Je ontvangt een &a&lLife &c&lFragment&7!"));
                    p.getInventory().addItem(Fragments.lifeFragment());
                    return;
                default:
                    p.sendMessage(Utils.prefix + "Onbekende Fragment soort.");
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.fragment;
    }

    @Override
    public String info() {
        return null;
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
