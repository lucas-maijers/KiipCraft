/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.outofuse.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class KiipEuroCommand implements CommandExecutor {

    public static int aantalKiipEuros;
    private final Main plugin;

    public KiipEuroCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("kiipeuro").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "Alleen spelers kunnen dit commando uitvoeren!");
            return true;
        }

        int defaultAmount = 64;
        int amount;

        Player p = (Player) sender;

        if (args.length != 0) {
            try {
                amount = Integer.parseInt(args[0]);
                aantalKiipEuros = amount;
            } catch (NumberFormatException ex) {
                p.sendMessage(prefix + "Verkeerde invoer, de invoer moet een getal zijn.");
                return true;
            }
        } else {
            aantalKiipEuros = defaultAmount;
        }

        if (p.hasPermission("kiipcraft.kiipeuro")) {
            p.sendMessage(prefix + "Oh, Kijk nou toch, je hebt de §6§lAlmachtige KiipEuro's §7gekregen!");
            p.getInventory().addItem(Utils.kiipEuro());
            return true;
        } else {
            p.sendMessage(prefix + "Hé hé hé, jij mag die dingen niet inspawnen. Foei!");

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.kiipeuro") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft geprobeerd §6§lKiipEuros §7in te spawnen, wat een stout jochie!");
                }
            }
        }
        return false;
    }

}
