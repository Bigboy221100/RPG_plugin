package main.Dungeon;

import java.util.UUID;

/**
 * Created by maxim on 12.09.2017.
 */
public class DungeonQueuePlayer {
    String dungeonname;
    UUID uuid;

    public DungeonQueuePlayer(String dungeonname, UUID uuid) {
        this.dungeonname = dungeonname;
        this.uuid = uuid;
    }

}
