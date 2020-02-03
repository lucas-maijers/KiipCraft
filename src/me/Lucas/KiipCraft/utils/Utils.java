package me.Lucas.KiipCraft.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.Lucas.KiipCraft.outofuse.commands.KiipEuroCommand;
import me.Lucas.KiipCraft.commands.XpBottleCommand;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

import static org.bukkit.Material.*;

public class Utils {

    public static String prefix = "§c§lKiipcraft §7§l»§7 ";

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack kiipEuro() {
        ItemStack kiipEuro = new ItemStack(IRON_NUGGET, KiipEuroCommand.aantalKiipEuros);
        ItemMeta kiipEuroMeta = kiipEuro.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§5De KiipEuro om items in de Shop mee te kopen.");
        lore.add("Officieel KiipCraft Store Item.");

        assert kiipEuroMeta != null;
        kiipEuroMeta.setDisplayName(Utils.chat("&6&l&oKiipEuro"));
        kiipEuroMeta.setLore(lore);

        kiipEuro.setItemMeta(kiipEuroMeta);
        return kiipEuro;
    }

    public static ItemStack kiipEuroAdmin() {
        ItemStack kiipEuro = new ItemStack(IRON_NUGGET, 64);
        ItemMeta kiipEuroMeta = kiipEuro.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§5De KiipEuro om items in de Shop mee te kopen.");
        lore.add("Officieel KiipCraft Store Item.");

        assert kiipEuroMeta != null;
        kiipEuroMeta.setDisplayName(Utils.chat("&6&l&oKiipEuro"));
        kiipEuroMeta.setLore(lore);

        kiipEuro.setItemMeta(kiipEuroMeta);
        return kiipEuro;
    }

    public static ItemStack kiipDollar() {
        ItemStack kiipDollar = new ItemStack(IRON_NUGGET, 64);
        ItemMeta kiipDollarMeta = kiipDollar.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5De KiipDollar om items in de Shop mee te kopen.");
        lore.add("§5De Halloween Tokens van 2019.");
        lore.add("Officieel KiipCraft Store Item.");

        assert kiipDollarMeta != null;
        kiipDollarMeta.setDisplayName(Utils.chat("&6&l&oKiipDollar"));
        kiipDollarMeta.setLore(lore);

        kiipDollar.setItemMeta(kiipDollarMeta);
        return kiipDollar;
    }

    public static ItemStack kiipFrank() {
        ItemStack kiipFrank = new ItemStack(IRON_NUGGET, 64);
        ItemMeta kiipFrankM = kiipFrank.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5De Kiipse Frank om items in de Shop mee te kopen.");
        lore.add("§5De Kerst Tokens van 2019.");
        lore.add("Officieel KiipCraft Store Item.");

        assert kiipFrankM != null;
        kiipFrankM.setDisplayName(Utils.chat("&6&l&oKiipse Frank"));
        kiipFrankM.setLore(lore);

        kiipFrank.setItemMeta(kiipFrankM);
        return kiipFrank;
    }

    public static ItemStack eventsTool() {
        ItemStack eventsTool = new ItemStack(NETHER_STAR);
        ItemMeta eventsToolM = eventsTool.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7Opent het Events Menu"));

        assert eventsToolM != null;
        eventsToolM.setDisplayName(Utils.chat("&3&lEvents Tool"));
        eventsToolM.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        eventsToolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        eventsToolM.setLore(lore);
        eventsTool.setItemMeta(eventsToolM);
        return eventsTool;
    }

    public static ItemStack adminTool() {
        ItemStack adminTool = new ItemStack(NETHER_STAR);
        ItemMeta adminToolM = adminTool.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7Opent het Admin Menu"));

        assert adminToolM != null;
        adminToolM.setDisplayName(Utils.chat("&4&lAdmin Tool"));
        adminToolM.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        adminToolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        adminToolM.setLore(lore);
        adminTool.setItemMeta(adminToolM);
        return adminTool;
    }

