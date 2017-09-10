package main.Minigame;

import main.rpg_main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by maxim on 18.08.2017.
 */
public class MinigameNoDamage implements Listener {
    Player p1, p2;
    int id1;
    Plugin pl;

    public MinigameNoDamage(Player p1, Player p2, Plugin pl) {
        this.p1 = p1;
        this.p2 = p2;
        this.pl = pl;
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void NoDamage(EntityDamageEvent e) {
        id1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int i = 20;

            @Override
            public void run() {
                if (e.getEntity() instanceof Player) {
                    Player p = (Player) e;
                    if (p.getUniqueId() == p1.getUniqueId() || p.getUniqueId() == p2.getUniqueId()) {
                        if (e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                            e.setCancelled(true);
                        }
                    }
                }
                if (i == 0) {
                    Bukkit.getScheduler().cancelTask(id1);
                }
                i--;
            }
        }, 0, 1);
    }
}
