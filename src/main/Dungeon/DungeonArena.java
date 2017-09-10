package main.Dungeon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

/**
 * Created by maxim on 08.09.2017.
 */
public class DungeonArena implements Listener{
    String name;
    Location spawn;
    Plugin pl;
    int dungeonid;
    ArrayList<DungeonMob> mobs = new ArrayList<DungeonMob>(0);
    int mobsLeft=0;

    public DungeonArena(String name, Location spawn, Plugin pl, int dungeonid) {
        this.name=name;
        this.spawn=spawn;
        this.dungeonid=dungeonid;

        this.pl = pl;
    }

    public void addMob(String mobName, Location locMob, String type) {
        mobs.add(new DungeonMob(mobName, locMob, type));
        mobsLeft++;
        Bukkit.broadcastMessage(mobs.size()+"");
    }


    public void start(Plugin pl) {
        for(DungeonMob m : mobs) {
            m.spawnMob();
        }
        sendScoreboard(new Player[3]);
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    public void sendScoreboard(Player[] players) {
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("aaa"+dungeonid,"bbb");
        obj.setDisplayName(ChatColor.GREEN + "Dungeon: " + ChatColor.RED + name);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Player p = Bukkit.getPlayer("Zedrex");

        Score zero = obj.getScore("Mobs left: " + mobsLeft);
        zero.setScore(0);

        p.setScoreboard(board);
    }

    @EventHandler
    public void mobDeathTest(EntityDeathEvent e) {
        for(DungeonMob m : mobs) {
            if(e.getEntity().getName().equalsIgnoreCase(m.mobName) && !(e.getEntity() instanceof Player)) {
                Bukkit.broadcastMessage("Ein Mob weniger");

                Player p = Bukkit.getPlayer("Zedrex");
                Objective obj = p.getScoreboard().getObjective("aaa"+dungeonid);

                obj.getScoreboard().resetScores("Mobs left: " + mobsLeft);

                mobsLeft--;

                Score testscore = obj.getScore("Mobs left: " + mobsLeft);
                testscore.setScore(0);

                break;
            }
        }
    }
}
