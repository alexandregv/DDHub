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
import fr.triinoxys.ddhub.utils.TitleUtils;


public class JoinQuitEvents implements Listener{
    
    static FileConfiguration config;
    
    private static String ADMIN, OPERATEUR, MODO, GUIDE, RESPBUILDER, BUILDER, DEV, STAFF, DRAGON, YT, SUPERVIP, SUFFIX;
    
    public JoinQuitEvents(){
        config = ConfigManager.getConfig();
        
        ADMIN = config.getString("prefixes.admin");
        OPERATEUR = config.getString("prefixes.operateur");
        MODO = config.getString("prefixes.modo");
        GUIDE = config.getString("prefixes.guide");
        RESPBUILDER = config.getString("prefixes.respbuilder");
        BUILDER = config.getString("prefixes.builder");
        DEV = config.getString("prefixes.developpeur");
        STAFF = config.getString("prefixes.staff");
        DRAGON = config.getString("prefixes.dragon");
        YT = config.getString("prefixes.youtuber");
        SUPERVIP = config.getString("prefixes.supervip");
        SUFFIX = config.getString("suffix");
    }
    
    
    
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
        
        if     (p.hasPermission("joinmsg.admin"))       e.setJoinMessage(ADMIN       + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.operateur"))   e.setJoinMessage(OPERATEUR   + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.moderateur"))  e.setJoinMessage(MODO        + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.guide"))       e.setJoinMessage(GUIDE       + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.respbuilder")) e.setJoinMessage(RESPBUILDER + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.builder"))     e.setJoinMessage(BUILDER     + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.developpeur")) e.setJoinMessage(DEV         + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.staff"))       e.setJoinMessage(STAFF       + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.dragon"))      e.setJoinMessage(DRAGON      + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.youtuber"))    e.setJoinMessage(YT          + p.getName() + SUFFIX);
        else if(p.hasPermission("joinmsg.supervip"))    e.setJoinMessage(SUPERVIP    + p.getName() + SUFFIX);
        else   e.setJoinMessage("§7[§a+§7] " + p.getName());
        
        TitleUtils.sendHeaderAndFooter(p, "§4§l>§6§l>§e§l> §b§lDragon District §e§l<§6§l<§4§l<", "     §aSite : http://dragondistrict.fr     ");
        
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        final Player p = e.getPlayer();
        e.setQuitMessage("§7[§c-§7] " + p.getName());
    }
    
}
