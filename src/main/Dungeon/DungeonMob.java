package main.Dungeon;

import net.minecraft.server.v1_12_R1.EnumCreatureType;
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
    String mobName, type;
    Location locMob;

    public DungeonMob(String mobName, Location locMob, String type) {
        this.mobName = mobName;
        this.locMob = locMob;
        this.type = type;
    }

    public void spawnMob() {
        if(type.equalsIgnoreCase("skeleton")) {
            Bukkit.broadcastMessage("skeleton");
            Skeleton s = (Skeleton) locMob.getWorld().spawnEntity(locMob, EntityType.SKELETON);
            s.setCustomName(mobName);
            s.setCustomNameVisible(true);
        }else if(type.equalsIgnoreCase("spider")) {
            Bukkit.broadcastMessage("Spider");
            Spider s = (Spider) locMob.getWorld().spawnEntity(locMob, EntityType.SPIDER);
            s.setCustomName(mobName);
            s.setCustomNameVisible(true);
        }else if(type.equalsIgnoreCase("zombie")) {
            Bukkit.broadcastMessage("Spider");
            Zombie s = (Zombie) locMob.getWorld().spawnEntity(locMob, EntityType.ZOMBIE);
            s.setCustomName(mobName);
            s.setCustomNameVisible(true);
        }
    }

}
