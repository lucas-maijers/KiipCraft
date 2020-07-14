/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.abilities;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.outfits.HoneySuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class HoneySuitAbility implements Listener {

    private final Main plugin;

    public HoneySuitAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (Objects.equals(p.getInventory().getHelmet(), HoneySuit.honeyHelmet())) {
                    if (Objects.equals(p.getInventory().getChestplate(), HoneySuit.honeyChestplate())) {
                        if (Objects.equals(p.getInventory().getLeggings(), HoneySuit.honeyLeggings())) {
                            if (Objects.equals(p.getInventory().getBoots(), HoneySuit.honeyBoots())) {

                                e.setDamage(0);
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
