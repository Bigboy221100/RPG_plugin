package main;

import main.Char.Klassen.Archer.Archerevents;
import main.Char.charcommands.*;
import main.Dungeon.Dungeon;
import main.Minigame.Minigame;
import main.Money.money;
import main.Quest.QuestSystem;
import main.text.info.commands;
import main.text.info.version;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Fabian on 19.07.2017.
 */
public class rpg_main extends JavaPlugin {

    public void onEnable() {
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

        this.getCommand("minigame").setExecutor(new Minigame(this));
        this.getCommand("dungeon").setExecutor(new Dungeon(this));

        //QuestSystem
        this.getCommand("createquest").setExecutor(new QuestSystem(this));
        this.getCommand("adddescription").setExecutor(new QuestSystem(this));
        this.getCommand("printquest").setExecutor(new QuestSystem(this));
        this.getCommand("addmissiontarget").setExecutor(new QuestSystem(this));
        this.getCommand("editmissiontarget").setExecutor(new QuestSystem(this));
        this.getCommand("deletemissiontarget").setExecutor(new QuestSystem(this));
        this.getCommand("spawnquestnpc").setExecutor(new QuestSystem(this));
        this.getCommand("bindquest").setExecutor(new QuestSystem(this));



        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new Archerevents(), this);


        Bukkit.getWorld("world").setGameRuleValue("keepInventory", "true");

        System.out.println("Rpg enabled");
    }

    public void onDisable() {
        System.out.println("Rpg disabled");
    }

}
