package main;

import main.Char.Klassen.CharPlayer;
import main.Char.Klassen.Normal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by user on 28.07.2017.
 */
public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p=(Player)e.getEntity().getPlayer();
        e.setDeathMessage("Sie sind gestorben und wurden aus ihrem Character ausgeloggt");
        CharPlayer charPlayer=new Normal(p);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p=(Player)e.getPlayer();
        p.sendMessage("========RPG-Server========");
        p.sendMessage("Willkommen auf dem Server");
        p.sendMessage("===" + p.getName() + "===");
        p.sendMessage("=========================");
        if(!p.hasPlayedBefore()){
            Bukkit.broadcastMessage(p.getName()+ " hat zum ersten Mal den Server betreten");
        }
        e.setJoinMessage("§1[§2+§1]§6 " + p.getName());
        CharPlayer charPlayer=new Normal(p);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = (Player) e.getPlayer();
        e.setQuitMessage("§1[§2-§1]§6 " + p.getName());
    }



}
