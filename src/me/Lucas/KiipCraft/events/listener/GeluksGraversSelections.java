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
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GeluksGraversSelections implements Listener {

    public static Set<String> pillarSelection = new HashSet<>();
    private static Main plugin;
    private static final ConfigManager cfgm = ConfigManager.getManager();
    private final File eventsFile;
    private Location firstLocation;

    private final Set<Location> pillarLocations = new HashSet<>();

    public GeluksGraversSelections(Main plugin) {
        GeluksGraversSelections.plugin = plugin;

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

        if (!path.isConfigurationSection("GeluksGravers")) {
            path.createSection("GeluksGravers");
        }

        ConfigurationSection teleport = eventsCFG.getConfigurationSection("Events.GeluksGravers");

        assert teleport != null;
        if (!teleport.isConfigurationSection("Teleport")) {
            teleport.createSection("Teleport");
        }

        ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.GeluksGravers.Teleport");

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

    public static void resetPillars(Player p) {
        FileConfiguration eventsCFG = cfgm.getEventsCFG();
        World w = p.getWorld();
        String[] blocks;

        if (!eventsCFG.isConfigurationSection("Events.GeluksGravers")) {
            p.sendMessage(Utils.prefix + Utils.chat("Er zijn nog geen Geluks Gravers pilaren ingesteld!"));
            return;
        }

        ConfigurationSection preset = eventsCFG.getConfigurationSection("Events.GeluksGravers.Preset");

        blocks = ((List<String>) preset.get("Blocks")).toArray(new String[0]);

        for (String key : eventsCFG.getConfigurationSection("Events.GeluksGravers.Pilaren").getKeys(false)) {
            ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.GeluksGravers.Pilaren." + key);
            assert cs != null;

            Location loc = (Location) cs.get("Data");

            assert loc != null;
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            for (int i = 0; i <= 53; i++) {

                if (i != 0) {
                    y--;
                }
                BlockData data = Bukkit.getServer().createBlockData(blocks[i]);
                Block b = w.getBlockAt(x, y, z);

                b.setType(data.getMaterial());

                b.setBlockData(Bukkit.getServer().createBlockData(blocks[i]));
            }

        }
    }

    @EventHandler
    public void selectGeluksGraversPillar(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.events")) {
            for (Map.Entry<String, String> entry : EventsCommand.selector.entrySet()) {
                if (entry.getKey().equals(p.getName())) {
                    if (entry.getValue().equals("geluksgravers")) {
                        if (p.getInventory().getItemInMainHand().equals(Utils.selectorTool())) {
                            if (pillarSelection.contains(p.getName())) {
                                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    Location loc = b.getLocation();
                                    pillarLocations.add(loc);

                                    if (pillarLocations.size() == 1) {
                                        firstLocation = loc;
                                    }

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt een pilaar van de Geluks Gravers geselecteerd!"));
                                    p.sendMessage(Utils.chat("&7Deze pilaar bevindt zich op de locatie:"));
                                    p.sendMessage(Utils.chat("&aX: &d" + loc.getBlockX()));
                                    p.sendMessage(Utils.chat("&aY: &d" + loc.getBlockY()));
                                    p.sendMessage(Utils.chat("&aZ: &d" + loc.getBlockZ()));
                                    p.sendMessage("");
                                    p.sendMessage(Utils.chat("&7Om de operatie af te ronden en de pilaren op te slaan, typ dan &a\"Done\" &7in de chat!"));
                                    p.sendMessage(Utils.chat("&7Al wil je nog meer pilaren selecteren, klik dan met &cLinker Muisknop &7op de volgende pilaar!"));
                                    p.sendMessage("");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChatMessage(PlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        if (p.hasPermission("kiipcraft.events")) {
            if (pillarSelection.contains(p.getName())) {
                if (message.equals("Done")) {
                    e.setCancelled(true);
                    pillarSelection.remove(p.getName());
                    EventsCommand.selector.remove(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de pilaren selectie afgerond, de data zal nu worden opgeslagen!"));
                    saveToConfig(p);
                }
            }
        }
    }

    private void saveToConfig(Player p) {
        FileConfiguration eventsCFG = cfgm.getEventsCFG();
        List<String> pillarBlocks = new ArrayList<>();
        int pillarAmount = 0;

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("GeluksGravers")) {
            path.createSection("GeluksGravers");
        }

        ConfigurationSection data = eventsCFG.getConfigurationSection("Events.GeluksGravers");

        assert data != null;

        if (!data.isConfigurationSection("Pilaren")) {
            data.createSection("Pilaren");
        }

        ConfigurationSection pilaren = eventsCFG.getConfigurationSection("Events.GeluksGravers.Pilaren");

        assert pilaren != null;

        if (!data.isConfigurationSection("Preset")) {
            data.createSection("Preset");
        }

        for (Location loc : pillarLocations) {
            if (!pilaren.isConfigurationSection("Pillar" + pillarAmount)) {
                pilaren.createSection("Pillar" + pillarAmount);
            }
            ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.GeluksGravers.Pilaren.Pillar" + pillarAmount);

            assert cs != null;
            cs.set("Data", loc);

            ++pillarAmount;
        }

        ConfigurationSection preset = eventsCFG.getConfigurationSection("Events.GeluksGravers.Preset");

        assert preset != null;

        for (double i = 0; i <= 53; i++) {
            if (i != 0) {
                firstLocation.setY(firstLocation.getY() - 1);
            }
            pillarBlocks.add(firstLocation.getBlock().getBlockData().getAsString());
        }

        preset.set("Blocks", pillarBlocks);

        try {
            eventsCFG.save(eventsFile);
            p.sendMessage(Utils.prefix + Utils.chat("De selectie is succesvol opgeslagen!"));
            pillarLocations.clear();
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Er is een fout opgetreden tijdens het opslaan, raadpleeg console voor meer informatie!"));
        }
    }
}
