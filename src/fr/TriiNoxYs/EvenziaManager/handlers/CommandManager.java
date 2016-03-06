package fr.TriiNoxYs.EvenziaManager.handlers;

import org.bukkit.plugin.java.JavaPlugin;
import fr.TriiNoxYs.EvenziaManager.listeners.commands.ChatCmds;
import fr.TriiNoxYs.EvenziaManager.listeners.commands.InvCmds;
import fr.TriiNoxYs.EvenziaManager.listeners.commands.PingCmd;
import fr.TriiNoxYs.EvenziaManager.listeners.commands.ReloadConfigCmd;
import fr.TriiNoxYs.EvenziaManager.listeners.commands.SpawnCmds;


public class CommandManager{
    
    public static void getCommands(JavaPlugin pl){
        /* COMMANDS */
        pl.getCommand("drop").setExecutor(new InvCmds());
        pl.getCommand("pickup").setExecutor(new InvCmds());
        pl.getCommand("moveitem").setExecutor(new InvCmds());
        
        pl.getCommand("spawn").setExecutor(new SpawnCmds());
        pl.getCommand("setspawn").setExecutor(new SpawnCmds());
        
        pl.getCommand("clearchat").setExecutor(new ChatCmds());
        pl.getCommand("chat").setExecutor(new ChatCmds());
        
        pl.getCommand("reloadconfig").setExecutor(new ReloadConfigCmd());
        
        pl.getCommand("ping").setExecutor(new PingCmd());
        
        
        /* TABCOMPLETERS */
        pl.getCommand("chat").setTabCompleter(new ChatCmds());
        pl.getCommand("drop").setTabCompleter(new ChatCmds());
        pl.getCommand("pickup").setTabCompleter(new ChatCmds());
        pl.getCommand("moveitem").setTabCompleter(new ChatCmds());
    }
    
}
