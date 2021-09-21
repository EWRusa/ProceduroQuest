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

    boolean isCharacter = false;
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
        //TODO set player health to be a random number based on class
        // use healthRoll method in DiceRoll.java
        super.entityMaxHP = entityCurrHP;
        super.playerClass = playerClass;

        super.name = name;
    }
}

class MobCreator extends EntityCreator {

}