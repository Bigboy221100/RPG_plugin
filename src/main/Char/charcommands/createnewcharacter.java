package main.Char.charcommands;

import main.Char.Klassen.Archer.Archer;
import main.Char.Klassen.CharPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Fabian on 19.07.2017.
 */
public class createnewcharacter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("createnewcharacter")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                if(p.hasPermission("rpg.character.new")){
                    if(args.length==2){
                        if(args[0].equalsIgnoreCase("Archer")){
                            CharPlayer charPlayer=new Archer(p.getUniqueId(),args[1]);
                            p.sendMessage("Sie sind nun ein Level 1 Bogenschütze mit dem Namen " + args[1]);
                        }else{
                            p.sendMessage("Diese Klasse gibt es nicht");
                        }
                    }else{
                        p.sendMessage("Fehler...  createnewcharacter <Klasse> <Name>");
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
