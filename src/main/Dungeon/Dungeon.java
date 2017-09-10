package main.Dungeon;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Created by maxim on 08.08.2017.
 */
public class Dungeon implements CommandExecutor{
    Plugin pl;
    int dungeonid=0;
    ArrayList<DungeonArena> dungeonArenas = new ArrayList<DungeonArena>(0);

    public Dungeon(Plugin pl) {
        this.pl = pl;
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
                if(args[0].equalsIgnoreCase("starten")) {
                    for(DungeonArena a : dungeonArenas) {
                        if(a.name.equalsIgnoreCase(args[1])) {
                            a.start(pl);
                            break;
                        }
                    }
                }
            }
        }

        return false;
    }
}
