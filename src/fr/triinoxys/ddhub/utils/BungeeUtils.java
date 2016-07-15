package fr.triinoxys.ddhub.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import org.bukkit.entity.Player;
import fr.triinoxys.ddhub.Main;

public class BungeeUtils{

    public static boolean send(Player player, String server){
        try{
            if(server.length() == 0){
                player.sendMessage("§cVous devez spécifier un serveur.");
                return false;
            }
            
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);
            out.writeUTF("Connect");
            out.writeUTF(server);
            
            player.sendPluginMessage(Main.pl, "BungeeCord", byteArray.toByteArray());
        }catch(Exception ex){
            player.sendMessage("§cImpossible de se connecter au serveur. Contactez un admin pour résoudre le problème.");
            
            ex.printStackTrace();
            Main.pl.getLogger().warning("Impossible d'envoyer \"" + player.getName() + "\" sur le serveur \"" + server + "\".");
            return false;
        }
        return true;
    }
}
