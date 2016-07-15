package fr.triinoxys.ddhub.events;

import org.bukkit.event.Listener;

public class CommandPreprocessEvent implements Listener{

//    @EventHandler(priority = EventPriority.LOWEST)
//    public void onCommands(PlayerCommandPreprocessEvent e){
//        Player p = e.getPlayer();
//        String[] args = e.getMessage().split(" ");
//        
//        if(args[0].equals("/pl") 
//                || args[0].equals("/plugins")
//                || args[0].equals("/bukkit:pl")
//                || args[0].equals("/bukkit:plugins") || args[0].equals("/help")
//                || args[0].equals("/?") || args[0].equals("/bukkit:help")
//                || args[0].equals("/bukkit:?")
//                || args[0].equals("/bukkit:ver")
//                || args[0].equals("/bukkit:version")
//                || args[0].equals("/bungee")){
//            if(!(p.hasPermission("ddhubs.plugins") || p.isOp())){
//                e.setCancelled(true);
//                ChatUtils.sendInfos(p);
//            }
//        }
//    }

}
