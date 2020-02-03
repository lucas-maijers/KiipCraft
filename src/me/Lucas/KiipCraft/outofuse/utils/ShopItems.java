package me.Lucas.KiipCraft.outofuse.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.Lucas.KiipCraft.utils.Utils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

import static org.bukkit.Material.*;

public class ShopItems {

    // Geld

    public static ItemStack kiipEuroCost() {
        ItemStack kiipEuro = new ItemStack(IRON_NUGGET, 5);
        ItemMeta kiipEuroMeta = kiipEuro.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§5De KiipEuro om items in de Shop mee te kopen.");
        lore.add("Officieel KiipCraft Store Item.");

        kiipEuroMeta.setDisplayName(Utils.chat("&6&l&oKiipEuro"));
        kiipEuroMeta.setLore(lore);

        kiipEuro.setItemMeta(kiipEuroMeta);
        return kiipEuro;
    }

    public static ItemStack kiipDollarCost() {
        ItemStack kiipDollar = new ItemStack(IRON_NUGGET, 5);
        ItemMeta kiipDollarMeta = kiipDollar.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5De KiipDollar om items in de Shop mee te kopen.");
        lore.add("§5De Halloween Tokens van 2019.");
        lore.add("Officieel KiipCraft Store Item.");

        kiipDollarMeta.setDisplayName(Utils.chat("&6&l&oKiipDollar"));
        kiipDollarMeta.setLore(lore);

        kiipDollar.setItemMeta(kiipDollarMeta);
        return kiipDollar;
    }

    public static ItemStack kiipFrankCost() {
        ItemStack kiipFrank = new ItemStack(IRON_NUGGET, 5);
        ItemMeta kiipFrankM = kiipFrank.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§5De Kiipse Frank om items in de Shop mee te kopen.");
        lore.add("§5De Kerst Tokens van 2019.");
        lore.add("Officieel KiipCraft Store Item.");

        kiipFrankM.setDisplayName(Utils.chat("&6&l&oKiipse Frank"));
        kiipFrankM.setLore(lore);

        kiipFrank.setItemMeta(kiipFrankM);
        return kiipFrank;
    }

    // Heilige Kiip Pakje
    public static ItemStack kiipHelm() {
        ItemStack kiipHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta kiipHelmMeta = kiipHelm.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();

        kiipHelmMeta.setDisplayName("Chicken Helmet");


        kiipHelm.setItemMeta(kiipHelmMeta);
        return kiipHelm;
    }

    public static ItemStack kiipChest() {
        ItemStack kiipChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta kiipChestMeta = kiipChest.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();

        kiipChestMeta.setDisplayName("Chicken Chestplate");

        kiipChest.setItemMeta(kiipChestMeta);
        return kiipChest;
    }

    public static ItemStack kiipLegs() {
        ItemStack kiipLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta kiipLegsMeta = kiipLegs.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();

        kiipLegsMeta.setDisplayName("Chicken Leggings");

        kiipLegs.setItemMeta(kiipLegsMeta);
        return kiipLegs;
    }

    public static ItemStack kiipBoots() {
        ItemStack kiipBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta kiipBootsMeta = kiipBoots.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();

        kiipBootsMeta.setDisplayName("Chicken Boots");

        kiipBoots.setItemMeta(kiipBootsMeta);
        return kiipBoots;
    }
    /*                                                              */

    // Demonische Kiip Pakje
    public static ItemStack demoKiipHelm() {
        ItemStack demoKiipHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta demoKiipHelmMeta = demoKiipHelm.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        demoKiipHelmMeta.setDisplayName("DemonischeKiip Helmet");

        demoKiipHelm.setItemMeta(demoKiipHelmMeta);
        return demoKiipHelm;
    }

    public static ItemStack demoKiipChest() {
        ItemStack demoKiipChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta demoKiipChestMeta = demoKiipChest.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        demoKiipChestMeta.setDisplayName("DemonischeKiip Chestplate");

        demoKiipChest.setItemMeta(demoKiipChestMeta);
        return demoKiipChest;
    }

