/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.items.DungeonItems;
import me.Lucas.KiipCraft.utils.Utils;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.TileEntity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OpenDungeonGate implements Listener {

    private Main plugin;

    private int openTime = 30;

    private File dungeonGatesFile;
    private FileConfiguration dungeonGatesCFG;

    public OpenDungeonGate(Main plugin) {
        this.plugin = plugin;

        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void rightClickGate(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        BlockData gateBlock;
        Location keyBlock;
        World w = p.getWorld();
        String dungeonType;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            assert b != null;
            if (b.getType() == Material.STRUCTURE_BLOCK) {
                keyBlock = b.getLocation();
                dungeonType = getDungeonType(keyBlock);
                switch (dungeonType) {
                    case "Gold":
                        gateBlock = Bukkit.createBlockData(Material.STRUCTURE_BLOCK, "[mode=data]");
                        if (p.getInventory().getItemInMainHand().equals(DungeonItems.goldKey())) {
                            e.setCancelled(true);
                            if (b.getBlockData().matches(gateBlock)) {
                                p.sendMessage(Utils.prefix + Utils.chat("Je hebt zojuist deze dungeon gate open gemaakt. Je hebt &c&l" + openTime + " seconden&7 om naar binnen te gaan voor de deur sluit."));
                                p.getInventory().removeItem(p.getInventory().getItemInMainHand());
                                openDungeonGate(storeDungeonBlocks(keyBlock, w));
                                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 50, 1);
                            }
                        } else {
                            p.sendMessage(Utils.prefix + Utils.chat("Je moet een &6&lGold &c&lDungeon Key &7gebruiken om deze deur te openen!"));
                        }
                        break;
                    case "Diamond":
                        gateBlock = Bukkit.createBlockData(Material.STRUCTURE_BLOCK, "[mode=save]");
                        if (p.getInventory().getItemInMainHand().equals(DungeonItems.diamondKey())) {
                            e.setCancelled(true);
                            if (b.getBlockData().matches(gateBlock)) {
                                p.sendMessage(Utils.prefix + Utils.chat("Je hebt zojuist deze dungeon gate open gemaakt. Je hebt &c&l" + openTime + " seconden&7 om naar binnen te gaan voor de deur sluit."));
                                p.getInventory().removeItem(p.getInventory().getItemInMainHand());
                                openDungeonGate(storeDungeonBlocks(keyBlock, w));
                                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 50, 1);
                            }
                        } else {
                            p.sendMessage(Utils.prefix + Utils.chat("Je moet een &b&lDiamond &c&lDungeon Key &7gebruiken om deze deur te openen!"));
                        }
                        break;
                    case "Emerald":
                        gateBlock = Bukkit.createBlockData(Material.STRUCTURE_BLOCK, "[mode=load]");
                        if (p.getInventory().getItemInMainHand().equals(DungeonItems.emeraldKey())) {
                            e.setCancelled(true);
                            if (b.getBlockData().matches(gateBlock)) {
                                p.sendMessage(Utils.prefix + Utils.chat("Je hebt zojuist deze dungeon gate open gemaakt. Je hebt &c&l" + openTime + " seconden&7 om naar binnen te gaan voor de deur sluit."));
                                p.getInventory().removeItem(p.getInventory().getItemInMainHand());
                                openDungeonGate(storeDungeonBlocks(keyBlock, w));
                                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 50, 1);
                            }
                        } else {
                            p.sendMessage(Utils.prefix + Utils.chat("Je moet een &a&lEmerald &c&lDungeon Key &7gebruiken om deze deur te openen!"));
                        }
                        break;
                    default:
                        p.sendMessage(Utils.prefix + Utils.chat("Deze dungeon gate is nog niet aangemaakt, dus kan nog niet open worden gemaakt."));
                        break;
                }
            }
        }
    }

    private Map<Location, BlockData> storeDungeonBlocks(Location kBlock, World w) {
        Map<Location, BlockData> restore = new HashMap<>();

        double x1, x2, y1, y2, z1, z2;

        boolean ignoreX = false;
        boolean ignoreZ = false;

        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
        for (String key : dungeonGatesCFG.getConfigurationSection("Dungeons").getKeys(false)) {
            ConfigurationSection cs = dungeonGatesCFG.getConfigurationSection("Dungeons." + key + ".Keyblock");
            assert cs != null;
            if (kBlock.getX() == cs.getDouble("X") && kBlock.getY() == cs.getDouble("Y") && kBlock.getZ() == cs.getDouble("Z")) {
                // loc c1
                ConfigurationSection locationCornerOne = dungeonGatesCFG.getConfigurationSection("Dungeons." + key + ".CornerTop");

                assert locationCornerOne != null;
                x1 = locationCornerOne.getDouble("X");
                y1 = locationCornerOne.getDouble("Y");
                z1 = locationCornerOne.getDouble("Z");

                // loc c2
                ConfigurationSection locationCornerTwo = dungeonGatesCFG.getConfigurationSection("Dungeons." + key + ".CornerBottom");

                assert locationCornerTwo != null;
                x2 = locationCornerTwo.getDouble("X");
                y2 = locationCornerTwo.getDouble("Y");
                z2 = locationCornerTwo.getDouble("Z");

                double highestX = Math.max(x1, x2);
                double highestY = Math.max(y1, y2);
                double highestZ = Math.max(z1, z2);

                double lowestX = Math.min(x1, x2);
                double lowestY = Math.min(y1, y2);
                double lowestZ = Math.min(z1, z2);

                if (highestX == lowestX) {
                    ignoreX = true;
                } else if (highestZ == lowestZ) {
                    ignoreZ = true;
                }

                if (ignoreX) {
                    for (double z = lowestZ; z <= highestZ; z++) {
                        for (double y = lowestY; y <= highestY; y++) {
                            int x = (int) highestX;
                            Block b = w.getBlockAt(x, (int) y, (int) z);
                            restore.put(b.getLocation(), b.getState().getBlockData());
                        }
                    }
                } else if (ignoreZ) {
                    for (double x = lowestX; x <= highestX; x++) {
                        for (double y = lowestY; y <= highestY; y++) {
                            int z = (int) highestZ;
                            Block b = w.getBlockAt((int) x, (int) y, z);
                            restore.put(b.getLocation(), b.getState().getBlockData());
                        }
                    }
                }
            }
        }
        return restore;
    }

    private void openDungeonGate(Map<Location, BlockData> putBack) {
        NBTTagCompound ntc = null;
        for (Map.Entry<Location, BlockData> entry : putBack.entrySet()) {
            Location loc = entry.getKey();
            World w = loc.getWorld();
            if (loc.getBlock().getType() == Material.STRUCTURE_BLOCK) {
                CraftWorld ws = (CraftWorld) w;
                CraftBlock cb = (CraftBlock) loc.getBlock();
                assert ws != null;
                TileEntity te = ws.getHandle().getTileEntity(cb.getPosition());

                if (te != null) {
                    ntc = new NBTTagCompound();
                    te.save(ntc);
                }
            }
            loc.getBlock().setType(Material.AIR);
        }

        final NBTTagCompound finalNtc = ntc;

        new BukkitRunnable() {
            final NBTTagCompound nbt = finalNtc;

            @Override
            public void run() {
                for (Map.Entry<Location, BlockData> entry : putBack.entrySet()) {
                    Location loc = entry.getKey();
                    String stringData = entry.getValue().getAsString();
                    BlockData data = entry.getValue();
                    Material mt = data.getMaterial();

                    loc.getBlock().setType(mt);
                    if (mt == Material.STRUCTURE_BLOCK) {

                        CraftWorld ws = (CraftWorld) loc.getWorld();
                        CraftBlock cb = (CraftBlock) loc.getBlock();
                        assert ws != null;
                        TileEntity te = ws.getHandle().getTileEntity(cb.getPosition());

                        assert te != null;
                        te.load(nbt);
                        te.update();
                    } else {
                        loc.getBlock().setBlockData(Bukkit.getServer().createBlockData(stringData));
                    }
                }
                putBack.clear();
            }
        }.runTaskLater(plugin, 20 * openTime);
    }

    private String getDungeonType(Location kBlock) {
        String type = "";
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);
        for (String key : dungeonGatesCFG.getConfigurationSection("Dungeons").getKeys(false)) {
            ConfigurationSection cs = dungeonGatesCFG.getConfigurationSection("Dungeons." + key + ".Keyblock");
            assert cs != null;
            if (kBlock.getX() == cs.getDouble("X") && kBlock.getY() == cs.getDouble("Y") && kBlock.getZ() == cs.getDouble("Z")) {
                ConfigurationSection typeSec = dungeonGatesCFG.getConfigurationSection("Dungeons." + key);
                assert typeSec != null;
                type = typeSec.getString("DungeonGateLockType");
            }
        }
        return type;
    }
}
