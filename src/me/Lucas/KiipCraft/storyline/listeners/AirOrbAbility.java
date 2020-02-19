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
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
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
import org.bukkit.util.Vector;

public class AirOrbAbility implements Listener {

    private Main plugin;
    private boolean cooldown;
    private String cooldownMessage = "";
    private int timeratNow = 120;

    public AirOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR && p.getItemInHand().equals(OrbItems.airOrb())) {
            if (p.hasPermission("kiipcraft.orb.use")) {
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 20, (float) 1.8);
                for (Entity et : p.getNearbyEntities(10, 10, 10)) {
                    if (et instanceof Player || et instanceof Monster) {
                        Location etLoc = et.getLocation();
                        Location pLoc = p.getLocation();

                        Location newLoc = etLoc.subtract(pLoc);
                        Vector newV = newLoc.toVector().normalize().multiply(2);
                        newV.setY(1);

                        et.setVelocity(newV);
                    }
                }
            } else {
                p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().equals(OrbItems.airOrb())) {
            e.setCancelled(true);
            if (p.hasPermission("kiipcraft.orb.use")) {
                if (cooldown) {
                    p.sendMessage(cooldownMessage);
                    return;
                }
                startCooldown();
                p.setAllowFlight(true);
                p.setExp(0F);
                p.setLevel(60);

                final int[] currentsubtraction = {0};

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        float currentExp = p.getExp();
                        float currentLevel = p.getLevel();

                        if (currentExp <= 0) {
                            if (currentLevel > 0) {
                                p.setLevel(p.getLevel() - 1);
                            }
                            p.setExp(1F);
                            currentsubtraction[0] = 0;
                        }

                        switch (currentsubtraction[0]) {
                            case 0:
                                p.setExp(0.95F);
                                break;
                            case 1:
                                p.setExp(0.90F);
                                break;
                            case 2:
                                p.setExp(0.85F);
                                break;
                            case 3:
                                p.setExp(0.80F);
                                break;
                            case 4:
                                p.setExp(0.75F);
                                break;
                            case 5:
                                p.setExp(0.70F);
                                break;
                            case 7:
                                p.setExp(0.65F);
                                break;
                            case 8:
                                p.setExp(0.60F);
                                break;
                            case 9:
                                p.setExp(0.55F);
                                break;
                            case 10:
                                p.setExp(0.50F);
                                break;
                            case 11:
                                p.setExp(0.45F);
                                break;
                            case 12:
                                p.setExp(0.40F);
                                break;
                            case 13:
                                p.setExp(0.35F);
                                break;
                            case 14:
                                p.setExp(0.30F);
                                break;
                            case 15:
                                p.setExp(0.25F);
                                break;
                            case 16:
                                p.setExp(0.20F);
                                break;
                            case 17:
                                p.setExp(0.15F);
                                break;
                            case 18:
                                p.setExp(0.10F);
                                break;
                            case 19:
                                p.setExp(0.05F);
                                break;
                            case 20:
                                p.setExp(0F);
                                break;
                        }
                        ++currentsubtraction[0];


                        if (currentLevel == 0 && currentExp == 0) {
                            p.setExp(0F);
                            p.setAllowFlight(false);
                            p.setFlying(false);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 1);
            } else {
                p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
            }
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL && p.getInventory().contains(OrbItems.airOrb())) {
                if (p.hasPermission("kiipcraft.orb.use")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().equals(OrbItems.airOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(OrbItems.airOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(OrbItems.airOrb())) {
                e.getDrops().remove(OrbItems.airOrb());
            }
        }
    }

    private void startCooldown() {
        cooldown = true;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (timeratNow > 0) {
                    timeratNow--;
                    cooldownMessage = Utils.prefix + Utils.chat("Deze ability zit nog op cooldown voor &c" + timeratNow + " &7seconden!");
                }
                if (timeratNow == 0) {
                    timeratNow = 120;
                    cooldown = false;
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
