package main.text.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 29.07.2017.
 */
public class commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("rpg commands")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                if(p.hasPermission("rpg.text.commands")){
                    p.sendMessage("Wichtige Befehle für dich");
                }
            }
        }
        return false;
    }
}
