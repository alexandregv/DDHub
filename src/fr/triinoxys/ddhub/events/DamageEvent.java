package fr.triinoxys.ddhub.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import fr.triinoxys.ddhub.handlers.ConfigManager;


public class DamageEvent implements Listener{
	
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            FileConfiguration config = ConfigManager.getConfig();
            final Player p = (Player) e.getEntity();
            
            e.setCancelled(true);
            
            if(e.getCause() == DamageCause.VOID){
                p.teleport(new Location(
                        Bukkit.getWorld(config.get("spawn.world").toString()), 
                        config.getDouble("spawn.x"), 
                        config.getDouble("spawn.y"), 
                        config.getDouble("spawn.z"),
                        config.getLong("spawn.yaw"),
                        config.getLong("spawn.pitch")));
            }
            e.setCancelled(true);
        }
    }

}
