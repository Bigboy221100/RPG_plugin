package main.Dungeon;

import org.bukkit.Location;

/**
 * Created by maxim on 08.09.2017.
 */
public class DungeonMob {
    String mobName;
    Location locMob;

    public DungeonMob(String mobName, Location locMob) {
        this.mobName = mobName;
        this.locMob = locMob;
    }

}
