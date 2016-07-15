package fr.TriiNoxYs.DDHubs.handlers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import fr.TriiNoxYs.DDHubs.events.BuildEvent;
import fr.TriiNoxYs.DDHubs.events.ChatEvent;
import fr.TriiNoxYs.DDHubs.events.CommandPreprocessEvent;
import fr.TriiNoxYs.DDHubs.events.DamageEvent;
import fr.TriiNoxYs.DDHubs.events.FoodChangeEvent;
import fr.TriiNoxYs.DDHubs.events.JoinQuitEvents;
import fr.TriiNoxYs.DDHubs.events.Menus;
import fr.TriiNoxYs.DDHubs.events.RespawnEvent;
import fr.TriiNoxYs.DDHubs.events.WeatherEvent;


public class EventManager{
    
    public static void registerEvents(Plugin pl){
       PluginManager pm = Bukkit.getPluginManager();
       
       pm.registerEvents(new CommandPreprocessEvent(), pl);
       pm.registerEvents(new JoinQuitEvents(), pl);
       pm.registerEvents(new Menus(), pl);
       pm.registerEvents(new ChatEvent(), pl);
       pm.registerEvents(new RespawnEvent(), pl);
       pm.registerEvents(new WeatherEvent(), pl);
       pm.registerEvents(new DamageEvent(), pl);
       pm.registerEvents(new BuildEvent(), pl);
       pm.registerEvents(new FoodChangeEvent(), pl);
    }
    
}
