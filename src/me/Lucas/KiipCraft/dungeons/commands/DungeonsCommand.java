/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.dungeons.commands;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.dungeons.items.DungeonItems;
import me.Lucas.KiipCraft.dungeons.listeners.DungeonGateCreation;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DungeonsCommand extends SubCommand {

    public static Set<String> creatingPlayer = new HashSet<>();
    private Main plugin;
    private File dungeonGatesFile;
    private FileConfiguration dungeonGatesCFG;
    private ConfigManager cfgm = ConfigManager.getManager();
    private List<String> dungeonList = new ArrayList<>();
    private ArrayList<String> dSubCommands = new ArrayList<>();

    public DungeonsCommand(Main plugin) {
        this.plugin = plugin;

        dungeonGatesFile = new File(plugin.getDataFolder(), "dungeons.yml");

        dSubCommands.add("help");
        dSubCommands.add("key");
        dSubCommands.add("tool");
        dSubCommands.add("create");
        dSubCommands.add("stopcreation");
        dSubCommands.add("list");
        dSubCommands.add("remove");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.staff")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een argument invoeren! Doe /kiipcraft dungeons help voor meer info."));
                return;
            }

            if (args[1].equalsIgnoreCase("help")) {
                p.sendMessage(Utils.prefix + Utils.chat("&c&lDungeon Commands:"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons key <soort> [speler]&7: Geeft een dungeon sleutel."));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons tool&7: Geeft je de Dungeon Creatietool."));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons create&7: Start het dungeon creatieproces."));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons stopcreation&7: Stopt het dungeon creatieproces."));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons list&7: Geeft je een lijst met dungeons."));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft dungeons remove <naam>&7: Verwijderd de dungeongate met de ingevoerde naam."));
            }

            /* Key */
            if (args[1].equalsIgnoreCase("key")) {
                if (args.length == 2) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet een geldige soort sleutel invoeren, geldige soorten zijn: Gold, Diamond, Emerald!"));
                    return;
                }
                Player sender = p;
                if (args.length == 4) {
                    for (Player plr : Bukkit.getOnlinePlayers()) {
                        if (args[3].equals(plr.getName())) {
                            p = plr;
                        }
                    }
                }

                switch (args[2].toUpperCase()) {
                    case "DIAMOND":
                        p.getInventory().addItem(DungeonItems.diamondKey());
                        if (args.length == 4) {
                            sender.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt &d&l%s &7een &b&LDiamond &c&lDungeon Key &7gegeven!", p.getName())));
                        }
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &b&lDiamond &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "EMERALD":
                        p.getInventory().addItem(DungeonItems.emeraldKey());
                        if (args.length == 4) {
                            sender.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt &d&l%s &7een &a&LEmerald &c&lDungeon Key &7gegeven!", p.getName())));
                        }
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &a&lEmerald &c&lDungeon Key &7ontvangen!"));
                        break;
                    case "GOLD":
                        p.getInventory().addItem(DungeonItems.goldKey());
                        if (args.length == 4) {
                            sender.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt &d&l%s &7een &6&LGold &c&lDungeon Key &7gegeven!", p.getName())));
                        }
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &6&lGold &c&lDungeon Key &7ontvangen!"));
                        break;
                    default:
                        p.sendMessage(Utils.prefix + Utils.chat("Dit is een ongeldige sleutelnaam!"));
                        break;
                }
                return;
            }
            /* GateTool */
            if (args[1].equalsIgnoreCase("tool")) {
                p.getInventory().addItem(DungeonItems.dungeonGateTool());
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de &c&lDungeons Gate Tool &7ontvangen!"));
                p.sendMessage(Utils.prefix + Utils.chat("Het Dungeon Creatieproces is gestart, om dit te annuleren doe &c/kiipcraft dungeons stopcreation&7!"));
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Selecteer de linker bovenhoek van de dungeon gate met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                creatingPlayer.add(p.getName());
                return;
            }
            /* Create Dungeon */
            if (args[1].equalsIgnoreCase("create")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het creatieproces al gestart, doe &c/kiipcraft dungeons stopcreation&7 om dit te annuleren!"));
                    return;
                }
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Dungeon Creatie proces gestart, om dit te annuleren doe &c/kiipcraft dungeons stopcreation&7!"));
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Selecteer de linker bovenhoek van de dungeon gate met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                creatingPlayer.add(p.getName());
                return;
            }
            /* Stop Creation */
            if (args[1].equalsIgnoreCase("stopcreation")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het Dungeon Creatie proces gestopt."));

                    DungeonGateCreation.phase1.remove(p.getName());
                    DungeonGateCreation.phase2.remove(p.getName());
                    DungeonGateCreation.phase3.remove(p.getName());
                    DungeonGateCreation.lockType.remove(p.getName());
                    DungeonGateCreation.phasesComplete.remove(p.getName());

                    creatingPlayer.remove(p.getName());
                    return;
                } else if (!creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen Dungeon Creatie proces geactiveerd, doe &c/kiipcraft dungeons create&7 om het te starten!"));
                    return;
                }
            }
            /* List Gates */
            if (args[1].equalsIgnoreCase("list")) {
                refreshList();
                dungeonGatesCFG = cfgm.getDungeonGatesCFG();
                p.sendMessage(Utils.prefix + Utils.chat("Dungeons:"));
                for (String s : dungeonList) {
                    ConfigurationSection cs = dungeonGatesCFG.getConfigurationSection("Dungeons." + s);
                    p.sendMessage(Utils.chat(String.format("  &7- &a%s &7(&c%s&7)", s, cs.getString("DungeonGateLockType"))));
                }
            }
            /* Remove Gates */
            if (args[1].equalsIgnoreCase("remove")) {
                refreshList();
                if (args.length == 2) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen dungeon ingevuld om te verwijderen, probeer het opnieuw!"));
                    return;
                }

                if (dungeonList.contains(args[2])) {
                    ConfigurationSection cs = dungeonGatesCFG.getConfigurationSection("Dungeons");

                    try {
                        assert cs != null;
                        cs.set(args[2], null);
                        dungeonGatesCFG.save(dungeonGatesFile);
                        dungeonList.remove(args[2]);
                    } catch (IOException e) {
                        p.sendMessage(Utils.prefix + Utils.chat("Error met het verwijderen van deze dungeongate, raadpleeg console voor meer informatie!"));
                        e.printStackTrace();
                    }
                    p.sendMessage(Utils.prefix + Utils.chat(String.format("De dungeongate met de naam: &c%s&7 wordt verwijderd!", args[2])));
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat(String.format("De dungeongate %s bestaat niet! Doe &c/kiipcraft dungeons list &7voor een lijst met dungeongates.", args[2])));
                }
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    private void refreshList() {
        dungeonList.clear();
        dungeonList.addAll(cfgm.getDungeonGatesCFG().getConfigurationSection("Dungeons").getKeys(false));
    }

    @Override
    public String name() {
        return plugin.cmdMngr.dungeons;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public List<String> getArguments(Player player, String[] args) {
        if (args.length == 2) {
            List<String> completionList = new ArrayList<>();
            if (!args[1].equals("")) {
                for (String s : dSubCommands) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }

            return new ArrayList<>(dSubCommands);
        } else if (args.length == 3) {
            if (args[1].equals("key")) {
                List<String> completionList = new ArrayList<>();
                List<String> types = new ArrayList<>();

                types.add("gold");
                types.add("diamond");
                types.add("emerald");

                if (!args[2].equals("")) {
                    for (String s : types) {
                        if (s.startsWith(args[2].toLowerCase())) {
                            completionList.add(s);
                        }
                    }
                    return completionList;
                }
                return types;
            }

            if (args[1].equals("remove")) {
                List<String> completionList = new ArrayList<>();
                refreshList();

                if (!args[2].equals("")) {
                    for (String s : dungeonList) {
                        if (s.startsWith(args[2])) {
                            completionList.add(s);
                        }
                    }
                    return completionList;
                }
                return dungeonList;
            }
        }
        return null;
    }
}
