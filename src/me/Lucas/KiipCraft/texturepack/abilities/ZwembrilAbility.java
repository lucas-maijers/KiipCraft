/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.abilities;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.outfits.Zwembril;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZwembrilAbility implements Listener {

    private final Main plugin;

    public ZwembrilAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void zwembrilNightVision(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getHelmet() != null) {
            if (p.getInventory().getHelmet().equals(Zwembril.zwembril())) {
                if (e.getTo().getBlock().isLiquid()) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 40, 1), true);
                }
            }
        }
    }
}
