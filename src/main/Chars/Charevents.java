package main.Chars;

import main.Chars.Classes.CharPlayer;
import main.Chars.Classes.Normal;
import main.InvSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by user on 13.10.2017.
 */
public class Charevents implements Listener {

    @EventHandler
    public void onCharLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = Charvoids.getcurrentchar(p.getUniqueId() + "");
        String inv[] = InvSerializer.playerInventoryToBase64(p.getInventory());
        Charvoids.setcharinv(inv[0], name);
        Charvoids.logoutcurrentchar(p.getUniqueId() + "");
        CharPlayer charPlayer = new Normal(p);
    }
}
