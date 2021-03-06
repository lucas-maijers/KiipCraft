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
import me.Lucas.KiipCraft.events.listener.*;
import me.Lucas.KiipCraft.events.ui.*;
import me.Lucas.KiipCraft.managers.CommandManager;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.servertour.ServerTourRequestSettings;
import me.Lucas.KiipCraft.servertour.ServerTourRequestsGUI;
import me.Lucas.KiipCraft.servertour.ServertourMenuClick;
import me.Lucas.KiipCraft.storyline.listeners.*;
import me.Lucas.KiipCraft.texturepack.abilities.HoneySuitAbility;
import me.Lucas.KiipCraft.texturepack.abilities.ZwembrilAbility;
import me.Lucas.KiipCraft.texturepack.elements.effects.DarknessSuitAbility;
import me.Lucas.KiipCraft.texturepack.elements.effects.FireSuitAbility;
import me.Lucas.KiipCraft.texturepack.elements.effects.LightningSuitAbility;
import me.Lucas.KiipCraft.texturepack.elements.effects.WaterSuitAbility;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public CommandManager cmdMngr;
    private ConfigManager cfgm;

    @Override
    public void onEnable() {

        loadConfigManager();

        loadConfig();

        cmdMngr = new CommandManager(this);
        cmdMngr.setup();

        getCommand("kiipcraft").setTabCompleter(new CommandManager(this));

        // Listeners
        new DrinkXPBottle(this);
        new InventoryClickListener(this);
        new SyncKistCreation(this);
        new SyncChests(this);
        new EventsToolClick(this);

        new DarknessSuitAbility(this);
        new HoneySuitAbility(this);
        new FireSuitAbility(this);
        new LightningSuitAbility(this);
        new WaterSuitAbility(this);
        new ZwembrilAbility(this);

        new BuildBattleSelections(this);
        new SpleefSelections(this);
        new GeluksGraversSelections(this);
        new TriathlonSelections(this);

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
        // Admin Tool
        new AdminToolGUI(this);
        new AdminToolOrbMenu(this);
        new AdminToolShardsMenu(this);
        new AdminToolPlayersGUI(this);
        new AdminToolPlayerSettings(this);

        MainEventsUI.initialize();
        GeluksGraversUI.initialize();
        SpleefUI.initialize();
        TriathlonUI.initialize();
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

        SpigotPluginUpdater spu = new SpigotPluginUpdater(this, "http://51.68.47.8/pluginupdate/plugin.html");
        spu.enableOut();

        if (spu.needsUpdate()) {
            spu.update();
        }
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
