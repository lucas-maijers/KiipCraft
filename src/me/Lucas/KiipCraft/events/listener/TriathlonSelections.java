/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.command.EventsCommand;
import me.Lucas.KiipCraft.events.ui.TriathlonUI;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TriathlonSelections implements Listener {

    public static Set<String> horseSelections = new HashSet<>();
    public static Set<String> spawnHorse = new HashSet<>();
    private static Main plugin;
    private static ConfigManager cfgm = ConfigManager.getManager();
    private File eventsFile;
    private FileConfiguration eventsCFG;

    public TriathlonSelections(Main plugin) {
        TriathlonSelections.plugin = plugin;

        eventsFile = new File(plugin.getDataFolder(), "events.yml");

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void removeHorses(Player p) {
        for (Entity e : p.getWorld().getEntities()) {
            if (e.getType() == EntityType.HORSE) {
                Horse h = (Horse) e;

                if (h.getCustomName().equals(Utils.chat("&7[&cEVENT&7] &3Triathlon Paard"))) {
                    h.remove();
                }
            }
        }
        TriathlonUI.areHorsesSpawned = false;

        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.hasPermission("kiipcraft.events")) {
                plr.sendMessage(Utils.prefix + Utils.chat(String.format("&b&l%s&7 heeft de &6&lEvent Paarden &7gekilled!", p.getName())));
            }
        }
    }

    public static void saveTeleportLocation(Player p) {
        FileConfiguration eventsCFG = cfgm.getEventsCFG();
        File eventsFile = new File(plugin.getDataFolder(), "events.yml");

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("Triathlon")) {
            path.createSection("Triathlon");
        }

        ConfigurationSection teleport = eventsCFG.getConfigurationSection("Events.Triathlon");

        assert teleport != null;
        if (!teleport.isConfigurationSection("Teleport")) {
            teleport.createSection("Teleport");
        }

        ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.Triathlon.Teleport");

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

    @EventHandler
    public void horseSpawnSelection(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.events")) {
            for (Map.Entry<String, String> entry : EventsCommand.selector.entrySet()) {
                if (entry.getKey().equals(p.getName())) {
                    if (entry.getValue().equals("triathlon")) {
                        if (p.getInventory().getItemInMainHand().equals(Utils.selectorTool())) {
                            if (horseSelections.contains(p.getName())) {
                                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    Location loc = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt een plek om een paard te spawnen geselecteerd!"));
                                    p.sendMessage(Utils.chat("&7Deze paard spawn bevindt zich op de locatie:"));
                                    p.sendMessage(Utils.chat("&aX: &d" + loc.getBlockX()));
                                    p.sendMessage(Utils.chat("&aY: &d" + loc.getBlockY()));
                                    p.sendMessage(Utils.chat("&aZ: &d" + loc.getBlockZ()));
                                    p.sendMessage("");
                                    p.sendMessage(Utils.chat("&7Het spawnpunt wordt nu opgeslagen!"));
                                    horseSelections.remove(p.getName());
                                    saveToConfig(p, loc);
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
        int toSpawn;

        if (p.hasPermission("kiipcraft.events")) {
            if (spawnHorse.contains(p.getName())) {
                e.setCancelled(true);
                try {
                    toSpawn = Integer.parseInt(message);
                    spawnHorses(p, toSpawn);
                } catch (NumberFormatException ex) {
                    p.sendMessage(Utils.prefix + Utils.chat("De invoer moet een getal zijn!"));
                }
            }
        }
    }

    private void spawnHorses(Player p, int spawnAmount) {
        eventsCFG = cfgm.getEventsCFG();

        if (!eventsCFG.isConfigurationSection("Events.Triathlon")) {
            p.sendMessage(Utils.prefix + Utils.chat("Kan de paarden niet spawnen, er is nog geen Paard spawnpunt aangemaakt!"));
            p.sendMessage(Utils.prefix + Utils.chat("Probeer het opnieuw!"));

            return;
        }

        ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.Triathlon.HorseLocation");
        spawnHorse.remove(p.getName());
        TriathlonUI.areHorsesSpawned = true;

        assert cs != null;

        for (int i = 0; i < spawnAmount; i++) {
            Horse horse = p.getWorld().spawn(new Location(p.getWorld(), cs.getInt("X"), cs.getInt("Y"), cs.getInt("Z")), Horse.class);
            horse.setAdult();
            horse.setCustomName(Utils.chat("&7[&cEVENT&7] &3Triathlon Paard"));
            horse.setCustomNameVisible(true);
            horse.setTamed(true);
            horse.setJumpStrength(0.4);

            horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.225);

            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        }

        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.hasPermission("kiipcraft.events")) {
                plr.sendMessage(Utils.prefix + Utils.chat(String.format("&d%s&7 heeft de &6&lEvent Paarden &7gespawned!", p.getName())));
            }
        }
    }

    private void saveToConfig(Player p, Location loc) {
        eventsCFG = cfgm.getEventsCFG();

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("Triathlon")) {
            path.createSection("Triathlon");
        }

        ConfigurationSection horsespawn = eventsCFG.getConfigurationSection("Events.Triathlon");

        assert horsespawn != null;

        if (!horsespawn.isConfigurationSection("HorseLocation")) {
            horsespawn.createSection("HorseLocation");
        }

        ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.Triathlon.HorseLocation");

        assert cs != null;

        cs.set("X", loc.getBlockX());
        cs.set("Y", loc.getBlockY());
        cs.set("Z", loc.getBlockZ());

        try {
            p.sendMessage(Utils.prefix + Utils.chat("De selectie is succesvol opgeslagen!"));
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Er is een fout opgetreden tijdens het opslaan, raadpleeg console voor meer informatie!"));
        }
    }


}
