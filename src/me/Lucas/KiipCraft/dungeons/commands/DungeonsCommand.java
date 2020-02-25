/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.items.DungeonItems;
import me.Lucas.KiipCraft.dungeons.listeners.DungeonGateCreation;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class DungeonsCommand implements CommandExecutor {

    private Main plugin;
    public static Set<String> creatingPlayer = new HashSet<>();

    public DungeonsCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("dungeons").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.consoleMessage);
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.dungeons")) {
            if (args.length == 0) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een subcommando invoeren!"));
                return true;
            }
            /* Key */
            if (args[0].equals("key") && p.hasPermission("kiipcraft.dungeons.key")) {
                if (args.length == 1) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet een geldige soort sleutel invoeren, geldige soorten zijn: Gold, Diamond, Emerald!"));
                    return true;
                }
                switch (args[1].toLowerCase()) {
                    case "diamond":
                        p.getInventory().addItem(DungeonItems.diamondKey());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &b&lDiamond &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "emerald":
                        p.getInventory().addItem(DungeonItems.emeraldKey());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &a&lEmerald &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "gold":
                        p.getInventory().addItem(DungeonItems.goldKey());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &6&lGold &c&lDungeon Key &7ontvangen!"));
                        break;
                    default:
                        p.sendMessage(Utils.prefix + Utils.chat("Dit is een ongeldige sleutelnaam!"));
                        break;
                }
                return true;
            }
            /* GateTool */
            if (args[0].equals("tool") && p.hasPermission("kiipcraft.dungeons.tool")) {
                p.getInventory().addItem(DungeonItems.dungeonGateTool());
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de &c&lDungeons Gate Tool &7ontvangen!"));
                return true;
            }
            /* Create Dungeon */
            if (args[0].equals("create") && p.hasPermission("kiipcraft.dungeons.tool.create")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het creatieproces al gestart, doe &c/dungeons stopcreation&7 om dit te annuleren!"));
                    return true;
                }
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Dungeon Creatie proces gestart, om dit te annuleren doe &c/dungeons stopcreation&7!"));
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Selecteer de linker bovenhoek van de dungeon gate met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                creatingPlayer.add(p.getName());
                return true;
            }
            /* Stop Creation */
            if (args[0].equals("stopcreation") && p.hasPermission("kiipcraft.dungeons.tool.create")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Dungeon Creatie proces gestopt."));

                    DungeonGateCreation.phase1.remove(p.getName());
                    DungeonGateCreation.phase2.remove(p.getName());
                    DungeonGateCreation.phase3.remove(p.getName());
                    DungeonGateCreation.phasesComplete.remove(p.getName());

                    creatingPlayer.remove(p.getName());
                    return true;
                } else if (!creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen Dungeon Creatie proces geactiveerd, doe &c/dungeons create&7 om het te starten!"));
                    return true;
                }
            }

        } else {
            p.sendMessage(Utils.noPermission);
        }
        return false;
    }
}
