public class EntityManager {
    //class,name,currHp,maxHp,strength,dexterity,constitution,intelligence,charisma,currXP,nextLevelXP,
    //and current level
    protected String name;
    protected EntityClass entityClass;
    protected int entityCurrHP, entityMaxHP, strength, dexterity, constitution, intelligence, charisma;
    /**
     * Initializes an entity of any type (player, mob) and stores its values
     * Character is a child class that behaves slightly differently with the ability to level up (XP)
     * Mob is a child class that rolls health stats based on the level of the player character
     * DO NOT CALL ENTITY MANAGER ON ITS OWN, ONLY CHILD CLASSES NEED TO BE CALLED
     **/

    public EntityManager(){
        strength = DiceRoll.bonusRoll();
        dexterity = DiceRoll.bonusRoll();
        constitution = DiceRoll.bonusRoll();
        intelligence = DiceRoll.bonusRoll();
        charisma = DiceRoll.bonusRoll();
    }

    // Getters and setters for character stats to be used during combat checks (only setter is for HP, bonuses never
    // changed post construction

    public int getEntityCurrHP() {
        return entityCurrHP;
    }
    public void setEntityCurrHP(int HPtoChange) {
        entityCurrHP += HPtoChange;
    }
    public int getStrength() { return strength;}
    public int getDexterity() { return dexterity;}
    public int getConstitution() { return constitution;}
    public int getIntelligence() { return intelligence;}
    public int getCharisma() { return charisma;}
    public EntityClass getEntityClass() { return entityClass; }
    public String getName() {return name;}
    public boolean isDead() {
        if (entityCurrHP <= 0) return true;
        else return false;
    }
}

