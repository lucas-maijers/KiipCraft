package me.Lucas.KiipCraft.admintool;

import me.Lucas.KiipCraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminToolGUIClick implements Listener {

    private Main plugin;

    public AdminToolGUIClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        if (title.equals(AdminToolGUI.inv_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(AdminToolGUI.inv_name)) {
                AdminToolGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(AdminToolPlayersGUI.inv_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(AdminToolPlayersGUI.inv_name)) {
                AdminToolPlayersGUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }

        if (title.equals(AdminToolShardsMenu.inv_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(AdminToolShardsMenu.inv_name)) {
                AdminToolShardsMenu.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }
}