    public static ItemStack demoKiipLegs() {
        ItemStack demoKiipLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta demoKiipLegsMeta = demoKiipLegs.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        demoKiipLegsMeta.setDisplayName("DemonischeKiip Leggings");

        demoKiipLegs.setItemMeta(demoKiipLegsMeta);
        return demoKiipLegs;
    }

    public static ItemStack demoKiipBoots() {
        ItemStack demoKiipBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta demoKiipBootsMeta = demoKiipBoots.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        demoKiipBootsMeta.setDisplayName("DemonischeKiip Boots");

        demoKiipBoots.setItemMeta(demoKiipBootsMeta);
        return demoKiipBoots;
    }


    // Halloween Shop

    // Witch Pakjes
    public static ItemStack witchHelm() {
        ItemStack witchHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta witchHelmMeta = witchHelm.getItemMeta();

        witchHelmMeta.setDisplayName("Witch Helmet");
        witchHelm.setItemMeta(witchHelmMeta);
        return witchHelm;
    }

    public static ItemStack witchChest() {
        ItemStack witchChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta witchChestMeta = witchChest.getItemMeta();

        witchChestMeta.setDisplayName("Witch Chestplate");
        witchChest.setItemMeta(witchChestMeta);
        return witchChest;
    }

    public static ItemStack witchLegs() {
        ItemStack witchLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta witchLegsMeta = witchLegs.getItemMeta();

        witchLegsMeta.setDisplayName("Witch Leggings");
        witchLegs.setItemMeta(witchLegsMeta);
        return witchLegs;
    }

    public static ItemStack witchBoots() {
        ItemStack witchBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta witchBootsMeta = witchBoots.getItemMeta();

        witchBootsMeta.setDisplayName("Witch Boots");
        witchBoots.setItemMeta(witchBootsMeta);
        return witchBoots;
    }

    // Vogelverschikker Pakje

    public static ItemStack vogelHoed() {
        ItemStack vogelHoed = new ItemStack(PLAYER_HEAD);
        SkullMeta vogelHoedMeta = (SkullMeta) vogelHoed.getItemMeta();

        vogelHoedMeta.setDisplayName(Utils.chat("&9Vogelverschikker Hoofd"));

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/5e404b603a61f7516d5ec776b83bf75363625a75e50ab1938b846239cc0e6bc";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            profileField = vogelHoedMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(vogelHoedMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        vogelHoed.setItemMeta(vogelHoedMeta);
        return vogelHoed;
    }

    public static ItemStack vogelChest() {
        ItemStack vogelChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta vogelverschChestMeta = vogelChest.getItemMeta();

        vogelverschChestMeta.setDisplayName("Vogelverschrikker Vest");

        vogelChest.setItemMeta(vogelverschChestMeta);
        return vogelChest;
    }

    public static ItemStack vogelLegs() {
        ItemStack vogelLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta vogelverschLegsMeta = vogelLegs.getItemMeta();

        vogelverschLegsMeta.setDisplayName("Vogelverschrikker Broek");

        vogelLegs.setItemMeta(vogelverschLegsMeta);
        return vogelLegs;
    }

    public static ItemStack skellyHoofd() {
        ItemStack skellyHoofd = new ItemStack(PLAYER_HEAD);
        SkullMeta skellyMeta = (SkullMeta) skellyHoofd.getItemMeta();

        skellyMeta.setDisplayName(Utils.chat("&9Skeleton Hoofd"));

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/5a6314eac34416ce10ab22c2e1c4dcb472a3feb98d4e04d3fbbb85a9a471b18";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            profileField = skellyMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skellyMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        skellyHoofd.setItemMeta(skellyMeta);
        return skellyHoofd;
    }

    public static ItemStack skellyChest() {
        ItemStack skellyChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta skellyChestMeta = skellyChest.getItemMeta();

        skellyChestMeta.setDisplayName("Skeleton Chestplate");

        skellyChest.setItemMeta(skellyChestMeta);
        return skellyChest;
    }

