/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.storyline.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.storyline.shards.ShardItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class ShardCommand extends SubCommand {

    private Main plugin;

    public ShardCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.storyline")) {
            if (args.length == 1) {
                p.sendMessage(prefix + "Je moet de naam van een Shard invullen.");
                p.sendMessage(prefix + Utils.chat("Geldige namen zijn: fire, water, air, earth, lightning, light, darkness, life"));
                return;
            }
            switch (args[1].toLowerCase()) {
                case "fire":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &4&lFire&7!"));
                    p.getInventory().addItem(ShardItems.fireShard());
                    return;
                case "water":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &1&lWater&7!"));
                    p.getInventory().addItem(ShardItems.waterShard());
                    return;
                case "air":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &f&lAir&7!"));
                    p.getInventory().addItem(ShardItems.airShard());
                    return;
                case "earth":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &6&lEarth&7!"));
                    p.getInventory().addItem(ShardItems.earthShard());
                    return;
                case "lightning":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &b&lLight&e&lning&7!"));
                    p.getInventory().addItem(ShardItems.lightningShard());
                    return;
                case "light":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &e&lLight&7!"));
                    p.getInventory().addItem(ShardItems.lightShard());
                    return;
                case "darkness":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &5&lDark&0&lness&7!"));
                    p.getInventory().addItem(ShardItems.darknessShard());
                    return;
                case "life":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &a&lLife&7!"));
                    p.getInventory().addItem(ShardItems.lifeShard());
                    return;
                default:
                    p.sendMessage(prefix + "Onbekende Shardnaam.");
                    return;
            }
        } else {
            p.sendMessage(noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.shard;
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
