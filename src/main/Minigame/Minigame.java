package main.Minigame;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;

public class Minigame implements CommandExecutor, Listener {

    public static Plugin pl;
    int id=0;
    int id2=0;
    int id3=0;
    int minX, maxX, minY, maxY;
    String[]minigame=new String[7];

    public Minigame(){}
    public Minigame(Plugin pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        String minigameName = "";
        if (command.getName().equalsIgnoreCase("minigame")) {
            //MiniGame erstellen und löschen
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("erstellen")) {
                    p.sendMessage("Versuche Minigame zu erstellen ...");
                    minigameName = args[1];
                    String gesamt="";
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))) {
                        String line="";
                        while((line=reader.readLine()) != null){
                            gesamt+=line;
                            gesamt+=System.lineSeparator();
                        }
                    } catch(IOException e) {
                        p.sendMessage(e.toString());
                    }
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))) {
                        writer.write(gesamt);
                        writer.write(minigameName + " 0" + " 0" + " 0" + " 0" + " 0" + " 0" );
                    } catch(IOException e) {
                        p.sendMessage(e.toString());
                    }
                    p.sendMessage("Du kannst jetzt mit /minigame Punkte (Minigame-Name) (Punkt1,Punkt2) eine Spielbegrenzung erstellen!");
                } else {
                    p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein.");
                }


                //Minigame löschen
                if (args[0].equalsIgnoreCase("löschen")) {
                    p.sendMessage("Versuche Minigame zu löschen ...");
                    String gesamt="";
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))){
                        String line="";
                        while((line=reader.readLine()) != null){
                            String[]minigameArray=line.split(" ");
                            if(args[1].equalsIgnoreCase(minigameArray[0])) {
                                break;
                            }
                            gesamt+=line;
                            gesamt+=System.lineSeparator();
                        }
                    } catch (IOException e) {
                        p.sendMessage("Das Minigame konnte nicht löschen werden!");
                    }
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/minigame.txt"),Charset.forName("UTF-8"))) {
                        writer.write(gesamt);
                    } catch (IOException e) {
                        p.sendMessage(e.toString());
                    }
                }



            //Minigame list
            } else if (args.length <= 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))){
                        String line="";
                        while((line=reader.readLine()) != null){
                            String[]minigameArray=line.split(" ");
                            p.sendMessage(minigameArray[0]);
                        }
                    } catch (IOException e) {
                        p.sendMessage("Die Liste für Minigames konnte nicht ausgegeben werden!");
                    }
                } else {
                    p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein.");
                }
            }



            //MiniGame Punkte erstellen
            if(args.length == 3) {
                if (args[0].equalsIgnoreCase("Punkt")) {
                    Location targetloc = p.getLocation();
                    String minigame[]=new String[7];
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))){
                        String line="";
                        while((line=reader.readLine()) != null){
                            String[]minigameArray=line.split(" ");
                            if(args[1].equalsIgnoreCase(minigameArray[0])) {
                                minigame[0]=minigameArray[0];
                                minigame[1]=minigameArray[1];
                                minigame[2]=minigameArray[2];
                                minigame[3]=minigameArray[3];
                                minigame[4]=minigameArray[4];
                                minigame[5]=minigameArray[5];
                                minigame[6]=minigameArray[6];
                            }
                        }
                    } catch (IOException e) {
                        p.sendMessage("Die Puntke konnten nicht gespeichert werden!");
                    }
                    if (args[1].equalsIgnoreCase(minigame[0]) && args[2].equalsIgnoreCase("1")) {
                        minigame[1]=targetloc.getBlockX()+"";
                        minigame[2]=targetloc.getBlockY()+"";
                        minigame[3]=targetloc.getBlockZ()+"";
                        p.sendMessage(minigame[1]+minigame[2]+minigame[3]);
                        String gesamt="";
                        try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))){
                            String line="";
                            while((line=reader.readLine()) != null){
                                String[]minigameArray=line.split(" ");
                                if(args[1].equalsIgnoreCase(minigameArray[0])) {
                                    gesamt+=minigameArray[0] + " " + minigame[1] + " " + minigame[2] + " " + minigame[3] + " " + minigameArray[4] + " " + minigameArray[5] + " " + minigameArray[6];
                                    gesamt+=System.lineSeparator();
                                    break;
                                }
                                gesamt+=line;
                                gesamt+=System.lineSeparator();
                            }
                        } catch (IOException e) {
                            p.sendMessage("Punkt 1 konnte nicht gespeichert werden!");
                        }
                        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/minigame.txt"),Charset.forName("UTF-8"))) {
                            writer.write(gesamt);
                        } catch (IOException e) {
                            p.sendMessage(e.toString());
                        }
                    }
                    if (args[1].equalsIgnoreCase(minigame[0]) && args[2].equalsIgnoreCase("2")) {
                        minigame[4]=targetloc.getBlockX()+"";
                        minigame[5]=targetloc.getBlockY()+"";
                        minigame[6]=targetloc.getBlockZ()+"";
                        p.sendMessage(minigame[4]+minigame[5]+minigame[6]);
                        String gesamt="";
                        try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))){
                            String line="";
                            while((line=reader.readLine()) != null){
                                String[]minigameArray=line.split(" ");
                                if(args[1].equalsIgnoreCase(minigameArray[0])) {
                                    gesamt+=minigameArray[0] + " " + minigameArray[1] + " " + minigameArray[2] + " " + minigameArray[3] + " " + minigame[4] + " " + minigame[5] + " " + minigame[6];
                                    gesamt+=System.lineSeparator();
                                    break;
                                }
                                gesamt+=line;
                                gesamt+=System.lineSeparator();
                            }
                        } catch (IOException e) {
                            p.sendMessage("Punkt 2 konnte nicht gespeichert werden!");
                        }
                        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/minigame.txt"),Charset.forName("UTF-8"))) {
                            writer.write(gesamt);
                        } catch (IOException e) {
                            p.sendMessage(e.toString());
                        }
                    }
                }
            }


            //Minigame starten
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("starten")) {
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"),Charset.forName("UTF-8"))){
                        String line="";
                        while((line=reader.readLine()) != null){
                            String[]split=line.split(" ");
                            if(args[1].equalsIgnoreCase(split[0])){
                                minigame[0]=split[0];
                                minigame[1]=split[1];
                                minigame[2]=split[2];
                                minigame[3]=split[3];
                                minigame[4]=split[4];
                                minigame[5]=split[5];
                                minigame[6]=split[6];
                            }
                        }
                    } catch(IOException e) {}
                    if(Integer.parseInt(minigame[1])>Integer.parseInt(minigame[4])) {
                        minX=Integer.parseInt(minigame[4]);
                        maxX=Integer.parseInt(minigame[1]);
                    } else {
                        minX=Integer.parseInt(minigame[1]);
                        maxX=Integer.parseInt(minigame[4]);
                    }
                    if(Integer.parseInt(minigame[3])>Integer.parseInt(minigame[6])) {
                        minY=Integer.parseInt(minigame[6]);
                        maxY=Integer.parseInt(minigame[3]);
                    } else {
                        minY=Integer.parseInt(minigame[3]);
                        maxY=Integer.parseInt(minigame[6]);
                    }
                    int hoehe=Integer.parseInt(minigame[2]);
                    for(int zeilenhoehe=0; zeilenhoehe<=1; zeilenhoehe++) {
                        for (int x = minX; x <= maxX; x++) {
                            for (int y = minY; y <= maxY; y++) {
                                Location loc = new Location(Bukkit.getServer().getWorld("world"), x, hoehe, y);
                                loc.getBlock().setType(Material.SNOW_BLOCK);
                            }
                        }
                        hoehe=hoehe-6;
                    }
                    Countdown();
                }
            }
        }
        //Minigame Challenge
        if(args.length == 3) {
            if (args[0].equalsIgnoreCase("challenge")) {
                Player eingeladenerSpieler = Bukkit.getServer().getPlayer(args[1]);
                String gesamtCurrentGames="";
                try (BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/inviteminigame.txt"), Charset.forName("UTF-8"))) {
                    String line="";
                    while((line=reader.readLine()) != null){
                        gesamtCurrentGames+=line;
                        gesamtCurrentGames+=System.lineSeparator();
                    }
                } catch (IOException e) {}
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/inviteminigame.txt"), Charset.forName("UTF-8"))) {
                    writer.write(gesamtCurrentGames);
                    eingeladenerSpieler.sendMessage("Sie wurden zu einem Battle von " + p.getName() + " in der Arena " + args[2] + " herausgefordert!");
                    eingeladenerSpieler.sendMessage("Nimm die Andrage mit /minigame annehmen <Arena> an");
                    writer.write(args[2] + " " + p.getName() + " " + args[1] + " false" );
                } catch (IOException e) {}
            }
        }

        //Minigame annehmen
        if(args.length == 2) {
            if (args[0].equalsIgnoreCase("annehmen")) {
                String angenommen="false";
                String eingeladeneSpielerGesamt="";
                try (BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/inviteminigame.txt"), Charset.forName("UTF-8"))) {
                    String line="";
                    while((line=reader.readLine()) != null) {
                        String[]splitArray = line.split(" ");
                        if(args[1].equalsIgnoreCase(splitArray[0]) && p.getName().equalsIgnoreCase(splitArray[2])) {
                            p.sendMessage("Du hast die Anfrage von " + splitArray[1] + " für die Arena " + splitArray[0] + " angenommen!");
                            splitArray[3]="true";
                            eingeladeneSpielerGesamt+=splitArray[0] + " " + splitArray[1] + " " + splitArray[2] + " " + splitArray[3];
                            eingeladeneSpielerGesamt+=System.lineSeparator();
                            break;
                        }
                        eingeladeneSpielerGesamt+=line;
                        eingeladeneSpielerGesamt+=System.lineSeparator();
                    }
                } catch (IOException e) {}
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/inviteminigame.txt"), Charset.forName("UTF-8"))) {
                    writer.write(eingeladeneSpielerGesamt);
                } catch (IOException e) {}
            }
        }
        return false;
    }


    public void Countdown(){
         id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int countdown=10;
            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage("Das Minigame " + ChatColor.RED + minigame[0] + ChatColor.WHITE + " startet in " + ChatColor.GREEN + countdown + ChatColor.WHITE + " Sekunden!");
                countdown--;
                if(countdown==-1) {
                    Bukkit.getServer().broadcastMessage("Das Game " + ChatColor.RED + minigame[0] + "hat gestartet!");
                    int hoehe=Integer.parseInt(minigame[2]);
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/Minigame/currentminigame.txt"), Charset.forName("UTF-8"))) {

                    } catch (IOException e){}
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        int x = 0;
                        int y = 0;
                        x = ((maxX - minX) / 2) + minX;
                        y = ((maxY - minY) / 2) + minY;
                        hoehe++;
                        p.setGameMode(GameMode.ADVENTURE);
                        p.getPlayer().getInventory().clear();
                        ItemStack item = new ItemStack(Material.DIAMOND_SPADE);
                        item.setDurability((short) -1);
                        p.getInventory().addItem(item);
                        Location loc = new Location(Bukkit.getServer().getWorld("world"), x, hoehe, y);
                        p.teleport(loc);
                    }
                    Countdown2();
                    Bukkit.getScheduler().cancelTask(id);
                }
            }
        }, 1*20,1*20);
    }

    public void Countdown2(){
        id2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int countdown=5;
            @Override
            public void run() {
                Bukkit.broadcastMessage("Das Spiel geht in " + ChatColor.RED + countdown + ChatColor.WHITE + " Sekunden los!");
                countdown--;
                if(countdown == -1) {
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                    hoehetester();
                    Bukkit.getScheduler().cancelTask(id2);
                }
            }
        }, 1*20,1*20);
    }

    public void hoehetester(){
        id3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            int hoehe = Integer.parseInt(minigame[2])-8;
            Location spawn = Bukkit.getServer().getWorld("world").getSpawnLocation();
            @Override
            public void run() {
                for(Player p: Bukkit.getOnlinePlayers()) {
                    double loc;
                    loc = p.getLocation().getY();
                    if(loc<=hoehe){
                        p.teleport(spawn);
                    }
                }
            }
        }, 0, 1);
    }
}
