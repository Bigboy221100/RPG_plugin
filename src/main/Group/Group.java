package main.Group;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 01.10.2017.
 */
public class Group {
    public List<Player>groupPlayers=new ArrayList<Player>();
    private Objective obj;
    private int groupId;
    private Plugin pl;

    public Group(int groupId, Player owner, Plugin pl) {
        this.groupId=groupId;
        this.pl=pl;
        this.groupPlayers.add(owner);
        setScoreboard();
    }

    public void addPlayer(Player p) {
        groupPlayers.add(p);
        updateScoreboard();
    }

    public void invitePlayer(Player p) {
        Groups.groupInvites.put(p, this);
    }

    public void leaveGroup(Player p){
        groupPlayers.remove(p);
        p.getServer().getScoreboardManager().getNewScoreboard();
        updateScoreboard();
    }

    public void setScoreboard() {
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

        obj = board.registerNewObjective("aaa"+groupId,"bbb");
        obj.setDisplayName(ChatColor.GREEN + "Group: ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score scorePlayer = obj.getScore("Group-Owner:" + groupPlayers.get(0).getName());
        scorePlayer.setScore(0);

        groupPlayers.get(0).setScoreboard(board);
    }

    public void updateScoreboard() {
        Scoreboard board = obj.getScoreboard();
        Score scorePlayer2 = obj.getScore("Player:" + groupPlayers.get(groupPlayers.size()-1).getName());
        scorePlayer2.setScore(groupPlayers.size()-1);
        for(Player players: groupPlayers) {
            players.setScoreboard(board);
        }
    }
}
