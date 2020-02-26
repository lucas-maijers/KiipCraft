/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.commands;

import me.Lucas.KiipCraft.ConfigManager;
import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.items.DungeonItems;
import me.Lucas.KiipCraft.dungeons.listeners.DungeonGateCreation;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DungeonsCommand implements CommandExecutor {

    private Main plugin;

    private File dungeonGatesFile;
    private FileConfiguration dungeonGatesCFG;

    private static ConfigManager cfgm = ConfigManager.getManager();

    private Set<String> dungeonList = new HashSet<>();

    public static Set<String> creatingPlayer = new HashSet<>();

    public DungeonsCommand(Main plugin) {
        this.plugin = plugin;

        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");
        dungeonGatesCFG = YamlConfiguration.loadConfiguration(dungeonGatesFile);

        plugin.getCommand("dungeons").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.consoleMessage);
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("kiipcraft.dungeons")) {
            if (args.length == 0) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een subcommando invoeren!"));
                return true;
            }
            /* Key */
            if (args[0].equals("key") && p.hasPermission("kiipcraft.dungeons.key")) {
                if (args.length == 1) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet een geldige soort sleutel invoeren, geldige soorten zijn: Gold, Diamond, Emerald!"));
                    return true;
                }
                if (args.length == 3) {
                    for (Player plr : Bukkit.getOnlinePlayers()) {
                        if (args[2].equals(plr.getName())) {
                            p = plr;
                        }
                    }
                }
                switch (args[1].toLowerCase()) {
                    case "diamond":
                        p.getInventory().addItem(DungeonItems.diamondKey());
                        if (args.length == 3) {
                            sender.sendMessage(Utils.prefix + Utils.chat("Je hebt &d&l" + p.getName() + " &7een &b&LDiamond &c&lDungeon Key &7gegeven!"));
                        }
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &b&lDiamond &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "emerald":
                        p.getInventory().addItem(DungeonItems.emeraldKey());
                        if (args.length == 3) {
                            sender.sendMessage(Utils.prefix + Utils.chat("Je hebt &d&l" + p.getName() + " &7een &a&LEmerald &c&lDungeon Key &7gegeven!"));
                        }
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &a&lEmerald &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "gold":
                        p.getInventory().addItem(DungeonItems.goldKey());
                        if (args.length == 3) {
                            sender.sendMessage(Utils.prefix + Utils.chat("Je hebt &d&l" + p.getName() + " &7een &6&LGold &c&lDungeon Key &7gegeven!"));
                        }
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &6&lGold &c&lDungeon Key &7ontvangen!"));
                        break;
                    default:
                        p.sendMessage(Utils.prefix + Utils.chat("Dit is een ongeldige sleutelnaam!"));
                        break;
                }
                return true;
            }
            /* GateTool */
            if (args[0].equals("tool") && p.hasPermission("kiipcraft.dungeons.tool")) {
                p.getInventory().addItem(DungeonItems.dungeonGateTool());
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de &c&lDungeons Gate Tool &7ontvangen!"));
                return true;
            }
            /* Create Dungeon */
            if (args[0].equals("create") && p.hasPermission("kiipcraft.dungeons.tool.create")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het creatieproces al gestart, doe &c/dungeons stopcreation&7 om dit te annuleren!"));
                    return true;
                }
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Dungeon Creatie proces gestart, om dit te annuleren doe &c/dungeons stopcreation&7!"));
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Selecteer de linker bovenhoek van de dungeon gate met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                creatingPlayer.add(p.getName());
                return true;
            }
            /* Stop Creation */
            if (args[0].equals("stopcreation") && p.hasPermission("kiipcraft.dungeons.tool.create")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Dungeon Creatie proces gestopt."));

                    DungeonGateCreation.phase1.remove(p.getName());
                    DungeonGateCreation.phase2.remove(p.getName());
                    DungeonGateCreation.phase3.remove(p.getName());
                    DungeonGateCreation.phasesComplete.remove(p.getName());

                    creatingPlayer.remove(p.getName());
                    return true;
                } else if (!creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen Dungeon Creatie proces geactiveerd, doe &c/dungeons create&7 om het te starten!"));
                    return true;
                }
            }
            /* List Gates */
            if (args[0].equals("list") && p.hasPermission("kiipcraft.dungeons.list")) {
                refreshList();
                p.sendMessage(Utils.prefix + Utils.chat("Dungeons:"));
                for (String s : dungeonList) {
                    p.sendMessage(Utils.chat("  &7- &a" + s));
                }
            }
            /* Remove Gates */
            if (args[0].equals("remove") && p.hasPermission("kiipcraft.dungeons.remove")) {
                refreshList();
                if (args.length == 1) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen dungeon ingevuld om te verwijderen, probeer het opnieuw!"));
                    return true;
                }

                if (dungeonList.contains(args[1])) {
                    ConfigurationSection cs = dungeonGatesCFG.getConfigurationSection("Dungeons");

                    try {
                        assert cs != null;
                        cs.set(args[1], null);
                        dungeonGatesCFG.save(dungeonGatesFile);
                        dungeonList.remove(args[1]);
                    } catch (IOException e) {
                        p.sendMessage(Utils.prefix + Utils.chat("Error met het verwijderen van deze dungeongate, raadpleeg console voor meer informatie!"));
                        e.printStackTrace();
                    }
                    p.sendMessage(Utils.prefix + Utils.chat("De dungeongate met de naam: &c" + args[1] + "&7 wordt verwijderd!"));
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat("De dungeongate " + args[1] + " bestaat niet! Doe &c/dungeons list &7voor een lijst met dungeongates."));
                }
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
        return false;
    }

    private void refreshList() {
        dungeonList.addAll(cfgm.getDungeonGatesCFG().getConfigurationSection("Dungeons").getKeys(false));
    }
}
