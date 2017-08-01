package main.Char.Klassen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Fabian on 01.08.2017.
 */
public class money implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("money")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                if(p.hasPermission("rpg.money.credit")){
                    try {
                        String charname[]=p.getDisplayName().split("\\s+");
                        p.sendMessage(charname);
                        FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + charname + "/" + charname + ".txt");

                        BufferedReader reader = new BufferedReader(fr);
                        for(int i=0;i<3;i++) {
                            reader.readLine();
                        }
                        p.sendMessage(reader.readLine());
                    }catch (IOException e){
                        p.sendMessage("Fehler");
                    }
                }
            }
        }
        return false;
    }
}
