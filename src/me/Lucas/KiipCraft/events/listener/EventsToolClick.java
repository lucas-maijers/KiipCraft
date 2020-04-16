/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.ui.MainEventsUI;
import org.bukkit.Bukkit;
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

import static me.Lucas.KiipCraft.utils.Utils.eventsTool;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class EventsToolClick implements Listener {

    private Main plugin;

    public EventsToolClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void openEventsTool(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.hasPermission("kiipcraft.events"))
                if (p.getInventory().getItemInMainHand().equals(eventsTool())) {
                    p.sendMessage(prefix + "Bezig met het openen van de §3§lEventsAdmin GUI§7...");
                    p.openInventory(MainEventsUI.mainGUI(p));
                }
        }
    }

    @EventHandler
    public void dropEventsTool(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(eventsTool())) {
            p.sendMessage(prefix + "Helaas, je kan dit item niet droppen.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().equals(eventsTool())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(eventsTool())) {
                e.getDrops().remove(eventsTool());
            }
        }
    }
}
