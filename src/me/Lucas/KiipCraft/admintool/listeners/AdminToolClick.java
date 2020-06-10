/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.admintool.listeners;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.admintool.guis.AdminToolGUI;
import me.Lucas.KiipCraft.utils.Utils;
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

public class AdminToolClick implements Listener {

    private final Main plugin;

    public AdminToolClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void openAdminTool(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getInventory().getItemInMainHand().equals(Utils.adminTool())) {
                if (p.hasPermission("kiipcraft.staff")) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je opent het &4&lAdmin Tool Menu&7!"));
                    p.openInventory(AdminToolGUI.adminToolGUI(p));
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat("Dat is niet de bedoeling, jij mag dit item niet gebruiken, hoe kom je hier eigenlijk aan?!"));
                    p.getInventory().remove(Utils.adminTool());
                }
            }
        }
    }

    @EventHandler
    public void dropAdminTool(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(Utils.adminTool())) {
            p.sendMessage(Utils.prefix + "Helaas, je kan dit item niet droppen.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().equals(Utils.adminTool())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(Utils.adminTool())) {
                e.getDrops().remove(Utils.adminTool());
            }
        }
    }
}
