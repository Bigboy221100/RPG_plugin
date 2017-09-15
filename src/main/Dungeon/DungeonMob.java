package main.Dungeon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;

/**
 * Created by maxim on 08.09.2017.
 */
public class DungeonMob {
    String type;
    Location locMob;
    String dungeonName;
    int wave;
    String mobName;

    public DungeonMob(Location locMob, String type, String dungeonName, int wave) {
        this.locMob = locMob;
        this.type = type;
        this.dungeonName = dungeonName;
    }

    public void spawnMob() {
        if(type.equalsIgnoreCase("skeleton")) {
            mobName = dungeonName + "-Skeleton";
            Skeleton s = (Skeleton) locMob.getWorld().spawnEntity(locMob, EntityType.SKELETON);
            s.setCustomName(mobName);
            s.setCustomNameVisible(true);
        }else if(type.equalsIgnoreCase("spider")) {
            mobName = dungeonName + "-Spider";
            Spider s = (Spider) locMob.getWorld().spawnEntity(locMob, EntityType.SPIDER);
            s.setCustomName(mobName);
            s.setCustomNameVisible(true);
        }else if(type.equalsIgnoreCase("zombie")) {
            mobName = dungeonName + "-Zombie";
            Zombie s = (Zombie) locMob.getWorld().spawnEntity(locMob, EntityType.ZOMBIE);
            s.setCustomName(mobName);
            s.setCustomNameVisible(true);
        }
    }

}
