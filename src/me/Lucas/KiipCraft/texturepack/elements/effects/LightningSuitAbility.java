/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.effects;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.elements.suits.LightningSuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class LightningSuitAbility implements Listener {

    private final Main plugin;


    public LightningSuitAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onWalk(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.isSprinting()) {
            if (Objects.equals(p.getInventory().getHelmet(), LightningSuit.lightningHelmet())) {
                if (Objects.equals(p.getInventory().getChestplate(), LightningSuit.lightningChestplate())) {
                    if (Objects.equals(p.getInventory().getLeggings(), LightningSuit.lightningLeggings())) {
                        if (Objects.equals(p.getInventory().getBoots(), LightningSuit.lightningBoots())) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 1), true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onLightningDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (Objects.equals(p.getInventory().getHelmet(), LightningSuit.lightningHelmet())) {
                if (Objects.equals(p.getInventory().getChestplate(), LightningSuit.lightningChestplate())) {
                    if (Objects.equals(p.getInventory().getLeggings(), LightningSuit.lightningLeggings())) {
                        if (Objects.equals(p.getInventory().getBoots(), LightningSuit.lightningBoots())) {
                            e.setCancelled(true);
                        }
                    }
                }
            }

        }
    }
}
