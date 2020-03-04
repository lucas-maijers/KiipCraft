/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.storyline.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.storyline.shards.OrbItems;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LightOrbAbility implements Listener {

    private Main plugin;


    public LightOrbAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR && p.getInventory().getItemInMainHand().equals(OrbItems.lightOrb())) {
            if (p.hasPermission("kiipcraft.storyline")) {
                for (Entity t : p.getNearbyEntities(25, 20, 25)) {
                    if (t instanceof Player) {
                        ((Player) t).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 30, 1));
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

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().equals(OrbItems.lightOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(OrbItems.lightOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().equals(OrbItems.lightOrb())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(OrbItems.lightOrb())) {
                e.getDrops().remove(OrbItems.lightOrb());
            }
        }
    }
}
