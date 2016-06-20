package fr.TriiNoxYs.DDHubs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import fr.TriiNoxYs.DDHubs.handlers.CommandManager;
import fr.TriiNoxYs.DDHubs.handlers.EventManager;

public class Main extends JavaPlugin{
    
    public static Main plugin;
    
    FileConfiguration config = getConfig();
    
    public void onEnable(){
        plugin = this;
        Bukkit.broadcastMessage("onEnable");
        
        Bukkit.broadcastMessage(String.valueOf(getConfig()));
        
        EventManager.registerEvents(this);
        CommandManager.getCommands(this);
    }
    
    public void onDisable(){
        saveConfig();
    }
    
}
