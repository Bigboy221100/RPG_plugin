package main.Char.Klassen;



import java.util.UUID;

/**
 * Created by Fabian on 19.07.2017.
 */
public class CharPlayer{
    protected UUID player;
    protected String name;
    protected String klasse;
    protected int level;
    protected int xp;

    public CharPlayer(UUID player, String name, String klasse){
        this.player=player;
        this.name=name;
        this.level=1;
        this.xp=0;
        this.klasse=klasse;
    }

    public CharPlayer() {
    }


    public String getName() {
        return name;
    }

    public String getKlasse() {
        return klasse;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public UUID getPlayer() {
        return player;
    }
}
