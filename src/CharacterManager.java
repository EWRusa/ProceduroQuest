public class CharacterManager extends EntityManager {
    protected int currXP, currLevel, nextLevelXP;
    /**
     * constructor for character manager, allows for player selected name and class, also holds level system
     * for character only, calls character level for spawning new mobs with that are balanced in health compared to
     * a player
     */

    public CharacterManager(String name, EntityClass playerClass) {
        super();
        currLevel = 1;
        currXP = 0;
        nextLevelXP = 10;
        super.entityCurrHP = DiceRoll.healthRoll(playerClass);
        super.entityMaxHP = entityCurrHP;
        super.entityClass = playerClass;
        super.name = name;
    }
    // addXP function called after enemy death, also handles leveling and HP bonuses
    public void addXP (int XPtoAdd) {
        currXP += XPtoAdd;
        if (currXP >= nextLevelXP) {
            nextLevelXP += 10;
            currLevel++;
            super.entityMaxHP += DiceRoll.healthRoll(super.entityClass);
            entityCurrHP = entityMaxHP;
            System.out.println("Level Up! New Level: " + currLevel + " New HP: " + entityCurrHP);
        }
    }

    public int getCurrLevel() { return currLevel; }



    //create checker for if player is dead and exit if so
}
