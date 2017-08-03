package main.Char.Klassen.Archer;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
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
                chest.setUnbreakable(true);
                p.getInventory().addItem(chestp);
                break;
            case 3:ItemStack leggi=new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta trouser=(LeatherArmorMeta) leggi.getItemMeta();
                trouser.setColor(Color.ORANGE);
                trouser.setUnbreakable(true);
                p.getInventory().addItem(leggi);
                break;
            case 4:ItemStack helme=new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta helm=(LeatherArmorMeta) helme.getItemMeta();
                helm.setColor(Color.ORANGE);
                helm.setUnbreakable(true);
                p.getInventory().addItem(helme);
                break;
            case 5:ItemStack boots=new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta boot=(LeatherArmorMeta) boots.getItemMeta();
                boot.setColor(Color.ORANGE);
                boot.setUnbreakable(true);
                p.getInventory().addItem(boots);
                break;
        }
    }
    @EventHandler
    public void repairWeapons(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            ((Player) e.getDamager()).getInventory().getItemInMainHand().setDurability((short) -1);
        }else if(e.getEntity() instanceof Player){
            ItemStack[] armor = ((Player) e.getEntity()).getInventory().getArmorContents();
            for(ItemStack i : armor){
                i.setDurability((short)0);
            }
        }
    }

    @EventHandler
    public void repairBow(EntityShootBowEvent e){
        if(e.getEntity() instanceof Player){
            e.getBow().setDurability((short) -1);
        }
    }

    @EventHandler
    public void onItemBreakDamage(PlayerItemBreakEvent e){
        ItemStack item = new ItemStack(e.getBrokenItem().getType());
        e.getPlayer().getInventory().addItem(item);
    }
}
