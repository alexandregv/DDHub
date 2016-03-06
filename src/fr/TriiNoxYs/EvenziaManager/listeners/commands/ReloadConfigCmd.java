package fr.TriiNoxYs.EvenziaManager.listeners.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.EvenziaManager.EvenziaManager;
import fr.TriiNoxYs.EvenziaManager.utils.ChatUtils;


public class ReloadConfigCmd implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("reloadconfig")){
            EvenziaManager.plugin.reloadConfig();
            if(sender instanceof Player){
                Player p = (Player) sender;
                
                ChatUtils.sendMsg(p, "§aConfiguration rechargée !");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " title {text:\" Configuration rechargée !\",color:green}");
            }
            else sender.sendMessage(ChatColor.GREEN + "Configuration rechargée !");
        }
        EvenziaManager.plugin.saveConfig();
        return false;
    }
}
