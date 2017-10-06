package main.News;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by maxim on 29.09.2017.
 */
public class News{
    protected int time;
    protected String news;
    protected int id;

    public News(String news, int time, Plugin pl) {
        this.news=news;
        this.time=time;
        this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.sendMessage(ChatColor.GOLD+"[NewsManager] " + ChatColor.RED+news);
                }
            }
        },20 , 20*time*60);
    }

    public void stopNews() {
        Bukkit.getScheduler().cancelTask(id);
    }
}
