package main.Quest.Types;

public class EscortQuest extends Quest {

    public EscortQuest(int questID,int levelRequirement,String questName){
        super(questID,levelRequirement,questName);
        questType = QuestTypes.Escort;
    }
}
