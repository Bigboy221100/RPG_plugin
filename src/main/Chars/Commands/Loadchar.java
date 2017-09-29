package main.Chars.Commands;

import main.Chars.Charvoids;
import main.Chars.Classes.Archer.Archer;
import main.Chars.Classes.CharPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 28.09.2017.
 */
public class Loadchar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("loadcharacter")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.load")) {
                    if (args.length == 1) {
                        if (Charvoids.charbelongsto(p.getUniqueId() + "")) {
                            if (Charvoids.isUsed(args[0]) == true) {
                                if(Charvoids.getcharClass(args[0]).equalsIgnoreCase("Archer")) {
                                    CharPlayer charPlayer = new Archer(p.getUniqueId(),args[0],Charvoids.getcharClass(args[0]),Charvoids.getcharmoney(args[0]),Charvoids.getcharlevel(args[0]),Charvoids.getcharxp(args[0]),Charvoids.getcharInv(args[0]));
                                }
                            } else {
                                p.sendMessage("This character does not exist.");
                            }
                        }else{
                            p.sendMessage("This character does not belong to you.");
                        }
                    }
                }
            }
        }
        return false;
    }
}