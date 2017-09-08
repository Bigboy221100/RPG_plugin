package main.Dungeon;

import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created by maxim on 08.09.2017.
 */
public class DungeonArena {
    String name;
    Location spawn;
    ArrayList<DungeonMob> mobs = new ArrayList<DungeonMob>(0);

    public DungeonArena(String name, Location spawn) {
        this.name = name;
        this.spawn = spawn;
    }

    public void addMob(String mobName, Location locMob) {
        mobs.add(new DungeonMob(mobName, locMob));
    }
}
