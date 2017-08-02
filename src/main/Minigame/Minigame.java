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

public class Minigame implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        String minigameName = "";
        if (command.getName().equalsIgnoreCase("minigame")) {
            //MiniGame erstellen und löschen
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("erstellen")) {
                    p.sendMessage("Versuche Minigame zu speichern ...");
                    minigameName = args[1];
                    PrintWriter pWriter = null;
                    try {

                        pWriter = new PrintWriter(new BufferedWriter(new FileWriter("plugins/RPG/MinigameTest/minigame.txt")));
                        FileReader freader = new FileReader("plugins/RPG/MinigameTest/minigame.txt");
                        BufferedReader reader = new BufferedReader(freader);
                        String line="";
                        while ((line=reader.readLine()) != null) {
                            p.sendMessage("Test");
                            pWriter.println(line);
                        }
                        pWriter.println(minigameName + " 0" + " 0");
                    } catch (IOException e) {
                    } finally {
                        if (pWriter != null){
                            pWriter.flush();
                            pWriter.close();
                        }
                    }
                    p.sendMessage("Du kannst jetzt mit /minigame Punkte (Minigame-Name) (Punkt1,Punkt2) eine Spielbegrenzung erstellen!");
                } else {
                    p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein.");
                }
                if (args[0].equalsIgnoreCase("löschen")) {
                    p.sendMessage("Versuche Minigame zu löschen ...");
                    try(BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))){
                        String line="";
                        while((line=reader.readLine()) != null){
                            String[]minigameArray=line.split(" ");

                        }
                    } catch (IOException e) {
                        p.sendMessage("Das Minigame konnte nicht löschen werden!");
                    }
                }
            } else if (args.length <= 1) {
                p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein.");
            }

            //MiniGame Punkte erstellen
            if(args.length == 2) {
                if (args[0].equalsIgnoreCase("Punkt")) {
                    try (BufferedReader reader = Files.newBufferedReader(Paths.get("plugins/RPG/Minigame/minigame.txt"), Charset.forName("UTF-8"))) {
                        String line="";
                        while((line=reader.readLine()) != null) {
                            minigameName=line.substring(0,line.indexOf(" "));
                            p.sendMessage(line);
                            p.sendMessage(minigameName);
                        }
                    } catch (IOException e) {}
                }
            }
        }
        return false;
    }
}
