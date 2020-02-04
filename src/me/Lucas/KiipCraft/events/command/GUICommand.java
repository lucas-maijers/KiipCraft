package me.Lucas.KiipCraft.events.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.ui.MainEventsUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class GUICommand implements CommandExecutor {

    private Main plugin;

    public GUICommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("eventsadmin").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Alleen spelers kunnen dit commando uitvoeren!");
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.events")) {
            p.sendMessage(prefix + "Bezig met het openen van de §3§lEventsAdmin GUI§7...");
            p.openInventory(MainEventsUI.mainGUI(p));
            return true;
        } else {
            p.sendMessage(noPermission);
        }
        return false;
    }
}
