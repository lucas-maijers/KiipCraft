/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.command.EventsCommand;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SyncKistCreation implements Listener {

    public static Set<String> phase1 = new HashSet<>();
    public static Set<String> phase2 = new HashSet<>();
    private final Main plugin;
    private final ConfigManager cfgm = ConfigManager.getManager();
    private final File syncFile;
    private FileConfiguration syncCFG;
    private Block kist1;
    private Block kist2;
    private String syncName;


    public SyncKistCreation(Main plugin) {
        this.plugin = plugin;

        syncFile = new File(plugin.getDataFolder(), "syncchests.yml");

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void creationProces(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("kiipcraft.staff")) {
            if (EventsCommand.creatingPlayer.contains(p.getName())) {
                if (p.getInventory().getItemInMainHand().equals(Utils.syncTool())) {

                    // Fase 1
                    if (!phase1.contains(p.getName())) {
                        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                            e.setCancelled(true);
                            if (e.getClickedBlock().getType().equals(Material.CHEST)) {
                                Block b = e.getClickedBlock();

                                assert b != null;
                                kist1 = b;

                                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de kist waarvan gesynchroniseerd moet worden geselecteerd!"));
                                p.sendMessage(" ");
                                p.sendMessage(Utils.chat("&7De coordinaten van die kist zijn:"));
                                p.sendMessage(Utils.chat("&d&lX: &a" + b.getX()));
                                p.sendMessage(Utils.chat("&d&lY: &a" + b.getY()));
                                p.sendMessage(Utils.chat("&d&lZ: &a" + b.getZ()));
                                p.sendMessage(" ");
                                p.sendMessage(Utils.chat("&7Selecteer nu de kist waar naartoe gesynchroniseerd moet worden met je &c&l&nRechter&7 &c&l&nMuisknop&7!"));
                                p.sendMessage(" ");

                                phase1.add(p.getName());
                            } else {
                                p.sendMessage(Utils.prefix + Utils.chat("Je moet een kist selecteren!"));
                            }
                            return;
                        }
                    }

                    // Fase 2
                    if (!phase2.contains(p.getName()) && phase1.contains(p.getName())) {
                        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            e.setCancelled(true);

                            if (e.getClickedBlock().getType().equals(Material.CHEST)) {
                                Block b2 = e.getClickedBlock();

                                assert b2 != null;
                                kist2 = b2;

                                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de kist waar naartoe gesynchroniseerd moet worden geselecteerd!"));
                                p.sendMessage(" ");
                                p.sendMessage(Utils.chat("&7De coordinaten van die kist zijn:"));
                                p.sendMessage(Utils.chat("&d&lX: &a" + b2.getX()));
                                p.sendMessage(Utils.chat("&d&lY: &a" + b2.getY()));
                                p.sendMessage(Utils.chat("&d&lZ: &a" + b2.getZ()));
                                p.sendMessage(" ");
                                p.sendMessage(Utils.chat("&7Voer nu het de naam van de synchronisatie in via de chat!!"));
                                p.sendMessage(" ");

                                phase2.add(p.getName());
                            } else {
                                p.sendMessage(Utils.prefix + Utils.chat("Je moet een kist selecteren!"));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChatMessageName(PlayerChatEvent e) {
        Player p = e.getPlayer();

        if (phase2.contains(p.getName())) {
            e.setCancelled(true);
            syncName = e.getMessage();

            p.sendMessage(Utils.prefix + Utils.chat("De naam voor de synchronisatie is: &c" + syncName));
            p.sendMessage(" ");
            p.sendMessage(" ");

            new BukkitRunnable() {
                @Override
                public void run() {
                    EventsCommand.creatingPlayer.remove(p.getName());
                    phase1.remove(p.getName());
                    phase2.remove(p.getName());
                    saveToConfig(p);
                }
            }.runTaskLater(plugin, 20 * 3);
        }
    }

    public void saveToConfig(Player p) {
        syncCFG = cfgm.getSyncChestsCFG();

        if (!syncCFG.isConfigurationSection("SyncedChests")) {
            syncCFG.createSection("SyncedChests");
        }

        ConfigurationSection cs = syncCFG.getConfigurationSection("SyncedChests");

        assert cs != null;
        if (cs.isConfigurationSection(syncName)) {
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: &7Deze Synchronisatie bestaat al, probeer het opnieuw!"));
            return;
        } else {
            p.sendMessage(Utils.prefix + Utils.chat("Gelukt, de Synchronisatie is succesvol aangemaakt en opgeslagen."));
        }

        cs.createSection(syncName);

        ConfigurationSection syncData = syncCFG.getConfigurationSection("SyncedChests." + syncName);

        assert syncData != null;

        syncData.createSection("From");
        ConfigurationSection from = syncCFG.getConfigurationSection("SyncedChests." + syncName + ".From");

        assert from != null;
        from.set("X", kist1.getX());
        from.set("Y", kist1.getY());
        from.set("Z", kist1.getZ());

        syncData.createSection("To");

        ConfigurationSection to = syncCFG.getConfigurationSection("SyncedChests." + syncName + ".To");

        assert to != null;
        to.set("X", kist2.getX());
        to.set("Y", kist2.getY());
        to.set("Z", kist2.getZ());

        try {
            syncCFG.save(syncFile);
        } catch (IOException e) {
            e.printStackTrace();
            p.sendMessage(Utils.prefix + Utils.chat("&4&lERROR: Opslaan mislukt!"));
        }
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        Item i = e.getItemDrop();

        if (i.getItemStack().equals(Utils.syncTool())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack droppedItem : e.getDrops()) {
            if (droppedItem.equals(Utils.syncTool())) {
                e.getDrops().remove(Utils.syncTool());
            }
        }
    }
}
