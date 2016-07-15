package fr.triinoxys.ddhub.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class VisibilityUtils{
    
    public static void maskPlayers(Player p){
        for(Player pl : Bukkit.getOnlinePlayers()){
            if(!(pl.isOp() || pl.hasPermission("ddhubs.bypass.hide")))
                if(p != pl) p.hidePlayer(pl);
        }
        p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 10.0F, 1.0F);
    }

    public static void showPlayers(Player p){
        for(Player pl : Bukkit.getOnlinePlayers()){
            p.showPlayer(pl);
        }
        p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 10.0F, 1.0F);
    }
    
}

