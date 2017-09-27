package main.Chars.Commands;

import main.Chars.Classes.Archer.Archer;
import main.Chars.Classes.CharPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 27.09.2017.
 */
public class CreatenewChar implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createnewchar")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.new")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("Archer")) {
                            CharPlayer Char=new Archer(p.getUniqueId(),args[1]);
                            p.sendMessage("Congratulation. You successfully create your Level 1 Archer.");
                        }else{
                            p.sendMessage("This Class does not exist");
                        }
                    }else{
                        p.sendMessage("Wrong parameters /createnewchar <class> <charname>");
                    }
                }else{
                    p.sendMessage("You do not have the required permissions to use this command.");
                }
            }else{
                System.out.println("You can only use this command as a player");
            }

        }
        return false;
    }
}
