package main.Char.Klassen.Archer;

import main.helpvoids;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Created by Fabian on 02.08.2017.
 */
public class Archerevents implements Listener {

    @EventHandler
    public void onLevel(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        helpvoids help = new helpvoids();
        if ((help.getcharacterklasse(p.getDisplayName() + "", p)).equalsIgnoreCase("Archer")) {
            p.sendMessage("Sie sind nun Level " + e.getNewLevel());
            switch (e.getNewLevel()) {
                case 2:
                    ItemStack chestp = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta chest = (LeatherArmorMeta) chestp.getItemMeta();
                    chest.setColor(Color.ORANGE);
                    chest.setUnbreakable(true);
                    p.getInventory().setChestplate(chestp);
                    break;
                case 3:
                    ItemStack leggi = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta trouser = (LeatherArmorMeta) leggi.getItemMeta();
                    trouser.setColor(Color.ORANGE);
                    trouser.setUnbreakable(true);
                    p.getInventory().setLeggings(leggi);
                    break;
                case 4:
                    ItemStack helme = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta helm = (LeatherArmorMeta) helme.getItemMeta();
                    helm.setColor(Color.ORANGE);
                    helm.setUnbreakable(true);
                    p.getInventory().setHelmet(helme);
                    break;
                case 5:
                    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta boot = (LeatherArmorMeta) boots.getItemMeta();
                    boot.setColor(Color.ORANGE);
                    boot.setUnbreakable(true);
                    p.getInventory().setBoots(boots);
                    p.sendMessage("Ihr Bogen ist zum Upgraden bereit!");
                    break;
            }
        }
    }

    public void bowupgrades(Player p) {
        Inventory inv = p.getServer().createInventory(null, 27, "Upgraden sie ihren Bogen");
        ItemStack power = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta metapower = power.getItemMeta();
        metapower.setDisplayName("Power");
        power.setItemMeta(metapower);
        inv.setItem(12, power);
        p.openInventory(inv);
    }

    @EventHandler
    public void repairWeapons(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            ((Player) e.getDamager()).getInventory().getItemInMainHand().setDurability((short) -1);
        } else if (e.getEntity() instanceof Player) {
            ItemStack[] armor = ((Player) e.getEntity()).getInventory().getArmorContents();
            for (ItemStack i : armor) {
                i.setDurability((short) 0);
            }
        }
    }

    @EventHandler
    public void repairBow(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            e.getBow().setDurability((short) -1);
        }
    }

    @EventHandler
    public void onItemBreakDamage(PlayerItemBreakEvent e) {
        ItemStack item = new ItemStack(e.getBrokenItem().getType());
        e.getPlayer().getInventory().addItem(item);
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getMaterial().equals(Material.SKULL_ITEM)) {
                Inventory inv = p.getServer().createInventory(null, 27, "Benutzerinterface");
                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta metabow = bow.getItemMeta();
                metabow.setDisplayName("Bow upgraden");
                bow.setItemMeta(metabow);
                inv.setItem(10,bow);
                p.openInventory(inv);
            }
        }
    }

    public void onPlayerInvItemClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == new ItemStack(Material.BOW)) {

        }
    }

}
