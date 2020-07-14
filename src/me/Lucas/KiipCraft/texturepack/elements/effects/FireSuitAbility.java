/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.effects;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.elements.suits.FireSuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class FireSuitAbility implements Listener {

    private final Main plugin;

    public FireSuitAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFireDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR || e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                if (Objects.equals(p.getInventory().getHelmet(), FireSuit.fireHelmet())) {
                    if (Objects.equals(p.getInventory().getChestplate(), FireSuit.fireChestplate())) {
                        if (Objects.equals(p.getInventory().getLeggings(), FireSuit.fireLeggings())) {
                            if (Objects.equals(p.getInventory().getBoots(), FireSuit.fireBoots())) {
                                p.setFireTicks(0);
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
