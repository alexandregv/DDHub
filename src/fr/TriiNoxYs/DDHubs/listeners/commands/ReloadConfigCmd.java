package fr.TriiNoxYs.DDHubs.listeners.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.Main;
import fr.TriiNoxYs.DDHubs.utils.ChatUtils;
import fr.TriiNoxYs.DDHubs.utils.TitleUtils;


public class ReloadConfigCmd implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	Main.pl.reloadConfig();
        if(sender instanceof Player){
            final Player p = (Player) sender;
            
            ChatUtils.sendMsg(p, "§aConfiguration rechargée !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " title {text:\" Configuration recharg�e !\",color:green}");
            TitleUtils.sendTitle(p, 40, 10, 5, "§aConfiguration rechargée!", null);
        }
        else sender.sendMessage(ChatColor.GREEN + "Configuration recharg�e !");
        Main.pl.saveConfig();
        return false;
    }
}
