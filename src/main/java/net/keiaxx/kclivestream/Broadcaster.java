/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.keiaxx.kclivestream;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 *
 * @author SUPERCOMPUTER
 */
public class Broadcaster implements Runnable {

    @Override
    public void run() {
        for (Map.Entry<String, String> entry : Main.channels.entrySet()) {
            StreamStatus m = new StreamStatus();

            String key = entry.getKey();
            String status = entry.getValue();


            if (status.equalsIgnoreCase("Online")) {
                Bukkit.getServer().broadcastMessage(Main.prefix + key + " is currently LiveStreaming!"
                        + ChatColor.GOLD+" To watch " + key + ", click: http://keicraft.us/live.php");
            }
        }
    }
}
