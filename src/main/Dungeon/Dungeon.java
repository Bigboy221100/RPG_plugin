package main.Dungeon;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by maxim on 08.08.2017.
 */
public class Dungeon implements CommandExecutor{
    Plugin pl;
    int dungeonid=0;
    ArrayList<DungeonArena> dungeonArenas = new ArrayList<DungeonArena>(0);
    ArrayList<DungeonQueue> dungeonQueues = new ArrayList<DungeonQueue>(0);


    public Dungeon(Plugin pl) {
        this.pl = pl;
        queueTester();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("erstellen")) {
                    dungeonArenas.add(new DungeonArena(args[1], p.getLocation(), pl, dungeonid));
                    dungeonQueues.add(new DungeonQueue(args[1]));
                    dungeonid++;
                    Bukkit.broadcastMessage("Dungeon erstellt");
                }
                if(args[0].equalsIgnoreCase("löschen")) {
                    for(DungeonArena a : dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
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
                            Bukkit.broadcastMessage("Mob " + args[1] + "wurde bei" + p.getLocation() + " erstellt!");
                        }
                    }
                }
                if(args[0].equalsIgnoreCase("boss")) {

                }
            }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("beenden")) {
                    for(DungeonArena a : dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
                            a.beenden();
                            break;
                        }
                    }
                }
            }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("beitreten")) {
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
