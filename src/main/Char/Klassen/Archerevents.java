package main.Char.Klassen;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Created by Fabian on 02.08.2017.
 */
public class Archerevents implements Listener{
    @EventHandler
    public void onLevel(PlayerLevelChangeEvent e){
        Player p=e.getPlayer();
        p.sendMessage("Sie sind nun Level "+ e.getNewLevel());
        switch (e.getNewLevel()){
            case 2:
                ItemStack chestp=new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chest=(LeatherArmorMeta) chestp.getItemMeta();
                chest.setColor(Color.ORANGE);
                p.getInventory().addItem(chestp);
                break;
        }
    }
}
