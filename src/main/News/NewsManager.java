package main.News;

import main.MySQL.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 29.09.2017.
 */
public class NewsManager implements CommandExecutor{
    private Plugin pl;
    public static List<News>newsManager=new ArrayList<News>();

    public NewsManager(Plugin pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player)commandSender;
            if(args.length >= 3) {
                if(args[0].equalsIgnoreCase("add")) {
                    String news="";
                    try {
                        int time = Integer.parseInt(args[1]);
                        for(int i=2; i<args.length; i++) {
                            news+=args[i]+" ";
                        }
                        if(time >= 1) {
                            newsManager.add(new News(news, time, pl));
                            p.sendMessage("News created!");
                            String id = newsManager.size()-1+"";
                            MySQL.update("INSERT INTO NewsManager (id, newsTime, news) VALUES ('"+id+"','"+time+"','"+news+"')");
                        } else {
                            p.sendMessage("Time format wrong! \n/news add <min> <news>");
                        }
                    } catch (Exception e) {
                        p.sendMessage("Wrong format! \n/news add <min> <news>");
                    }
                }
            }
            if(args.length==1) {
                if(args[0].equalsIgnoreCase("list")) {
                    listNews(p);
                }
            }
            if(args.length >= 2) {
                if(args[0].equalsIgnoreCase("delete")) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if(id>=0 && id<newsManager.size()) {
                            p.sendMessage("[" + id + "] removed from NewsManager");
                            MySQL.update("DELETE FROM NewsManager WHERE id='"+id+"'");
                            newsManager.get(id).stopNews();
                            newsManager.remove(id);
                        } else {
                            p.sendMessage("Id incorrect!");
                        }
                    } catch (Exception e) {
                        p.sendMessage("Wrong format! \n/news delete <id>");
                    }
                }
            }

        }
        return false;
    }

    public void listNews(Player p) {
        for(int i=0; i<=newsManager.size()-1; i++) {
            p.sendMessage("["+i+"]"+newsManager.get(i).news+"|"+newsManager.get(i).time);
        }
    }
}
