/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.servertour;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.SubCommand;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class ServerTourCommand extends SubCommand {

    private Main plugin;

    private FileConfiguration warps;
    private File warpsfile;

    public ServerTourCommand(Main plugin) {
        this.plugin = plugin;

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warps = YamlConfiguration.loadConfiguration(warpsfile);
    }


    @Override
    public void onCommand(Player p, String[] args) {
        Location loc = p.getLocation();
        World w = p.getWorld();

        if (args == null || args.length == 1) {
            try {
                makeNewWarp(loc, p);
                ServerTourRequestsGUI.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (args[1].equals("menu") && p.hasPermission("kiipcraft.servertour")) {
            p.sendMessage(prefix + "Je opent het Servertour Menu.");
            warps = YamlConfiguration.loadConfiguration(warpsfile);
            try {
                warps.save(warpsfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerTourRequestsGUI.initialize();
            p.openInventory(ServerTourRequestsGUI.serverTourUI(p));
            return;
        } else {
            p.sendMessage(noPermission);
        }
        return;
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

    private void makeNewWarp(Location loc, Player creator) throws IOException {
        if (!warps.isConfigurationSection("Warps")) {
            warps.createSection("Warps");
        }

        warps = YamlConfiguration.loadConfiguration(warpsfile);

        ConfigurationSection cs = warps.getConfigurationSection("Warps");
        assert cs != null;
        if (cs.isConfigurationSection(creator.getName())) {
            creator.sendMessage(prefix + "Sorry, maar jij kan dit commando nu niet uitvoeren");
            return;
        } else {
            creator.sendMessage(prefix + "Succes, je hebt een locatie aangemaakt voor de servertour");
        }
        cs.createSection(creator.getName());
        ConfigurationSection warp = warps.getConfigurationSection("Warps." + creator.getName());

        assert warp != null;
        warp.set("X", loc.getX());
        warp.set("Y", loc.getY());
        warp.set("Z", loc.getZ());
        warp.set("world", loc.getWorld().getName());
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
