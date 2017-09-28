package main.Chars.Commands;

import main.Chars.Charvoids;
import main.MySQL.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 27.09.2017.
 */
public class DeleteChar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("deletecharacter")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.delete")) {
                    if (args.length == 1) {
                        if(Charvoids.isUsed(args[0])==true){
                            if(Charvoids.charbelongsto(p.getUniqueId()+"")==true){
                                MySQL.update("DELETE FROM Characters WHERE charname='"+args[0]+"' AND UUID='"+p.getUniqueId()+"'");
                                p.sendMessage("The character "+args[0]+ " was successfully deleted");
                            }else{
                                p.sendMessage("This character does not belong to you");
                            }
                        }else{
                            p.sendMessage("This character does not exist.");
                        }
                    }
                }else{
                    p.sendMessage("You do not have the required permissions to use this command.");
                }
            }else{
                System.out.println("You can only use this command as a player.");
            }
        }
        return false;
    }


}
