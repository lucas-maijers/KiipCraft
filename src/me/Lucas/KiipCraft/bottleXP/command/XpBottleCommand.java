/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.bottleXP.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class XpBottleCommand extends SubCommand {

    public static Set<Integer> amountList = new HashSet<>();
    public static int amountGet;
    public static String bottler;
    private static final int maxAmount = 50;
    private final Main plugin;

    private final Set<String> delayPlayers = new HashSet<>();

    public XpBottleCommand(Main plugin) {
        this.plugin = plugin;
    }

    public static void initializelist() {
        for (int i = 0; i < maxAmount; i++) {
            amountList.add(i);
        }
    }

    @Override
    public void onCommand(Player p, String[] args) {
        bottler = p.getName();

        if (args.length == 1) {
            p.sendMessage(Utils.prefix + "Je moet een getal invoeren.");
            return;
        }

        int amount;

        // Zet tekst over in Int
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            p.sendMessage(Utils.prefix + "Verkeerde invoer, de invoer moet een geheel getal zijn.");
            return;
        }

        amountGet = amount;

        // Max Amount Check
        if (amount > maxAmount) {
            p.sendMessage(Utils.prefix + "Je kan maximaal §a50 Levels §7in een flesje stoppen.");
            return;
        }

        if (p.hasPermission("kiipcraft.donator")) {

            if (delayPlayers.contains(p.getName())) {
                p.sendMessage(Utils.prefix + Utils.chat("Dit commando zit nog op cooldown!"));
                return;
            }

            if (p.getInventory().firstEmpty() == -1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je inventory zit vol, dus je kan dit momenteel niet doen!"));
                return;
            }

            if (amount > p.getLevel()) {
                p.sendMessage(Utils.prefix + Utils.chat(String.format("Helaas, het ingevoerde aantal levels heb jij niet, je komt &a%d Levels &7te kort.", amount - p.getLevel())));
                return;
            }
            if (amount >= 10) {
                if (p.getInventory().contains(Material.GLASS_BOTTLE)) {
                    p.sendMessage(Utils.prefix + "Je hebt zojuist §a" + amount + " Levels §7gebottled.");
                    p.setLevel(p.getLevel() - amount);
                    p.getInventory().removeItem(Utils.glasFlesje());
                    p.getInventory().addItem(Utils.xpDrinkFles());
                    delayPlayers.add(p.getName());
                    startDelay(p);
                } else if (!(p.getInventory().contains(Material.GLASS_BOTTLE))) {
                    p.sendMessage(Utils.prefix + "Je moet een §6Glass Bottle§7 in je inventory hebben om dit te doen.");
                }
            } else {
                p.sendMessage(Utils.prefix + "Je moet minimaal §a10 Levels§7 bottlen.");
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.bottlexp;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> amountList = new ArrayList<>();
            for (int i = 10; i <= 50; i++) {
                amountList.add(String.valueOf(i));
            }
            Arrays.sort(amountList.toArray());
            return amountList;
        }

        return null;
    }

    private void startDelay(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                delayPlayers.remove(p.getName());
            }
        }.runTaskLater(plugin, 20 * 5);
    }
}
