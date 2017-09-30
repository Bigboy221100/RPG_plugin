package main.Quest.Types;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Quest {
    protected int questID;
    protected int levelRequirement;
    protected String questName;
    protected String description;
    protected HashMap<Integer,String> missionTargets;
    protected String reward;

    public enum QuestTypes{
        Killing,
        Collection,
        Escort,
        Delivering,
        Assasination
    }

    protected QuestTypes questType;

    Quest (int questID,int levelRequirement,String questName){
        this.questID = questID;
        this.levelRequirement = levelRequirement;
        this.questName = questName;
        description = "";
        missionTargets = new HashMap<>();
        reward = "Einen Keks"; // :P
    }

    public Quest(int questID, int levelRequirement, String questName, String description, HashMap<Integer, String> missionTargets, String reward, QuestTypes questType) {
        this.questID = questID;
        this.levelRequirement = levelRequirement;
        this.questName = questName;
        this.description = description;
        this.missionTargets = missionTargets;
        this.reward = reward;
        this.questType = questType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMissionTarget(int index,String description){
        missionTargets.put(index,description);
    }

    public void editMissionTarget(int index,String description) { missionTargets.replace(index,description);}

    public void deleteMissionTarget(int index) { missionTargets.remove(index);}

    public void writeToPlayer(Player p){
        p.sendMessage(questName + " ID: " + questID);
        p.sendMessage(description);
        p.sendMessage(missionTargets.toString());
        p.sendMessage(reward);
    }

    public void setReward(String reward){ this.reward = reward;}


    @Override
    public String toString() {
        return questName + " ID: " + questID + " Type: " + questType.toString();
    }

    public int getQuestID() {
        return questID;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public String getQuestName() {
        return questName;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<Integer, String> getMissionTargets() {
        return missionTargets;
    }

    public String getReward() {
        return reward;
    }

    public QuestTypes getQuestType() {
        return questType;
    }

}
