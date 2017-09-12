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
import java.util.UUID;

/**
 * Created by maxim on 08.09.2017.
 */
public class DungeonArena implements Listener{
    String name;
    Location spawn;
    Plugin pl;
    int dungeonid;
    boolean istBeendet=true;
    ArrayList<DungeonMob> mobs = new ArrayList<DungeonMob>(0);
    int mobsLeft=0;
    Player[]currentPlayers = new Player[2];
    Objective obj;

    public DungeonArena(String name, Location spawn, Plugin pl, int dungeonid) {
        this.name=name;
        this.spawn=spawn;
        this.dungeonid=dungeonid;
        this.pl = pl;
    }

    public void addMob(String mobName, Location locMob, String type) {
        mobs.add(new DungeonMob(mobName, locMob, type, name));
        Bukkit.broadcastMessage(mobs.size()+"");
    }

    public void start(Plugin pl, DungeonQueue q) {
        if(istBeendet == true) {
            mobsLeft = mobs.size();
            for (DungeonMob m : mobs) {
                m.spawnMob();
            }
            for(int i=0; i<2; i++) {
                currentPlayers[i]=Bukkit.getPlayer(q.uuids.get(i));
            }
            sendScoreboard();
            pl.getServer().getPluginManager().registerEvents(this, pl);
            istBeendet = false;
        }
    }

    public void beenden() {
        istBeendet=true;
    }

    public void sendScoreboard() {
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

        obj = board.registerNewObjective("aaa"+dungeonid,"bbb");
        obj.setDisplayName(ChatColor.GREEN + "Dungeon: " + ChatColor.RED + name);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score zero = obj.getScore("Mobs left: " + mobsLeft);
        zero.setScore(0);

        for(int i=0; i <currentPlayers.length; i++) {
            currentPlayers[i].setScoreboard(board);
        }
    }

    @EventHandler
    public void mobDeathTest(EntityDeathEvent e) {
        if(istBeendet == false) {
            for (DungeonMob m : mobs) {
                if (e.getEntity().getName().equalsIgnoreCase(m.mobName) && !(e.getEntity() instanceof Player && m.dungeonName.equalsIgnoreCase(name))) {
                    Bukkit.broadcastMessage("Ein Mob weniger");
                    for(int i=0; i <currentPlayers.length; i++) {
                        Objective obj = currentPlayers[i].getScoreboard().getObjective("aaa" + dungeonid);
                    }
                    obj.getScoreboard().resetScores("Mobs left: " + mobsLeft);

                    mobsLeft--;
                    Score testscore = obj.getScore("Mobs left: " + mobsLeft);
                    testscore.setScore(0);
                    break;
                }
            }
        }
    }
}
