package main.Quest.Types;
import java.util.HashMap;

public class Quest {
    protected int questID;
    protected int levelRequirement;
    protected String questName;
    protected String description;
    protected HashMap<Integer,String> missionTargets;
    protected String reward;

    Quest (int questID,int levelRequirement,String questName){
        this.questID = questID;
        this.levelRequirement = levelRequirement;
        this.questName = questName;
        description = "";
        missionTargets = new HashMap<>();
        reward = "Einen Dildo"; // :P
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMissionTarget(int index,String description){
        missionTargets.put(index,description);
    }

    public void editMissionTarget(int index,String description) { missionTargets.replace(index,description);}

    @Override
    public String toString() {
        return questName + " ID: " + questID + " LvlRequire: " + levelRequirement + " Reward: " + reward;
    }
}
