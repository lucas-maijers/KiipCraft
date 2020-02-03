package me.Lucas.KiipCraft.admintool;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.Lucas.KiipCraft.utils.Utils.prefix;

public class AdminToolClick implements Listener {

    private Main plugin;

    public AdminToolClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void openAdminTool(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getItemInHand().equals(Utils.adminTool()) && p.hasPermission("kiipcraft.admintool.use")) {
                p.sendMessage(prefix + Utils.chat("Je opent het &4&lAdmin Tool Menu&7!"));
                p.openInventory(AdminToolGUI.adminToolGUI(p));
            } else if (p.getItemInHand().equals(Utils.adminTool()) && !(p.hasPermission("kiipcraft.admintool.use"))) {
                p.sendMessage(prefix + Utils.chat("Dat is niet de bedoeling, jij mag dit item niet gebruiken, hoe kom je hier eigenlijk aan?!"));
                p.getInventory().remove(Utils.adminTool());
            }
        }
    }
}
