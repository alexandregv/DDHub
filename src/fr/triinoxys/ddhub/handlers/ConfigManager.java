package fr.triinoxys.ddhub.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import fr.triinoxys.ddhub.Main;


public class ConfigManager{

    private static Main pl = Main.pl;
    private static FileConfiguration config;

    public static void loadConfig(){ //Get stuff like arraylists here
        pl.reloadConfig();
        config = pl.getConfig();
    }

    public static void saveConfig(){ //Put stuff to save like arraylists
        config = pl.getConfig();
        pl.saveConfig();
    }
    
    public static FileConfiguration getConfig(){
        config = pl.getConfig();
        return config;
    }
}
