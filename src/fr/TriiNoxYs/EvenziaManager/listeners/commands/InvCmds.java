package fr.TriiNoxYs.EvenziaManager.listeners.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


public class InvCmds implements CommandExecutor, TabCompleter{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            
            if(label.equalsIgnoreCase("drop")){
                if(args.length != 1) p.sendMessage(ChatColor.RED + "Usage: /drop <on | off>");
                else{
                    p.sendMessage(ChatColor.RED + "Cette fonctionnalité est actuellement buguée a cause d'un probleme avec la config.");
                    p.sendMessage(ChatColor.RED + "Le developpeur (§eTriiNoxYs§c) vous tiendra au courant quand elle sera disponnible.");
                    /*
                    if(!config.isSet("worlds." + p.getWorld().getName())){
                        config.set("worlds." + p.getWorld().getName(), "drop");
                        config.set("worlds." + p.getWorld().getName(), "pickup");
                        config.set("worlds." + p.getWorld().getName(), "moveitem");
                        
                        config.set("worlds." + p.getWorld().getName() + ".drop", "on");
                        config.set("worlds." + p.getWorld().getName() + ".pickup", "on");
                        config.set("worlds." + p.getWorld().getName() + ".moveitem", "on");
                    }
                    
                    if(args[0].equalsIgnoreCase("on")){
                        config.set("worlds." + p.getWorld().getName() + ".drop", "on");
                        p.sendMessage("§aVous avez §e§lactivé§r§a le drop dans le monde " + p.getWorld().getName());
                    }
                    else if(args[0].equalsIgnoreCase("off")){
                        config.set("worlds." + p.getWorld().getName() + ".drop", "off");
                        p.sendMessage("§aVous avez §c§ldésactivé§r§a le drop dans le monde " + p.getWorld().getName());
                    }
                    else p.sendMessage(ChatColor.RED + "Usage: /drop <on | off>");
                    ConfigManager.saveConfig();
                    */
                }
            }
            else if(label.equalsIgnoreCase("pickup")){
                if(args.length != 1) p.sendMessage(ChatColor.RED + "Usage: /pickup <on | off>");
                else{
                    p.sendMessage(ChatColor.RED + "Cette fonctionnalité est actuellement buguée a cause d'un probleme avec la config.");
                    p.sendMessage(ChatColor.RED + "Le developpeur (§eTriiNoxYs§c) vous tiendra au courant quand elle sera disponnible.");
                    /*
                    if(args[0].equalsIgnoreCase("on")){
                        config.set("worlds." + p.getWorld().getName() + ".pickup", "on");
                        p.sendMessage("§aVous avez §e§lactivé§r§a le pickup dans le monde " + p.getWorld().getName());
                    }
                    else if(args[0].equalsIgnoreCase("off")){
                        config.set("worlds." + p.getWorld().getName() + ".pickup", "on");
                        p.sendMessage("§aVous avez §c§ldésactivé§r§a le drop dans le monde " + p.getWorld().getName());
                    }
                    else p.sendMessage(ChatColor.RED + "Usage: /pickup <on | off>");
                    ConfigManager.saveConfig();
                    */
                } 
            }
            else if(label.equalsIgnoreCase("moveitem")){
                if(args.length != 1) p.sendMessage(ChatColor.RED + "Usage: /moveitem <on | off>");
                else{
                    p.sendMessage(ChatColor.RED + "Cette fonctionnalité est actuellement buguée a cause d'un probleme avec la config.");
                    p.sendMessage(ChatColor.RED + "Le developpeur (§eTriiNoxYs§c) vous tiendra au courant quand elle sera disponnible.");
                    /*
                    if(args[0].equalsIgnoreCase("on")){
                        config.set("worlds." + p.getWorld().getName() + ".moveitem", "on");
                        p.sendMessage("§aVous avez §e§lactivé§r§a le moveitem dans le monde " + p.getWorld().getName());
                    }
                    else if(args[0].equalsIgnoreCase("off")){
                        config.set("worlds." + p.getWorld().getName() + ".moveitem", "on");
                        p.sendMessage("§aVous avez §c§ldésactivé§r§a le moveitem dans le monde " + p.getWorld().getName());
                    }
                    else p.sendMessage(ChatColor.RED + "Usage: /moveitem <on | off>");
                    ConfigManager.saveConfig();
                    */
                } 
            }
        }
        else sender.sendMessage(ChatColor.RED + "Vous devez etre un joueur pour executer cette commande.");
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
