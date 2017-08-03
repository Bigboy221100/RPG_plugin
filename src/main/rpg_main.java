package main;

import main.Char.*;
import main.Char.Klassen.Archer.Archerevents;
import main.Char.Klassen.money;
import main.Minigame.Minigame;
import main.text.info.commands;
import main.text.info.version;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

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
        this.getCommand("money").setExecutor(new money());
        this.getCommand("saveinv").setExecutor(new invtest());
        this.getCommand("loadinv").setExecutor(new invtest());

        this.getCommand("rpg commands").setExecutor(new commands());
        this.getCommand("rpg version").setExecutor(new version());

        this.getCommand("minigame").setExecutor(new Minigame());

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(),this);
        Bukkit.getPluginManager().registerEvents(new Archerevents(),this);


        Bukkit.getWorld("world").setGameRuleValue("keepInventory","true");

        System.out.println("Rpg enabled");
    }

    public void onDisable(){
        System.out.println("Rpg disabled");
    }

    public void countdown(){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

            }
        }, 0L, 20L);
    }

}
