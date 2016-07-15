package fr.triinoxys.ddhub.handlers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import fr.triinoxys.ddhub.events.BuildEvent;
import fr.triinoxys.ddhub.events.ChatEvent;
import fr.triinoxys.ddhub.events.CommandPreprocessEvent;
import fr.triinoxys.ddhub.events.DamageEvent;
import fr.triinoxys.ddhub.events.FoodChangeEvent;
import fr.triinoxys.ddhub.events.JoinQuitEvents;
import fr.triinoxys.ddhub.events.Menus;
import fr.triinoxys.ddhub.events.RespawnEvent;
import fr.triinoxys.ddhub.events.WeatherEvent;


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
