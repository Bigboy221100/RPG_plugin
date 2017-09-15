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
    Player[]currentPlayers;
    Objective obj;

    public DungeonArena(String name, Location spawn, Plugin pl, int dungeonid) {
        this.name=name;
        this.spawn=spawn;
        this.dungeonid=dungeonid;
        this.pl = pl;
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    public void addMob(Location locMob, String type, int wave) {
        mobs.add(new DungeonMob(locMob, type, name, wave));
        Bukkit.broadcastMessage(mobs.size()+"");
    }

    public void start(Player[]players) {
        Bukkit.broadcastMessage("Dungeon startet ..." + players.length);
        if(istBeendet == true) {
            mobsLeft = mobs.size();
            for (DungeonMob m : mobs) {
                m.spawnMob();
            }
            currentPlayers=new Player[2];
            for(int i=0; i<players.length; i++) {
                currentPlayers[i] = players[i];
                currentPlayers[i].teleport(spawn);
            }
            sendScoreboard();
            istBeendet = false;
        }
    }

    public void beenden() {
        istBeendet=true;
        for(int i=0; i<currentPlayers.length; i++) {
            currentPlayers[i].setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
            currentPlayers[i]=null;
        }
    }

    public void sendScoreboard() {
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

        obj = board.registerNewObjective("aaa"+dungeonid,"bbb");
        obj.setDisplayName(ChatColor.GREEN + "Dungeon: " + ChatColor.RED + name);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score zero = obj.getScore("Mobs left: " + mobsLeft);
        zero.setScore(0);
        Score scorePlayer1 = obj.getScore("Player: " + currentPlayers[0].getDisplayName());
        scorePlayer1.setScore(1);
        Score scorePlayer2 = obj.getScore("Player:" + currentPlayers[1].getDisplayName());
        scorePlayer2.setScore(2);


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
                    for(int i=0;i<currentPlayers.length; i++) {
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
