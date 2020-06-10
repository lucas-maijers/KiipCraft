/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.abilities;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.pakjes.HoneySuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

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
            if (p.getInventory().getHelmet() == HoneySuit.honeyHelmet()) {
                if (p.getInventory().getChestplate() == HoneySuit.honeyChestplate()) {
                    if (p.getInventory().getLeggings() == HoneySuit.honeyLeggings()) {
                        if (p.getInventory().getBoots() == HoneySuit.honeyBoots()) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
