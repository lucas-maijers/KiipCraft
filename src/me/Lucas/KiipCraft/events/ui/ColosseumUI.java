package me.Lucas.KiipCraft.events.ui;

import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.Lucas.KiipCraft.utils.Utils.prefix;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.*;

public class ColosseumUI {

    public static Inventory invColosseum;
    public static String inventory_name;
    public static int inv_rows = 3 * 9;
    private static boolean pvp = false;
    private static boolean mobspawning = false;
    private static boolean buttons = false;


    public static void initialize() {
        inventory_name = Utils.chat("&6&lColosseum Controls");

        invColosseum = createInventory(null, inv_rows);
    }

    public static Inventory colosseumGUI(Player p) {
        Inventory toReturn = createInventory(null, inv_rows, inventory_name);

        for (int i = 1; i < 10; i++) {
            Utils.createItem(invColosseum, BLACK_STAINED_GLASS_PANE, 1, i, " ");
        }

        for (int j = 19; j < 21 + 1; j++) {
            Utils.createItem(invColosseum, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }
        for (int k = 24; k < inv_rows + 1; k++) {
            Utils.createItem(invColosseum, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        Utils.createItem(invColosseum, BLACK_STAINED_GLASS_PANE, 1, 10, " ");
        Utils.createItem(invColosseum, BLACK_STAINED_GLASS_PANE, 1, 18, " ");

        if (!pvp) {
            Utils.createItemLore(invColosseum, DIAMOND_SWORD, 1, 11, "&a&lPvP aan", "&7Zet de Colosseum PvP aan");
        } else {
            Utils.createItemLore(invColosseum, SHIELD, 1, 11, "&c&lPvP uit", "&7Zet de Colosseum PvP uit");
        }

        if (!mobspawning) {
            Utils.createItemLore(invColosseum, ZOMBIE_SPAWN_EGG, 1, 12, "&a&lMob Spawning Aan", "&7Zet mob spawning aan");
        } else {
            Utils.createItemLore(invColosseum, BAT_SPAWN_EGG, 1, 12, "&c&lMob Spawning Uit", "&7Zet mob spawning uit");
        }

        if (!buttons) {
            Utils.createItemLore(invColosseum, STONE_BUTTON, 1, 13, "&a&lButtons aan", "&7Zet buttons aan");
        } else {
            Utils.createItemLore(invColosseum, STONE_BUTTON, 1, 13, "&c&lButtons uit", "&7Zet buttons uit");
        }

        Utils.createItemLore(invColosseum, HORSE_SPAWN_EGG, 1, 14, "&6&lSpawn Paard", "Spawnt een paard voor de paardenrace");

        Utils.createItemLore(invColosseum, NAME_TAG, 1, 15, "&6&lMaak Teams", "&7Maakt de teams");

        Utils.createItemLore(invColosseum, WHITE_BANNER, 1, 16, "&6&lReset Teams", "&7Reset de teams");

        Utils.createItemLore(invColosseum, COMPASS, 1, 17, "&6&lTeleport", "&7Teleporteer naar het Colosseum");


        Utils.createItem(invColosseum, ARROW, 1, 22, "Terug");
        Utils.createItem(invColosseum, BARRIER, 1, 23, "&cSluiten");


        toReturn.setContents(invColosseum.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        // PvP
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lPvP aan"))) {
            p.sendMessage(prefix + "Je hebt de §6§lColosseum PvP §aaangezet§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival collo pvp allow");
            pvp = true;
            p.openInventory(ColosseumUI.colosseumGUI(p));

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lColosseum PvP §aaangezet§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lPvP uit"))) {
            p.sendMessage(prefix + "Je hebt de §6§lColosseum PvP §cuitgezet§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival collo pvp deny");
            pvp = false;
            p.openInventory(ColosseumUI.colosseumGUI(p));

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lColosseum PvP §cuitgezet§7!");
                }
            }
        }
        /*                                               */

        // Mob Spawning
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lMob Spawning Aan"))) {
            p.sendMessage(prefix + "Je hebt §6§lMob Spawning §aaangezet§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival collo mob-spawning allow");
            mobspawning = true;
            p.openInventory(ColosseumUI.colosseumGUI(p));

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §6§lMob Spawning §aaangezet§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lMob Spawning Uit"))) {
            p.sendMessage(prefix + "Je hebt §6§lMob Spawning §cuitgezet§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival collo mob-spawning deny");
            mobspawning = false;
            p.openInventory(ColosseumUI.colosseumGUI(p));

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §6§lMob Spawning §cuitgezet§7!");
                }
            }
        }
        /*                                 */

        // Buttons
        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&a&lButtons aan"))) {
            p.sendMessage(prefix + "Je hebt §6§lButtons §aaangezet§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival collo use allow");
            buttons = true;
            p.openInventory(ColosseumUI.colosseumGUI(p));

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§f heeft §6§lButtons §aaangezet§7!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&c&lButtons uit"))) {
            p.sendMessage(prefix + "Je hebt §6§lButtons §cuitgezet§7!");
            dispatchCommand(getConsoleSender(), "rg flag -w Survival collo use deny");
            buttons = false;
            p.openInventory(ColosseumUI.colosseumGUI(p));

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft §6§lButtons §cuitgezet§7!");
                }
            }
        }
        /*                  */

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lSpawn Paard"))) {
            p.sendMessage(prefix + "Je hebt een §6§lEvent Paard §7gespawned!");
            p.performCommand("summon horse ~ ~ ~ {Bred:0b,Tame:1b,Variant:0,Attributes:[{Name:generic.movementSpeed,Base:0.225},{Name:horse.jumpStrength,Base:0.6}],SaddleItem:{id:\"minecraft:saddle\",Count:1b}}");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft een §6§lEvent Paard §7gespawned!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lMaak teams"))) {
            p.sendMessage(prefix + "§7Je hebt de teams gemaakt!");
            dispatchCommand(getConsoleSender(), "setblock -1371 63 -3589 redstone_block");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lTeams §7gemaakt!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lReset teams"))) {
            p.sendMessage(prefix + "§7Je hebt de teams gereset!");
            dispatchCommand(getConsoleSender(), "team leave @a");

            for (Player plr : getOnlinePlayers()) {
                if (plr.hasPermission("kiipcraft.infomessage") || plr.getName().equals("Thunderkookie15")) {
                    plr.sendMessage(prefix + "§b§l" + p.getName() + "§7 heeft de §6§lTeams §7gereset!");
                }
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&lTeleport"))) {
            p.teleport(new Location(getWorld("Survival"), -1328, 63, -3554));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(MainEventsUI.mainGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase((Utils.chat("&cSluiten")))) {
            p.closeInventory();
        }
    }
}
