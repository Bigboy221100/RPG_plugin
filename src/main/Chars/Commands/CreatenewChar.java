package main.Chars.Commands;

import main.Chars.Classes.Archer.Archer;
import main.Chars.Classes.CharPlayer;
import main.MySQL.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 27.09.2017.
 */
public class CreatenewChar implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createnewcharacter")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.new")) {
                    if (args.length == 2) {
                        if((isUsed(args[1]))==false) {
                            if (args[0].equalsIgnoreCase("Archer")) {
                                CharPlayer Char = new Archer(p.getUniqueId(), args[1]);
                                p.sendMessage("Congratulation. You successfully create your Level 1 Archer "+args[1]+" .");
                            } else {
                                p.sendMessage("This Class does not exist.");
                            }
                        }else{
                            p.sendMessage("This name is already in use.");
                        }
                    }else{
                        p.sendMessage("Wrong parameters /createnewchar <class> <charname>");
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

    private boolean isUsed(String charname){
        ResultSet rs = MySQL.getResultSet("SELECT * FROM Characters");
        try {
            while(rs.next()) {
                if(rs.getString("charname").equalsIgnoreCase(charname)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
