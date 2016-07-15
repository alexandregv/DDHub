package fr.triinoxys.ddhub;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import fr.triinoxys.ddhub.handlers.CommandManager;
import fr.triinoxys.ddhub.handlers.ConfigManager;
import fr.triinoxys.ddhub.handlers.EventManager;

public class Main extends JavaPlugin{
    
    public static Main pl;
    
    public List<String> list = new ArrayList<String>();
    
    public static ArrayList<Player> bypassed = new ArrayList<Player>();
    
    @Override  
    public void onEnable(){
        pl = this;
        
        saveDefaultConfig(); 
        ConfigManager.loadConfig();
        
        EventManager.registerEvents(this);
        CommandManager.getCommands(this);
        
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }
    
    @Override    
    public void onDisable(){     
        ConfigManager.saveConfig(); 
    } 
    
}


