package main;

import main.Char.*;
import main.Minigame.Minigame;
import main.text.info.commands;
import main.text.info.version;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Fabian on 19.07.2017.
 */
public class rpg_main extends JavaPlugin {
    public void onEnable(){
        //Commands
        this.getCommand("createnewcharacter").setExecutor(new createnewcharacter());
        this.getCommand("loadcharacter").setExecutor(new loadcharacter());
        this.getCommand("logoutcharacter").setExecutor(new logoutcharacter());
        this.getCommand("deletecharacter").setExecutor(new deletechar());
        this.getCommand("listcharacter").setExecutor(new listchars());
        this.getCommand("saveinv").setExecutor(new invtest());
        this.getCommand("loadinv").setExecutor(new invtest());

        this.getCommand("rpg commands").setExecutor(new commands());
        this.getCommand("rpg version").setExecutor(new version());

        this.getCommand("minigame").setExecutor(new Minigame());

        PluginManager pm= Bukkit.getPluginManager();
        pm.registerEvents(new PlayerEvents(),this);

        Bukkit.getWorld("world").setGameRuleValue("keepInventory","true");

        System.out.println("Rpg enabled");
    }

    public void onDisable(){
        System.out.println("Rpg disabled");
    }

}
