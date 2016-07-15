package fr.triinoxys.ddhub.utils;

import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;


/**
 * Simple and easy to use Title Utils class
 * @author TriiNoxYs
 * @version 1.0
 * @see http://ftp.triinoxys.altervista.org/Uploads/Files/JavaUtils/TitleUtils.java
 */

public class TitleUtils{
    
    /**
     * Send a title to a player
     * @param p Target player
     * @param fadeIn The time that it will take to display the title
     * @param stay The time that the title will be displayed
     * @param fadeOut The time that it will take to mask the title
     * @param title Title on middle screen
     * @param subtitle Under the title
     */
	public static void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String title, String subtitle){
        if(title == null) title = "";   
		if(subtitle == null) subtitle = "";
        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '"+ ChatColor.translateAlternateColorCodes('&', title)+ "'}");
        IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '"+ ChatColor.translateAlternateColorCodes('&', subtitle)+ "'}");
        Packet<?> length = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, titleJSON, fadeIn,stay, fadeOut);
        Packet<?> titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, fadeIn,stay, fadeOut);
        Packet<?> subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON,fadeIn, stay, fadeOut);
        connection.sendPacket(titlePacket);
        connection.sendPacket(length);
        connection.sendPacket(subtitlePacket);
    }
    
    /**
     * Send a title to a player
     * @param fadeIn The time that it will take to display the title
     * @param stay The time that the title will be displayed
     * @param fadeOut The time that it will take to mask the title
     * @param title Title on middle screen
     * @param subtitle Under the title
     */
    public static void broadcastTitle(int fadeIn, int stay, int fadeOut, String title, String subtitle){
        for(Player pl : Bukkit.getOnlinePlayers()){
            sendTitle(pl, fadeIn, stay, fadeOut, title, subtitle);
        }
    }
    
    /**
     * Send an actionbar message to a player
     * @param p Target player
     * @param msg The message
     */
    public static void sendActionBar(Player p, String msg){
      IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
      PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
      ((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoc);
    }
    
    /**
     * Send an actionbar message to all players
     * @param msg The message
     */
    public static void broadcastActionBar(String msg){
        for(Player pl : Bukkit.getOnlinePlayers()){
            sendActionBar(pl, msg);
        }
    }
    
    /**
     * Modify the tablist
     * @param p Target player
     * @param head The header
     * @param foot The footer
     */
    public static void sendHeaderAndFooter(Player p, String head, String foot){
      CraftPlayer craftplayer = (CraftPlayer)p;
      PlayerConnection connection = craftplayer.getHandle().playerConnection;
      IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + ChatColor.translateAlternateColorCodes('&', head) + "'}");
      IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + ChatColor.translateAlternateColorCodes('&', foot) + "'}");
      PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
      try{
        Field headerField = packet.getClass().getDeclaredField("a");
        headerField.setAccessible(true);
        headerField.set(packet, header);
        headerField.setAccessible(!headerField.isAccessible());
        
        Field footerField = packet.getClass().getDeclaredField("b");
        footerField.setAccessible(true);
        footerField.set(packet, footer);
        footerField.setAccessible(!footerField.isAccessible());
      }
      catch (Exception e){
        e.printStackTrace();
      }
      connection.sendPacket(packet);
    }
    
}