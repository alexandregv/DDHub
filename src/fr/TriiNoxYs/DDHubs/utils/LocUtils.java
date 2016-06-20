package fr.TriiNoxYs.DDHubs.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class LocUtils {
	
	private static Location spawnLoc = new Location(Bukkit.getWorld("world"), 0, 5 , 0);
	
	public static void tpToSpawn(Player player){
		player.teleport(spawnLoc);
	}
	
	public static void tpAllToSpawn(){
		for(Player p : Bukkit.getOnlinePlayers())
			tpToSpawn(p);
	}
}
