package fr.triinoxys.ddhub.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import fr.triinoxys.ddhub.utils.ChatUtils;


public class ChatEvent implements Listener{
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        final Player p = e.getPlayer();
        if(ChatUtils.isMuted() && !p.hasPermission("ddhubs.bypass.chatmute")){
            e.setCancelled(true);
            ChatUtils.sendMsg(p, ChatColor.RED + "Le chat est désactivé.");
        }
    }
    
}
