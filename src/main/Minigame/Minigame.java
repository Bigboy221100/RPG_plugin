package main.Minigame;



import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Minigame implements CommandExecutor, Listener{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args){
        Player p = (Player) commandSender;
        String minigameName = "";
        if (command.getName().equalsIgnoreCase("minigame")) {
            //MiniGame erstellen und löschen
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("erstellen")) {
                    p.sendMessage("Versuche Minigame zu erstellen ...");
                    minigameName = args[1];
                    String gesamt="";
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/MinigameTest/minigame.txt"), Charset.forName("UTF-8"))) {
                        String line="";
                        while((line=reader.readLine()) != null){
                            gesamt+=line;
                            gesamt+=System.lineSeparator();
                        }
                    } catch(IOException e) {
                        p.sendMessage(e.toString());
                    }
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/MinigameTest/minigame.txt"), Charset.forName("UTF-8"))) {
                        writer.write(gesamt);
                        writer.write(minigameName + " 0" + " 0");
                    } catch(IOException e) {
                        p.sendMessage(e.toString());
                    }
                    p.sendMessage("Du kannst jetzt mit /minigame Punkte (Minigame-Name) (Punkt1,Punkt2) eine Spielbegrenzung erstellen!");
                } else {
                    p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein.");
                }
                if (args[0].equalsIgnoreCase("löschen")) {
                    p.sendMessage("Versuche Minigame zu löschen ...");
                    String gesamt="";
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/MinigameTest/minigame.txt"), Charset.forName("UTF-8"))){
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
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("plugins/RPG/MinigameTest/minigame.txt"),Charset.forName("UTF-8"))) {
                        writer.write(gesamt);
                    } catch (IOException e) {
                        p.sendMessage(e.toString());
                    }
                }
            } else if (args.length <= 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/MinigameTest/minigame.txt"), Charset.forName("UTF-8"))){
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
            if(args.length == 2) {
                if (args[0].equalsIgnoreCase("Punkt")) {


                }
            }
        }
        return false;
    }
}
