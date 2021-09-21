public class EntityEngine {
    //calls creators, savers, and updaters

    //method to initialize new character with a character name and playerClass
    public void newCharacter(String name, CharacterClass playerClass){
        CharacterCreator playerCharacter = new CharacterCreator(name, playerClass);
    }
}
class EntityCreator {
    //class,name,currHp,maxHp,strength,dexterity,constitution,intelligence,charisma,currXP,nextLevelXP,
    //and current level
    String name;
    CharacterClass playerClass;
    int entityCurrHP, entityMaxHP, strength, dexterity, constitution, intelligence, charisma, currXP,
            currLevel, nextLevelXP;
    //initializes any entity type with a base level and bonus stats, this is not a static method due to multiple
    //objects of mobs

    public EntityCreator(){
        strength = DiceRoll.bonusRoll();
        dexterity = DiceRoll.bonusRoll();
        constitution = DiceRoll.bonusRoll();
        intelligence = DiceRoll.bonusRoll();
        charisma = DiceRoll.bonusRoll();
        currXP = 0;
        currLevel = 1;
        nextLevelXP = 10;
    }
}
class CharacterCreator extends EntityCreator {

    public CharacterCreator(String name, CharacterClass playerClass){
        super();
        super.entityCurrHP = DiceRoll.healthRoll(playerClass);
        super.entityMaxHP = entityCurrHP;
        super.playerClass = playerClass;
        super.name = name;

        //TODO call character saver after creation
        //possibly save in file?
    }
}

class MobCreator extends EntityCreator {

}

class CharacterUpdater {

}