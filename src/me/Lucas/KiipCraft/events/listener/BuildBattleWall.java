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
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BuildBattleWall implements Listener {

    public static Set<String> phase1 = new HashSet<>();
    private Main plugin;
    private ConfigManager cfgm = ConfigManager.getManager();
    private File eventsFile;
    private FileConfiguration eventsCFG;
    private Location leftCorner;
    private Location rightCorner;

    public BuildBattleWall(Main plugin) {
        this.plugin = plugin;

        eventsFile = new File(plugin.getDataFolder(), "events.yml");

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void createWall(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.staff")) {
            for (Map.Entry<String, String> entry : EventsCommand.selector.entrySet()) {
                if (entry.getKey().equals(p.getName())) {
                    if (entry.getValue().equals("bbwall")) {
                        if (p.getInventory().getItemInMainHand().equals(Utils.selectorTool())) {
                            if (!(phase1.contains(p.getName()))) {
                                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();
                                    assert b != null;

                                    leftCorner = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de linker bovenhoek van de build battle muur geselecteerd."));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + b.getLocation().getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + b.getLocation().getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + b.getLocation().getZ()));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7Selecteer nu de rechter onderhoek van de build battle muur met je &c&l&nRechter&7 &c&l&nMuisknop&7!"));
                                    p.sendMessage(" ");

                                    phase1.add(p.getName());
                                    return;
                                }
                            }
                        }

                        if (phase1.contains(p.getName())) {
                            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                e.setCancelled(true);

                                Block b2 = e.getClickedBlock();
                                assert b2 != null;

                                rightCorner = b2.getLocation();

                                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de rechter onderhoek van de build battle muur geselecteerd."));
                                p.sendMessage(" ");
                                p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                                p.sendMessage(Utils.chat("&d&lX: &a" + b2.getLocation().getX()));
                                p.sendMessage(Utils.chat("&d&lY: &a" + b2.getLocation().getY()));
                                p.sendMessage(Utils.chat("&d&lZ: &a" + b2.getLocation().getZ()));
                                p.sendMessage(" ");

                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void saveToConfig(Player p) {
        eventsCFG = cfgm.getEventsCFG();

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (path.isConfigurationSection("BuildBattle")) {
            path.createSection("BuildBattle");
        }

        ConfigurationSection wallData = eventsCFG.getConfigurationSection("Events.BuildBattle");

        assert wallData != null;

        // Corner One
        wallData.createSection("TopCorner");
        ConfigurationSection cornerOne = eventsCFG.getConfigurationSection("Events.BuildBattle.TopCorner");

        assert cornerOne != null;
        cornerOne.set("X", leftCorner.getX());
        cornerOne.set("Y", leftCorner.getY());
        cornerOne.set("Z", leftCorner.getZ());

        // Corner Two
        wallData.createSection("BottomCorner");
        ConfigurationSection cornerTwo = eventsCFG.getConfigurationSection("Events.BuildBattle.BottomCorner");

        assert cornerTwo != null;
        cornerTwo.set("X", rightCorner.getX());
        cornerTwo.set("Y", rightCorner.getY());
        cornerTwo.set("Z", rightCorner.getZ());

        try {
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Er is een fout opgetreden tijdens het opslaan, raadpleeg console voor meer informatie!"));
        }
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(Utils.selectorTool())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(Utils.selectorTool())) {
                e.getDrops().remove(Utils.selectorTool());
            }
        }
    }
}
