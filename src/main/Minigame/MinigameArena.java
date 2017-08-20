package main.Minigame;

import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Created by maxim on 08.08.2017.
 */
public class MinigameArena{
    String arenaName;
    Location Rand1, Rand2;
    Player p1, p2;
    ArrayList<MinigameQueue> queues;
    int minigameQueuei;
    boolean punkt1Gesetzt=false;
    boolean punkt2Gesetzt=false;
    int id2, id3, id4;
    boolean spieltgerade;
    double minX, maxX;
    double minZ, maxZ;

    //Konstruktor
    public MinigameArena (String arenaName) {
        this.arenaName=arenaName;
        spieltgerade=false;
    }
    //Rand1 setzen
    public void setLocation1(Location loc) {
        Rand1=loc;
        punkt1Gesetzt=true;
    }
    //Rand2 setzen
    public void setLocation2(Location loc) {
        Rand2=loc;
        punkt2Gesetzt=true;
    }
    //Minigame starten
    public void starten(Plugin pl, Player p1, Player p2, ArrayList<MinigameQueue> queues, int minigameQueuei) {
        if(spieltgerade==false) {
            this.queues=queues;
            this.minigameQueuei = minigameQueuei;
            this.p1 = p1;
            this.p2 = p2;
            p1.sendMessage("Starte Spiel ...");
            p2.sendMessage("Starte Spiel ...");
            spieltgerade=true;
            Countdown1(pl);
        }
    }

    public void Countdown1(Plugin pl) {
        id2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int i=10;
            double hoehe;

            @Override
            public void run() {
                p1.sendMessage("Das Spiel starte in " + i + " Sekunden in der Arena " + arenaName);
                p2.sendMessage("Das Spiel starte in " + i + " Sekunden in der Arena " + arenaName);
                if(i==0) {
                    if(punkt1Gesetzt && punkt2Gesetzt) {
                        if (Rand1.getX() > Rand2.getX()) {
                            minX = Rand2.getX();
                            maxX = Rand1.getX();
                        } else {
                            minX = Rand1.getX();
                            maxX = Rand2.getX();
                        }
                        if (Rand1.getZ() > Rand2.getZ()) {
                            minZ = Rand2.getZ();
                            maxZ = Rand1.getZ();
                        } else {
                            minZ = Rand1.getZ();
                            maxZ = Rand2.getZ();
                        }
                        hoehe=Rand1.getY();
                        for(int i=0; i<=1; i++) {
                            for (double x = minX; x < maxX; x++) {
                                for (double z = minZ; z < maxZ; z++) {
                                    Location locSpawnBlock = new Location(Rand1.getWorld(), x, hoehe, z);
                                    locSpawnBlock.getBlock().setType(Material.SNOW_BLOCK);
                                }
                            }
                            hoehe=hoehe-6;
                        }
                        double teleportX, teleportY, teleportZ;
                        teleportX = ((maxX-minX)/2)+minX;
                        teleportY = Rand1.getY()+1;
                        teleportZ = ((maxZ-minZ)/2)+minZ;
                        Location teleportLoc = new Location(Rand1.getWorld(),teleportX,teleportY,teleportZ);
                        p1.teleport(teleportLoc);
                        p2.teleport(teleportLoc);
                        p1.setGameMode(GameMode.ADVENTURE);
                        p2.setGameMode(GameMode.ADVENTURE);
                        p1.getInventory().clear();
                        p2.getInventory().clear();
                        p1.getInventory().addItem(new ItemStack(Material.DIAMOND_SPADE));
                        p2.getInventory().addItem(new ItemStack(Material.DIAMOND_SPADE));
                        Countdown2(pl);
                    } else {
                        Bukkit.getServer().broadcastMessage("Die Punkte für die Arena sind nicht gesetzt!");
                        spieltgerade=false;
                        queues.remove(minigameQueuei);
                    }
                    Bukkit.getServer().getScheduler().cancelTask(id2);
                }
                i--;
            }
        },0,20);
    }

    public void Countdown2(Plugin pl) {
        id3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int i=5;

            @Override
            public void run() {
                new MinigameNoDamage(p1,p2);
                p1.sendMessage("Das Spiel geht in " + i + " Sekunden los!");
                p2.sendMessage("Das Spiel geht in " + i + " Sekunden los!");
                if(i==0) {
                    p1.setGameMode(GameMode.SURVIVAL);
                    p2.setGameMode(GameMode.SURVIVAL);
                    tester(pl);
                    Bukkit.getServer().getScheduler().cancelTask(id3);
                }
                i--;
            }
        }, 20, 20);
    }

    public void tester(Plugin pl) {
        id4 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            @Override
            public void run() {
                tester2(p1,p2);
                tester2(p2,p1);
            }
        },0,1);
    }

    public void tester2(Player p1, Player p2) {
        new MinigameNoDamage(p1,p2);
        if(p1.getLocation().getY() < Rand1.getY()-8) {
            Bukkit.getServer().broadcastMessage("Der Spieler " + p2.getName() + " hat " + p1.getName() + " besiegt!");
            p1.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
            p1.getInventory().clear();
            p2.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
            p2.getInventory().clear();
            Bukkit.getServer().getScheduler().cancelTask(id4);
            spieltgerade=false;
            queues.remove(minigameQueuei);
        }
    }
}
