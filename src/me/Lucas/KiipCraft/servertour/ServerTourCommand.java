/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.servertour;

import me.Lucas.KiipCraft.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class ServerTourCommand implements CommandExecutor {

    private Main plugin;

    private FileConfiguration warps;
    private File warpsfile;

    public ServerTourCommand(Main plugin) {
        this.plugin = plugin;

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");
        warps = YamlConfiguration.loadConfiguration(warpsfile);

        plugin.getCommand("servertour").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            Location loc = p.getLocation();
            World w = p.getWorld();

            if (args == null || args.length == 0) {
                try {
                    makeNewWarp(loc, p);
                    ServerTourRequestsGUI.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else if (args[0].equals("menu") && p.hasPermission("kiipcraft.servertour.menu")) {
                p.sendMessage(prefix + "Je opent het Servertour Menu.");
                warps = YamlConfiguration.loadConfiguration(warpsfile);
                try {
                    warps.save(warpsfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ServerTourRequestsGUI.initialize();
                p.openInventory(ServerTourRequestsGUI.serverTourUI(p));
                return true;
            } else {
                p.sendMessage(noPermission);
            }
            return true;
        }
        return false;
    }

    public void makeNewWarp(Location loc, Player creator) throws IOException {
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
}
