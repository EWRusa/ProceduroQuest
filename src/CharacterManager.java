public class CharacterManager extends EntityManager {
    protected int currXP, currLevel, nextLevelXP;
    /**
     * constructor for character manager, allows for player selected name and class, also holds level system
     * for character only, calls character level for spawning new mobs with that are balanced in health compared to
     * a player
     */

    public CharacterManager(String name, EntityClass playerClass) {
        super(name);
        currLevel = 1;
        currXP = 0;
        nextLevelXP = 10;
        super.entityMaxHP = entityCurrHP;
        super.entityClass = playerClass;
        setInventory(entityClass);
        setArmorClass(entityClass);
        super.entityCurrHP = DiceRoll.healthRoll(entityClass);
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
    //Player level getter in order to get health rolls for monsters
    public int getCurrLevel() { return currLevel; }

    /**
     * setup method for player inventory (passes player class to specify weapons)
     * sets player weapons and dice damage values
     */
    //sets armor class of player based on player class

    private void setArmorClass(EntityClass playerClass){
        switch (playerClass) {
            case BARBARIAN:
                armorClass = 13;
                break;
            case FIGHTER:
                armorClass = 11;
                break;
            case ROGUE:
                armorClass = 16;
                break;
            case DRUID:
                armorClass = 8;
                break;
            case WARLOCK:
                armorClass = 9;
                break;
        }
    }

    //Player specific death checker, outputs that player is dead if player has died and ends the program
    @Override
    public boolean isDead() {
        if (entityCurrHP <= 0) {
            System.out.println("You have died.");
            System.exit(1);
        }
        return false;
    }

    @Override
    public void setEntityCurrHP(int HPtoChange) {
        //prevents health change (if positive) from going over maximum health
        if (HPtoChange + getEntityCurrHP() > entityMaxHP) {
            int overflowPrevention = (getEntityCurrHP() + HPtoChange) - entityMaxHP;
            HPtoChange = HPtoChange- overflowPrevention;
        }
        entityCurrHP += HPtoChange;
        isDead();
    }

    public static void main(String[] args){
        CharacterManager player = new CharacterManager("test", EntityClass.BARBARIAN);
        System.out.println(player.getName() + ":" + player.getClass());
        System.out.println(player.getEntityCurrHP());
        player.setEntityCurrHP(player.getEntityCurrHP());
        System.out.println(player.getEntityCurrHP());
        player.setEntityCurrHP(player.getEntityCurrHP() * -1);
    }
}