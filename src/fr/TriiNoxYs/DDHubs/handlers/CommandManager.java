package fr.TriiNoxYs.DDHubs.handlers;

import org.bukkit.plugin.java.JavaPlugin;
import fr.TriiNoxYs.DDHubs.commands.BypassCmd;
import fr.TriiNoxYs.DDHubs.commands.ChatCmds;
import fr.TriiNoxYs.DDHubs.commands.PingCmd;
import fr.TriiNoxYs.DDHubs.commands.ReloadConfigCmd;
import fr.TriiNoxYs.DDHubs.commands.SpawnCmds;


public class CommandManager{
    
    public static void getCommands(JavaPlugin pl){
        /* COMMANDS */
        pl.getCommand("spawn").setExecutor(new SpawnCmds());
        pl.getCommand("setspawn").setExecutor(new SpawnCmds());
        
        pl.getCommand("clearchat").setExecutor(new ChatCmds());
        pl.getCommand("chat").setExecutor(new ChatCmds());
        
        pl.getCommand("reloadconfig").setExecutor(new ReloadConfigCmd());
        
        pl.getCommand("ping").setExecutor(new PingCmd());
        
        pl.getCommand("bypass").setExecutor(new BypassCmd());
        

        /* TABCOMPLETERS */
        pl.getCommand("chat").setTabCompleter(new ChatCmds());
    }
    
}
