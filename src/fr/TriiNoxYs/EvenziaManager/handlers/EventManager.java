package fr.TriiNoxYs.EvenziaManager.handlers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import fr.TriiNoxYs.EvenziaManager.listeners.events.Players;


public class EventManager{
    
    public static void registerEvents(Plugin pl){
       PluginManager pm = Bukkit.getPluginManager();
       pm.registerEvents(new Players(), pl);
    }
    
}
