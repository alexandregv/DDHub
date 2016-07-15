package fr.triinoxys.ddhub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HelpCmd implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player)sender;
        if (p.hasPermission("help.op"))
        {
          p.sendMessage(ChatColor.YELLOW + "------------- " + ChatColor.RED + "Menu d'aide (Op\u00E9rateur)" + ChatColor.YELLOW + " -------------");
          p.sendMessage(ChatColor.DARK_AQUA + "/ban <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Bannir un joueur d\u00E9finitivement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/banip <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Bannir IP un joueur.");
          p.sendMessage(ChatColor.DARK_AQUA + "/unban <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "D\u00E9bannir un joueur.");
          p.sendMessage(ChatColor.DARK_AQUA + "/unbanip <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "D\u00E9bannir IP un joueur.");
          p.sendMessage(" ");
          p.sendMessage(" ");
        }
        
        if (p.hasPermission("help.mod"))
        {
          p.sendMessage(ChatColor.YELLOW + "------------- " + ChatColor.GOLD + "Menu d'aide (Mod\u00E9rateur)" + ChatColor.YELLOW + " ------------");
          p.sendMessage(ChatColor.DARK_AQUA + "/tempban <joueur> <temps> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Bannir un joueur temporairement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/tempbanip <joueur> <temps> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Bannir ip un joueur temporairement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/unban <joueur>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "D\u00E9banni un joueur que vous avez banni.");
          p.sendMessage(ChatColor.DARK_AQUA + "/banlist" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Affiche la banlist.");
          p.sendMessage(ChatColor.DARK_AQUA + "/mute <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Mute un joueur d\u00E9finitivement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/muteip <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Mute IP un joueur d\u00E9finitivement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/unmute <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Demute un joueur.");
          p.sendMessage(ChatColor.DARK_AQUA + "/unmuteip <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Demute IP un joueur.");
          p.sendMessage(" ");
          p.sendMessage(" ");
        }
        
        if (p.hasPermission("help.guide"))
        {
          p.sendMessage(ChatColor.YELLOW + "--------------- " + ChatColor.DARK_AQUA + "Menu d'aide (Helper)" + ChatColor.YELLOW + " --------------");
          p.sendMessage(ChatColor.DARK_AQUA + "/tempmute <joueur> <temps> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Mute un joueur temporairement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/tempmuteip <joueur> <temps> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Mute IP un joueur temporairement.");
          p.sendMessage(ChatColor.DARK_AQUA + "/kick <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Kick un joueur.");
          p.sendMessage(ChatColor.DARK_AQUA + "/sc <message>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Parler aux autres membres du staff.");
          p.sendMessage(" ");
          p.sendMessage(" ");
        }
        
        p.sendMessage(ChatColor.YELLOW + "--------------- " + ChatColor.GRAY + "Menu d'aide (Joueur)" + ChatColor.YELLOW + " --------------");
        p.sendMessage(ChatColor.DARK_AQUA + "/dragon" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Ouvre le menu principal.");
        p.sendMessage(ChatColor.DARK_AQUA + "/pvpbox" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Allez sur le serveur Pvp Box.");
        p.sendMessage(ChatColor.DARK_AQUA + "/adventure" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Allez sur le serveur Pvp Adeventure");
        p.sendMessage(ChatColor.DARK_AQUA + "/report <joueur> <raison>" + ChatColor.YELLOW + " - " + ChatColor.GREEN + "Report un joueur.");
        
        return true;
      }

}
