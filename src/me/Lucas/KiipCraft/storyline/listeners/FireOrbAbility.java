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
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
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
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class FireOrbAbility implements Listener {

    private final Main plugin;

    public FireOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            if (p.getInventory().getItemInMainHand().equals(OrbItems.fireOrb())) {
                if (p.hasPermission("kiipcraft.storyline")) {
                    World w = p.getWorld();

                    double y = p.getLocation().getY();

                    Location spawnFireBall = p.getLocation();
                    spawnFireBall.setY(y + 0.8);

                    final Fireball fireball = p.getWorld().spawn(spawnFireBall, Fireball.class);
                    fireball.setVelocity(p.getEyeLocation().getDirection().multiply(3));
                    fireball.setBounce(false);
                    fireball.setIsIncendiary(true);
                    fireball.setYield(0F);

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (!(fireball.isValid())) {
                                w.createExplosion(fireball.getLocation(), 3, true, false, p);
                                this.cancel();
                            }

                            if (fireball.getTicksLived() == 20 * 30) {
                                fireball.remove();
                                Bukkit.getServer().getLogger().log(Level.INFO, "Fireball leeft langer dan 30 seconden, fireball verwijderen!");
                                this.cancel();
                            }

                            if (fireball.isValid()) {
                                final Firework fw = fireball.getWorld().spawn(fireball.getLocation(), Firework.class);
                                fw.setSilent(true);
                                FireworkMeta fmeta = fw.getFireworkMeta();
                                FireworkEffect fwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.ORANGE).build();

                                fmeta.addEffect(fwEffect);
                                fw.setFireworkMeta(fmeta);
                                fw.detonate();
                            }
                        }
                    }.runTaskTimer(plugin, 3, 1);
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
                }
            }
        }
    }

    @EventHandler
    public void onFire(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                if (p.hasPermission("kiipcraft.storyline")) {
                    if (p.getInventory().contains(OrbItems.fireOrb())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void throwOrb(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getInventory().getItemInMainHand().equals(OrbItems.fireOrb()) || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInMainHand().equals(OrbItems.fireOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(OrbItems.fireOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().equals(OrbItems.fireOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(OrbItems.fireOrb())) {
                e.getDrops().remove(OrbItems.fireOrb());
            }
        }
    }
}
