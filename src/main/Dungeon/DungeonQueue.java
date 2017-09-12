package main.Dungeon;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by maxim on 12.09.2017.
 */
public class DungeonQueue {
    String dungeonname;
    ArrayList<UUID>uuids=new ArrayList<UUID>(0);

    public DungeonQueue(Player[]p, String dungeonname) {
        this.dungeonname = dungeonname;
        for(Player players : p) {
            uuids.add(players.getUniqueId());
        }
    }

}
