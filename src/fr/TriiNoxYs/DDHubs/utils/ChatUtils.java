package fr.TriiNoxYs.DDHubs.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.Main;

public class ChatUtils{
    
    private static String prefix = "§8[§4§lDD§6Hubs§8]§r ";
    private static boolean chat = true;
	
	public static void broadcast(String msg){
		Bukkit.broadcastMessage(prefix + msg);
	}
	
	public static void sendMsg(Player player, String msg){
		player.sendMessage(prefix + msg);
	}
	
	public static void sendInfos(Player p){
	    p.sendMessage("");
        ChatUtils.sendMsg(p, "§8-------------------------");
        ChatUtils.sendMsg(p, "§8|§a Développeur: §eTriiNoxYs         §8|");
        ChatUtils.sendMsg(p, "§8|§a Plugin: §eDDHubs                    §8|");
        ChatUtils.sendMsg(p, "§8|§a Version: §e" + Main.plugin.getDescription().getVersion() + "                            §8|");
        ChatUtils.sendMsg(p, "§8-------------------------");
	}
	
	public static boolean getChatState(){
	    return chat;
	}
	
	public static void setChatState(boolean state){
	    chat = state;
	}
	
}