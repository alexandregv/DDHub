package fr.TriiNoxYs.DDHubs.listeners.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.utils.ChatUtils;
import fr.TriiNoxYs.DDHubs.utils.TitleUtils;


public class ChatCmds implements CommandExecutor, TabCompleter{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("clearchat")){
            for(int i=0; i < 150; i++)Bukkit.broadcastMessage("");
            if(sender instanceof Player) TitleUtils.broadcastActionBar("§e§l" + sender.getName() + ChatColor.GREEN + " a nettoyé le chat.");
            else TitleUtils.broadcastActionBar("§e§lUn administrateur" + ChatColor.GREEN + " a nettoyé le chat.");
        }
        else if(label.equalsIgnoreCase("chat")){
            if(args.length != 1) sender.sendMessage(ChatColor.RED + "Usage: /chat <on | off>");
            else{
                if(args[0].equalsIgnoreCase("on")){
                    if(!ChatUtils.isMuted()) sender.sendMessage(ChatColor.GREEN + "Le chat est déjà activé.");
                    else{
                        ChatUtils.unmuteChat();
                        if(sender instanceof Player) ChatUtils.broadcast("§e§l" + sender.getName() + ChatColor.GREEN + " a activé le chat.");
                        else ChatUtils.broadcast("§e§lUn administrateur" + ChatColor.GREEN + " a activé le chat.");
                    }
                }
                else if(args[0].equalsIgnoreCase("off")){
                    if(ChatUtils.isMuted()) sender.sendMessage(ChatColor.GREEN + "Le chat est déjà désactivé.");
                    else{
                        ChatUtils.muteChat();
                        if(sender instanceof Player) ChatUtils.broadcast("§e§l" + sender.getName() + ChatColor.RED + " a désactivé le chat.");
                        else ChatUtils.broadcast("§e§lUn administrateur" + ChatColor.RED + " a désactivé le chat.");
                    }
                }
                else sender.sendMessage(ChatColor.RED + "Usage: /chat <on | off>");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 1){
            ArrayList<String> pArgs = new ArrayList<String>();
            pArgs.add("on");
            pArgs.add("off");
            Collections.sort(pArgs);
            return pArgs;
        }
        return null;
    }
}
