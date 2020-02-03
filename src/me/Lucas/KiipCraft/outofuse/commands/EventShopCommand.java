package me.Lucas.KiipCraft.outofuse.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.outofuse.shopUI.EventShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class EventShopCommand implements CommandExecutor {

    private Main plugin;

    public EventShopCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("eventshop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(prefix + "Je opent de §3§lEvent Shop§7!");
            p.openInventory(EventShop.eventShop(p));
            return true;
        } else {
            sender.sendMessage(prefix + "alleen spelers kunnen dit commando uitvoeren!");
        }
        return false;
    }
}
