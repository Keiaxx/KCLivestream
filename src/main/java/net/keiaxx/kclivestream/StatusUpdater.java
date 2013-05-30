/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.keiaxx.kclivestream;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;

/**
 *
 * @author SUPERCOMPUTER
 */
public class StatusUpdater implements Runnable {

    @Override
    public void run() {
        if (Main.Debug) {
            System.out.println("Parsing...");
        }
        for (Map.Entry<String, String> entry : Main.channels.entrySet()) {
            StreamStatus m = new StreamStatus();
            String key = entry.getKey();

            //before Check
            String value = entry.getValue();
            //after Check
            String stat = m.liveStatus(key);


            if (value.equalsIgnoreCase(stat)) {
                if (Main.Debug) {
                    System.out.println("Channel " + key + " is still " + stat);
                }

            } else {
                if (Main.Debug) {
                    System.out.println("Channel " + key + " is now " + stat);
                }
                if (stat.equalsIgnoreCase("offline")) {
                    Bukkit.getServer().broadcastMessage(Main.prefix + key + " is now offline!");
                }
                if (stat.equalsIgnoreCase("online")) {
                    Bukkit.getServer().broadcastMessage(Main.prefix + key + " is now online!"
                            +ChatColor.GOLD+" To watch " + key + ", click: http://keicraft.us/live.php");
                }
                Main.channels.put(key, stat);
            }
        }
        if (Main.Debug) {
            System.out.println(Main.channels);
        }
    }
}
