/*
 * Copyright (c) 2020.
 * Created by Lucas Maijers.
 * All rights reserved.
 */

package me.Lucas.KiipCraft;

import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

public class SpigotPluginUpdater {
    public static final String VERSION = "SPU 2.0";
    private final JavaPlugin plugin;
    private final String pluginurl;
    private URL url;
    private boolean canceled = false;
    private String version = "";
    private String downloadURL = "";
    private String changeLog = "";
    private boolean out = true;

    public SpigotPluginUpdater(JavaPlugin plugin, String pluginurl) {
        try {
            this.url = new URL(pluginurl);
        } catch (MalformedURLException var4) {
            this.canceled = true;
            plugin.getLogger().log(Level.WARNING, "Error: Bad URL while checking {0} !", plugin.getName());
        }

        this.plugin = plugin;
        this.pluginurl = pluginurl;
    }

    public void enableOut() {
        this.out = true;
    }

    public void disableOut() {
        this.out = false;
    }

    public boolean needsUpdate() {
        if (!this.canceled) {
            try {
                URLConnection con = this.url.openConnection();
                InputStream _in = con.getInputStream();
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(_in);
                Node nod = doc.getElementsByTagName("item").item(0);
                NodeList children = nod.getChildNodes();
                this.version = children.item(1).getTextContent();
                this.downloadURL = children.item(3).getTextContent();
                this.changeLog = children.item(5).getTextContent();
                if (this.newVersionAvailiable(this.plugin.getDescription().getVersion(), this.version.replaceAll("[a-zA-z ]", ""))) {
                    if (this.out) {
                        this.plugin.getLogger().log(Level.INFO, " New Version found: {0}", this.version.replaceAll("[a-zA-z ]", ""));
                        this.plugin.getLogger().log(Level.INFO, " Download it here: {0}", this.downloadURL);
                        this.plugin.getLogger().log(Level.INFO, " Changelog: {0}", this.changeLog);
                    }

                    return true;
                }
            } catch (SAXException | ParserConfigurationException | IOException var6) {
                this.plugin.getLogger().log(Level.WARNING, "Error in checking update for ''{0}''!", this.plugin.getName());
                this.plugin.getLogger().log(Level.WARNING, "Error: ", var6);
            }

        }
        return false;
    }

    public boolean newVersionAvailiable(String oldv, String newv) {
        if (oldv != null && newv != null) {
            oldv = oldv.replace('.', '_');
            newv = newv.replace('.', '_');
            if (oldv.split("_").length != 0 && oldv.split("_").length != 1 && newv.split("_").length != 0 && newv.split("_").length != 1) {
                int vnum = Integer.parseInt(oldv.split("_")[0]);
                int vsec = Integer.parseInt(oldv.split("_")[1]);
                int newvnum = Integer.parseInt(newv.split("_")[0]);
                int newvsec = Integer.parseInt(newv.split("_")[1]);
                if (newvnum > vnum) {
                    return true;
                }

                return newvnum == vnum && newvsec > vsec;
            }
        }

        return false;
    }

    public void update() {
        try {
            URL download = new URL(this.getFolder(this.pluginurl) + this.downloadURL);
            BufferedInputStream in = null;
            FileOutputStream fout = null;

            try {
                if (this.out) {
                    this.plugin.getLogger().log(Level.INFO, "Trying to download from: {0}{1}", new Object[]{this.getFolder(this.pluginurl), this.downloadURL});
                }

                in = new BufferedInputStream(download.openStream());
                fout = new FileOutputStream("plugins/" + this.downloadURL);
                byte[] data = new byte[1024];

                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    fout.write(data, 0, count);
                }
            } finally {
                if (in != null) {
                    in.close();
                }

                if (fout != null) {
                    fout.close();
                }

            }

            if (this.out) {
                this.plugin.getLogger().log(Level.INFO, "Succesfully downloaded file: {0}", this.downloadURL);
                this.plugin.getLogger().log(Level.INFO, "To install the new features you have to restart your server!");
            }
        } catch (IOException var10) {
            this.plugin.getLogger().log(Level.WARNING, "Unable to download update!", var10);
        }

    }

    public void externalUpdate() {
        try {
            URL download = new URL(this.downloadURL);
            BufferedInputStream in = null;
            FileOutputStream fout = null;

            try {
                if (this.out) {
                    this.plugin.getLogger().log(Level.INFO, "Trying to download {0} ..", this.downloadURL);
                }

                in = new BufferedInputStream(download.openStream());
                fout = new FileOutputStream("plugins/" + this.plugin.getName());
                byte[] data = new byte[1024];

                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    fout.write(data, 0, count);
                }
            } finally {
                if (in != null) {
                    in.close();
                }

                if (fout != null) {
                    fout.close();
                }

            }

            if (this.out) {
                this.plugin.getLogger().log(Level.INFO, "Succesfully downloaded file {0} !", this.downloadURL);
                this.plugin.getLogger().log(Level.INFO, "To install the new features you have to restart your server!");
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    private String getFolder(String s) {
        return s.substring(0, s.lastIndexOf("/") + 1);
    }
}
