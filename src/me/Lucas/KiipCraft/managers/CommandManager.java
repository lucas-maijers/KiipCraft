/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.managers;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.admintool.command.AdminToolCommand;
import me.Lucas.KiipCraft.bottleXP.command.XpBottleCommand;
import me.Lucas.KiipCraft.commands.HelpCommand;
import me.Lucas.KiipCraft.commands.UpdateCommand;
import me.Lucas.KiipCraft.dungeons.commands.DungeonsCommand;
import me.Lucas.KiipCraft.events.command.EventTokenCommand;
import me.Lucas.KiipCraft.events.command.EventsToolCommand;
import me.Lucas.KiipCraft.events.command.GUICommand;
import me.Lucas.KiipCraft.servertour.ServerTourCommand;
import me.Lucas.KiipCraft.storyline.commands.OrbCommand;
import me.Lucas.KiipCraft.storyline.commands.ShardCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<>();

    private Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    public static ArrayList<String> commandList = new ArrayList<>();

    public String main = "kiipcraft";

    public String help = "help";
    public String update = "update";
    public String admintool = "admintool";
    public String bottlexp = "bottlexp";
    public String eventstool = "eventstool";
    public String eventsadmin = "eventsadmin";
    public String eventtoken = "eventstoken";
    public String dungeons = "dungeons";
    public String servertour = "servertour";
    public String orb = "orb";
    public String shard = "shard";

    public void setup() {
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new HelpCommand(plugin));
        this.commands.add(new UpdateCommand(plugin));
        this.commands.add(new XpBottleCommand(plugin));
        this.commands.add(new EventsToolCommand(plugin));
        this.commands.add(new GUICommand(plugin));
        this.commands.add(new EventTokenCommand(plugin));
        this.commands.add(new AdminToolCommand(plugin));
        this.commands.add(new DungeonsCommand(plugin));
        this.commands.add(new ServerTourCommand(plugin));
        this.commands.add(new OrbCommand(plugin));
        this.commands.add(new ShardCommand(plugin));

        commandList.add(help);
        commandList.add(update);
        commandList.add(admintool);
        commandList.add(bottlexp);
        commandList.add(eventstool);
        commandList.add(eventsadmin);
        commandList.add(eventtoken);
        commandList.add(dungeons);
        commandList.add(servertour);
        commandList.add(orb);
        commandList.add(shard);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.consoleMessage);
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                p.sendMessage(Utils.prefix + Utils.chat("Geen subcommando gevonden. Doe &c/kiipcraft help &7voor meer informatie!"));
                return true;
            }

            SubCommand target = this.get(args[0]);

            if (target == null) {
                p.sendMessage(Utils.prefix + Utils.chat("Ongeldig subcommando, doe &c/kiipcraft help &7voor een lijst met geldige commmandos!"));
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);
            try {
                target.onCommand(p, args);
            } catch (Exception e) {
                p.sendMessage(Utils.prefix + Utils.chat("&cEr is een error voorgekomen, raadpleeg een Administrator voor meer informatie!"));

                e.printStackTrace();
            }
        }
        return true;
    }

    private SubCommand get(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommand scmd = subcommands.next();
            if (scmd.name().equalsIgnoreCase(name)) {
                return scmd;
            }

            String[] aliases;
            int length = (aliases = scmd.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return scmd;
                }
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) { // kiipcraft <subcommand> <args>
            List<String> completionList = new ArrayList<>();

            if (!args[0].equals("")) {
                for (String s : commandList) {
                    if (s.startsWith(args[0].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }

            ArrayList<String> subCommands = new ArrayList<>(commandList);
            return subCommands;
        } else if (args.length > 1) {
            for (SubCommand command : commands) {
                if (args[0].equalsIgnoreCase(command.name())) {
                    return command.getArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}
