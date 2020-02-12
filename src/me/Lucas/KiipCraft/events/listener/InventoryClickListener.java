/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.ui.*;
import me.Lucas.KiipCraft.outofuse.shopUI.EventShop;
import me.Lucas.KiipCraft.outofuse.shopUI.HalloweenShop;
import me.Lucas.KiipCraft.outofuse.shopUI.KerstShop;
import me.Lucas.KiipCraft.outofuse.shopUI.ShopAdmin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryClickListener implements Listener {

    private Main plugin;

    public InventoryClickListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        // Main UI
        if (title.equals(MainEventsUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(MainEventsUI.inventory_name)) {
                MainEventsUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        // Anvil Drop UI
        if (title.equals(AnvilDropUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(AnvilDropUI.inventory_name)) {
                AnvilDropUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(SpleefUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(SpleefUI.inventory_name)) {
                SpleefUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(ColosseumUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(ColosseumUI.inventory_name)) {
                ColosseumUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(TheQuizUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(TheQuizUI.inventory_name)) {
                TheQuizUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(VloerIsLavaUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(VloerIsLavaUI.inventory_name)) {
                VloerIsLavaUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(BuildBattleUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(BuildBattleUI.inventory_name)) {
                BuildBattleUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(EventShop.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(EventShop.inventory_name)) {
                EventShop.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(HalloweenShop.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(HalloweenShop.inventory_name)) {
                HalloweenShop.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(ShopAdmin.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(ShopAdmin.inventory_name)) {
                ShopAdmin.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(KerstShop.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(KerstShop.inventory_name)) {
                KerstShop.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }
}
