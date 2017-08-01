package main.Char;

import main.Char.Klassen.Archer;
import main.Char.Klassen.CharPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.UUID;

/**
 * Created by user on 27.07.2017.
 */
public class loadcharacter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("loadcharacter")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                if(p.hasPermission("rpg.character.load")){
                    if(args.length==1) {
                        try {
                            UUID player=p.getUniqueId();
                            FileReader fr = new FileReader("plugins/RPG/Chars/"+player+"/"+args[0]+"/"+args[0]+".txt");
                            BufferedReader reader = new BufferedReader(fr);
                            String UUID=reader.readLine();
                            UUID uuidr= java.util.UUID.fromString(UUID);
                            String name=reader.readLine();
                            String klasse=reader.readLine();
                            int money=Integer.parseInt(reader.readLine());
                            String level=reader.readLine();
                            int levelr=Integer.parseInt(level);
                            String xp=reader.readLine();
                            int xpr= Integer.parseInt(xp);
                            if(player.equals(uuidr)) {
                                CharPlayer charPlayer = new Archer(uuidr, name, klasse, money,levelr, xpr);
                                p.sendMessage("Sie haben sich erfolgreich in ihren Level " + levelr + " " + klasse + " " + name + " eingeloggt");
                            }else{
                                p.sendMessage("Dieser Character gehört ihnen nicht");
                            }
                        }catch (FileNotFoundException e){
                            p.sendMessage("Diesen Character gibt es nicht");
                            System.out.println("Diese Datei gibt es nicht");
                        }catch (IOException a){
                            System.out.println("Fehler");
                        }
                    }
                }else{
                    p.sendMessage("Du hast dafür keine Berechtigungen");
                }
            }else{
                System.out.println("Nur Spieler können diesen Befehl ausführen");
            }
        }
        return false;
    }
}
