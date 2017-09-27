package main.Chars.Classes;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 28.07.2017.
 */
public class Normal extends CharPlayer {
    public Normal(Player p) {
        this.player = p.getUniqueId();
        this.name = p.getName();
        this.klasse = "Normal";
        this.level = 0;
        this.xp = 0;
        try {
            String charname[] = p.getDisplayName().split("\\s+");
            if (name.equalsIgnoreCase(Bukkit.getPlayer(player).getDisplayName())) {

            } else {
                YamlConfiguration c = new YamlConfiguration();
                c.set("inventory.armor", p.getInventory().getArmorContents());
                c.set("inventory.content", p.getInventory().getContents());
                c.save(new File("plugins/RPG/Chars/" + player + "/" + charname[1] + "/" + charname[1] + "_inv.yml"));
            }
            p.getInventory().clear();
            p.setDisplayName("§1[§7Normal§1]§2 " + name + "§f");
            p.setPlayerListName("§1[§7Normal§1]§2 " + name + "§f");
            p.setLevel(0);
            p.setExp(0);
            p.setGameMode(GameMode.ADVENTURE);
        } catch (IOException e) {
            System.out.println("Fehler");
        }
    }
}
