/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.roleplay.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.roleplay.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DarknessOrbAbility implements Listener {

    private Main plugin;

    public DarknessOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR && p.getItemInHand().equals(OrbItems.darknessOrb())) {
            if (p.hasPermission("kiipcraft.orb.use")) {
                for (Entity t : p.getNearbyEntities(25, 20, 25)) {
                    if (t instanceof Player) {
                        ((Player) t).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 15, 1));
                    }
                }
            } else {
                p.sendMessage(Utils.prefix + Utils.chat("Jij kan de krachten van deze orb niet gebruiken!"));
            }
        }
    }

    @EventHandler
    public void throwOrb(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().equals(OrbItems.darknessOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().equals(OrbItems.darknessOrb())) {
            e.setCancelled(true);
        }
    }

}
