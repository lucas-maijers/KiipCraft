/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.events.command;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.events.listener.SyncKistCreation;
import me.Lucas.KiipCraft.managers.ConfigManager;
import me.Lucas.KiipCraft.managers.SubCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EventsCommand extends SubCommand {

    public static int aantal = 64;
    public static Set<String> creatingPlayer = new HashSet<>();
    public static Map<String, String> selector = new HashMap<>();
    private Main plugin;
    private ConfigManager cfgm = ConfigManager.getManager();
    private File syncFile;
    private FileConfiguration syncCFG;
    private List<String> synced = new ArrayList<>();
    private ArrayList<String> eSubCommands = new ArrayList<>();
    private ArrayList<String> selectorTypes = new ArrayList<>();

    public EventsCommand(Main plugin) {
        this.plugin = plugin;

        syncFile = new File(plugin.getDataFolder(), "syncchests.yml");
        syncCFG = YamlConfiguration.loadConfiguration(syncFile);

        eSubCommands.add("help");
        eSubCommands.add("tool");
        eSubCommands.add("token");
        eSubCommands.add("synctool");
        eSubCommands.add("synckist");
        eSubCommands.add("synclist");
        eSubCommands.add("stopsync");
        eSubCommands.add("selectortool");
        eSubCommands.add("select");
        eSubCommands.add("remove");

        selectorTypes.add("buildbattlewall");

    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("kiipcraft.staff")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een argument invoeren! Doe /kiipcraft events help voor meer info."));
                return;
            }

            // Help
            if (args[1].equalsIgnoreCase("help")) {
                p.sendMessage(Utils.prefix + Utils.chat("&c&lEvent Commands:"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events tool&7: Geeft je de events tool!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events token [aantal] [speler]&7: Geeft event tokens aan een speler!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events synctool&7: Geeft je de kistsynctool!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events synckist&7: Start het kist synchronisatieproces!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events synclist&7: Geeft je de lijst met gesynchroniseerde kisten!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events stopsync&7: Stop het synchronisatieproces!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events selectortool&7: Geeft je de selectortool!"));
                p.sendMessage(Utils.chat(" &7- &3/kiipcraft events remove [naam]&7: Verwijderd de kistsynchronisatie met de ingevoerde naam!"));
                return;
            }

            // Tool
            if (args[1].equalsIgnoreCase("tool")) {
                if (!(p.getInventory().contains(Utils.eventsTool()))) {
                    p.sendMessage(Utils.prefix + "Je hebt de §3§lEvents Tool §7gekregen.");
                    p.getInventory().addItem(Utils.eventsTool());
                } else if (p.getInventory().contains(Utils.eventsTool())) {
                    p.sendMessage(Utils.prefix + "Je hebt de §3§lEvents Tool §7uit je inventaris verwijderd.");
                    p.getInventory().removeItem(Utils.eventsTool());
                }
                return;
            }

            // Selectortool
            if (args[1].equalsIgnoreCase("selectortool")) {
                if (!(p.getInventory().contains(Utils.selectorTool()))) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de selectortool ontvangen!"));
                    p.getInventory().addItem(Utils.selectorTool());
                } else if (p.getInventory().contains(Utils.selectorTool())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de selectortool verwijderd!"));
                    p.getInventory().removeItem(Utils.selectorTool());
                }
                return;
            }

            // Select
            if (args[1].equalsIgnoreCase("select")) {
                if (selectorTypes.contains(args[2])) {
                    p.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt de selectie gestart voor:&c %s&7!", args[2])));
                    selector.put(p.getName(), args[2]);
                }
            }

            // Token
            if (args[1].equalsIgnoreCase("token")) {
                if (args.length == 2) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt jezelf Event Tokens gegeven"));
                    p.getInventory().addItem(Utils.eventToken());
                } else if (args.length == 3) {
                    try {
                        aantal = Integer.parseInt(args[2]);
                        p.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt &6%d&7 Event Tokens aan jezelf gegeven!", aantal)));
                        p.getInventory().addItem(Utils.eventToken());
                    } catch (NumberFormatException e) {
                        p.sendMessage(Utils.prefix + Utils.chat("Je moet een geldig getal invullen!"));
                    }
                } else if (args.length == 4) {
                    aantal = Integer.parseInt(args[2]);
                    for (Player plr : Bukkit.getOnlinePlayers()) {
                        if (args[3].equalsIgnoreCase(plr.getName())) {
                            p.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt &6%d&7 Event Tokens aan &d&l%s&7 gegeven!", aantal, plr.getName())));
                            plr.getInventory().addItem(Utils.eventToken());
                        } else if (!Bukkit.getOnlinePlayers().contains(plr)) {
                            p.sendMessage(Utils.prefix + Utils.chat("Deze speler is niet gevonden!"));
                        }
                    }
                }
            }

            // Synctool
            if (args[1].equalsIgnoreCase("synctool")) {
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt de &6&lSyncTool &7ontvangen!"));
                p.getInventory().addItem(Utils.syncTool());
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt het kist synchronisatie proces gestart, om dit te annuleren doe &c/kiipcraft events stopcreation"));
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Selecteer de kist waarvan gesynchroniseerd moet worden met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                creatingPlayer.add(p.getName());
                return;
            }

            // Synckist
            if (args[1].equalsIgnoreCase("synckist")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het creatieproces al gestart, doe &c/kiipcraft events stopsync&7om dit te annuleren!"));
                    return;
                }
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt het kist synchronisatie proces gestart, om dit te annuleren doe &c/kiipcraft events stopcreation"));
                p.sendMessage(" ");
                p.sendMessage(Utils.prefix + Utils.chat("Selecteer de kist waarvan gesynchroniseerd moet worden met je &c&l&nLinker&7 &c&l&nMuisknop&7!"));
                creatingPlayer.add(p.getName());
                return;
            }

            // Stopcreation
            if (args[1].equalsIgnoreCase("stopsync")) {
                if (creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt het kist synchronisatie proces gestopt."));

                    SyncKistCreation.phase1.remove(p.getName());
                    SyncKistCreation.phase2.remove(p.getName());

                    creatingPlayer.remove(p.getName());
                    return;
                } else if (!creatingPlayer.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen kist synchronisatie proces geactiveerd, doe &c/kiipcraft events synckist&7 om het te starten!"));
                    return;
                }
            }

            // Synclist
            if (args[1].equalsIgnoreCase("synclist")) {
                p.sendMessage(Utils.prefix + Utils.chat("Synchroniseerde Kisten:"));
                refreshList();
                for (String s : synced) {
                    p.sendMessage(Utils.chat("  &7- &a" + s));
                }
                return;
            }

            // Remove
            if (args[1].equalsIgnoreCase("remove")) {
                refreshList();
                if (args.length == 2) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt geen chestsync ingevuld om te verwijderen, probeer het opnieuw!"));
                    return;
                }

                if (synced.contains(args[2])) {
                    ConfigurationSection cs = syncCFG.getConfigurationSection("SyncedChests");

                    try {
                        assert cs != null;
                        cs.set(args[2], null);
                        syncCFG.save(syncFile);
                        synced.remove(args[2]);
                    } catch (IOException e) {
                        p.sendMessage(Utils.prefix + Utils.chat("Error met het verwijderen van deze chestsynchronisatie, raadpleeg console voor meer informatie!"));
                        e.printStackTrace();
                    }
                    p.sendMessage(Utils.prefix + Utils.chat("De chestsync met de naam: &c" + args[2] + "&7 wordt verwijderd!"));
                } else {
                    p.sendMessage(Utils.prefix + Utils.chat("De chestsync " + args[2] + " bestaat niet! Doe &c/kiipcraft events synclist &7voor een lijst met synchronisaties."));
                }
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    private void refreshList() {
        synced.clear();
        synced.addAll(cfgm.getSyncChestsCFG().getConfigurationSection("SyncedChests").getKeys(false));
    }

    @Override
    public String name() {
        return plugin.cmdMngr.events;
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
                for (String s : eSubCommands) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return new ArrayList<>(eSubCommands);
        } else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("token")) {
                List<String> amountList = new ArrayList<>();
                for (int i = 1; i <= 64; i++) {
                    amountList.add(String.valueOf(i));
                }
                Arrays.sort(amountList.toArray());
                return amountList;
            }
        }

        if (args[1].equals("remove")) {
            List<String> completionList = new ArrayList<>();
            refreshList();

            if (!args[2].equals("")) {
                for (String s : synced) {
                    if (s.startsWith(args[2])) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return synced;
        }
        return null;
    }
}
