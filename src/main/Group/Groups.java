package main.Group;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maxim on 01.10.2017.
 */
public class Groups implements CommandExecutor{
    private Plugin pl;
    public static List<Group> groups = new ArrayList<Group>();
    public static Map<Player, Group> groupInvites = new HashMap<Player, Group>();
    private int groupsId=0;

    public Groups(Plugin pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player p = (Player)commandSender;
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("invite")) {
                    if(!isInGroup(p)) {
                        createGroup(p);
                        p.sendMessage("Group created" + groups.size());
                        invitePlayer(p, args[1]);
                    } else if(isOwner(p)){
                        p.sendMessage("You are owner");
                        invitePlayer(p, args[1]);
                    } else {
                        p.sendMessage("You can't invite " + args[1]);
                    }
                }
            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("accept")) {
                    acceptGroup(p);
                }
            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("decline")) {
                    p.sendMessage("Group invited declined!");
                    groupInvites.remove(p);
                }
            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("leave")) {
                    if(isInGroup(p)) {
                        int buffer = getGroup(p);
                        groups.get(buffer).leaveGroup(p);
                        if(groups.get(buffer).groupPlayers.size()==0) {
                            groups.remove(buffer);
                        }
                    } else {
                        p.sendMessage("You are not in a group");
                    }
                }
            }
        }
        return false;
    }


    public void acceptGroup(Player p) {
        groupInvites.get(p).addPlayer(p);
        groupInvites.remove(p);
        p.sendMessage("Group invited accepted!");
    }

    public void invitePlayer(Player p, String playerToInvite) {
        for (Player onlineplayers : Bukkit.getServer().getOnlinePlayers()) {
            if (onlineplayers.getName().equalsIgnoreCase(playerToInvite)) {
                if(!isInGroup(onlineplayers)) {
                    onlineplayers.sendMessage(p.getName() + " invited you to a group!");
                    p.sendMessage("You invited " + playerToInvite + " to your group!");
                    p.sendMessage(getGroup(p)+"");
                    groups.get(getGroup(p)).invitePlayer(onlineplayers);
                    p.sendMessage(groupInvites.size()+"");
                    return;
                } else {
                    p.sendMessage("User is in a group");
                    return;
                }
            }
        }
        p.sendMessage("Player not online!");
    }

    public boolean isInGroup(Player p) {
        for(Group g: groups) {
            for(Player playersInGroup: g.groupPlayers) {
                if(playersInGroup.getName().equalsIgnoreCase(p.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isOwner(Player p) {
        for(Group g: groups) {
            if(g.groupPlayers.get(0).getName().equalsIgnoreCase(p.getName())) {
                return true;
            }
        }
        return false;
    }
    public void createGroup(Player owner) {
        groups.add(new Group(groupsId, owner, pl));
        groupsId++;
    }
    public int getGroup(Player p) {
        for(int i=0; i<groups.size(); i++) {
            for (Player groupPlayers: groups.get(i).groupPlayers) {
                if(p.getName().equalsIgnoreCase(groupPlayers.getName())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
