package main.Money;

import main.Chars.Charvoids;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Fabian on 01.08.2017.
 */
public class Moneyview implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Moneyview")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.moneyview.credit")) {
                    int money = Charvoids.getcharmoney(p.getDisplayName());
                    int gold = money / 1000000;
                    int silver = (money / 1000) % 100;
                    int bronze = money % 100;
                    p.sendMessage("§6G: " + gold + " §7S: " + silver + " §4B: " + bronze);
                }
            }
        }
        return false;
    }
}