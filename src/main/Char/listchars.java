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
                    String [] list=new String[files.length/2];
                    int u=0;
                    for(int i=0;i<list.length;i++){
                        list[i] = files[u]+"";
                        u=u+2;
                    }
                    p.sendMessage("Das sind ihre Character:");
                    for(int i=0;i<list.length;i++) {
                        String help=list[i].substring(55,list[i].length()-4);
                        p.sendMessage(help);
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
