/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft.texturepack.elements.effects;

import me.Lucas.KiipCraft.Main;
import me.Lucas.KiipCraft.texturepack.elements.suits.DarknessSuit;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class DarknessSuitAbility implements Listener {

    private final Main plugin;

    private final Map<UUID, Integer> chargingSpecial = new HashMap<>();
    private final Set<UUID> onBasicCooldown = new HashSet<>();
    private final Set<UUID> onSpecialCooldown = new HashSet<>();

    public DarknessSuitAbility(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (Objects.equals(p.getInventory().getHelmet(), DarknessSuit.darknessHelmet())) {
                if (Objects.equals(p.getInventory().getChestplate(), DarknessSuit.darknessChestplate())) {
                    if (Objects.equals(p.getInventory().getLeggings(), DarknessSuit.darknessLeggings())) {
                        if (Objects.equals(p.getInventory().getBoots(), DarknessSuit.darknessBoots())) {
                            if (!checkBasicCooldown(p.getUniqueId())) {
                                if (e.getDamager() instanceof Player) {
                                    Player damager = (Player) e.getDamager();
                                    if (!(damager == p)) {
                                        damager.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5 * 20, 1), true);

                                        p.sendMessage(Utils.prefix + Utils.chat(String.format("Je &5&lDark&0&lness &7ability is afgegaan en heeft &d%s &7een &0&lWither&7 vloek gegeven!", damager.getName())));
                                        storeValues(p, "BASIC");
                                    }
                                } else if (e.getDamager() instanceof LivingEntity) {
                                    LivingEntity damager = (LivingEntity) e.getDamager();

                                    damager.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5 * 20, 1), true);

                                    p.sendMessage(Utils.prefix + Utils.chat(String.format("Je &5&lDark&0&lness &7ability is afgegaan en heeft een &d%s &7een &0&lWither&7 vloek gegeven!", damager.getType())));
                                    storeValues(p, "BASIC");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onShiftAbility(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();

        if (Objects.equals(p.getInventory().getHelmet(), DarknessSuit.darknessHelmet())) {
            if (Objects.equals(p.getInventory().getChestplate(), DarknessSuit.darknessChestplate())) {
                if (Objects.equals(p.getInventory().getLeggings(), DarknessSuit.darknessLeggings())) {
                    if (Objects.equals(p.getInventory().getBoots(), DarknessSuit.darknessBoots())) {
                        if (p.isSneaking()) {
                            if (!checkSpecialCooldown(p.getUniqueId())) {
                                if (!chargingSpecial.containsKey(p.getUniqueId())) {
                                    p.sendMessage(Utils.prefix + Utils.chat("Je &5Dark&0ness &7vloek is aan het opladen, klik binnen 2 seconden nog een keer op shift om het af te vuren!"));
                                    chargingSpecial.put(p.getUniqueId(), 1);
                                    startSpecialCharge(p);
                                } else {
                                    chargingSpecial.replace(p.getUniqueId(), 1, 2);
                                    storeValues(p, "SPECIAL");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void storeValues(Player p, String abilityType) {
        if (abilityType.equals("BASIC")) {
            onBasicCooldown.add(p.getUniqueId());
        } else if (abilityType.equals("SPECIAL")) {
            onSpecialCooldown.add(p.getUniqueId());
        }
        startCooldown(p.getUniqueId());
    }

    private Boolean checkBasicCooldown(UUID p) {
        return onBasicCooldown.contains(p);
    }

    private Boolean checkSpecialCooldown(UUID p) {
        return onSpecialCooldown.contains(p);
    }

    private void startCooldown(UUID caster) {
        if (onBasicCooldown.contains(caster)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    onBasicCooldown.remove(caster);
                }
            }.runTaskLater(plugin, 5 * 20);
        }

        if (onSpecialCooldown.contains(caster)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    onSpecialCooldown.remove(caster);
                    if (Bukkit.getOfflinePlayer(caster).isOnline()) {
                        Player p = Bukkit.getPlayer(caster);
                        assert p != null;
                        p.sendMessage(Utils.prefix + Utils.chat("Je &5Dark&0ness &7ability is van cooldown af!"));
                    }
                }
            }.runTaskLater(plugin, 300 * 20L);
        }
    }

    private void startSpecialCharge(Player p) {
        final int taskID = new BukkitRunnable() {
            @Override
            public void run() {
                if (chargingSpecial.get(p.getUniqueId()) == 2) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je vuurt de &5Dark&0ness &5Energie &7af!"));
                    p.sendMessage(Utils.prefix + Utils.chat("Je &5Dark&0ness &7ability heeft nu een &d5 minuten&7 cooldown!"));
                    chargingSpecial.remove(p.getUniqueId());
                    fireSpecial(p);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 1L).getTaskId();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getScheduler().isQueued(taskID)) {
                    if (chargingSpecial.get(p.getUniqueId()) == 1) {
                        chargingSpecial.remove(p.getUniqueId());
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt in 2 seconden niet gebukt, de energie wordt niet afgevuurd!"));
                        Bukkit.getScheduler().cancelTask(taskID);
                    }
                }
            }
        }.runTaskLater(plugin, 2 * 20L);
    }

    private void fireSpecial(Player p) {
        for (Entity e : p.getNearbyEntities(20, 20, 20)) {
            if (e instanceof Player) {
                Player target = ((Player) e).getPlayer();

                assert target != null;
                target.getWorld().spawnParticle(Particle.SMOKE_NORMAL, target.getLocation(), 20, .8, .8, .8, 0.1);
                target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 15 * 20, 1), true);
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15 * 20, 1), true);
                target.playSound(target.getLocation(), Sound.ENTITY_WITHER_SPAWN, (float) 1, (float) 0.7);
            }
        }
    }
}
