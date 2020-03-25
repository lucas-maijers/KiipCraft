/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft;

import me.Lucas.KiipCraft.admintool.guis.*;
import me.Lucas.KiipCraft.admintool.listeners.AdminToolClick;
import me.Lucas.KiipCraft.admintool.listeners.AdminToolGUIClick;
import me.Lucas.KiipCraft.bottleXP.command.XpBottleCommand;
import me.Lucas.KiipCraft.bottleXP.listener.DrinkXPBottle;
import me.Lucas.KiipCraft.dungeons.listeners.DungeonGateCreation;
import me.Lucas.KiipCraft.dungeons.listeners.OpenDungeonGate;
import me.Lucas.KiipCraft.dungeons.recipes.DungeonKeyRecipes;
import me.Lucas.KiipCraft.events.listener.EventsToolClick;
import me.Lucas.KiipCraft.events.listener.InventoryClickListener;
import me.Lucas.KiipCraft.events.listener.SyncChests;
import me.Lucas.KiipCraft.events.listener.SyncKistCreation;
import me.Lucas.KiipCraft.events.ui.*;
import me.Lucas.KiipCraft.managers.CommandManager;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.servertour.ServerTourRequestSettings;
import me.Lucas.KiipCraft.servertour.ServerTourRequestsGUI;
import me.Lucas.KiipCraft.servertour.ServertourMenuClick;
import me.Lucas.KiipCraft.storyline.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public CommandManager cmdMngr;
    private ConfigManager cfgm;

    @Override
    public void onEnable() {

        loadConfigManager();

        loadConfig();

        // Update
        SpigotPluginUpdater spu = new SpigotPluginUpdater(this, "http://51.68.47.8/pluginupdate/plugin.html");
        spu.enableOut();

        if (spu.needsUpdate()) {
            spu.update();
        }

        cmdMngr = new CommandManager(this);
        cmdMngr.setup();

        getCommand("kiipcraft").setTabCompleter(new CommandManager(this));

        // Listeners
        new DrinkXPBottle(this);
        new InventoryClickListener(this);
        new SyncKistCreation(this);
        new SyncChests(this);
        new EventsToolClick(this);

        new ServertourMenuClick(this);
        new ServerTourRequestSettings(this);
        new DungeonKeyRecipes(this);

        new FireOrbAbility(this);
        new WaterOrbAbility(this);
        new AirOrbAbility(this);
        new EarthOrbAbility(this);
        new LightningOrbAbility(this);
        new LightOrbAbility(this);
        new DarknessOrbAbility(this);
        new LifeOrbAbility(this);

        new DungeonGateCreation(this);
        new OpenDungeonGate(this);

        new AdminToolClick(this);
        new AdminToolGUIClick(this);
        // UIs
        new TheQuizUI(this);
        new VloerIsLavaUI(this);
        new ServerTourRequestsGUI(this);
        // Admin Tool
        new AdminToolGUI(this);
        new AdminToolOrbMenu(this);
        new AdminToolShardsMenu(this);
        new AdminToolPlayersGUI(this);
        new AdminToolPlayerSettings(this);

        MainEventsUI.initialize();
        AnvilDropUI.initialize();
        SpleefUI.initialize();
        ColosseumUI.initialize();
        TheQuizUI.initialize();
        VloerIsLavaUI.initialize();
        BuildBattleUI.initialize();

        XpBottleCommand.initializelist();

        ServerTourRequestsGUI.initialize();
        ServerTourRequestSettings.initialize();

        AdminToolGUI.initialize();
        AdminToolPlayersGUI.initialize();
        AdminToolShardsMenu.initialize();
        AdminToolOrbMenu.initialize();
    }

    @Override
    public void onDisable() {
        cfgm.saveWarps();
        cfgm.saveDungeonGates();
        cfgm.saveSyncChests();
        cfgm.saveEvents();
        saveConfig();
    }

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();

        cfgm.saveDungeonGates();
        cfgm.saveWarps();
        cfgm.saveSyncChests();
        cfgm.saveEvents();

        cfgm.reloadDungeonGates();
        cfgm.reloadWarps();
        cfgm.reloadSyncChests();
        cfgm.reloadEvents();

    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
