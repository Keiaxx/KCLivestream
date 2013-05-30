/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.keiaxx.kclivestream;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author SUPERCOMPUTER
 */
public class Main extends JavaPlugin {

    public static HashMap<String, String> channels = new HashMap<String, String>();
    public static HashMap<String, String> broadTask = new HashMap<String, String>();
    public static boolean Debug = false;
    public static String prefix = ChatColor.AQUA + "[" + ChatColor.RED + "KC" + ChatColor.GRAY
            + "Livestream" + ChatColor.AQUA + "] " + ChatColor.YELLOW;
    Logger log;

    public static int broadID = 0;
    public static int statID = 0;
    
    @Override
    public void onEnable() {

        this.log = getLogger();

        PluginDescriptionFile pdf = this.getDescription();
        channels.put("keiaxx", "Offline");
        channels.put("rushnett", "Offline");
        channels.put("walrusthefluffy", "Offline");


        log.info(pdf.getName() + " version " + pdf.getVersion() + " has been enabled!");
        //checkUpdates();
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Broadcaster(), 20L, 6000L);
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new StatusUpdater(), 20L, 2400L);

    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getScheduler().cancelTask(broadID);
        Bukkit.getServer().getScheduler().cancelTask(statID);
        log.info("[KCLiveStream] has been disabled!");
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("stream")) {
           
                
            try{
                
                if(args[0].equalsIgnoreCase("debug")){
                    
                    if(Main.Debug ==  true){
                        Main.Debug = false;
                        sender.sendMessage(prefix + "Disabled debug mode.");
                    }else{
                        Main.Debug = true;
                        sender.sendMessage(prefix + "Enabled debug mode.");
                    }
                
                }else if (channels.containsKey(args[0]) || channels.containsKey(args[0].toLowerCase())) {
                    sender.sendMessage(prefix + "Click to view: http://twitch.tv/" + args[0].toLowerCase());
                    return true;

                } else {
                    sender.sendMessage(prefix + "Channel not found! Valid channels: " + channels.keySet());
                    return true;
                }
                
                
            }catch(ArrayIndexOutOfBoundsException ae){
                sender.sendMessage(prefix+" Incorrect syntax! /stream <Channel> ");
                return true;
            }


        }
        return false;
    }
}