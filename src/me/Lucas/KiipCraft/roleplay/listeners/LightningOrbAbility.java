/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.roleplay.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class LightningOrbAbility implements Listener {

    private Main plugin;

    public LightningOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR && p.getItemInHand().equals(OrbItems.lightningOrb())) {
            if (p.hasPermission("kiipcraft.orb.use")) {
                World w = p.getWorld();

                double y = p.getLocation().getY();

                Location arrowSpawn = p.getLocation();
                arrowSpawn.setY(y + 0.8);
                final Arrow arrow = p.getWorld().spawn(arrowSpawn, Arrow.class);
                arrow.setShooter(p);
                arrow.setVelocity(p.getEyeLocation().getDirection().multiply(5));
                arrow.setDamage(0);
                arrow.setGravity(false);

                w.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 50, (float) 0.2);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (!(arrow.isValid())) {
                            arrow.remove();
                            this.cancel();
                        }

                        if (!(arrow.isOnGround())) {
                            arrow.getWorld().spawnParticle(Particle.CLOUD, arrow.getLocation(), 20, 0.8, 0.8, 0.8, 0.1);
                        } else {
                            w.strikeLightning(arrow.getLocation());
                            w.strikeLightning(arrow.getLocation());
                            w.strikeLightning(arrow.getLocation());
                            w.createExplosion(arrow.getLocation(), (float) 1.5, true, true, p);
                            w.createExplosion(arrow.getLocation(), 2, false, false);
                            arrow.remove();
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
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        World w = p.getWorld();
        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().equals(OrbItems.lightningOrb())) {
            e.setCancelled(true);
            if (p.hasPermission("kiipcraft.orb.use")) {
                Location loc = p.getLocation();
                double posX = p.getLocation().getX();
                double posZ = p.getLocation().getZ();
                Location loc2 = p.getLocation();
                double y = p.getLocation().getY() + 30;
                p.setFlySpeed(0);

                new BukkitRunnable() {

                    double t = 0;
                    double r = 21;

                    @Override
                    public void run() {

                        t = t + Math.PI / 64;

                        double x = r * cos(t);
                        double z = r * sin(t);

                        loc.setX(posX + x);
                        loc.setY(y);
                        loc.setZ(posZ + z);

                        loc2.setX(posX + -x);
                        loc2.setY(y);
                        loc2.setZ(posZ + -z);

                        final Firework fw = w.spawn(loc, Firework.class);
                        final Firework fw2 = w.spawn(loc2, Firework.class);
                        fw.setSilent(true);
                        fw2.setSilent(true);
                        FireworkMeta fmeta = fw.getFireworkMeta();
                        FireworkEffect fwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.AQUA).build();

                        FireworkMeta fmeta2 = fw.getFireworkMeta();
                        FireworkEffect fwEffect2 = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.AQUA).build();

                        fmeta.addEffect(fwEffect);
                        fmeta.setPower(0);
                        fw.setFireworkMeta(fmeta);

                        fmeta2.addEffect(fwEffect2);
                        fmeta2.setPower(0);
                        fw2.setFireworkMeta(fmeta2);

                        fw.detonate();
                        fw2.detonate();

                        if (t > Math.PI * 1) {
                            fw.detonate();
                            fw2.detonate();
                            p.setFlySpeed((float) .1);
                            for (Entity t : p.getNearbyEntities(20, 255, 20)) {

                                if (!(t instanceof Firework)) {
                                    t.getWorld().strikeLightning(t.getLocation());
                                }
                            }
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
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().equals(OrbItems.lightningOrb())) {
            e.setCancelled(true);
        }
    }
}
