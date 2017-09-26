package main.Quest.Types;

public class KillingQuest extends Quest{

    public KillingQuest(int questID,int levelRequirement,String questName){
        super(questID,levelRequirement,questName);
        questType = QuestTypes.Killing;
    }

}
