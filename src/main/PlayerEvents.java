package main;

import main.Chars.Classes.CharPlayer;
import main.Chars.Classes.Normal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by user on 28.07.2017.
 */
public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity().getPlayer();
        p.sendMessage("Sie sind gestorben und wurden aus ihrem Character ausgeloggt");
        CharPlayer charPlayer = new Normal(p);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        p.sendMessage("========RPG-Server========");
        p.sendMessage("Willkommen auf dem Server");
        p.sendMessage("===" + p.getName() + "===");
        p.sendMessage("=========================");
        if (!p.hasPlayedBefore()) {
            Bukkit.broadcastMessage(p.getName() + " hat zum ersten Mal den Server betreten");
        }
        CharPlayer charPlayer = new Normal(p);
        e.setJoinMessage("§1[§2+§1]§6 " + p.getName());

        //-----------DungeonEvents--------------------------//
        ItemStack item = new ItemStack(Material.EYE_OF_ENDER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Dungeons");
        item.setItemMeta(meta);
        p.getInventory().setItem(7, item);
        //---------------------------------------------------//
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = (Player) e.getPlayer();
        CharPlayer charPlayer = new Normal(p);
        e.setQuitMessage("§1[§2-§1]§6 " + p.getName());
    }

    @EventHandler
    public void food(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }
    /*
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        String itemname = e.getItem().getItemMeta().getDisplayName();
        if (itemname.equals(ChatColor.GREEN + "Dungeons")) {
            Bukkit.broadcastMessage("test");
            Inventory inv = p.getServer().createInventory(null, 9   , "Dungeoninventory");
            p.openInventory(inv);
        }
    }
    */
}
