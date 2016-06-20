package fr.TriiNoxYs.DDHubs.listeners.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.utils.ChatUtils;


public class PingCmd implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(args.length == 0){
            if(sender instanceof Player){
                Player p = (Player) sender;
                int ping = ((CraftPlayer) p).getHandle().ping;
                ChatUtils.sendMsg(p, ChatColor.GREEN + "Votre ping: " +  ChatColor.GOLD + ping);
            }
            else sender.sendMessage(ChatColor.RED + "Usage for console: ping <player>");
        }
        else if(args.length == 1){
            Player p = (Player) sender;
            Player target = Bukkit.getServer().getPlayer(args[0]);
            
            if(target.isOnline()){
                int ping = ((CraftPlayer) target).getHandle().ping;
                if(sender instanceof Player) ChatUtils.sendMsg(p, ChatColor.GREEN + "Ping de " + target.getName() + ": " +  ChatColor.GOLD + ping + "ms");
                else sender.sendMessage(ChatColor.GREEN + "Ping de " + target.getName() + ": " +  ChatColor.GOLD + ping + "ms"); 
            }
            else{
                if(sender instanceof Player) sender.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.RED + " n'est pas connecté.");
                else ChatUtils.sendMsg(target, ChatColor.YELLOW + args[0] + ChatColor.RED + " n'est pas connecté.");
            }
        }
        else sender.sendMessage(ChatColor.RED + "Usage: /ping [player]");
        return false;
    }

}
