package fr.TriiNoxYs.DDHubs.listeners.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import fr.TriiNoxYs.DDHubs.handlers.ConfigManager;


public class VoidDamageEvent implements Listener{
	
    @EventHandler
    public void onVoidDamage(EntityDamageEvent e){
    	if (!(e.getEntity() instanceof Player) || e.getCause() != DamageCause.VOID)
    	    return;
    	
    	FileConfiguration config = ConfigManager.getConfig();
    	final Player p = (Player) e.getEntity();
        
    	p.teleport(new Location(
                Bukkit.getWorld(config.get("spawn.world").toString()), 
                config.getDouble("spawn.x"), 
                config.getDouble("spawn.y"), 
                config.getDouble("spawn.z"),
                config.getLong("spawn.yaw"),
                config.getLong("spawn.pitch")));
    }

}
