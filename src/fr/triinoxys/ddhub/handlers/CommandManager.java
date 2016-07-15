package fr.triinoxys.ddhub.handlers;

import org.bukkit.plugin.java.JavaPlugin;
import fr.triinoxys.ddhub.commands.BypassCmd;
import fr.triinoxys.ddhub.commands.ChatCmds;
import fr.triinoxys.ddhub.commands.HelpCmd;
import fr.triinoxys.ddhub.commands.PingCmd;
import fr.triinoxys.ddhub.commands.ReloadConfigCmd;
import fr.triinoxys.ddhub.commands.SpawnCmds;


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
        
        pl.getCommand("aide").setExecutor(new HelpCmd());
        

        /* TABCOMPLETERS */
        pl.getCommand("chat").setTabCompleter(new ChatCmds());
    }
    
}
