/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.commands.DungeonsCommand;
import me.Lucas.KiipCraft.dungeons.items.DungeonItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DungeonGateCreation implements Listener {

    private Main plugin;

    private File dungeonGatesFile;
    private FileConfiguration dungeonGatesCFG;

    public static Set<String> phase1 = new HashSet<>();
    public static Set<String> phase2 = new HashSet<>();
    public static Set<String> phase3 = new HashSet<>();

    public static Set<String> phasesComplete = new HashSet<>();
    public static Set<String> lockType = new HashSet<>();

    private String dungeonGateName;
    private String dungeonType;

    private Location leftCorner;
    private Location rightCorner;
    private Location sleutelBlock;

    public DungeonGateCreation(Main plugin) {
        this.plugin = plugin;

        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void createDungeonGate(PlayerInteractEvent e) {
        Player p = e.getPlayer();


        if (p.hasPermission("kiipcraft.dungeons")) {
            if (DungeonsCommand.creatingPlayer.contains(p.getName())) {
                if (p.getItemInHand().equals(DungeonItems.dungeonGateTool())) {
                    // Fase 1
                    if (!phase1.contains(p.getName())) {
                        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                            e.setCancelled(true);

                            Block b = e.getClickedBlock();
                            assert b != null;
                            leftCorner = b.getLocation();

                            p.sendMessage(Utils.prefix + Utils.chat("Je hebt de linker bovenhoek van de dungeon gate geselecteerd."));
                            p.sendMessage(" ");
                            p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                            p.sendMessage(Utils.chat("&d&lX: &a" + b.getLocation().getX()));
                            p.sendMessage(Utils.chat("&d&lY: &a" + b.getLocation().getY()));
                            p.sendMessage(Utils.chat("&d&lZ: &a" + b.getLocation().getZ()));
                            p.sendMessage(" ");
                            p.sendMessage(Utils.chat("&7Selecteer nu de rechter onderhoek van de dungeon gate met je &c&l&nRechter&7 &c&l&nMuisknop&7!"));
                            p.sendMessage(" ");

                            phase1.add(p.getName());
                            return;
                        }
                    }
                    // Fase 2
                    if (!phase2.contains(p.getName()) && phase1.contains(p.getName())) {
                        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            e.setCancelled(true);

                            Block b2 = e.getClickedBlock();
                            assert b2 != null;
                            rightCorner = b2.getLocation();

                            p.sendMessage(Utils.prefix + Utils.chat("Je hebt de rechter onderhoek van de dungeon gate geselecteerd."));
                            p.sendMessage(" ");
                            p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                            p.sendMessage(Utils.chat("&d&lX: &a" + b2.getLocation().getX()));
                            p.sendMessage(Utils.chat("&d&lY: &a" + b2.getLocation().getY()));
                            p.sendMessage(Utils.chat("&d&lZ: &a" + b2.getLocation().getZ()));
                            p.sendMessage(" ");
                            p.sendMessage(Utils.chat("&7Selecteer nu het Dungeon Key Block met je &c&l&nLinker&7 &c&l&nmuisknop!"));
                            p.sendMessage(" ");

                            phase2.add(p.getName());
                            return;
                        }
                    }
                    // Fase 3
                    if (!phase3.contains(p.getName()) && phase2.contains(p.getName())) {
                        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                            e.setCancelled(true);

                            Block keyBlock = e.getClickedBlock();
                            assert keyBlock != null;
                            sleutelBlock = keyBlock.getLocation();

                            if (!(keyBlock.getType() == Material.STRUCTURE_BLOCK)) {
                                p.sendMessage(Utils.prefix + Utils.chat("Dit is geen dungeon slot blok, probeer het opnieuw!"));
                                return;
                            }

                            p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Sleutelblok van de dungeon gate geselecteerd."));
                            p.sendMessage(" ");
                            p.sendMessage(Utils.chat("&7De coordinaten van dat blok zijn:"));
                            p.sendMessage(Utils.chat("&d&lX: &a" + keyBlock.getLocation().getX()));
                            p.sendMessage(Utils.chat("&d&lY: &a" + keyBlock.getLocation().getY()));
                            p.sendMessage(Utils.chat("&d&lZ: &a" + keyBlock.getLocation().getZ()));
                            p.sendMessage(" ");
                            p.sendMessage(" ");

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.sendMessage(Utils.prefix + Utils.chat("Je de 3 blokken geselecteerd, check de coordinaten nog of alles klopt."));
                                    p.sendMessage(" ");

                                    p.sendMessage(Utils.chat("&7----  &cLinker Bovenhoek &7----"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + leftCorner.getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + leftCorner.getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + leftCorner.getZ()));
                                    p.sendMessage(Utils.chat("&7-------------------------------"));

                                    p.sendMessage(" ");

                                    p.sendMessage(Utils.chat("&7----  &cRechter Onderhoek &7----"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + rightCorner.getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + rightCorner.getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + rightCorner.getZ()));
                                    p.sendMessage(Utils.chat("&7-------------------------------"));

                                    p.sendMessage(" ");

                                    p.sendMessage(Utils.chat("&7----  &cSleutelblok &7----"));
                                    p.sendMessage(Utils.chat("&d&lX: &a" + sleutelBlock.getX()));
                                    p.sendMessage(Utils.chat("&d&lY: &a" + sleutelBlock.getY()));
                                    p.sendMessage(Utils.chat("&d&lZ: &a" + sleutelBlock.getZ()));
                                    p.sendMessage(Utils.chat("&7-------------------------------"));
                                    p.sendMessage(" ");
                                    p.sendMessage(" ");
                                    p.sendMessage(Utils.prefix + Utils.chat("Voer nu het slottype voor de dungeon gate in via de chat."));
                                    p.sendMessage(" ");
                                }
                            }.runTaskLater(plugin, 20);

                            phase3.add(p.getName());

                            lockType.add(p.getName());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChatDungeonTypeMessage(PlayerChatEvent e) {
        Player p = e.getPlayer();

        if (lockType.contains(p.getName())) {
            e.setCancelled(true);

            String message = e.getMessage();

            switch (message.toUpperCase()) {
                case "GOLD":
                    dungeonType = "Gold";
                    p.sendMessage(Utils.prefix + Utils.chat("Het Slot Type dat je hebt ingevuld is: &6&lGold&7!"));
                    p.sendMessage(" ");
                    p.sendMessage(Utils.prefix + Utils.chat("Voer nu de naam voor de dungeon gate in via de chat."));

                    lockType.remove(p.getName());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            phasesComplete.add(p.getName());
                        }
                    }.runTaskLater(plugin, 10);
                    break;
                case "DIAMOND":
                    dungeonType = "Diamond";
                    p.sendMessage(Utils.prefix + Utils.chat("Het Slot Type dat je hebt ingevuld is: &b&lDiamond&7!"));
                    p.sendMessage(" ");
                    p.sendMessage(Utils.prefix + Utils.chat("Voer nu de naam voor de dungeon gate in via de chat."));

                    lockType.remove(p.getName());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            phasesComplete.add(p.getName());
                        }
                    }.runTaskLater(plugin, 10);
                    break;
                case "EMERALD":
                    dungeonType = "Emerald";
                    p.sendMessage(Utils.prefix + Utils.chat("Het Slot Type dat je hebt ingevuld is: &a&lEmerald&7!"));
                    p.sendMessage(" ");
                    p.sendMessage(Utils.prefix + Utils.chat("Voer nu de naam voor de dungeon gate in via de chat."));
                    p.sendMessage(" ");

                    lockType.remove(p.getName());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            phasesComplete.add(p.getName());
                        }
                    }.runTaskLater(plugin, 10);

                    break;
                default:
                    p.sendMessage(Utils.prefix + Utils.chat("Dit is geen geldig slot type, geldige type zijn: Gold, Diamond, Emerald."));
            }
        }
    }

    @EventHandler
    public void onChatMessage(PlayerChatEvent e) {
        Player p = e.getPlayer();

        if (phasesComplete.contains(p.getName())) {
            e.setCancelled(true);
            dungeonGateName = e.getMessage();

            p.sendMessage(Utils.prefix + Utils.chat("De naam voor de Dungeon Gate die jij hebt ingevuld is: &c" + dungeonGateName));
            p.sendMessage(" ");
            p.sendMessage(" ");

            new BukkitRunnable() {
                @Override
                public void run() {
                    DungeonsCommand.creatingPlayer.remove(p.getName());
                    phase1.remove(p.getName());
                    phase2.remove(p.getName());
                    phase3.remove(p.getName());
                    phasesComplete.remove(p.getName());
                    saveToConfig(p);
                }
            }.runTaskLater(plugin, 20 * 3);
        }
    }

    public void saveToConfig(Player p) {
        if (!dungeonGatesCFG.isConfigurationSection("Dungeons")) {
            dungeonGatesCFG.createSection("Dungeons");
        }

        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);

        ConfigurationSection cs = dungeonGatesCFG.getConfigurationSection("Dungeons");

        assert cs != null;
        if (cs.isConfigurationSection(dungeonGateName)) {
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Deze Dungeon Gate bestaat al, probeer het opnieuw!"));
            return;
        } else {
            p.sendMessage(Utils.prefix + Utils.chat("Gelukt, de Dungeon Gate is succesvol aangemaakt en opgeslagen."));
        }

        cs.createSection(dungeonGateName);

        ConfigurationSection dungeonData = dungeonGatesCFG.getConfigurationSection("Dungeons." + dungeonGateName);

        assert dungeonData != null;

        // Save Corner One
        dungeonData.createSection("CornerTop");
        ConfigurationSection cornerOne = dungeonGatesCFG.getConfigurationSection("Dungeons." + dungeonGateName + ".CornerTop");

        assert cornerOne != null;
        cornerOne.set("X", leftCorner.getX());
        cornerOne.set("Y", leftCorner.getY());
        cornerOne.set("Z", leftCorner.getZ());

        // Save Corner Two
        dungeonData.createSection("CornerBottom");
        ConfigurationSection cornerTwo = dungeonGatesCFG.getConfigurationSection("Dungeons." + dungeonGateName + ".CornerBottom");

        assert cornerTwo != null;
        cornerTwo.set("X", rightCorner.getX());
        cornerTwo.set("Y", rightCorner.getY());
        cornerTwo.set("Z", rightCorner.getZ());

        // Save Key Block Location
        dungeonData.createSection("Keyblock");
        ConfigurationSection keyblock = dungeonGatesCFG.getConfigurationSection("Dungeons." + dungeonGateName + ".Keyblock");

        assert keyblock != null;
        keyblock.set("X", sleutelBlock.getX());
        keyblock.set("Y", sleutelBlock.getY());
        keyblock.set("Z", sleutelBlock.getZ());

        dungeonData.set("DungeonGateLockType", dungeonType);

        try {
            dungeonGatesCFG.save(dungeonGatesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(DungeonItems.dungeonGateTool())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(DungeonItems.dungeonGateTool())) {
                e.getDrops().remove(DungeonItems.dungeonGateTool());
            }
        }
    }
}
