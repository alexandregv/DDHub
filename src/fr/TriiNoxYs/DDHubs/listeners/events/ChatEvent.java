package fr.TriiNoxYs.DDHubs.listeners.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import fr.TriiNoxYs.DDHubs.utils.ChatUtils;


public class ChatEvent implements Listener{
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(ChatUtils.getChatState() == false && !p.hasPermission("ddhubs.bypass.mute")){
            e.setCancelled(true);
            ChatUtils.sendMsg(p, ChatColor.RED + "Le chat est désactivé.");
        }
    }
    
}
