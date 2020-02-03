package me.Lucas.KiipCraft.roleplay.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.shards.ShardItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class ShardCommand implements CommandExecutor {

    private Main plugin;

    public ShardCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("shard").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Alleen spelers kunnen dit commando gebruiken!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.shard")) {
            if (args.length == 0) {
                p.sendMessage(prefix + "Je moet de naam van een Shard invullen.");
                p.sendMessage(prefix + Utils.chat("Geldige namen zijn: fire, water, air, earth, lightning, light, darkness, life"));
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "fire":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &4&lFire&7!"));
                    p.getInventory().addItem(ShardItems.fireShard());
                    return true;
                case "water":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &1&lWater&7!"));
                    p.getInventory().addItem(ShardItems.waterShard());
                    return true;
                case "air":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &f&lAir&7!"));
                    p.getInventory().addItem(ShardItems.airShard());
                    return true;
                case "earth":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &6&lEarth&7!"));
                    p.getInventory().addItem(ShardItems.earthShard());
                    return true;
                case "lightning":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &b&lLight&e&lning&7!"));
                    p.getInventory().addItem(ShardItems.lightningShard());
                    return true;
                case "light":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &e&lLight&7!"));
                    p.getInventory().addItem(ShardItems.lightShard());
                    return true;
                case "darkness":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &5&lDark&0&lness&7!"));
                    p.getInventory().addItem(ShardItems.darknessShard());
                    return true;
                case "life":
                    p.sendMessage(prefix + Utils.chat("Je ontvangt de &c&lShard of &a&lLife&7!"));
                    p.getInventory().addItem(ShardItems.lifeShard());
                    return true;
                default:
                    p.sendMessage(prefix + "Onbekende Shardnaam.");
                    return true;
            }
        } else {
            p.sendMessage(prefix + "Sorry, maar jij mag dit commando niet gebruiken!");
        }
        return false;
    }
}
