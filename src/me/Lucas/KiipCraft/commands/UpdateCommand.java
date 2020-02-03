package me.Lucas.KiipCraft.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.SpigotPluginUpdater;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class UpdateCommand implements CommandExecutor {

    private Main plugin;

    public UpdateCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("kiipcraftupdate").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Checken voor KiipCraft plugin updates.");

            SpigotPluginUpdater spu = new SpigotPluginUpdater(plugin, "http://51.68.47.8/pluginupdate/plugin.html");
            // Checkt voor Update
            if (spu.needsUpdate()) {
                sender.sendMessage(prefix + "Update gevonden, start update!");
                sender.sendMessage(prefix + "De update wordt bij de eerstvolgende restart toegepast.");
                spu.update();
            } else {
                sender.sendMessage(prefix + "Geen update gevonden");
            }
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.update") || p.getName().equals("Thunderkookie15")) {
            sender.sendMessage(prefix + "Checken voor KiipCraft plugin updates.");
            SpigotPluginUpdater spu = new SpigotPluginUpdater(plugin, "http://51.68.47.8/pluginupdate/plugin.html");

            // Checkt voor Update
            if (spu.needsUpdate()) {
                p.sendMessage(prefix + "Update gevonden, start update!");
                p.sendMessage(prefix + "De update wordt bij de eerstvolgende restart toegepast.");
                spu.update();
            } else {
                p.sendMessage(prefix + "Geen update gevonden");
            }
            return true;
        } else {
            p.sendMessage(prefix + "Dat is nou jammer, jij mag dit commando niet uitvoeren.");
        }
        return false;
    }
}
