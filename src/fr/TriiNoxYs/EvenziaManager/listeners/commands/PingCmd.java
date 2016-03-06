package fr.TriiNoxYs.EvenziaManager.listeners.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.EvenziaManager.utils.ChatUtils;


public class PingCmd implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("ping")){
            if(args.length == 0){
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    int ping = ((CraftPlayer) p).getHandle().ping;
                    ChatUtils.sendMsg(p, ChatColor.GREEN + "Votre ping: " +  ChatColor.GOLD + ping);
                }
                else sender.sendMessage(ChatColor.RED + "Usage for console: /ping <player>");
            }
            else if(args.length == 1){
                if(sender instanceof Player){
                    Player p = Bukkit.getServer().getPlayer(args[0]);
                    if(p.isOnline()){
                        int ping = ((CraftPlayer) p).getHandle().ping;
                        ChatUtils.sendMsg(p, ChatColor.GREEN + "Votre ping: " +  ChatColor.GOLD + ping); 
                    }
                    else ChatUtils.sendMsg(p, ChatColor.YELLOW + args[0] + ChatColor.RED + " n'est pas connecté.");
                }
                else sender.sendMessage(ChatColor.RED + "Console usage: /ping <player>");
            }
            else sender.sendMessage(ChatColor.RED + "Usage: /ping [player]");
        }
        return false;
    }

}
