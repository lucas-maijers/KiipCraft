package me.Lucas.KiipCraft.bottleXP.listener;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.bottleXP.command.XpBottleCommand;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import static me.Lucas.KiipCraft.utils.Utils.prefix;


public class DrinkXPBottle implements Listener {

    private Main plugin;

    public DrinkXPBottle(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDrinkBottle(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();


        for (int i = 0; i < XpBottleCommand.amountList.size() + 1; i++) {
            if (p.getItemInHand().getItemMeta().getDisplayName().equals(Utils.chat("&aExperience Bottle &7(&9" + i + " Levels&7)"))) {
                p.setLevel(p.getLevel() + i);
                p.sendMessage(prefix + "Je hebt een flesje van §a" + i + " Experience Levels §7opgedronken en de levels gekregen.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
                return;
            }
        }
    }
}
