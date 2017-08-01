package main.Char;

//Wird nicht funktionieren

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by user on 28.07.2017.
 */
public class listchars implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("listcharacter")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                if(p.hasPermission("rpg.character.list")){
                    File maindir = new File("plugins/RPG/Chars/"+p.getUniqueId());
                    String files[] = maindir.list();
                    p.sendMessage("Ihre Character lauten");
                    for(int i=0;i<files.length;i++){
                        p.sendMessage(files[i]);
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
