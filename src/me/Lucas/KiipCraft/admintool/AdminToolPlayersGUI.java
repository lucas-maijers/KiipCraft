package me.Lucas.KiipCraft.admintool;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.Material.*;

public class AdminToolPlayersGUI {

    public static Inventory inv;
    public static String inv_name;
    public static Player plrData = null;
    private static Main plugin;
    private static int row_now = 5;
    public static int inv_rows = row_now * 9;

    public AdminToolPlayersGUI(Main plugin) {
        AdminToolPlayersGUI.plugin = plugin;
    }

    public static void initialize() {
        inv_name = Utils.chat("&4&lSpelers Menu");

        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory spelersMenu(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int j = 37; j < 41; j++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, j, " ");
        }

        for (int k = 42; k < inv_rows + 1; k++) {
            Utils.createItem(inv, BLACK_STAINED_GLASS_PANE, 1, k, " ");
        }

        int i = 1;
        inv.remove(PLAYER_HEAD);
        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.getName().equals(p.getName())) {
                Utils.createItemHead(inv, playerHead(plr), 1, i, "&6&l" + plr.getName(), " ", "&c&lHealth: &c" + plr.getHealth(), "&b&lX: &a" + plr.getLocation().getX(), "&b&lY: &a" + plr.getLocation().getY(), "&b&lZ: &a" + plr.getLocation().getZ(), " ", "&5Dit ben jij zelf!");
            } else {
                Utils.createItemHead(inv, playerHead(plr), 1, i, "&6&l" + plr.getName(), " ", "&c&lHealth: &c" + plr.getHealth(), "&b&lX: &a" + plr.getLocation().getX(), "&b&lY: &a" + plr.getLocation().getY(), "&b&lZ: &a" + plr.getLocation().getZ(), " ", "&5Klik om instellingen voor deze speler te openen.");
            }
            i++;
        }

        Utils.createItem(inv, ARROW, 1, 40, "Terug");
        Utils.createItem(inv, BARRIER, 1, 41, "&cSluiten");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }


    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {

        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&6&l" + plr.getName()))) {
                plrData = plr;
                p.sendMessage(Utils.prefix + Utils.chat("Je opent de settings voor &d&l" + plrData.getName() + "&7!"));
                AdminToolPlayerSettings.initialize();
                p.openInventory(AdminToolPlayerSettings.playerSettings(p));
            }
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("Terug"))) {
            p.openInventory(AdminToolGUI.adminToolGUI(p));
        }

        if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&cSluiten"))) {
            p.closeInventory();
        }
    }


    private static ItemStack playerHead(Player p) {
        ItemStack adminHead = new ItemStack(PLAYER_HEAD);
        SkullMeta adminHeadM = (SkullMeta) adminHead.getItemMeta();

        assert adminHeadM != null;
        adminHeadM.setOwningPlayer(p);
        adminHead.setItemMeta(adminHeadM);
        return adminHead;
    }
}
