package me.Lucas.KiipCraft.roleplay.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
                Block b = p.getTargetBlock(null, 50);

                double x = p.getLocation().getX();
                double y = p.getLocation().getY();
                double z = p.getLocation().getZ();

                Location arrowSpawn = p.getLocation();
                arrowSpawn.setY(y + 0.8);
                final Arrow arrow = p.getWorld().spawn(arrowSpawn, Arrow.class);
                arrow.setShooter(p);
                arrow.setVelocity(p.getEyeLocation().getDirection().multiply(5));
                arrow.setGravity(false);
                arrow.setBounce(false);
                arrow.setSilent(true);

                w.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 100, (float) 0.2);


                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (!(arrow.isValid())) {
                            arrow.remove();
                            this.cancel();
                        }

                        if (!(arrow.isOnGround())) {
                            arrow.getWorld().spawnParticle(Particle.CLOUD, arrow.getLocation(), 10, 0, 0, 0, 0.1);
                        } else {
                            w.strikeLightning(arrow.getLocation());
                            w.strikeLightning(arrow.getLocation());
                            w.strikeLightning(arrow.getLocation());
                            w.createExplosion(arrow.getLocation(), 2, true, true, p);
                            arrow.remove();
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 0L);
            } else {
                p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
            }
        }
    }


    @EventHandler
    public void throwOrb(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        World w = p.getWorld();
        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().equals(OrbItems.lightningOrb())) {
            e.setCancelled(true);
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