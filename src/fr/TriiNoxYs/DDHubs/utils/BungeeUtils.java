package fr.TriiNoxYs.DDHubs.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import fr.TriiNoxYs.DDHubs.Main;

public class BungeeUtils{

    public static boolean connect(Player player, String server){
        try{
            if(server.length() == 0){
                player.sendMessage("§cTarget server was \"\" (empty string) cannot connect to it.");
                return false;
            }
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(Main.pl, "BungeeCord", byteArray.toByteArray());
        }catch(Exception ex){
            player.sendMessage(ChatColor.RED + "An unexpected exception has occurred. Please notify the server's staff about this. (They should look at the console).");
            ex.printStackTrace();
            Main.pl.getLogger().warning("Could not connect \"" + player.getName() + "\" to the server \"" + server + "\".");
            return false;
        }
        return true;
    }
}
