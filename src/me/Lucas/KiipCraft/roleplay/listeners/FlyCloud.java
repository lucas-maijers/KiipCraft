package me.Lucas.KiipCraft.roleplay.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.commands.CloudCommand;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class FlyCloud implements Listener {

    private Main plugin;
    private Plugin pl;

    public FlyCloud(Main plugin, Plugin pl) {
        this.plugin = plugin;
        this.pl = pl;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void generateFlyCloud(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        World w = e.getPlayer().getWorld();

        BukkitScheduler scheduler = Bukkit.getScheduler();

        if (e.isFlying() && (CloudCommand.playerList.contains(p.getName()))) {
            scheduler.scheduleSyncRepeatingTask(pl, () -> {
                float red = 121;
                float green = 96;
                float blue = 76;

                float red2 = 87;
                float green2 = 65;
                float blue2 = 47;

                new BukkitRunnable() {
                    Location loc = p.getLocation();
                    Location loc2 = p.getLocation();

                    double t = 0;
                    double r = 1;

                    double cX = loc.getX();
                    double cY = loc.getY();
                    double cZ = loc.getZ();

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


                        Location storeP = p.getLocation();

                        if (storeP.getX() != cX || storeP.getY() != cY || storeP.getZ() != cZ) {
                            this.cancel();
                        }
                        if (t > Math.PI * 8) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(pl, 0, 1);
            }, 0L, 20L);

            scheduler.scheduleSyncDelayedTask(pl, () -> {
                Bukkit.getScheduler().cancelTasks(pl);
                CloudCommand.playerList.remove(p.getName());
            }, 20 * 15);
        }

        if (!(e.isFlying())) {
            Bukkit.getScheduler().cancelTasks(pl);
        }
    }
}
