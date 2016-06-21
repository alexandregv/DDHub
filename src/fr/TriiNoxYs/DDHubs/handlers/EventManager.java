package fr.TriiNoxYs.DDHubs.handlers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import fr.TriiNoxYs.DDHubs.listeners.events.ChatEvent;
import fr.TriiNoxYs.DDHubs.listeners.events.CommandPreprocessEvent;
import fr.TriiNoxYs.DDHubs.listeners.events.VoidDamageEvent;
import fr.TriiNoxYs.DDHubs.listeners.events.JoinQuitEvents;
import fr.TriiNoxYs.DDHubs.listeners.events.Menus;
import fr.TriiNoxYs.DDHubs.listeners.events.RespawnEvent;
import fr.TriiNoxYs.DDHubs.listeners.events.WeatherEvent;


public class EventManager{
    
    public static void registerEvents(Plugin pl){
       PluginManager pm = Bukkit.getPluginManager();
       
       pm.registerEvents(new CommandPreprocessEvent(), pl);
       pm.registerEvents(new JoinQuitEvents(), pl);
       pm.registerEvents(new Menus(), pl);
       pm.registerEvents(new ChatEvent(), pl);
       pm.registerEvents(new RespawnEvent(), pl);
       pm.registerEvents(new WeatherEvent(), pl);
       pm.registerEvents(new VoidDamageEvent(), pl);
    }
    
}
