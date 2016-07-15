package fr.triinoxys.ddhub.events;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import fr.triinoxys.ddhub.handlers.ConfigManager;
import fr.triinoxys.ddhub.utils.InvUtils;


public class JoinQuitEvents implements Listener{
    
    FileConfiguration config;
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        config = ConfigManager.getConfig();
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
        
        p.teleport(new Location(
                Bukkit.getWorld(config.get("spawn.world").toString()), 
                config.getDouble("spawn.x"), 
                config.getDouble("spawn.y"), 
                config.getDouble("spawn.z"),
                config.getLong("spawn.yaw"),
                config.getLong("spawn.pitch")));
        
        p.setGameMode(GameMode.ADVENTURE);
        
        if     (p.hasPermission("joinmsg.admin"))       e.setJoinMessage("§c[§4Administrateur§c] §4"      + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.operateur"))   e.setJoinMessage("§6[§cOpérateur§6] §c"           + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.moderateur"))  e.setJoinMessage("§e[§6Modérateur§e] §6"          + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.guide"))       e.setJoinMessage("§b[§3Guide§b] §3"               + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.respbuilder")) e.setJoinMessage("§e[§2Responsable Builder§e] §2" + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.builder"))     e.setJoinMessage("§e[§2Builder§e] §2"             + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.developpeur")) e.setJoinMessage("§e[§2Builder§e] §2"             + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.staff"))       e.setJoinMessage("§e[§aStaff§e] §a"               + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.dragon"))      e.setJoinMessage("§9[§1Dragon§9] §1"              + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.youtuber"))    e.setJoinMessage("§d[§5Youtuber§d] §5"            + p.getName() + "§e a rejoint le lobby !");
        else if(p.hasPermission("joinmsg.supervip"))    e.setJoinMessage("§f[§bSuperVIP§f] §b"            + p.getName() + "§e a rejoint le lobby !");
        else   e.setJoinMessage("§7[§&+§7] " + p.getName());
        
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        final Player p = e.getPlayer();
        e.setQuitMessage("§7[§c-§7] " + p.getName());
    }
    
}
