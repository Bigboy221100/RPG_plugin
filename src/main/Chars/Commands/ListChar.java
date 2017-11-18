package main.Chars.Commands;

import main.Chars.Charvoids;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 28.09.2017.
 */
public class ListChar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("listcharacter")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.list")) {
                    Object []tmp=Charvoids.getuserchars(p.getUniqueId()+"");
                    p.sendMessage("These are your Characters");
                    for(int i=0;i<tmp.length;i++){
                        p.sendMessage(tmp[i]+"");
                    }
                }
            }
        }
        return false;
    }
}
