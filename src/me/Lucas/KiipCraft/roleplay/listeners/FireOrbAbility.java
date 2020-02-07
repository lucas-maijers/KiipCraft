package me.Lucas.KiipCraft.roleplay.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class FireOrbAbility implements Listener {

    private Main plugin;

    public FireOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR && p.getItemInHand().equals(OrbItems.fireOrb())) {
            if (p.hasPermission("kiipcraft.orb.use")) {
                World w = p.getWorld();

                double y = p.getLocation().getY();

                Location spawnFireBall = p.getLocation();
                spawnFireBall.setY(y + 0.8);

                final Fireball fireball = p.getWorld().spawn(spawnFireBall, Fireball.class);
                fireball.setShooter(p);
                fireball.setVelocity(p.getEyeLocation().getDirection().multiply(3));
                fireball.setBounce(false);
                fireball.setIsIncendiary(true);
                fireball.setYield(0F);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (!(fireball.isValid())) {
                            w.createExplosion(fireball.getLocation(), 5, true, false, p);
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
                }.runTaskTimer(plugin, 0, 1);
            } else {
                p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
            }
        }
    }

    @EventHandler
    public void throwOrb(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().equals(OrbItems.fireOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().equals(OrbItems.fireOrb())) {
            e.setCancelled(true);
        }
    }
}
