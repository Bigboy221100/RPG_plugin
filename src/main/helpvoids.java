package main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;

import java.io.*;
import java.util.UUID;

/**
 * Created by Fabian on 02.08.2017.
 */
public class helpvoids {

    public helpvoids(){

    }


    public UUID getcharacteruuid(String displayname){
        UUID player=null;
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            player=java.util.UUID.fromString(reader.readLine());
        }catch (IOException e){
            p.sendMessage("Fehler");
            System.out.println("Fehler");
        }
        return player;
    }

    public String getcharactername(String displayname){
        String name="";
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            reader.readLine();
            name=reader.readLine();
        }catch (IOException e){
            p.sendMessage("Fehler");
            System.out.println("Fehler");
        }
        return name;
    }
    public String getcharacterklasse(String displayname){
        String klasse="";
        System.out.println("ging1");
        String help[]=displayname.split("\\s+");
        System.out.println(help[1]);
        Player p= Bukkit.getPlayer(displayname);
        System.out.println("ging3");
        p.sendMessage(help[1]);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/"+p.getUniqueId()+"/"+help[1]+"/"+help[1]+".txt");
            BufferedReader reader = new BufferedReader(fr);
            for(int i=0;i<2;i++){
                reader.readLine();
            }
            klasse=reader.readLine();
        }catch (IOException e){
            p.sendMessage("Fehler");
            System.out.println("Fehler");
        }
        return klasse;
    }
    public int getcharactermoney(String displayname){
        int money=0;
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            for(int i=0;i<3;i++){
                reader.readLine();
            }
            money=Integer.parseInt(reader.readLine());
        }catch (IOException e){
            p.sendMessage("Fehler");
            System.out.println("Fehler");
        }
        return money;
    }

    public void setcharactermoney(String displayname, int money){
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            String UUID=reader.readLine();
            UUID uuidr= java.util.UUID.fromString(UUID);
            String name=reader.readLine();
            String klasse=reader.readLine();
            int moneyr=Integer.parseInt(reader.readLine());
            String level=reader.readLine();
            int levelr=Integer.parseInt(level);
            String xp=reader.readLine();
            int xpr= Integer.parseInt(xp);
            PrintWriter pWriter = null;
            File inv = new File("plugins/RPG/Chars/"+p.getUniqueId()+"/"+displayname+"/"+displayname+".txt");
            inv.getParentFile().mkdirs();
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(inv)));
            pWriter.println(uuidr);
            pWriter.println(name);
            pWriter.println(klasse);
            pWriter.println(moneyr);
            pWriter.println(levelr);
            pWriter.println(xpr);
        }catch (IOException e){
            System.out.println("Fehler");
            p.sendMessage("Fehler");
        }
    }

    public int getcharacterlevel(String displayname){
        int level=0;
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            for(int i=0;i<4;i++){
                reader.readLine();
            }
            level=Integer.parseInt(reader.readLine());
        }catch (IOException e){
            p.sendMessage("Fehler");
            System.out.println("Fehler");
        }
        return level;
    }

    public void setcharacterlevel(String displayname, int level){
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            String UUID=reader.readLine();
            UUID uuidr= java.util.UUID.fromString(UUID);
            String name=reader.readLine();
            String klasse=reader.readLine();
            int moneyr=Integer.parseInt(reader.readLine());
            String levelr=reader.readLine();
            int levelrr=Integer.parseInt(levelr);
            String xp=reader.readLine();
            int xpr= Integer.parseInt(xp);
            PrintWriter pWriter = null;
            File inv = new File("plugins/RPG/Chars/"+p.getUniqueId()+"/"+displayname+"/"+displayname+".txt");
            inv.getParentFile().mkdirs();
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(inv)));
            pWriter.println(uuidr);
            pWriter.println(name);
            pWriter.println(klasse);
            pWriter.println(moneyr);
            pWriter.println(levelrr);
            pWriter.println(xpr);
        }catch (IOException e){
            System.out.println("Fehler");
            p.sendMessage("Fehler");
        }
    }

    public int getcharacterxp(String displayname){
        int xp=0;
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            for(int i=0;i<5;i++){
                reader.readLine();
            }
            xp=Integer.parseInt(reader.readLine());
        }catch (IOException e){
            p.sendMessage("Fehler");
            System.out.println("Fehler");
        }
        return xp;
    }

    public void setcharacterxp(String displayname, int xp){
        Player p= Bukkit.getServer().getPlayer(displayname);
        try {
            FileReader fr = new FileReader("plugins/RPG/Chars/" + p.getUniqueId() + "/" + displayname + "/" + displayname + ".txt");
            BufferedReader reader = new BufferedReader(fr);
            String UUID=reader.readLine();
            UUID uuidr= java.util.UUID.fromString(UUID);
            String name=reader.readLine();
            String klasse=reader.readLine();
            int moneyr=Integer.parseInt(reader.readLine());
            String level=reader.readLine();
            int levelr=Integer.parseInt(level);
            String xpr=reader.readLine();
            int xprr= Integer.parseInt(xpr);
            PrintWriter pWriter = null;
            File inv = new File("plugins/RPG/Chars/"+p.getUniqueId()+"/"+displayname+"/"+displayname+".txt");
            inv.getParentFile().mkdirs();
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(inv)));
            pWriter.println(uuidr);
            pWriter.println(name);
            pWriter.println(klasse);
            pWriter.println(moneyr);
            pWriter.println(levelr);
            pWriter.println(xprr);
        }catch (IOException e){
            System.out.println("Fehler");
            p.sendMessage("Fehler");
        }
    }
}