    public static ItemStack grinchMasker() {
        ItemStack grinchMask = new ItemStack(PLAYER_HEAD);
        SkullMeta grinchMaskM = (SkullMeta) grinchMask.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/19c89a889c4bb421ea9c56cbeae7af1e6d85f4faff34acba72e1a8d5c4116a8";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            assert grinchMaskM != null;
            profileField = grinchMaskM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(grinchMaskM, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        grinchMask.setItemMeta(grinchMaskM);
        return grinchMask;
    }

    public static ItemStack elfMasker() {
        ItemStack elfMask = new ItemStack(PLAYER_HEAD);
        SkullMeta elfMaskM = (SkullMeta) elfMask.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/7fbbf9e4efd0193c5264719ba478b917a2607146f6ea80cb5fe093c3b99a4c";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            assert elfMaskM != null;
            profileField = elfMaskM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(elfMaskM, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        elfMask.setItemMeta(elfMaskM);
        return elfMask;
    }

    public static ItemStack spMasker() {
        ItemStack spMask = new ItemStack(PLAYER_HEAD);
        SkullMeta spMaskM = (SkullMeta) spMask.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/e27695bee2babf9baef11ae19fe5f817fa088cd52c4c473cc7b441d5c3f50fe";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            assert spMaskM != null;
            profileField = spMaskM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(spMaskM, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        spMask.setItemMeta(spMaskM);
        return spMask;
    }

    public static ItemStack rdMasker() {
        ItemStack rdMask = new ItemStack(PLAYER_HEAD);
        SkullMeta rdMaskM = (SkullMeta) rdMask.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/ebd46b38b21b342caf917ad9ca42afb68388a5591bcc9aded1e8e346e18890";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            assert rdMaskM != null;
            profileField = rdMaskM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(rdMaskM, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        rdMask.setItemMeta(rdMaskM);
        return rdMask;
    }

    public static ItemStack santaMask() {
        ItemStack scMask = new ItemStack(PLAYER_HEAD);
        SkullMeta scMaskM = (SkullMeta) scMask.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/14e424b1676feec3a3f8ebade9e7d6a6f71f7756a869f36f7df0fc182d436e";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            assert scMaskM != null;
            profileField = scMaskM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(scMaskM, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        scMask.setItemMeta(scMaskM);
        return scMask;
    }

    public static ItemStack spleefSchep() {
        ItemStack spleefSchep = new ItemStack(DIAMOND_SHOVEL, 1);
        ItemMeta schepMeta = spleefSchep.getItemMeta();

        assert schepMeta != null;
        schepMeta.setDisplayName(Utils.chat("&b&lSpleefSchep"));
        schepMeta.addEnchant(Enchantment.DIG_SPEED, 50, true);
        schepMeta.addEnchant(Enchantment.DURABILITY, 100, true);

        spleefSchep.setItemMeta(schepMeta);
        return spleefSchep;
    }

    public static ItemStack xpDrinkFles() {
        ItemStack xpFlesje = new ItemStack(POTION, 1);
        PotionMeta xpFlesjeM = (PotionMeta) xpFlesje.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&7Deze levels zijn door &b" + XpBottleCommand.bottler + "&7 in dit flesje gestopt!"));

        assert xpFlesjeM != null;
        xpFlesjeM.setDisplayName(Utils.chat("&aExperience Bottle &7(&9" + XpBottleCommand.amountGet + " Levels&7)"));
        xpFlesjeM.setColor(Color.fromRGB(128, 255, 32));
        xpFlesjeM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        xpFlesjeM.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);
        xpFlesjeM.setLore(lore);

        xpFlesje.setItemMeta(xpFlesjeM);
        return xpFlesje;
    }

    public static ItemStack glasFlesje() {
        return new ItemStack(GLASS_BOTTLE, 1);
    }

    public static ItemStack createItem(Inventory inv, Material materialName, int amount, int invSlot, String displayName) {

        ItemStack item;

        item = new ItemStack(materialName, amount);

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }

    public static ItemStack createItemLore(Inventory inv, Material materialName, int amount, int invSlot, String displayName, String... loreString) {

        ItemStack item;
        ArrayList<String> lore = new ArrayList<>();

        item = new ItemStack(materialName, amount);

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }

    public static void createItemHead(Inventory inv, ItemStack stackName, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item;
        ArrayList<String> lore = new ArrayList<>();

        item = stackName;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
    }
}