package main.BanManager;

import main.MySQL.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by maxim on 26.09.2017.
 */
public class BanManager implements CommandExecutor, Listener{
    Plugin pl;

    public BanManager(Plugin pl) {
        this.pl = pl;
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("ban")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (args.length >= 2) {
                    boolean isBanned=isBanned(getPlayerUUID(args[0]));
                    if(isBanned==false) {
                        String grund = "";
                        for (int i = 1; i < args.length; i++) {
                            grund += args[i] + " ";
                        }
                        banPlayer(getPlayerUUID(args[0]), grund);
                        p.sendMessage("Spieler wurde gebannt!");
                        for(Player pOnline: Bukkit.getServer().getOnlinePlayers()) {
                            if(pOnline.getName().equalsIgnoreCase(args[0])) {
                                pOnline.kickPlayer("You are banned from this server! \n " + grund);
                            }
                        }
                    } else {
                        p.sendMessage("Spieler wurde bereits gebannt!");
                    }
                }
            }
        }
        if(command.getName().equalsIgnoreCase("unban")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (args.length == 1) {
                    unbanPlayer(getPlayerUUID(args[0]));
                    p.sendMessage("Spieler entbannt!");
                }
            }
        }
        if(command.getName().equalsIgnoreCase("tempban")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(args.length >= 3) {
                    boolean isBanned=isBanned(getPlayerUUID(args[0]));
                    if(isBanned == false) {
                        String grund="";
                        int period = -1;
                        try {
                            period=Integer.parseInt(args[1]);
                        } catch (Exception e) {}
                        if(period > 0) {
                            for (int i = 2; i < args.length; i++) {
                                grund += args[i] + " ";
                            }
                            Date date = new Date();
                            tempbanPlayer(getPlayerUUID(args[0]), (date.getTime()+(period*1000)), grund);
                            p.sendMessage("Spieler wurde gebannt!");
                            for(Player pOnline: Bukkit.getServer().getOnlinePlayers()) {
                                if(pOnline.getName().equalsIgnoreCase(args[0])) {
                                    pOnline.kickPlayer("You are banned from this server! \n " + grund);
                                }
                            }
                        } else {
                            p.sendMessage("Spieler nicht gebannt (/tempban <Playername> <Period sec> <Reason>)!");
                        }
                    } else {
                        p.sendMessage("Spieler wurde bereits gebannt!");
                    }
                }
            }
        }
        return false;
    }


    public static void banPlayer(String uuid, String grund) {
        MySQL.update("INSERT INTO BanManager (UUID, Period, Grund) VALUES ('"+uuid+"','"+"-1"+"','"+grund+"')");
    }

    public static void tempbanPlayer(String uuid, long period, String grund) {
        MySQL.update("INSERT INTO BanManager (UUID, Period, Grund) VALUES ('"+uuid+"','"+period+"','"+grund+"')");
    }

    public static void unbanPlayer(String uuid) {
        MySQL.update("DELETE FROM BanManager WHERE UUID='"+uuid+"'");
    }

    public boolean isBanned(String uuid) {
        ResultSet rs = MySQL.getResultSet("SELECT * FROM BanManager");
        try {
            while(rs.next()) {
                if(rs.getString("UUID").equalsIgnoreCase(uuid)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPlayerUUID(String name) {
        return Bukkit.getServer().getOfflinePlayer(name).getUniqueId().toString();
    }

    @EventHandler
    public void PlayerLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if(isBanned(getPlayerUUID(p.getName()))) {
            ResultSet rs = MySQL.getResultSet("SELECT * FROM BanManager");
            try {
                while(rs.next()) {
                    if(rs.getString("UUID").equalsIgnoreCase(getPlayerUUID(p.getName()))) {
                        if(rs.getString("Period").equalsIgnoreCase("-1")) {
                            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "PERMANENT BAN! \nReason: "+rs.getString("Grund")+"\nFor more information visit (Websitedomain)");
                            break;
                        }
                        Date date = new Date();
                        if(date.getTime() < Long.parseLong(rs.getString("Period"))) {
                            long timeLeft=Long.parseLong(rs.getString("Period"))-date.getTime();
                            timeLeft=timeLeft/1000;
                            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "LIMITED BAN! \nReason: "+rs.getString("Grund")+"\nTime left: " + timeLeft);
                            break;
                        }

                        break;
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @EventHandler
    public void PlayerJoinListener(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(isBanned(getPlayerUUID(p.getName()))) {
            ResultSet rs = MySQL.getResultSet("SELECT * FROM BanManager");
            try {
                while(rs.next()) {
                    if(rs.getString("UUID").equalsIgnoreCase(getPlayerUUID(p.getName()))) {
                        MySQL.update("DELETE FROM BanManager WHERE UUID='"+getPlayerUUID(p.getName())+"'");
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
