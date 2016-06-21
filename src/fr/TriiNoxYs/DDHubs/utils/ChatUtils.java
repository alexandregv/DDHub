package fr.TriiNoxYs.DDHubs.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.Main;

public class ChatUtils{
    
    private static final String PREFIX = "§8[§4§lDD§6Hubs§8]§r ";
    private static boolean muted = true;
	
	public static void broadcast(String msg){
		Bukkit.broadcastMessage(PREFIX + msg);
	}
	
	public static void sendMsg(Player player, String msg){
		player.sendMessage(PREFIX + msg);
	}
	
	public static void sendInfos(Player p){
	    p.sendMessage("");
        ChatUtils.sendMsg(p, "§8-------------------------");
        ChatUtils.sendMsg(p, "§8|§a Développeur: §eTriiNoxYs         §8|");
        ChatUtils.sendMsg(p, "§8|§a Plugin: §eDDHubs                    §8|");
        ChatUtils.sendMsg(p, "§8|§a Version: §e" + Main.pl.getDescription().getVersion() + "                            §8|");
        ChatUtils.sendMsg(p, "§8-------------------------");
	}
	
	public static boolean isMuted(){
	    return muted;
	}
	
	public static void muteChat(){
	    muted = true;
    }
	
	public static void unmuteChat(){
	    muted = false;
    }
	
	public static void sendBypassError(Player p){
        p.sendMessage("§cVous devez être en créatif pour que le bypass soit actif.");
        
        IChatBaseComponent comp = ChatSerializer.a("[\"\",{\"text\":\"Cliquez ici pour passer en créatif.\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/gamemode 1\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour passer en créatif\",\"color\":\"gray\"}]}}}]");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
	
}