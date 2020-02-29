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

import static me.Lucas.KiipCraft.utils.Utils.adminTool;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class AdminToolClick implements Listener {

    private Main plugin;

    public AdminToolClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void openAdminTool(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getItemInHand().equals(Utils.adminTool()) && p.hasPermission("kiipcraft.admintool")) {
                p.sendMessage(prefix + Utils.chat("Je opent het &4&lAdmin Tool Menu&7!"));
                p.openInventory(AdminToolGUI.adminToolGUI(p));
            } else if (p.getItemInHand().equals(Utils.adminTool()) && !(p.hasPermission("kiipcraft.admintool"))) {
                p.sendMessage(prefix + Utils.chat("Dat is niet de bedoeling, jij mag dit item niet gebruiken, hoe kom je hier eigenlijk aan?!"));
                p.getInventory().remove(Utils.adminTool());
            }
        }
    }

    @EventHandler
    public void dropAdminTool(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(adminTool())) {
            p.sendMessage(prefix + "Helaas, je kan dit item niet droppen.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getItemInHand().equals(adminTool())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(adminTool())) {
                e.getDrops().remove(adminTool());
            }
        }
    }
}
