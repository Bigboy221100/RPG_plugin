package main.Char.Klassen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by Fabian on 19.07.2017.
 */
public class Archer extends CharPlayer {
    //nur zum Erstellen gedacht
    public Archer(UUID player, String name, String klasse) {
        super(player, name, klasse);
        ausrüsten();
    }
    //nur zum Erstellen gedacht
    public Archer(UUID player, String name){
        this.player=player;
        this.name=name;
        this.klasse="Archer";
        ausrüsten();
    }
    //nur zum Laden gedacht
    public Archer(UUID player, String name, String klasse, int money,int level, int xp){
        this.player=player;
        this.name=name;
        this.klasse=klasse;
        this.money=money;
        this.level=level;
        this.xp=xp;
        Player p=Bukkit.getPlayer(player);
        p.getInventory().clear();
        YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/RPG/Chars/"+player+"/"+name+"_inv.yml"));
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
        p.setLevel(this.level);
        p.setDisplayName("[Archer] " + name);
        p.setPlayerListName("[Archer] " + name);

    }

    private void ausrüsten(){
        this.level=1;
        Player p= Bukkit.getPlayer(player);
        p.getInventory().clear();
        p.setDisplayName("[Archer] " + name);
        p.setPlayerListName("[Archer] " + name);
        p.setCustomName("[Archer] " + name);
        p.getInventory().addItem(new ItemStack(Material.BOW));
        for(int i=0;i<16;i++) {
            p.getInventory().addItem(new ItemStack(Material.ARROW));
        }
        p.setLevel(level);
        PrintWriter pWriter = null;
        try {
            //create Char txt
            File inv = new File("plugins/RPG/Chars/"+player+"/"+name+"/"+name+".txt");
            inv.getParentFile().mkdirs();
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(inv)));
            pWriter.println(player);
            pWriter.println(name);
            pWriter.println(klasse);
            pWriter.println(money);
            pWriter.println(level);
            pWriter.println(xp);
            //Create inv txt
            YamlConfiguration c = new YamlConfiguration();
            c.set("inventory.armor", p.getInventory().getArmorContents());
            c.set("inventory.content", p.getInventory().getContents());
            c.save(new File("plugins/RPG/Chars/"+player+"/"+name+"/"+name+"_inv.yml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }


    }
}
