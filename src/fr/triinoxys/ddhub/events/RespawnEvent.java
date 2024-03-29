package fr.triinoxys.ddhub.events;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import fr.triinoxys.ddhub.handlers.ConfigManager;
import fr.triinoxys.ddhub.utils.InvUtils;


public class RespawnEvent implements Listener{
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        FileConfiguration config = ConfigManager.getConfig();
        final Player p = e.getPlayer();
        InvUtils.clearInv(p);
        
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName("§c§lMon profil");
        headMeta.setLore(Arrays.asList("§7Consultez votre profil !"));
        headMeta.setOwner(p.getName());
        head.setItemMeta(headMeta);
        
        p.getInventory().setItem(0, head);
        p.getInventory().setItem(2, Menus.gold);
        p.getInventory().setItem(4, Menus.compass);
        p.getInventory().setItem(6, Menus.sugar);
        p.getInventory().setItem(8, Menus.nametag);
        
        e.setRespawnLocation(new Location(
                Bukkit.getWorld(config.get("spawn.world").toString()), 
                config.getDouble("spawn.x"), 
                config.getDouble("spawn.y"), 
                config.getDouble("spawn.z"),
                config.getLong("spawn.yaw"),
                config.getLong("spawn.pitch")));
    }
    
}