    public static ItemStack skellyLegs() {
        ItemStack skellyLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta skellyLegsMeta = skellyLegs.getItemMeta();

        skellyLegsMeta.setDisplayName("Skeleton Leggings");

        skellyLegs.setItemMeta(skellyLegsMeta);
        return skellyLegs;
    }

    public static ItemStack skellyBoots() {
        ItemStack skellyBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta skellyBootsMeta = skellyBoots.getItemMeta();

        skellyBootsMeta.setDisplayName("Skeleton Boots");

        skellyBoots.setItemMeta(skellyBootsMeta);
        return skellyBoots;
    }

    public static ItemStack zombieHead() {
        ItemStack zombieHead = new ItemStack(PLAYER_HEAD);
        SkullMeta zombieHeadM = (SkullMeta) zombieHead.getItemMeta();

        zombieHeadM.setDisplayName(Utils.chat("&9Zombie Hoofd"));

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        String url = "http://textures.minecraft.net/texture/37dbe7bf224c48c24820434ee995476ea203f50bbe1f38c550cc609dc6559826";

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;

        try {
            profileField = zombieHeadM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(zombieHeadM, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        zombieHead.setItemMeta(zombieHeadM);
        return zombieHead;
    }

    public static ItemStack zombieChest() {
        ItemStack zombieChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta zombieChestMeta = zombieChest.getItemMeta();

        zombieChestMeta.setDisplayName("Zombie Chestplate");

        zombieChest.setItemMeta(zombieChestMeta);
        return zombieChest;
    }

    public static ItemStack zombieLegs() {
        ItemStack zombieLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta zombieLegsMeta = zombieLegs.getItemMeta();

        zombieLegsMeta.setDisplayName("Zombie Leggings");

        zombieLegs.setItemMeta(zombieLegsMeta);
        return zombieLegs;
    }

    public static ItemStack zombieBoots() {
        ItemStack zombieBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta zombieBootsMeta = zombieBoots.getItemMeta();

        zombieBootsMeta.setDisplayName("Zombie Boots");

        zombieBoots.setItemMeta(zombieBootsMeta);
        return zombieBoots;
    }


    //Kerst Pakjes

    public static ItemStack grinchHelm() {
        ItemStack grinchHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta grinchHelmM = grinchHelm.getItemMeta();

        grinchHelmM.setDisplayName("Grinch Helmet");

        grinchHelm.setItemMeta(grinchHelmM);
        return grinchHelm;
    }

    public static ItemStack grinchChest() {
        ItemStack grinchChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta grinchChestM = grinchChest.getItemMeta();

        grinchChestM.setDisplayName("Grinch Chestplate");

        grinchChest.setItemMeta(grinchChestM);
        return grinchChest;
    }

    public static ItemStack grinchLegs() {
        ItemStack grinchLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta grinchLegsM = grinchLegs.getItemMeta();

        grinchLegsM.setDisplayName("Grinch Leggings");

        grinchLegs.setItemMeta(grinchLegsM);
        return grinchLegs;
    }

    public static ItemStack grinchBoots() {
        ItemStack grinchBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta grinchBootsM = grinchBoots.getItemMeta();

        grinchBootsM.setDisplayName("Grinch Boots");

        grinchBoots.setItemMeta(grinchBootsM);
        return grinchBoots;
    }

    public static ItemStack elfHelmet() {
        ItemStack elfHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta elfHelmM = elfHelm.getItemMeta();

        elfHelmM.setDisplayName("Elf Helmet");

        elfHelm.setItemMeta(elfHelmM);
        return elfHelm;
    }

    public static ItemStack elfChest() {
        ItemStack elfChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta elfChestM = elfChest.getItemMeta();

        elfChestM.setDisplayName("Elf Chestplate");

        elfChest.setItemMeta(elfChestM);
        return elfChest;
    }

    public static ItemStack elfLegs() {
        ItemStack elfLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta elfLegsM = elfLegs.getItemMeta();

        elfLegsM.setDisplayName("Elf Leggings");

        elfLegs.setItemMeta(elfLegsM);
        return elfLegs;
    }

    public static ItemStack elfBoots() {
        ItemStack elfBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta elfBootsM = elfBoots.getItemMeta();

        elfBootsM.setDisplayName("Elf Boots");

        elfBoots.setItemMeta(elfBootsM);
        return elfBoots;
    }

    public static ItemStack sneeuwpopHelm() {
        ItemStack spHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta spHelmM = spHelm.getItemMeta();

        spHelmM.setDisplayName("Sneeuwpop Helmet");

        spHelm.setItemMeta(spHelmM);
        return spHelm;
    }

    public static ItemStack sneeuwpopChest() {
        ItemStack spChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta spChestM = spChest.getItemMeta();

        spChestM.setDisplayName("Sneeuwpop Chestplate");

        spChest.setItemMeta(spChestM);
        return spChest;
    }

    public static ItemStack sneeuwpopLegs() {
        ItemStack spLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta spLegsM = spLegs.getItemMeta();

        spLegsM.setDisplayName("Sneeuwpop Leggings");

        spLegs.setItemMeta(spLegsM);
        return spLegs;
    }

    public static ItemStack sneeuwpopBoots() {
        ItemStack spBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta spBootsM = spBoots.getItemMeta();

        spBootsM.setDisplayName("Sneeuwpop Boots");

        spBoots.setItemMeta(spBootsM);
        return spBoots;
    }

    public static ItemStack rudolfHelmet() {
        ItemStack rudolfHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta rudolfHelmM = rudolfHelm.getItemMeta();

        rudolfHelmM.setDisplayName("Rudolf Helmet");

        rudolfHelm.setItemMeta(rudolfHelmM);
        return rudolfHelm;
    }

    public static ItemStack rudolfChest() {
        ItemStack rudolfChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta rudolfChestM = rudolfChest.getItemMeta();

        rudolfChestM.setDisplayName("Rudolf Chestplate");

        rudolfChest.setItemMeta(rudolfChestM);
        return rudolfChest;
    }

    public static ItemStack rudolfLegs() {
        ItemStack rudolfLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta rudolfLegsM = rudolfLegs.getItemMeta();

        rudolfLegsM.setDisplayName("Rudolf Leggings");

        rudolfLegs.setItemMeta(rudolfLegsM);
        return rudolfLegs;
    }

    public static ItemStack rudolfBoots() {
        ItemStack rudolfBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta rudolfBootsM = rudolfBoots.getItemMeta();

        rudolfBootsM.setDisplayName("Rudolf Boots");

        rudolfBoots.setItemMeta(rudolfBootsM);
        return rudolfBoots;
    }


    public static ItemStack santaHelmet() {
        ItemStack santaHelm = new ItemStack(CHAINMAIL_HELMET);
        ItemMeta santaHelmM = santaHelm.getItemMeta();

        santaHelmM.setDisplayName("Santaclaus Helmet");

        santaHelm.setItemMeta(santaHelmM);
        return santaHelm;
    }

    public static ItemStack santaChest() {
        ItemStack santaChest = new ItemStack(CHAINMAIL_CHESTPLATE);
        ItemMeta santaChestM = santaChest.getItemMeta();

        santaChestM.setDisplayName("Santaclaus Chestplate");

        santaChest.setItemMeta(santaChestM);
        return santaChest;
    }

    public static ItemStack santaLegs() {
        ItemStack santaLegs = new ItemStack(CHAINMAIL_LEGGINGS);
        ItemMeta santaLegsM = santaLegs.getItemMeta();

        santaLegsM.setDisplayName("Santaclaus Leggings");

        santaLegs.setItemMeta(santaLegsM);
        return santaLegs;
    }

    public static ItemStack santaBoots() {
        ItemStack santaBoots = new ItemStack(CHAINMAIL_BOOTS);
        ItemMeta santaBootsM = santaBoots.getItemMeta();

        santaBootsM.setDisplayName("Santaclaus Boots");

        santaBoots.setItemMeta(santaBootsM);
        return santaBoots;
    }
}