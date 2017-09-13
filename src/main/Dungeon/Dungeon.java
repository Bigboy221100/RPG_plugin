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
    ArrayList<DungeonQueuePlayer> dungeonQueuePlayers = new ArrayList<DungeonQueuePlayer>(0);
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
                            a.addMob(args[1], p.getLocation(), args[2]);
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
            if(args.length == 3) {
                if(args[0].equalsIgnoreCase("beitreten")) {
                    for(DungeonArena a: dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[2])) {
                            dungeonQueuePlayers.add(new DungeonQueuePlayer(a.name, p.getUniqueId()));
                            p.sendMessage("Du bist dem Dungeon " + a.name + " beigetreten");
                        }
                    }
                }
            }
        }
        return false;
    }

    public void queueTester() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int dungeonsize=1;
            @Override
            public void run() {
                if(dungeonQueuePlayers.size() >= dungeonsize) {
                    Player[]players = new Player[dungeonsize];
                    String dungeonname="";
                    for(int i=0; i<dungeonsize; i++) {
                        players[i]=Bukkit.getServer().getPlayer(dungeonQueuePlayers.get(i).uuid);
                    }
                    dungeonname = dungeonQueuePlayers.get(0).dungeonname;
                    dungeonQueues.add(new DungeonQueue(players, dungeonname));
                    for(int i=0; i<dungeonsize; i++) {
                        dungeonQueuePlayers.remove(i);
                    }
                    Bukkit.broadcastMessage(dungeonQueuePlayers.size()+"");
                }
                if(dungeonQueues.size() > 0) {
                    for(DungeonArena a: dungeonArenas) {
                        if(a.istBeendet) {
                            for(DungeonQueue q: dungeonQueues) {
                                if(a.name.equalsIgnoreCase(q.dungeonname)) {
                                    a.start(pl, q);
                                    dungeonQueues.remove(q);
                                    Bukkit.broadcastMessage(dungeonQueues.size()+"");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        },20,20);
    }
}
