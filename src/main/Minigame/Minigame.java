package main.Minigame;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Minigame implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        String minigameName="";
        p.sendMessage(args.length+"");
        if(args.length == 2) {
            if (args[1].equalsIgnoreCase("erstellen")) {
                minigameName = args[2];
                try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("/RPG/Minigame/"), Charset.forName("UTF-8"))){
                    writer.write(minigameName);
                    p.sendMessage("MiniGame erfolgreich erstellt!");
                } catch (IOException e) {
                    p.sendMessage("Das Minigame konnte nicht erstellt werden!");
                }
            }
        } else if(args.length == 0) {
            p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein.");
            p.sendMessage("Fehler 1");
        } else if(args.length == 1) {
            if(args[1].equalsIgnoreCase("erstellen")) {
                p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein");
                p.sendMessage("Fehler 2");
            } else {
                p.sendMessage("Bitte gib /minigame erstellen (Minigame-Name) ein");
                p.sendMessage("Fehler 3");
            }
        }
        return false;
    }
}
