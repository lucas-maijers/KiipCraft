/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.effects;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.elements.suits.WaterSuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class WaterSuitAbility implements Listener {

    private final Main plugin;

    public WaterSuitAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSwimming(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (Objects.equals(p.getInventory().getHelmet(), WaterSuit.waterHelmet())) {
            if (Objects.equals(p.getInventory().getChestplate(), WaterSuit.waterChestplate())) {
                if (Objects.equals(p.getInventory().getLeggings(), WaterSuit.waterLeggings())) {
                    if (Objects.equals(p.getInventory().getBoots(), WaterSuit.waterBoots())) {
                        if (e.getTo().getBlock().isLiquid()) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 40, 1), true);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20, 4), true);
                        }
                    }
                }
            }
        }
    }
}
