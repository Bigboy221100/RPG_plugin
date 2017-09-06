package main.Minigame;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by maxim on 18.08.2017.
 */
public class MinigameNoDamage {
    Player p1,p2;

    public MinigameNoDamage(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e;
            if (p.getUniqueId() == p1.getUniqueId() || p.getUniqueId() == p2.getUniqueId()) {
                if (e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
