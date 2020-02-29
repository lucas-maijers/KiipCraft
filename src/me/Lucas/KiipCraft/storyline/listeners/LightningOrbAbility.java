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
import org.bukkit.entity.*;
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
            if (p.hasPermission("kiipcraft.storyline")) {
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
            if (p.hasPermission("kiipcraft.storyline")) {
                Location loc = p.getLocation();

                ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                as.setVisible(false);
                as.setGravity(false);

                double posX = p.getLocation().getX();
                double posZ = p.getLocation().getZ();
                Location loc2 = p.getLocation();
                double y = p.getLocation().getY() + 30;

                new BukkitRunnable() {

                    int amount = 96;

                    double t = 0;
                    double t2 = 0;
                    double r = 20;

                    @Override
                    public void run() {

                        t = t + Math.PI / amount;
                        t2 = t2 - Math.PI / amount;

                        double x = r * cos(t);
                        double z = r * sin(t);

                        double x2 = r * cos(t2);
                        double z2 = r * sin(t2);

                        loc.setX(posX + x);
                        loc.setY(y);
                        loc.setZ(posZ + z);

                        loc2.setX(posX + x2);
                        loc2.setY(y);
                        loc2.setZ(posZ + z2);

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

                            Location asLoc = as.getLocation();
                            asLoc.setY(as.getLocation().getY() + 18);

                            final Firework endFW = w.spawn(asLoc, Firework.class);
                            endFW.setSilent(true);
                            FireworkMeta endFWmeta = endFW.getFireworkMeta();
                            FireworkEffect endFwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.STAR).withColor(Color.AQUA).build();

                            endFWmeta.addEffect(endFwEffect);
                            endFWmeta.setPower(0);
                            endFW.setFireworkMeta(endFWmeta);

                            endFW.detonate();

                            fw.detonate();
                            fw2.detonate();
                            for (Entity t : as.getNearbyEntities(r, 30, r)) {
                                if (t instanceof Player && !(t == p) || t instanceof Monster) {
                                    t.getWorld().strikeLightning(t.getLocation());
                                }
                            }
                            as.remove();
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
    public void onHit(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING || e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                if (p.hasPermission("kiipcraft.storyline") && p.getInventory().contains(OrbItems.lightningOrb())) {
                    e.setCancelled(true);
                }
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

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(OrbItems.lightningOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(OrbItems.lightningOrb())) {
                e.getDrops().remove(OrbItems.lightningOrb());
            }
        }
    }
}
