package me.Lucas.KiipCraft.roleplay.commands;

import me.Lucas.KiipCraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class CloudCommand implements CommandExecutor {

    public static Set<String> playerList = new HashSet<>();
    private Main plugin;
    private Plugin pl;

    public CloudCommand(Main plugin, Plugin pl) {
        this.plugin = plugin;

        this.pl = pl;

        plugin.getCommand("flycloud").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Helaas, alleen spelers kunnen dit commando uitvoeren.");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.flycloud") && !(playerList.contains(p.getName()))) {
            playerList.add(p.getName());
            p.sendMessage(prefix + "Je hebt zojuist de flycloud aangezet!");
            return true;
        } else if (p.hasPermission("kiipcraft.flycloud") && playerList.contains(p.getName())) {
            playerList.remove(p.getName());
            p.sendMessage(prefix + "Je hebt zojuist de flycloud uitgezet!");
            Bukkit.getScheduler().cancelTasks((Plugin) pl);
            return true;
        } else {
            p.sendMessage(prefix + "Helaas, jij mag dit commando niet uitvoeren");
        }

        return false;
    }
}
