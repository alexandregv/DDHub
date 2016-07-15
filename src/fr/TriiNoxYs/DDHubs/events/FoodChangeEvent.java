package fr.TriiNoxYs.DDHubs.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;


public class FoodChangeEvent implements Listener{
    
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
    
}
