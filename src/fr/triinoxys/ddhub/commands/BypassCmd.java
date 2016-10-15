package fr.triinoxys.ddhub.commands;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import fr.triinoxys.ddhub.Main;


public class BypassCmd implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            final Player p = (Player) sender;
            if(p.hasPermission("ddhubs.bypass")){
                if(Main.bypassed.contains(p)){
                    Main.bypassed.remove(p);
                    p.sendMessage("§c§oBypass OFF");
                }
                else{
                    Main.bypassed.add(p);
                    p.sendMessage("§a§oBypass ON");
                    p.sendMessage("§cRappel: Vous devez être en créatif pour que le bypass soit actif.");
                    
                    IChatBaseComponent comp = ChatSerializer.a("[\"\",{\"text\":\"Cliquez ici pour passer en créatif.\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/gamemode 1\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Cliquez pour passer en créatif\",\"color\":\"gray\"}]}}}]");
                    PacketPlayOutChat packet = new PacketPlayOutChat(comp);
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                }
            }
            else p.sendMessage("§cVous n'avez pas la permission d'utiliser le bypass.");
        }
        else sender.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
        return true;
    }
}