package main.text.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 29.07.2017.
 */
public class version implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("rpg version")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                if(p.hasPermission("rpg.text.info.version")){
                    p.sendMessage("Comming soon");
                }
            }
        }
        return false;
    }
}
