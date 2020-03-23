/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.storyline.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.storyline.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class WaterOrbAbility implements Listener {

    private Main plugin;
    private boolean cooldown;
    private String cooldownMessage = "";
    private int timeratNow = 10;

    public WaterOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block target;

        if (e.getAction() == Action.LEFT_CLICK_AIR)
            if (p.getInventory().getItemInMainHand().equals(OrbItems.waterOrb())) {
                if (p.hasPermission("kiipcraft.storyline")) {
                    if (cooldown) {
                        p.sendMessage(cooldownMessage);
                        return;
                    }
                    target = p.getTargetBlock(null, 20);
                    BlockFace rotation = p.getFacing();
                    placeWall(rotation, target);
                    startCooldown();
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
                }
            }
    }

    @EventHandler
    public void loseOxygen(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                if (p.hasPermission("kiipcraft.storyline"))
                    if (p.getInventory().contains(OrbItems.waterOrb())) {
                        e.setCancelled(true);
                    }
            }
        }
    }

    @EventHandler
    public void throwOrb(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getInventory().getItemInMainHand().equals(OrbItems.waterOrb()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInMainHand().equals(OrbItems.waterOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(OrbItems.waterOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().equals(OrbItems.waterOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(OrbItems.waterOrb())) {
                e.getDrops().remove(OrbItems.waterOrb());
            }
        }
    }

    // Cooldown shit
    private void startCooldown() {
        cooldown = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (timeratNow > 0) {
                    timeratNow--;
                    cooldownMessage = Utils.prefix + Utils.chat(String.format("Deze ability zit nog op cooldown voor &c%d &7seconden!", timeratNow));
                }
                if (timeratNow == 0) {
                    timeratNow = 10;
                    cooldown = false;
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    // Whatever you do, DO NOT OPEN THIS METHOD!
    private void placeWall(BlockFace rotation, Block target) {
        BukkitScheduler scheduler = Bukkit.getScheduler();

        // East and West
        int wallStayFor = 5;
        if (rotation == BlockFace.WEST || rotation == BlockFace.EAST) {
            // row 1
            target.getLocation().add(new Vector(0, 1, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(0, 1, 1)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(0, 1, -1)).getBlock().setType(Material.PACKED_ICE);
            // row 2
            target.getLocation().add(new Vector(0, 2, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(0, 2, 1)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(0, 2, -1)).getBlock().setType(Material.PACKED_ICE);
            // row 3
            target.getLocation().add(new Vector(0, 3, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(0, 3, 1)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(0, 3, -1)).getBlock().setType(Material.PACKED_ICE);

            if (rotation == BlockFace.WEST) {
                // row 1 backwards
                target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 4, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 4, -1)).getBlock().setType(Material.PACKED_ICE);
                // row 2 backwards
                target.getLocation().add(new Vector(1, 5, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 5, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 5, -1)).getBlock().setType(Material.PACKED_ICE);
                // row 3 backwards
                target.getLocation().add(new Vector(2, 6, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(2, 6, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(2, 6, -1)).getBlock().setType(Material.PACKED_ICE);
            }

            if (rotation == BlockFace.EAST) {
                // row 1 backwards
                target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 4, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 4, -1)).getBlock().setType(Material.PACKED_ICE);
                // row 2 backwards
                target.getLocation().add(new Vector(-1, 5, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 5, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 5, -1)).getBlock().setType(Material.PACKED_ICE);
                // row 3 backwards
                target.getLocation().add(new Vector(-2, 6, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-2, 6, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-2, 6, -1)).getBlock().setType(Material.PACKED_ICE);
            }

            scheduler.scheduleSyncDelayedTask(plugin, () -> {
                // row 1
                target.getLocation().add(new Vector(0, 1, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(0, 1, 1)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(0, 1, -1)).getBlock().setType(Material.AIR);
                //2 raw
                target.getLocation().add(new Vector(0, 2, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(0, 2, 1)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(0, 2, -1)).getBlock().setType(Material.AIR);
                //3 raw
                target.getLocation().add(new Vector(0, 3, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(0, 3, 1)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(0, 3, -1)).getBlock().setType(Material.AIR);


                if (rotation == BlockFace.WEST) {
                    // row 1 backwards
                    target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 4, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 4, -1)).getBlock().setType(Material.AIR);
                    // row 2 backwards
                    target.getLocation().add(new Vector(1, 5, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 5, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 5, -1)).getBlock().setType(Material.AIR);
                    // row 3 backwards
                    target.getLocation().add(new Vector(2, 6, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(2, 6, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(2, 6, -1)).getBlock().setType(Material.AIR);
                }

                if (rotation == BlockFace.EAST) {
                    // row 1 backwards
                    target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 4, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 4, -1)).getBlock().setType(Material.AIR);
                    // row 2 backwards
                    target.getLocation().add(new Vector(-1, 5, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 5, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 5, -1)).getBlock().setType(Material.AIR);
                    // row 3 backwards
                    target.getLocation().add(new Vector(-2, 6, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-2, 6, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-2, 6, -1)).getBlock().setType(Material.AIR);
                }

            }, 20 * wallStayFor);
        }

        if (rotation == BlockFace.SOUTH || rotation == BlockFace.NORTH) {
            target.getLocation().add(new Vector(0, 1, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(1, 1, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(-1, 1, 0)).getBlock().setType(Material.PACKED_ICE);
            //2 raw
            target.getLocation().add(new Vector(0, 2, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(1, 2, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(-1, 2, 0)).getBlock().setType(Material.PACKED_ICE);
            //3 raw
            target.getLocation().add(new Vector(0, 3, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(1, 3, 0)).getBlock().setType(Material.PACKED_ICE);
            target.getLocation().add(new Vector(-1, 3, 0)).getBlock().setType(Material.PACKED_ICE);

            if (rotation == BlockFace.SOUTH) {
                target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 4, -1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 4, -1)).getBlock().setType(Material.PACKED_ICE);
                //2 raw
                target.getLocation().add(new Vector(0, 5, -1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 5, -1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 5, -1)).getBlock().setType(Material.PACKED_ICE);
                //3 raw
                target.getLocation().add(new Vector(0, 6, -2)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 6, -2)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 6, -2)).getBlock().setType(Material.PACKED_ICE);
            }

            if (rotation == BlockFace.NORTH) {
                target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 4, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 4, 1)).getBlock().setType(Material.PACKED_ICE);
                //2 raw
                target.getLocation().add(new Vector(0, 5, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 5, 1)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 5, 1)).getBlock().setType(Material.PACKED_ICE);
                //3 raw
                target.getLocation().add(new Vector(0, 6, 2)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(1, 6, 2)).getBlock().setType(Material.PACKED_ICE);
                target.getLocation().add(new Vector(-1, 6, 2)).getBlock().setType(Material.PACKED_ICE);
            }

            scheduler.scheduleSyncDelayedTask(plugin, () -> {

                target.getLocation().add(new Vector(0, 1, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(1, 1, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(-1, 1, 0)).getBlock().setType(Material.AIR);
                //2 raw
                target.getLocation().add(new Vector(0, 2, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(1, 2, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(-1, 2, 0)).getBlock().setType(Material.AIR);
                //3 raw
                target.getLocation().add(new Vector(0, 3, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(1, 3, 0)).getBlock().setType(Material.AIR);
                target.getLocation().add(new Vector(-1, 3, 0)).getBlock().setType(Material.AIR);

                if (rotation == BlockFace.SOUTH) {
                    target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 4, -1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 4, -1)).getBlock().setType(Material.AIR);
                    //2 raw
                    target.getLocation().add(new Vector(0, 5, -1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 5, -1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 5, -1)).getBlock().setType(Material.AIR);
                    //3 raw
                    target.getLocation().add(new Vector(0, 6, -2)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 6, -2)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 6, -2)).getBlock().setType(Material.AIR);
                }

                if (rotation == BlockFace.NORTH) {
                    target.getLocation().add(new Vector(0, 4, 0)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 4, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 4, 1)).getBlock().setType(Material.AIR);
                    //2 raw
                    target.getLocation().add(new Vector(0, 5, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 5, 1)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 5, 1)).getBlock().setType(Material.AIR);
                    //3 raw
                    target.getLocation().add(new Vector(0, 6, 2)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(1, 6, 2)).getBlock().setType(Material.AIR);
                    target.getLocation().add(new Vector(-1, 6, 2)).getBlock().setType(Material.AIR);
                }
            }, 20 * wallStayFor);
        }
    }
}
