/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.servertour;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerTourCommand extends SubCommand {

    private Main plugin;
    private ConfigManager cfgm = ConfigManager.getManager();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private FileConfiguration warps;
    private File warpsfile;

    public ServerTourCommand(Main plugin) {
        this.plugin = plugin;

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
    }


    @Override
    public void onCommand(Player p, String[] args) {
        Location loc = p.getLocation();
        Date date = new Date();

        if (args == null || args.length == 1) {
            try {
                makeNewWarp(loc, formatter.format(date), p);
                ServerTourRequestsGUI.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (args[1].equals("menu")) {
            if (p.hasPermission("kiipcraft.staff")) {
                p.sendMessage(Utils.prefix + "Je opent het Servertour Menu.");
                warps = YamlConfiguration.loadConfiguration(warpsfile);
                try {
                    warps.save(warpsfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ServerTourRequestsGUI.initialize();
                p.openInventory(ServerTourRequestsGUI.serverTourUI(p));
            } else {
                p.sendMessage(Utils.noPermission);
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.servertour;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    private void makeNewWarp(Location loc, String date, Player creator) throws IOException {
        warps = cfgm.getWarpsCFG();

        if (!warps.isConfigurationSection("Warps")) {
            warps.createSection("Warps");
        }


        ConfigurationSection cs = warps.getConfigurationSection("Warps");
        assert cs != null;
        if (cs.isConfigurationSection(creator.getName())) {
            creator.sendMessage(Utils.prefix + "Sorry, maar jij kan dit commando nu niet uitvoeren, je hebt al een servertour locatie!");
            return;
        } else {
            creator.sendMessage(Utils.prefix + "Succes, je hebt een locatie aangemaakt voor de servertour.");
        }
        cs.createSection(creator.getName());
        ConfigurationSection warp = warps.getConfigurationSection("Warps." + creator.getName());

        assert warp != null;
        warp.set("X", loc.getX());
        warp.set("Y", loc.getY());
        warp.set("Z", loc.getZ());
        warp.set("world", loc.getWorld().getName());
        warp.set("Date", date);
        warp.set("creator", creator.getName());
        warps.save(warpsfile);
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        List<String> menu = new ArrayList<>();
        List<String> completionList = new ArrayList<>();

        menu.add("menu");
        if (args.length == 2) {

            if (!args[1].equals("")) {
                for (String s : menu) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return menu;
        }

        return null;
    }
}
