package main.Minigame;


import io.netty.handler.codec.string.LineSeparator;
import org.apache.logging.log4j.core.config.yaml.YamlConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Minigame implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        String minigameName = "";
        p.sendMessage(args.length + "");
        if (command.getName().equalsIgnoreCase("minigame")) {
            //MiniGame erstellen und löschen
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("erstellen")) {
                    p.sendMessage("Versuche Minigame zu speichern ...");
                    minigameName = args[1];
                    try {
                        PrintWriter pWriter = null;
                        File minigamePfad = new File("plugins/RPG/MinigameTest/minigame.txt");
                        minigamePfad.getParentFile().mkdirs();
                        pWriter = new PrintWriter(new BufferedWriter(new FileWriter(minigamePfad)));
                        FileReader freader = new FileReader("plugins/RPG/MinigameTest/minigame.txt");
                        BufferedReader reader = new BufferedReader(freader);
                        String line;
                        while ((line=reader.readLine()) != null) {
                            pWriter.println(line);
                        }
                        pWriter.println(minigameName + " 0" + " 0");
                    } catch (IOException e) {}
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
