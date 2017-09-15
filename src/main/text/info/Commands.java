package main.text.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 15.09.2017.
 */
public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("commands")) {
            if(args.length==0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (p.hasPermission("rpg.text.commands")) {
                        p.sendMessage("Geben sie bitte ihren spezifischen Command ein um ihre Hilfe zu bekommen");
                    }
                }
            }else if(args[0].equalsIgnoreCase("charater")){
                if(sender instanceof Player){
                    Player p= (Player) sender;
                    if(p.hasPermission("rpg.text.commands.character")){
                        p.sendMessage("Mit Hilfe von /createnewcharchter <Klasse> <Name> erstellen sie einen neuen Char");
                        p.sendMessage("Mit Hilfe von /deletecharachter <Name> können sie einen Char löschen");
                        p.sendMessage("Mit Hilfe von /listcharachter können sie ihre Chars auflisten lassen");
                        p.sendMessage("Mit Hilfe von /loadcharachter <Name> können sie einen Char laden");
                        p.sendMessage("Mit Hilfe von /logoutcharchter können sie sich aus ihrem aktuellen Char ausloggen");
                        p.sendMessage("Mit Hilfe von /money wird das aktuelle geld ihres Chars ausgegeben");
                    }
                }
            }else if(args[0].equalsIgnoreCase("minigame")){
                if(sender instanceof Player){
                    Player p=(Player) sender;
                    if(p.hasPermission("rpg.text.commands.minigame")){
                        p.sendMessage("Mit Hilfe von /minigame challenge <Name> können sie einen anderen Spieler herausfordern");
                    }
                }
            }else if(args[0].equalsIgnoreCase("dungeon")){
                if(sender instanceof Player){
                    Player p=(Player) sender;
                    if(p.hasPermission("rpg.text.commands.dungeon")){
                        p.sendMessage("Mit Hilfe ");
                    }
                }
            }
        }
        return false;
    }
}
