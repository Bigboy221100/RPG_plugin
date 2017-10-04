package main;

import main.BanManager.BanManager;
import main.Chars.Classes.Archer.Archerevents;
import main.Chars.Commands.CreatenewChar;
import main.Chars.Commands.DeleteChar;
import main.Chars.Commands.Loadchar;
import main.Dungeon.Dungeon;
import main.Dungeon.DungeonArena;
import main.Dungeon.DungeonMob;
import main.Dungeon.DungeonQueue;
import main.Minigame.Minigame;
import main.MySQL.FileManager;
import main.MySQL.MySQL;
import main.News.News;
import main.News.NewsManager;
import main.Quest.QuestJSON;
import main.Quest.QuestSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Fabian on 19.07.2017.
 */
public class rpg_main extends JavaPlugin {

    public void onEnable() {
        //MySQL
        FileManager.setStandardMySQL();
        FileManager.readMySQL();
        MySQL.connect();
        MySQL.createTable();

        //Chars
            this.getCommand("createnewcharacter").setExecutor(new CreatenewChar());
            this.getCommand("deletecharacter").setExecutor(new DeleteChar());
            this.getCommand("loadcharacter").setExecutor(new Loadchar());


        //MinigameSystem
        this.getCommand("minigame").setExecutor(new Minigame(this));

        //----------DungeonSystem---------//
        this.getCommand("dungeon").setExecutor(new Dungeon(this));
        ResultSet rs = MySQL.getResultSet("SELECT * FROM Dungeons");
        try {
            while (rs.next()) {
                String[] loc = rs.getString(3).split(",");
                Dungeon.dungeonArenas.add(new DungeonArena(rs.getString(1), new Location(Bukkit.getServer().getWorld("world"), Double.parseDouble(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2])), this, Integer.parseInt(rs.getString(2))));
                Dungeon.dungeonid=Integer.parseInt(rs.getString(2))+1;
                Dungeon.dungeonQueues.add(new DungeonQueue(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs = MySQL.getResultSet("SELECT * FROM DungeonMobs");
        try {
            while (rs.next()) {
                for(DungeonArena a: Dungeon.dungeonArenas) {
                    if(a.name.equalsIgnoreCase(rs.getString(1))) {
                        String[]loc = rs.getString(2).split(",");
                        a.mobs.add(new DungeonMob(new Location(Bukkit.getServer().getWorld("world"),Double.parseDouble(loc[0]),Double.parseDouble(loc[1]),Double.parseDouble(loc[2])), rs.getString(3), rs.getString(1), Integer.parseInt(rs.getString(4))));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //-------------------//

        //---------- BanManager ---------//
        BanManager banManager = new BanManager(this);
        this.getCommand("ban").setExecutor(banManager);
        this.getCommand("unban").setExecutor(banManager);
        this.getCommand("tempban").setExecutor(banManager);
        //-------------------//

        //---------- News ---------//
        this.getCommand("news").setExecutor(new NewsManager(this));
        ResultSet rs2 = MySQL.getResultSet("SELECT * FROM NewsManager");
        try {
            while (rs2.next()) {
                NewsManager.newsManager.add(new News(rs2.getString("news"), Integer.parseInt(rs2.getString("newsTime")), this));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //-------------------//

        //QuestSystem
        QuestSystem questSystem = new QuestSystem(this);
        this.getCommand("createquest").setExecutor(questSystem);
        this.getCommand("adddescription").setExecutor(questSystem);
        this.getCommand("printquest").setExecutor(questSystem);
        this.getCommand("addmissiontarget").setExecutor(questSystem);
        this.getCommand("editmissiontarget").setExecutor(questSystem);
        this.getCommand("deletemissiontarget").setExecutor(questSystem);
        this.getCommand("editreward").setExecutor(questSystem);
        this.getCommand("spawnquestnpc").setExecutor(questSystem);
        this.getCommand("bindquest").setExecutor(questSystem);
        this.getCommand("savequests").setExecutor(questSystem);
        this.getCommand("loadquests").setExecutor(questSystem);

        File f = new File("plugins/RPG/Quests");
        if(!f.exists())
            f.mkdir();

        f = new File("plugins/RPG/Quests/QuestList.json");
        if(!f.exists())
            QuestSystem.quests = new ArrayList<>();
        else
            QuestSystem.quests = QuestJSON.load();

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new Archerevents(), this);

        Bukkit.getWorld("world").setGameRuleValue("keepInventory", "true");

        System.out.println("Rpg enabled");
    }

    public void onDisable() {
        System.out.println("Rpg disabled");
        QuestJSON.writeJSON();

        MySQL.close();
    }

}
