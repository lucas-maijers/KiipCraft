/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SyncChests implements Listener {

    private final Main plugin;

    private final ConfigManager cfgm = ConfigManager.getManager();

    private FileConfiguration syncCFG;


    public SyncChests(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void chestUpdate(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if (b.getType() == Material.CHEST) {
                syncCFG = cfgm.getSyncChestsCFG();
                Chest from = (Chest) b.getState();
                InventoryHolder holder = from.getInventory().getHolder();
                if (holder instanceof DoubleChest) {

                    DoubleChest doubleChest = ((DoubleChest) holder);

                    for (String key : syncCFG.getConfigurationSection("SyncedChests").getKeys(false)) {
                        ConfigurationSection cs = cfgm.getSyncChestsCFG().getConfigurationSection("SyncedChests." + key + ".From");
                        assert cs != null;
                        int x = cs.getInt("X");
                        int y = cs.getInt("Y");
                        int z = cs.getInt("Z");

                        if (from.getX() == x && from.getY() == y && from.getZ() == z) {
                            ConfigurationSection cs2 = cfgm.getSyncChestsCFG().getConfigurationSection("SyncedChests." + key + ".To");
                            assert cs2 != null;
                            int x2 = cs2.getInt("X");
                            int y2 = cs2.getInt("Y");
                            int z2 = cs2.getInt("Z");

                            BlockState bs = p.getWorld().getBlockAt(x2, y2, z2).getState();
                            try {
                                DoubleChest to = (DoubleChest) ((Chest) bs).getInventory().getHolder();

                                assert to != null;
                                to.getInventory().setContents(doubleChest.getInventory().getContents());
                            } catch (ClassCastException ex) {
                                p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR:&7 Het blok waar de chest naartoe moet is geen chest!"));
                                ex.printStackTrace();
                            }
                        }
                    }
                } else {
                    for (String key : syncCFG.getConfigurationSection("SyncedChests").getKeys(false)) {
                        ConfigurationSection cs = cfgm.getSyncChestsCFG().getConfigurationSection("SyncedChests." + key + ".From");
                        assert cs != null;
                        int x = cs.getInt("X");
                        int y = cs.getInt("Y");
                        int z = cs.getInt("Z");

                        if (from.getX() == x && from.getY() == y && from.getZ() == z) {
                            ConfigurationSection cs2 = cfgm.getSyncChestsCFG().getConfigurationSection("SyncedChests." + key + ".To");
                            assert cs2 != null;
                            int x2 = cs2.getInt("X");
                            int y2 = cs2.getInt("Y");
                            int z2 = cs2.getInt("Z");

                            BlockState bs = p.getWorld().getBlockAt(x2, y2, z2).getState();
                            Inventory invFrom = from.getInventory();
                            try {
                                Chest to = (Chest) bs;

                                to.update();
                                to.getInventory().setContents(invFrom.getContents());
                            } catch (ClassCastException ex) {
                                p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR:&7 Het blok waar de chest naartoe moet is geen chest!"));
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
