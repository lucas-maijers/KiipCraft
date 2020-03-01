/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventTokenCommand extends SubCommand {

    private Main plugin;

    public static int aantal = 64;

    public EventTokenCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public void onCommand(Player p, String[] args) {

        if (p.hasPermission("kiipcraft.events")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt jezelf Event Tokens gegeven"));
                p.getInventory().addItem(Utils.eventToken());
            } else if (args.length == 2) {
                try {
                    aantal = Integer.parseInt(args[1]);
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt &6" + aantal + "&7 Event Tokens aan jezelf gegeven!"));
                    p.getInventory().addItem(Utils.eventToken());
                } catch (NumberFormatException e) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet een getal invullen!"));
                }
            } else if (args.length == 3) {
                aantal = Integer.parseInt(args[1]);
                for (Player plr : Bukkit.getOnlinePlayers()) {
                    if (args[2].equals(plr.getName())) {
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt &6" + aantal + "&7 Event Tokens aan &d&l" + plr.getName() + "&7 gegeven!"));
                        plr.getInventory().addItem(Utils.eventToken());
                    } else if (!Bukkit.getOnlinePlayers().contains(plr)) {
                        p.sendMessage(Utils.prefix + Utils.chat("Deze speler is niet gevonden!"));
                    }
                }
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.eventtoken;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[]{"token"};
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {

        if (args.length == 2) {
            List<String> amountList = new ArrayList<>();
            for (int i = 1; i <= 64; i++) {
                amountList.add(String.valueOf(i));
            }
            Arrays.sort(amountList.toArray());
            return amountList;
        }

        return null;
    }
}
