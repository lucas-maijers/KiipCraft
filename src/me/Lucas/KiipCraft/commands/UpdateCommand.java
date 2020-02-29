/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.SpigotPluginUpdater;
import me.Lucas.KiipCraft.managers.SubCommand;
import org.bukkit.entity.Player;

import static me.Lucas.KiipCraft.utils.Utils.noPermission;
import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class UpdateCommand extends SubCommand {

    private Main plugin;

    public UpdateCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.staff")) {
            p.sendMessage(prefix + "Checken voor KiipCraft plugin updates.");
            SpigotPluginUpdater spu = new SpigotPluginUpdater(plugin, "http://51.68.47.8/pluginupdate/plugin.html");

            // Checkt voor Update
            if (spu.needsUpdate()) {
                p.sendMessage(prefix + "Update gevonden, start update!");
                p.sendMessage(prefix + "De update wordt bij de eerstvolgende restart toegepast.");
                spu.update();
            } else {
                p.sendMessage(prefix + "Geen update gevonden");
            }
        } else {
            p.sendMessage(noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.update;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

}
