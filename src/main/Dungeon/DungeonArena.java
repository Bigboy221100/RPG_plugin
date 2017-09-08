package main.Dungeon;

import org.bukkit.Location;

/**
 * Created by maxim on 08.09.2017.
 */
public class DungeonArena {
    String name;
    Location spawn;

    public DungeonArena(String name, Location spawn) {
        this.name=name;
        this.spawn=spawn;
    }
}
