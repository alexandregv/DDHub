package fr.TriiNoxYs.DDHubs.listeners.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.handlers.ConfigManager;


public class SpawnCmds implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(label.equalsIgnoreCase("spawn")){
                if(p.hasPermission("ddhubs.spawn")){
                    FileConfiguration config = ConfigManager.getConfig();
                    
                    p.teleport(new Location(
                            Bukkit.getWorld(config.get("spawn.world").toString()), 
                            (double) config.get("spawn.x"), 
                            (double) config.get("spawn.y"), 
                            (double) config.get("spawn.z"),
                            (float) config.get("spawn.yaw"),
                            (float) config.get("spawn.pitch")));
                    
                    p.sendMessage("§7§oVous voila de retour au spawn.");
                }
            }else if(label.equalsIgnoreCase("setspawn")){
                if(p.hasPermission("ddhubs.setspawn")){
                    FileConfiguration config = ConfigManager.getConfig();
                    Location loc = p.getLocation();
                    
                    p.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                    
                    config.set("spawn.world", loc.getWorld().getName());
                    config.set("spawn.x", loc.getX());
                    config.set("spawn.y", loc.getY());
                    config.set("spawn.z", loc.getZ());
                    config.set("spawn.yaw", loc.getYaw());
                    config.set("spawn.pitch", loc.getPitch());
                    
                    p.sendMessage(ChatColor.GOLD + "Vous avez redéfini le spawn.");
                }else
                    p.sendMessage(ChatColor.RED+ "Vous n'avez pas la permission d'éxécuter cette commande.");
            }
        }else
            sender.sendMessage("§cVous devez etre un joueur pour utiliser cette commande.");
        return true;
    }
}
