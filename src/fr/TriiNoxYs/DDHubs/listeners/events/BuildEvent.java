package fr.TriiNoxYs.DDHubs.listeners.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import fr.TriiNoxYs.DDHubs.Main;
import fr.TriiNoxYs.DDHubs.utils.ChatUtils;


public class BuildEvent implements Listener{
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(Main.bypassed.contains(p)){
            if(!p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                ChatUtils.sendBypassError(p);
            }
        }
        else e.setCancelled(true);
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(Main.bypassed.contains(p)){
            if(!p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(true);
                ChatUtils.sendBypassError(p);
            }
        }
        else e.setCancelled(true);
    }
    
    
}
