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
import org.bukkit.block.data.BlockData;
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
import java.util.*;

public class BuildBattleSelections implements Listener {

    public static Set<String> phase1 = new HashSet<>();
    private static final ConfigManager cfgman = ConfigManager.getManager();
    public static Set<String> bbAreaStep1 = new HashSet<>();
    public static Set<String> bbAreaStep2 = new HashSet<>();
    public static Set<String> bbAreaStep3 = new HashSet<>();
    private static Main plugin;
    private final ConfigManager cfgm = ConfigManager.getManager();
    private final File eventsFile;
    private FileConfiguration eventsCFG;
    private Location leftCorner;
    private Location rightCorner;
    private Location bbAreaLeftTopCorner;
    private Location bbAreaLeftBottomCorner;
    private Location bbAreaRightTopCorner;
    private Location bbAreaRightBottomCorner;

    public BuildBattleSelections(Main plugin) {
        BuildBattleSelections.plugin = plugin;

        eventsFile = new File(plugin.getDataFolder(), "events.yml");

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void saveTeleportLocation(Player p) {
        FileConfiguration eventsCFG = cfgman.getEventsCFG();
        File eventsFile = new File(plugin.getDataFolder(), "events.yml");

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("BuildBattle")) {
            path.createSection("BuildBattle");
        }

        ConfigurationSection teleport = eventsCFG.getConfigurationSection("Events.BuildBattle");

        assert teleport != null;
        if (!teleport.isConfigurationSection("Teleport")) {
            teleport.createSection("Teleport");
        }

        ConfigurationSection cs = eventsCFG.getConfigurationSection("Events.BuildBattle.Teleport");

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

    public static void removeWall(Player p) {
        FileConfiguration eventsCFG = cfgman.getEventsCFG();
        File eventsFile = new File(plugin.getDataFolder(), "events.yml");
        List<String> blocks = new ArrayList<>();

        World w = p.getWorld();

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;
        if (!path.isConfigurationSection("BuildBattle")) {
            p.sendMessage(Utils.prefix + Utils.chat("Er is nog geen muur geselecteerd!"));
            return;
        }

        ConfigurationSection savedWall = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall");

        assert savedWall != null;
        savedWall.set("WallRemoved", true);

        if (!savedWall.isConfigurationSection("WallCopy")) {
            savedWall.createSection("WallCopy");
        }

        ConfigurationSection wallData = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.WallCopy");
        assert wallData != null;

        ConfigurationSection leftTopCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.TopCorner");

        assert leftTopCorner != null;

        double topX = leftTopCorner.getDouble("X");
        double topY = leftTopCorner.getDouble("Y");
        double topZ = leftTopCorner.getDouble("Z");

        ConfigurationSection leftBottomCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.BottomCorner");

        assert leftBottomCorner != null;

        double bottomX = leftBottomCorner.getDouble("X");
        double bottomY = leftBottomCorner.getDouble("Y");
        double bottomZ = leftBottomCorner.getDouble("Z");

        double highestX = Math.max(topX, bottomX);
        double highestY = Math.max(topY, bottomY);
        double highestZ = Math.max(topZ, bottomZ);

        double lowestX = Math.min(topX, bottomX);
        double lowestY = Math.min(topY, bottomY);
        double lowestZ = Math.min(topZ, bottomZ);

        for (double x = lowestX; x <= highestX; x++) {
            for (double y = lowestY; y <= highestY; y++) {
                for (double z = lowestZ; z <= highestZ; z++) {
                    Block b = w.getBlockAt((int) x, (int) y, (int) z);
                    blocks.add(b.getBlockData().getAsString());
                    b.setType(Material.AIR);
                }
            }
        }

        wallData.set("Blocks", blocks);

        try {
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceWall(Player p) {
        cfgman.reloadEvents();
        FileConfiguration eventsCFG = cfgman.getEventsCFG();
        File eventsFile = new File(plugin.getDataFolder(), "events.yml");

        World w = p.getWorld();

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;
        if (!path.isConfigurationSection("BuildBattle")) {
            p.sendMessage(Utils.prefix + Utils.chat("Er is nog geen muur geselecteerd!"));
            return;
        }

        ConfigurationSection savedWall = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall");

        assert savedWall != null;
        savedWall.set("WallRemoved", false);

        ConfigurationSection wallData = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.WallCopy");
        assert wallData != null;

        ConfigurationSection leftTopCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.TopCorner");

        assert leftTopCorner != null;

        double topX = leftTopCorner.getDouble("X");
        double topY = leftTopCorner.getDouble("Y");
        double topZ = leftTopCorner.getDouble("Z");

        ConfigurationSection leftBottomCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.BottomCorner");

        assert leftBottomCorner != null;

        double bottomX = leftBottomCorner.getDouble("X");
        double bottomY = leftBottomCorner.getDouble("Y");
        double bottomZ = leftBottomCorner.getDouble("Z");

        double highestX = Math.max(topX, bottomX);
        double highestY = Math.max(topY, bottomY);
        double highestZ = Math.max(topZ, bottomZ);

        double lowestX = Math.min(topX, bottomX);
        double lowestY = Math.min(topY, bottomY);
        double lowestZ = Math.min(topZ, bottomZ);

        String[] blocks;
        blocks = ((List<String>) wallData.get("Blocks")).toArray(new String[0]);
        int i = 0;

        for (double x = lowestX; x <= highestX; x++) {
            for (double y = lowestY; y <= highestY; y++) {
                for (double z = lowestZ; z <= highestZ; z++) {
                    BlockData data = Bukkit.getServer().createBlockData(blocks[i]);
                    Block b = w.getBlockAt((int) x, (int) y, (int) z);
                    b.setType(data.getMaterial());

                    b.setBlockData(Bukkit.getServer().createBlockData(blocks[i]));
                    i++;
                }
            }
        }

        ConfigurationSection clearWallData = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall");

        assert clearWallData != null;
        clearWallData.set("WallCopy", null);

        try {
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetBuildArea(Player p) {
        World w = p.getWorld();
        FileConfiguration eventsCFG = cfgman.getEventsCFG();

        ConfigurationSection leftAreaTop = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.LeftBuildArea.TopCorner");

        assert leftAreaTop != null;
        double leftTopX = leftAreaTop.getDouble("X");
        double leftTopY = leftAreaTop.getDouble("Y");
        double leftTopZ = leftAreaTop.getDouble("Z");

        ConfigurationSection leftAreaBottom = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.LeftBuildArea.BottomCorner");

        assert leftAreaBottom != null;
        double leftBottomX = leftAreaBottom.getDouble("X");
        double leftBottomY = leftAreaBottom.getDouble("Y");
        double leftBottomZ = leftAreaBottom.getDouble("Z");

        double highestLeftX = Math.max(leftTopX, leftBottomX);
        double highestLeftY = Math.max(leftTopY, leftBottomY);
        double highestLeftZ = Math.max(leftTopZ, leftBottomZ);

        double lowestLeftX = Math.min(leftTopX, leftBottomX);
        double lowestLeftY = Math.min(leftTopY, leftBottomY);
        double lowestLeftZ = Math.min(leftTopZ, leftBottomZ);

        ConfigurationSection rightAreaTop = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.RightBuildArea.TopCorner");

        assert rightAreaTop != null;
        double rightTopX = rightAreaTop.getDouble("X");
        double rightTopY = rightAreaTop.getDouble("Y");
        double rightTopZ = rightAreaTop.getDouble("Z");

        ConfigurationSection rightAreaBottom = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.RightBuildArea.BottomCorner");

        assert rightAreaBottom != null;
        double rightBottomX = rightAreaBottom.getDouble("X");
        double rightBottomY = rightAreaBottom.getDouble("Y");
        double rightBottomZ = rightAreaBottom.getDouble("Z");

        double highestRightX = Math.max(rightTopX, rightBottomX);
        double highestRightY = Math.max(rightTopY, rightBottomY);
        double highestRightZ = Math.max(rightTopZ, rightBottomZ);

        double lowestRightX = Math.min(rightTopX, rightBottomX);
        double lowestRightY = Math.min(rightTopY, rightBottomY);
        double lowestRightZ = Math.min(rightTopZ, rightBottomZ);

        // Clear Left Side

        for (double x = lowestLeftX; x <= highestLeftX; x++) {
            for (double y = lowestLeftY; y <= highestLeftY; y++) {
                for (double z = lowestLeftZ; z <= highestLeftZ; z++) {
                    Block b = w.getBlockAt((int) x, (int) y, (int) z);

                    if (!(b.getType() == Material.CHEST)) {
                        b.setType(Material.AIR);
                    }
                }
            }
        }

        // Clear Right Side

        for (double x = lowestRightX; x <= highestRightX; x++) {
            for (double y = lowestRightY; y <= highestRightY; y++) {
                for (double z = lowestRightZ; z <= highestRightZ; z++) {
                    Block b = w.getBlockAt((int) x, (int) y, (int) z);

                    if (!(b.getType() == Material.CHEST)) {
                        b.setType(Material.AIR);
                    }
                }
            }
        }
    }

    @EventHandler
    public void createWall(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.events")) {
            for (Map.Entry<String, String> entry : EventsCommand.selector.entrySet()) {
                if (entry.getKey().equals(p.getName())) {
                    if (entry.getValue().equals("buildbattlewall")) {
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
                                EventsCommand.selector.remove(p.getName());
                                saveWallToConfig(p);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void createBuildArea(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.events")) {
            for (Map.Entry<String, String> entry : EventsCommand.selector.entrySet()) {
                if (entry.getKey().equals(p.getName())) {
                    if (entry.getValue().equals("buildarea")) {
                        if (p.getInventory().getItemInMainHand().equals(Utils.selectorTool())) {
                            if (!bbAreaStep1.contains(p.getName())) {
                                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    bbAreaLeftTopCorner = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de linker bovenhoek van de linker build area geselecteerd."));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + b.getLocation().getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + b.getLocation().getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + b.getLocation().getZ()));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7Selecteer nu de rechter onderhoek van de linker build area met je &c&l&nRechter&7 &c&l&nMuisknop&7!"));
                                    p.sendMessage(" ");
                                    bbAreaStep1.add(p.getName());
                                    return;
                                }
                            }

                            if (!bbAreaStep2.contains(p.getName()) && bbAreaStep1.contains(p.getName())) {
                                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    bbAreaLeftBottomCorner = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de rechter bovenhoek van de linker build area geselecteerd."));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + b.getLocation().getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + b.getLocation().getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + b.getLocation().getZ()));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7Selecteer nu de linker bovenhoek van de rechter build area met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                                    p.sendMessage(" ");
                                    bbAreaStep2.add(p.getName());
                                    return;
                                }
                            }
                            if (!bbAreaStep3.contains(p.getName()) && bbAreaStep2.contains(p.getName())) {
                                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    bbAreaRightTopCorner = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de linker bovenhoek van de rechter build area geselecteerd."));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + b.getLocation().getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + b.getLocation().getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + b.getLocation().getZ()));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7Selecteer nu de rechter onderhoek van de rechter build area met je &c&l&nRechter&7 &c&l&nMuisknop&7!"));
                                    p.sendMessage(" ");
                                    bbAreaStep3.add(p.getName());
                                    return;
                                }
                            }
                            if (bbAreaStep3.contains(p.getName())) {
                                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                    e.setCancelled(true);

                                    Block b = e.getClickedBlock();

                                    assert b != null;
                                    bbAreaRightBottomCorner = b.getLocation();

                                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de rechter onderhoek van de rechter build area geselecteerd."));
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + b.getLocation().getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + b.getLocation().getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + b.getLocation().getZ()));
                                    p.sendMessage(" ");

                                    EventsCommand.selector.remove(p.getName());
                                    bbAreaStep1.remove(p.getName());
                                    bbAreaStep2.remove(p.getName());
                                    bbAreaStep3.remove(p.getName());
                                    saveAreaToConfig(p);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void saveWallToConfig(Player p) {
        eventsCFG = cfgm.getEventsCFG();

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("BuildBattle")) {
            path.createSection("BuildBattle");
        }

        ConfigurationSection wallSection = eventsCFG.getConfigurationSection("Events.BuildBattle");

        assert wallSection != null;
        if (!wallSection.isConfigurationSection("Wall")) {
            wallSection.createSection("Wall");
        }

        ConfigurationSection wallData = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall");

        assert wallData != null;

        // Corner One
        if (!wallData.isConfigurationSection("TopCorner")) {
            wallData.createSection("TopCorner");
        }
        ConfigurationSection cornerOne = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.TopCorner");

        assert cornerOne != null;
        cornerOne.set("X", leftCorner.getX());
        cornerOne.set("Y", leftCorner.getY());
        cornerOne.set("Z", leftCorner.getZ());

        // Corner Two
        if (!wallData.isConfigurationSection("BottomCorner")) {
            wallData.createSection("BottomCorner");
        }
        ConfigurationSection cornerTwo = eventsCFG.getConfigurationSection("Events.BuildBattle.Wall.BottomCorner");

        assert cornerTwo != null;
        cornerTwo.set("X", rightCorner.getX());
        cornerTwo.set("Y", rightCorner.getY());
        cornerTwo.set("Z", rightCorner.getZ());

        try {
            p.sendMessage(Utils.prefix + Utils.chat("De selectie is succesvol opgeslagen!"));
            eventsCFG.save(eventsFile);
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Er is een fout opgetreden tijdens het opslaan, raadpleeg console voor meer informatie!"));
        }
    }

    private void saveAreaToConfig(Player p) {
        eventsCFG = cfgm.getEventsCFG();

        if (!eventsCFG.isConfigurationSection("Events")) {
            eventsCFG.createSection("Events");
        }

        ConfigurationSection path = eventsCFG.getConfigurationSection("Events");

        assert path != null;

        if (!path.isConfigurationSection("BuildBattle")) {
            path.createSection("BuildBattle");
        }

        ConfigurationSection areaSection = eventsCFG.getConfigurationSection("Events.BuildBattle");

        assert areaSection != null;
        if (!areaSection.isConfigurationSection("BuildArea")) {
            areaSection.createSection("BuildArea");
        }

        ConfigurationSection areaData = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea");

        assert areaData != null;
        if (!areaData.isConfigurationSection("LeftBuildArea")) {
            areaData.createSection("LeftBuildArea");
        }

        if (!areaData.isConfigurationSection("RightBuildArea")) {
            areaData.createSection("RightBuildArea");
        }

        // Left Data
        ConfigurationSection leftData = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.LeftBuildArea");
        assert leftData != null;
        if (!leftData.isConfigurationSection("TopCorner")) {
            leftData.createSection("TopCorner");
        }

        ConfigurationSection leftTopCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.LeftBuildArea.TopCorner");

        assert leftTopCorner != null;
        leftTopCorner.set("X", bbAreaLeftTopCorner.getX());
        leftTopCorner.set("Y", bbAreaLeftTopCorner.getY());
        leftTopCorner.set("Z", bbAreaLeftTopCorner.getZ());

        if (!leftData.isConfigurationSection("BottomCorner")) {
            leftData.createSection("BottomCorner");
        }
        ConfigurationSection leftBottomCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.LeftBuildArea.BottomCorner");

        assert leftBottomCorner != null;
        leftBottomCorner.set("X", bbAreaLeftBottomCorner.getX());
        leftBottomCorner.set("Y", bbAreaLeftBottomCorner.getY());
        leftBottomCorner.set("Z", bbAreaLeftBottomCorner.getZ());

        // Right Data
        ConfigurationSection rightData = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.RightBuildArea");
        assert rightData != null;
        if (!rightData.isConfigurationSection("TopCorner")) {
            rightData.createSection("TopCorner");
        }

        ConfigurationSection rightTopCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.RightBuildArea.TopCorner");
        assert rightTopCorner != null;

        rightTopCorner.set("X", bbAreaRightTopCorner.getX());
        rightTopCorner.set("Y", bbAreaRightTopCorner.getY());
        rightTopCorner.set("Z", bbAreaRightTopCorner.getZ());

        if (!rightData.isConfigurationSection("BottomCorner")) {
            rightData.createSection("BottomCorner");
        }
        ConfigurationSection rightBottomCorner = eventsCFG.getConfigurationSection("Events.BuildBattle.BuildArea.RightBuildArea.BottomCorner");

        assert rightBottomCorner != null;
        rightBottomCorner.set("X", bbAreaRightBottomCorner.getX());
        rightBottomCorner.set("Y", bbAreaRightBottomCorner.getY());
        rightBottomCorner.set("Z", bbAreaRightBottomCorner.getZ());

        try {
            p.sendMessage(Utils.prefix + Utils.chat("De selectie is succesvol opgeslagen!"));
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
