/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.bottleXP.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class XpBottleCommand implements CommandExecutor {

    public static Set<Integer> amountList = new HashSet<>();
    public static int amountGet;
    public static String bottler;
    public static int maxAmount = 50;
    private Main plugin;

    public XpBottleCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("bottlexp").setExecutor(this);
    }

    public static void initializelist() {
        for (int i = 0; i < maxAmount; i++) {
            amountList.add(i);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.consoleMessage);
            return true;
        }

        Player p = (Player) sender;
        bottler = p.getName();

        if (args.length == 0) {
            p.sendMessage(prefix + "Je moet een getal invoeren.");
            return true;
        }

        int amount = 0;

        // Zet tekst over in Int
        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            p.sendMessage(prefix + "Verkeerde invoer, de invoer moet een geheel getal zijn.");
            return true;
        }

        amountGet = amount;

        // Max Amount Check
        if (amount > maxAmount) {
            p.sendMessage(prefix + "Je kan maximaal §a50 Levels §7 in een flesje stoppen.");
            return true;
        }

        if (p.hasPermission("kiipcraft.donator")) {
            if (amount > p.getLevel()) {
                p.sendMessage(prefix + "Helaas, het ingevoerde aantal levels heb jij niet, je komt §a" + (amount - p.getLevel()) + " Levels §7te kort.");
                return true;
            }
            if (amount >= 10 && p.getInventory().contains(Material.GLASS_BOTTLE)) {
                p.sendMessage(prefix + "Je hebt zojuist §a" + amount + " Levels §7gebottled");
                p.setLevel(p.getLevel() - amount);
                p.getInventory().removeItem(Utils.glasFlesje());
                p.getInventory().addItem(Utils.xpDrinkFles());
                return true;
            } else if (!(p.getInventory().contains(Material.GLASS_BOTTLE))) {
                p.sendMessage(prefix + "Je moet een §6Glass Bottle§7 in je inventory hebben om dit te doen.");
                return true;
            } else {
                p.sendMessage(prefix + "Je moet minimaal §a10 Levels§7 bottlen.");
            }
            return true;
        } else {
            p.sendMessage(noPermission);
        }
        return false;
    }
}
