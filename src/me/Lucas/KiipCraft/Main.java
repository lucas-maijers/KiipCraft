package me.Lucas.KiipCraft;

import me.Lucas.KiipCraft.admintool.*;
import me.Lucas.KiipCraft.commands.KiipCraftCommand;
import me.Lucas.KiipCraft.commands.UpdateCommand;
import me.Lucas.KiipCraft.commands.XpBottleCommand;
import me.Lucas.KiipCraft.events.command.EventsToolCommand;
import me.Lucas.KiipCraft.events.command.GUICommand;
import me.Lucas.KiipCraft.events.listener.EventsToolClick;
import me.Lucas.KiipCraft.events.ui.*;
import me.Lucas.KiipCraft.gods.commands.CloudCommand;
import me.Lucas.KiipCraft.gods.commands.ShardCommand;
import me.Lucas.KiipCraft.gods.listeners.FlyCloud;
import me.Lucas.KiipCraft.listeners.DrinkXPBottle;
import me.Lucas.KiipCraft.listeners.InventoryClickListener;
import me.Lucas.KiipCraft.servertour.ServerTourCommand;
import me.Lucas.KiipCraft.servertour.ServerTourRequestSettings;
import me.Lucas.KiipCraft.servertour.ServerTourRequestsGUI;
import me.Lucas.KiipCraft.servertour.ServertourMenuClick;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

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

        // Commands
        new GUICommand(this);
        new KiipCraftCommand(this);
        new XpBottleCommand(this);
        new EventsToolCommand(this);
        new UpdateCommand(this);
        new CloudCommand(this, this);

        new ServerTourCommand(this);

        new ShardCommand(this);

        new AdminToolCommand(this);
        // Listeners
        new DrinkXPBottle(this);
        new InventoryClickListener(this);
        new EventsToolClick(this);
        new FlyCloud(this, this);

        new ServertourMenuClick(this);
        new ServerTourRequestSettings(this);

        new AdminToolClick(this);
        new AdminToolGUIClick(this);
        // UIs
        new TheQuizUI(this);
        new VloerIsLavaUI(this);
        new ServerTourRequestsGUI(this);
        new AdminToolGUI(this);
        new AdminToolPlayersGUI(this);

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
    }

    public void onDisable() {
        cfgm.saveWarps();
        saveConfig();
    }

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();
        cfgm.saveWarps();
        cfgm.reloadWarps();

    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
