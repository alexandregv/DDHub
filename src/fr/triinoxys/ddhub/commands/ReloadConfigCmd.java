package fr.triinoxys.ddhub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.triinoxys.ddhub.Main;
import fr.triinoxys.ddhub.utils.ChatUtils;
import fr.triinoxys.ddhub.utils.TitleUtils;


public class ReloadConfigCmd implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Main.pl.saveConfig();
        Main.pl.reloadConfig();
        if(sender instanceof Player){
            final Player p = (Player) sender;
            
            ChatUtils.sendMsg(p, "§aConfiguration rechargée !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " title {text:\" Configuration recharg�e !\",color:green}");
            TitleUtils.sendTitle(p, 40, 10, 5, "§aConfiguration rechargée!", null);
        }
        else sender.sendMessage(ChatColor.GREEN + "Configuration rechargée !");
        return false;
    }
}
