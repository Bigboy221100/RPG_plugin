package main.Char.Klassen.Archer;

import main.helpvoids;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
        help.setcharacterlevel(p.getDisplayName(), p, e.getNewLevel());
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
                inv.setItem(10, bow);
                p.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onPlayerInvItemClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        Inventory inv = p.getServer().createInventory(null, 54, "Upgraden sie ihren Bogen");
        ItemStack power[] = new ItemStack[5];
        ItemStack punch[] = new ItemStack[5];
        ItemStack flame[] = new ItemStack[2];

        if (e.getSlot() == 10) {
            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta metabow = bow.getItemMeta();
            metabow.setDisplayName("Bow upgraden");
            bow.setItemMeta(metabow);
            if (e.getInventory().getItem(10).equals(bow)) {
                if (p.getLevel() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        power[i] = new ItemStack(Material.BOOK);
                        ItemMeta metapower = power[i].getItemMeta();
                        metapower.setDisplayName("Power");
                        power[i].setItemMeta(metapower);
                        inv.setItem(2 + (i * 9), power[i]);
                    }

                    for (int i = 0; i < 5; i++) {
                        punch[i] = new ItemStack(Material.BOOK);
                        ItemMeta metapunch = power[i].getItemMeta();
                        metapunch.setDisplayName("Punch");
                        punch[i].setItemMeta(metapunch);
                        inv.setItem(4 + (i * 9), punch[i]);
                    }

                    for (int i = 0; i < 2; i++) {
                        flame[i] = new ItemStack(Material.BOOK);
                        ItemMeta metaflame = power[i].getItemMeta();
                        metaflame.setDisplayName("Flame");
                        flame[i].setItemMeta(metaflame);
                    }
                    inv.setItem(33, flame[0]);
                    inv.setItem(42, flame[1]);


                    ItemStack skillpoints = new ItemStack(Material.NETHER_STAR, (p.getLevel() / 5));
                    ItemMeta metaskill = skillpoints.getItemMeta();
                    metaskill.setDisplayName("Skillpoints");
                    skillpoints.setItemMeta(metaskill);
                    inv.setItem(53, skillpoints);
                    p.openInventory(inv);

                } else {
                    p.sendMessage("Ihr Bogen ist noch nicht zum Upgraden bereit");
                }
            }
        }
        //Buch auswahl system
        ItemStack powerm = new ItemStack(Material.BOOK);
        ItemMeta metapowerm = powerm.getItemMeta();
        metapowerm.setDisplayName("Power");
        powerm.setItemMeta(metapowerm);
        try {
            if (e.getInventory().getItem(2).equals(powerm)) {
                if (e.getSlot() == 2) {
                    power[0].addEnchantment(Enchantment.ARROW_DAMAGE,1);
                    p.sendMessage("JA");
                }
            }
        }catch(NullPointerException a){

        }
    }


}
