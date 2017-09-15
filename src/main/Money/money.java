package main.Money;

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
public class money implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("money")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("rpg.money.credit")) try {
                    String charname[] = p.getDisplayName().split("\\s+");
                    FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + charname[1] + "/" + charname[1] + ".txt");
                    BufferedReader reader = new BufferedReader(fr);
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    int money=Integer.parseInt(reader.readLine());
                    int gold=money/1000000;
                    int silver=(money/1000)%100;
                    int bronze=money%100;

                    p.sendMessage("§6G: " + gold + " §7S: "+ silver + " §4B: " + bronze);
                } catch (IOException e) {
                    p.sendMessage("Fehler");
                }
            }
        }
        return false;
    }
}
