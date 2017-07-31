package main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Created by user on 26.07.2017.
 */
public class invtest implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("saveinv")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                try {
                    YamlConfiguration c = new YamlConfiguration();
                    c.set("inventory.armor", p.getInventory().getArmorContents());
                    c.set("inventory.content", p.getInventory().getContents());
                    c.save(new File("plugins/RPG/inv/",p.getName()+".yml"));
                    p.getInventory().clear();
                    p.sendMessage("Inventar wurde gespeichert");
                    return true;
                }catch (IOException e){
                    System.out.println("Fehler");
                }
            }else {
                System.out.println("Sie sind kein Spieler");
            }
        }
        if(cmd.getName().equalsIgnoreCase("loadinv")){
            if(sender instanceof Player){
                Player p=(Player)sender;
                YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/RPG/inv/", p.getName()+".yml"));
                ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
                p.getInventory().setArmorContents(content);
                content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
                p.getInventory().setContents(content);
                return true;
            }else {
                System.out.println("Sie sind kein Spieler");
            }
        }
        return false;
    }
}
