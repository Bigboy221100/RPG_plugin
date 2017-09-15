package main.Char.charcommands;

import main.Char.Klassen.CharPlayer;
import main.Char.Klassen.Normal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by user on 28.07.2017.
 */
public class Logoutcharacter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Logoutcharacter")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.logout")) {
                    CharPlayer charPlayer = new Normal(p);
                    p.sendMessage("Du wurdest ausgelogt");
                } else {
                    p.sendMessage("Du hast dafür keine Berechtigungen");
                }
            } else {
                System.out.println("Nur Spieler können diesen Befehl ausführen");
            }
        }
        return false;
    }
}
