package main.Dungeon;

import main.MySQL.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Created by maxim on 08.08.2017.
 */
public class Dungeon implements CommandExecutor, Listener{
    Plugin pl;
    public static int dungeonid;
    public static ArrayList<DungeonArena> dungeonArenas = new ArrayList<DungeonArena>(0);
    ArrayList<DungeonQueue> dungeonQueues = new ArrayList<DungeonQueue>(0);


    public Dungeon(Plugin pl) {
        this.pl = pl;
        queueTester();
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("create")) {
                    boolean dungeonExists=false;
                    for(DungeonArena a: dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
                            dungeonExists=true;
                        }
                    }
                    if(dungeonExists==false) {
                        dungeonArenas.add(new DungeonArena(args[1], p.getLocation(), pl, dungeonid));
                        dungeonQueues.add(new DungeonQueue(args[1]));
                        Bukkit.broadcastMessage("Dungeon erstellt");
                        MySQL.update("INSERT INTO Dungeons (DungeonName, DungeonID, Punkt1, Punkt2, Spawn) VALUES ('" + args[1] + "','" + dungeonid + "','" + 0 + "','" + 0 + "','"+p.getLocation().getX()+","+p.getLocation().getY()+","+p.getLocation().getZ()+"')");
                        Bukkit.broadcastMessage("MySQL upgedatet!");
                        dungeonid++;
                    } else {
                        Bukkit.broadcastMessage("Dungeon with the name " + args[1] + " already exists!");
                    }
                }
                if(args[0].equalsIgnoreCase("delete")) {
                    for(DungeonArena a : dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
                            MySQL.update("DELETE FROM Dungeons WHERE DungeonName='"+a.name+"'");
                            Bukkit.broadcastMessage("Dungeon aus der MySQL Datenbank gelöscht!");
                            dungeonArenas.remove(a);
                            Bukkit.broadcastMessage("Dungeon gelöscht");
                            break;
                        }
                    }
                }
            }
            if(args.length == 4) {
                if(args[0].equalsIgnoreCase("mob")) {
                    for(DungeonArena a : dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[3])) {
                            a.addMob(p.getLocation(), args[1], Integer.parseInt(args[2]));
                            Bukkit.broadcastMessage("Mob " + args[1] + " wurde bei " + p.getLocation() + " erstellt!");
                        }
                    }
                }
                if(args[0].equalsIgnoreCase("boss")) {
                    
                }
            }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("close")) {
                    for(DungeonArena a : dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
                            a.beenden();
                            break;
                        }
                    }
                }
            }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("join")) {
                    for(DungeonArena a: dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
                            p.sendMessage("Du bist dem Dungeon " + a.name + " beigetreten");
                            for(DungeonQueue q: dungeonQueues) {
                                if(q.dungeonname.equalsIgnoreCase(args[1])) {
                                    q.addPlayerToQueue(p);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void queueTester() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            @Override
            public void run() {
                for(DungeonQueue q: dungeonQueues) {
                    for(DungeonArena a: dungeonArenas) {
                        if(q.dungeonname.equalsIgnoreCase(a.name)) {
                            if(q.players.size() >= 2) {
                                if(a.istBeendet==true) {
                                    Bukkit.broadcastMessage("Queue erstellt");
                                    Player[] players = new Player[2];
                                    players[0] = q.players.get(0);
                                    players[1] = q.players.get(1);
                                    q.players.remove(0);
                                    q.players.remove(0);
                                    a.start(players);
                                }
                            }
                        }
                    }
                }
            }
        },20,20);
    }
}
