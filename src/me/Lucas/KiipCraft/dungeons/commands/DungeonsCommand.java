/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.items.DungeonKeys;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DungeonsCommand implements CommandExecutor {

    private Main plugin;

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
            if (args[0].equals("key") && p.hasPermission("kiipcraft.dungeons.key")) {
                if (args.length == 1) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet een geldige soort sleutel invoeren, geldige soorten zijn: Gold, Diamond, Emerald!"));
                    return true;
                }

                switch(args[1].toLowerCase()) {
                    case "diamond":
                        p.getInventory().addItem(DungeonKeys.diamondKey());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &b&lDiamond &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "emerald":
                        p.getInventory().addItem(DungeonKeys.emeraldKey());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &a&lEmerald &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "gold":
                        p.getInventory().addItem(DungeonKeys.goldKey());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &6&lGold &c&lDungeon Key &7ontvangen!"));
                        break;
                    default:
                        p.sendMessage(Utils.prefix + Utils.chat("Dit is een ongeldige sleutelnaam!"));
                        break;
                }
                return true;
            }

        } else {
            p.sendMessage(Utils.noPermission);
        }
        return false;
    }
}
