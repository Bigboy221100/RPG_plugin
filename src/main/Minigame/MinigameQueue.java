package main.Minigame;

import org.bukkit.entity.Player;

/**
 * Created by maxim on 08.08.2017.
 */
public class MinigameQueue {
    Player p1, p2;
    boolean istAngenommen;
    String arena;

    public MinigameQueue(Player p1, Player p2, String arena) {
        this.p1 = p1;
        this.p2 = p2;
        this.arena = arena;
        istAngenommen = false;
    }

    public void annehmen() {
        istAngenommen = true;
    }
}
