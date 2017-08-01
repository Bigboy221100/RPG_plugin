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
                    File files[] = maindir.listFiles();
                    String [] list=new String[files.length];
                    for(int i=0;i<list.length;i++){
                        p.sendMessage(list[i]);
                    }
                }else{
                    p.sendMessage("Du hast dafür keine Berechtigungen");
                }
            }else{
                System.out.println("Nur Spieler können diesen Befehl ausfüh ren");
            }
        }
        return false;
    }
}
