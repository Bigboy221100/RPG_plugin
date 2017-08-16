package main.Minigame;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

    public class Minigame implements CommandExecutor, Listener {

        public static Plugin pl;
        int id1;
        public ArrayList<MinigameArena> minigameArenas = new ArrayList<MinigameArena>(0);
        public ArrayList<MinigameQueue> minigameQueues = new ArrayList<MinigameQueue>(0);

        public Minigame() {
            queueTesten();
        }

        public Minigame(Plugin pl) {
            this.pl = pl;
            queueTesten();
        }

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (args.length == 2) {
                    //Minigame erstellen
                    if (args[0].equalsIgnoreCase("erstellen")) {
                        boolean istNichtVorhanden = true;
                        for (MinigameArena a : minigameArenas) {
                            if (args[1].equalsIgnoreCase(a.arenaName)) {
                                istNichtVorhanden = false;
                                p.sendMessage("Die Arena " + a.arenaName + " gibt es schon!");
                                break;
                            }
                        }
                        if (istNichtVorhanden) {
                            minigameArenas.add(new MinigameArena(args[1]));
                        }
                        p.sendMessage(minigameArenas.size() + "");
                    }
                    //Minigame löschen
                    if (args[0].equalsIgnoreCase("löschen")) {
                        int i = 0;
                        for (MinigameArena a : minigameArenas) {
                            if (args[1].equalsIgnoreCase(a.arenaName)) {
                                minigameArenas.remove(i);
                                p.sendMessage(minigameArenas.size() + "");
                                break;
                            }
                            i++;
                        }
                    }
                }
                //Minigame list
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        for (MinigameArena a : minigameArenas) {
                            p.sendMessage(a.arenaName);
                        }
                    }
                }
                //Punkte erstellen
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("Punkt")) {
                        if (args[1].equalsIgnoreCase("1")) {
                            for (MinigameArena a : minigameArenas) {
                                if (args[2].equalsIgnoreCase(a.arenaName)) {
                                    a.setLocation1(p.getLocation());
                                    p.sendMessage("Punkt 1 erstellt");
                                }
                            }
                        }
                        if (args[1].equalsIgnoreCase("2")) {
                            for (MinigameArena a : minigameArenas) {
                                if (args[2].equalsIgnoreCase(a.arenaName)) {
                                    a.setLocation2(p.getLocation());
                                    p.sendMessage("Punkt 2 erstellt");
                                }
                            }
                        }
                    }
                }
                //Anfrage senden
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("challenge")) {
                        boolean isGegnerOnline = false;
                        for (Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
                            if (onlinePlayers.getName().equalsIgnoreCase(args[1])) {
                                isGegnerOnline = true;
                            }
                        }
                        if (isGegnerOnline == false) {
                            p.sendMessage("Dein Gegner ist nicht online!");
                        }
                        if (isGegnerOnline) {
                            Player gegner = Bukkit.getPlayer(args[1]);
                            boolean istNichtSpielerGequeuet = true;
                            for (MinigameQueue q : minigameQueues) {
                                if (q.p1.getUniqueId().equals(p.getUniqueId())) {
                                    p.sendMessage("Du wurdest oder hast schon wenn herausgefordert!");
                                    istNichtSpielerGequeuet = false;
                                    break;
                                }
                                if (q.p2.getUniqueId().equals(p.getUniqueId())) {
                                    p.sendMessage("Du wurdest oder hast schon wenn herausgefordert!");
                                    istNichtSpielerGequeuet = false;
                                    break;
                                }
                                if (q.p1.getUniqueId().equals(gegner.getUniqueId())) {
                                    p.sendMessage("Du wurdest oder hast schon wenn herausgefordert!");
                                    istNichtSpielerGequeuet = false;
                                    break;
                                }
                                if (q.p2.getUniqueId().equals(gegner.getUniqueId())) {
                                    p.sendMessage("Du wurdest oder hast schon wenn herausgefordert!");
                                    istNichtSpielerGequeuet = false;
                                    break;
                                }
                            }
                            if (istNichtSpielerGequeuet) {
                                String arena = args[2];
                                p.sendMessage("Du hast " + gegner.getName() + " zu einem Duel in der Arena " + arena + " herausgefordert!");
                                gegner.sendMessage("Du wurdest von " + p.getName() + " zu einem Duel in der Arena " + arena + " herausgefordert!");
                                gegner.sendMessage("Du kannst mit /minigame annehmen <Name des Gegners> das Duel annehmen oder mit /minigame ablehnen <Name des Gegners> ablehnen");
                                minigameQueues.add(new MinigameQueue(p, gegner, arena));
                                Bukkit.broadcastMessage(minigameQueues.size()+"");
                            }
                        }
                    }
                }
                //Anfrage annehmen
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("annehmen")) {
                        boolean isGegnerOnline = false;
                        for (Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
                            if (onlinePlayers.getName().equalsIgnoreCase(args[1])) {
                                isGegnerOnline = true;
                            }
                        }
                        if (isGegnerOnline) {
                            boolean annahmenGefunden = false;
                            Player gegner = Bukkit.getPlayer(args[1]);
                            for (MinigameQueue q : minigameQueues) {
                                if (q.p1.getUniqueId().equals(gegner.getUniqueId())) {
                                    q.annehmen();
                                    annahmenGefunden = true;
                                    p.sendMessage("Du hast die Duel-Anfrage angenommen!");
                                }
                            }
                            if (annahmenGefunden == false) {
                                p.sendMessage("Du hast von dieser Person keine Anfrage bekommen!");
                            }
                        } else {
                            p.sendMessage("Dein Gegner ist nicht online!");
                        }
                    }
                }
                //Anfrage ablehnen
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("ablehnen")) {
                        boolean isGegnerOnline = false;
                        for (Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
                            if (onlinePlayers.getName().equalsIgnoreCase(args[1])) {
                                isGegnerOnline = true;
                            }
                        }
                        if (isGegnerOnline) {
                            Player gegner = Bukkit.getPlayer(args[1]);
                            int i = 0;
                            for (MinigameQueue q : minigameQueues) {
                                if (q.p1.getUniqueId().equals(gegner.getUniqueId())) {
                                    minigameQueues.remove(i);
                                    p.sendMessage(minigameQueues.size() + "");
                                    break;
                                }
                                i++;
                            }
                        } else {
                            p.sendMessage("Dein Gegner ist nicht online!");
                        }
                    }
                }
            }
            return true;
        }

        //Migame Queue testen
        public void queueTesten() {
            id1= Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
                @Override
                public void run() {
                    int MinigameQueuei=0;
                    for(MinigameQueue q : minigameQueues) {
                        if(q.istAngenommen) {
                            for(MinigameArena a : minigameArenas){
                                if(a.arenaName.equalsIgnoreCase(q.arena)) {
                                    a.starten(pl, q.p1, q.p2, minigameQueues, MinigameQueuei);
                                    break;
                                }
                            }
                        }
                        MinigameQueuei++;
                    }
                }
            },10*20,20);
        }
    }
