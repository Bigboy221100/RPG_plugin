package main.Chars;

import main.Chars.Classes.CharPlayer;
import main.Chars.Classes.Normal;
import main.InventoryStringDeSerializer;
import main.MySQL.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by user on 13.10.2017.
 */
public class Charevents implements Listener {

    @EventHandler
    public void onCharLeave(PlayerQuitEvent e){
        Player p= e.getPlayer();
        String name=Charvoids.getcurrentchar(p.getUniqueId()+"");
        String inv = InventoryStringDeSerializer.InventoryToString(p.getInventory(),p);
        Charvoids.setcharinv(inv,name);
        CharPlayer charPlayer = new Normal(p);
    }
}
