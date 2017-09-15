package main.Dungeon;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by maxim on 13.09.2017.
 */
public class DungeonQueue {
    ArrayList<Player>players = new ArrayList<Player>(0);
    String dungeonname;
    boolean istFull=false;

    public DungeonQueue(String dungeonname) {
        this.dungeonname = dungeonname;
    }

    public void addPlayerToQueue(Player p) {
        players.add(p);
    }

    public void addPlayersToQueue(Player[]players) {
        for(int i=0; i<players.length; i++) {
            this.players.add(players[i]);
        }
    }

}

