/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.storyline.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.storyline.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class EarthOrbAbility implements Listener {

    public static Set<String> stunnedPlayerList = new HashSet<>();
    private final Main plugin;

    public EarthOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        BukkitScheduler scheduler = Bukkit.getScheduler();

        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            if (p.getInventory().getItemInMainHand().equals(OrbItems.earthOrb())) {
                if (p.hasPermission("kiipcraft.storyline")) {
                    World w = p.getWorld();

                    for (Entity target : p.getNearbyEntities(7, 14, 7)) {
                        if (target instanceof Player) {
                            stunnedPlayerList.add(target.getName());

                            scheduler.scheduleSyncRepeatingTask(plugin, () -> new BukkitRunnable() {
                                final Location loc = target.getLocation();
                                final Location loc2 = target.getLocation();
                                final float red = 121;
                                final float green = 96;
                                final float blue = 76;

                                final float red2 = 87;
                                final float green2 = 65;
                                final float blue2 = 47;

                                double t = 0;
                                final double r = 1;

                                @Override
                                public void run() {

                                    t = t + Math.PI / 16;

                                    double x = r * cos(t);
                                    double y = 0.08 * t;
                                    double z = r * sin(t);
                                    loc.add(x, y, z);
                                    loc2.add(z, y, x);

                                    Particle.DustOptions lightBrown = new Particle.DustOptions(
                                            Color.fromRGB((int) red, (int) green, (int) blue), 1);
                                    Particle.DustOptions darkBrown = new Particle.DustOptions(
                                            Color.fromRGB((int) red2, (int) green2, (int) blue2), 1);

                                    w.spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 0.01, lightBrown);
                                    w.spawnParticle(Particle.REDSTONE, loc2, 1, 0, 0, 0, 0.1, darkBrown);
                                    loc.subtract(x, y, z);
                                    loc2.subtract(z, y, x);

                                    if (t > Math.PI * 8) {
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(plugin, 0, 1), 0L, 20L);


                            scheduler.scheduleSyncDelayedTask(plugin, () -> {
                                Bukkit.getScheduler().cancelTasks(plugin);
                                stunnedPlayerList.clear();
                            }, 20 * 15);
                        }
                    }
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
                }
            }
        }
    }

    @EventHandler
    public void throwOrb(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getInventory().getItemInMainHand().equals(OrbItems.earthOrb()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInMainHand().equals(OrbItems.earthOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(OrbItems.earthOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().equals(OrbItems.earthOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void stunnedMovement(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (stunnedPlayerList.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(OrbItems.earthOrb())) {
                e.getDrops().remove(OrbItems.earthOrb());
            }
        }
    }
}
