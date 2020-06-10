/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.command.EventsCommand;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SpleefSelections implements Listener {

    private static Main plugin;

    private static final ConfigManager cfgm = ConfigManager.getManager();

    private final File eventsFile;

    private Location middleBlock;
    private Location outerBlock;

    private final Set<String> phase1 = new HashSet<>();

    public SpleefSelections(Main plugin) {
        SpleefSelections.plugin = plugin;

        eventsFile = new File(plugin.getDataFolder(), "events.yml");

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void saveTeleportLocation(Player p) {
        FileConfiguration eventsCFG = cfgm.getEventsCFG();
        File eventsFile = new File(plugin.getDataFolder(), "events.yml");

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("Spleef")) {
            path.createSection("Spleef");
        }

        ConfigurationSection teleport = eventsCFG.getConfigurationSection("Events.Spleef");

        assert teleport != null;
        if (!teleport.isConfigurationSection("Teleport")) {
            teleport.createSection("Teleport");
        }

        ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.Spleef.Teleport");

        assert cs != null;
        cs.set("X", p.getLocation().getX());
        cs.set("Y", p.getLocation().getY());
        cs.set("Z", p.getLocation().getZ());

        try {
            p.sendMessage(Utils.prefix + Utils.chat("De teleport locatie is aangemaakt!"));
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Er is een fout opgetreden tijdens het opslaan, raadpleeg console voor meer informatie!"));
        }
    }

    public static void regenerateCircle(Player p) {
        FileConfiguration eventsCFG = cfgm.getEventsCFG();
        World w = p.getWorld();

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;
        if (!path.isConfigurationSection("Spleef")) {
            p.sendMessage(Utils.prefix + Utils.chat("Er is nog geen spleef area aangegeven, het gebied kan dus niet worden gereset!"));
            return;
        }

        ConfigurationSection data = eventsCFG.getConfigurationSection("Events.Spleef.Locations");

        assert data != null;
        double radius = data.getDouble("Radius");
        int r = (int) radius + 1;

        ConfigurationSection middleData = eventsCFG.getConfigurationSection("Events.Spleef.Locations.Middle");

        assert middleData != null;
        int cx = middleData.getInt("X");
        int y = middleData.getInt("Y");
        int cz = middleData.getInt("Z");

        int rSquared = r * r;

        for (int x = cx - r; x <= cx + r; x++) {
            for (int z = cz - r; z <= cz + r; z++) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
                    if (w.getBlockAt(x, y, z).getType() == Material.AIR) {
                        Random random = new Random();
                        int clay = random.nextInt(2);

                        if (clay == 1) {
                            w.getBlockAt(x, y, z).setType(Material.CLAY);
                        } else {
                            w.getBlockAt(x, y, z).setType(Material.SNOW_BLOCK);
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void selectSpleefArea(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.events")) {
            for (Map.Entry<String, String> entry : EventsCommand.selector.entrySet()) {
                if (entry.getKey().equals(p.getName())) {
                    if (entry.getValue().equals("spleef")) {
                        if (p.getInventory().getItemInMainHand().equals(Utils.selectorTool())) {
                            if (!phase1.contains(p.getName())) {
                                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    middleBlock = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het midden geselecteerd, selecteer nu de buitenste ring."));
                                    phase1.add(p.getName());
                                    return;
                                }
                            }

                            if (phase1.contains(p.getName())) {
                                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b1 = e.getClickedBlock();

                                    assert b1 != null;
                                    outerBlock = b1.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de buitenste rij geselecteerd, data wordt gechecked en opgeslagen!"));
                                    p.sendMessage(Utils.chat("&7..."));
                                    phase1.remove(p.getName());

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (checkCircle(p, middleBlock, outerBlock)) {
                                                saveToConfig(p, middleBlock, outerBlock);
                                            }
                                        }
                                    }.runTaskLater(plugin, 40);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkCircle(Player p, Location center, Location edge) {

        int cx = center.getBlockX();
        int cz = center.getBlockZ();

        int ex = edge.getBlockX();
        int ez = edge.getBlockZ();

        if (cx == ex || cz == ez) {
            p.sendMessage(Utils.prefix + Utils.chat("Data check voltooid, data wordt opgeslagen"));
            return true;
        }

        p.sendMessage(Utils.prefix + Utils.chat("De blokken hebben geen enkele overeenkomende coordinaat, data wordt niet opgeslagen!"));
        return false;
    }

    private void saveToConfig(Player p, Location center, Location edge) {
        FileConfiguration eventsCFG = cfgm.getEventsCFG();

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;
        if (!path.isConfigurationSection("Spleef")) {
            path.createSection("Spleef");
        }

        ConfigurationSection data = eventsCFG.getConfigurationSection("Events.Spleef");

        assert data != null;
        if (!data.isConfigurationSection("Locations")) {
            data.createSection("Locations");
        }

        ConfigurationSection locationData = eventsCFG.getConfigurationSection("Events.Spleef.Locations");

        assert locationData != null;
        if (!locationData.isConfigurationSection("Middle")) {
            locationData.createSection("Middle");
        }

        ConfigurationSection middle = eventsCFG.getConfigurationSection("Events.Spleef.Locations.Middle");

        assert middle != null;
        middle.set("X", center.getBlockX());
        middle.set("Y", center.getBlockY());
        middle.set("Z", center.getBlockZ());

        locationData.set("Radius", center.distance(edge));

        try {
            p.sendMessage(Utils.prefix + Utils.chat("De selectie is succesvol opgeslagen!"));
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Er is een fout opgetreden tijdens het opslaan, raadpleeg console voor meer informatie!"));
        }
    }
}
