package main.Chars.Classes.Archer;


import main.Chars.Classes.CharPlayer;
import main.InvSerializer;
import main.MySQL.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Fabian on 19.07.2017.
 */
public class Archer extends CharPlayer implements Listener {
    //nur zum Erstellen gedacht
    public Archer() {

    }

    public Archer(UUID player, String name, String klasse) {
        super(player, name, klasse);
        ausrüsten();
    }

    //nur zum Erstellen gedacht
    public Archer(UUID player, String name) {
        this.player = player;
        this.name = name;
        this.klasse = "Archer";
        ausrüsten();
    }

    //nur zum Laden gedacht
    public Archer(UUID player, String name, String klasse, int money, int level, double xp, String inv) {
        this.player = player;
        this.name = name;
        this.klasse = klasse;
        this.money = money;
        this.level = level;
        this.xp = xp;
        Player p = Bukkit.getPlayer(player);
        p.getInventory().clear();
        try {
            ItemStack[]test2 = InvSerializer.itemStackArrayFromBase64(inv);
            for (ItemStack is : test2) {
                if (is != null)
                {
                    p.getInventory().addItem(is);
                }

            }
        }catch (IOException e){

        }


        p.setLevel(this.level);
        p.setExp((float)xp);
        p.setDisplayName("§1[§6Archer§1]§2 " + name);
        p.setCustomName("[Archer] " + name);
        p.setCustomNameVisible(true);
        p.setPlayerListName("§1[§6Archer§1]§2 " + name);
        p.setGameMode(GameMode.ADVENTURE);


    }

    private void ausrüsten() {
        this.level = 1;
        Player p = Bukkit.getPlayer(player);
        p.getInventory().clear();
        p.setDisplayName("§1[§6Archer§1]§2 " + name);
        p.setPlayerListName("§1[§6Archer§1]§2 " + name);
        p.setCustomName("[Archer] " + name);
        p.setCustomNameVisible(true);
        p.getInventory().addItem(new ItemStack(Material.BOW));
        p.setGameMode(GameMode.ADVENTURE);
        for (int i = 0; i < 16; i++) {
            p.getInventory().addItem(new ItemStack(Material.ARROW));
        }
        p.setLevel(level);
        p.setExp(0);

        ItemStack head = new ItemStack(Material.SKULL_ITEM);
        ItemMeta skull = head.getItemMeta();
        skull.setDisplayName("Benutzerinterface");
        head.setItemMeta(skull);
        p.getInventory().setItem(8, head);

        String playerinv[]=InvSerializer.playerInventoryToBase64(p.getInventory());

        MySQL.update("INSERT INTO Characters (UUID, charname, charclass, charmoney, charlevel, charxp, charinv, currentplaying) VALUES ('"+player+"','"+name+"','"+klasse+"','"+money+"','"+level+"','"+xp+"','"+playerinv[0]+"',true)");
    }

}
