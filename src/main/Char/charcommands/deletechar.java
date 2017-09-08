package main.Char.charcommands;

import main.Char.Klassen.CharPlayer;
import main.Char.Klassen.Normal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by user on 28.07.2017.
 */
public class deletechar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("deletecharacter")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.character.delete")) {
                    if (args.length == 1) {
                        File deltxt = new File("plugins/RPG/Chars/" + p.getUniqueId() + "/" + args[0] + "/" + args[0] + ".txt");
                        if (!deltxt.exists()) {
                            p.sendMessage("Diesen main.Char gibt es nicht oder gehört nicht ihnen");
                        }
                        File delyml = new File("plugins/RPG/Chars/" + p.getUniqueId() + "/" + args[0] + "/" + args[0] + "_inv.yml");
                        boolean gingtxt = deltxt.delete();
                        boolean gingyml = delyml.delete();
                        if (gingtxt == true && gingyml == true) {
                            p.sendMessage("Der Character " + args[0] + " wurde erfolgreich gelöscht");
                            CharPlayer charPlayer = new Normal(p);
                        } else {
                            if (deltxt.exists()) {
                                p.sendMessage("Der Character " + args[0] + " konnte nicht gelöscht werden");
                            }
                        }
                    } else {
                        p.sendMessage("/deletecharacter <Character>");
                    }
                } else {
                    p.sendMessage("Du hast dafür keine Berechtigungen");
                }
            } else {
                System.out.println("Nur Spieler können diesen Befehl ausfüh ren");
            }
        }
        return false;
    }
}
