package fr.TriiNoxYs.EvenziaManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import fr.TriiNoxYs.EvenziaManager.handlers.CommandManager;
import fr.TriiNoxYs.EvenziaManager.handlers.EventManager;

public class EvenziaManager extends JavaPlugin{
    
    public static EvenziaManager plugin;
    
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
