import java.util.ArrayList;

public class EntityManager {
    //class,name,currHp,maxHp,strength,dexterity,constitution,intelligence,charisma,currXP,nextLevelXP,
    //and current level
    protected String name;
    protected EntityClass entityClass;
    protected int entityCurrHP, entityMaxHP, strength, dexterity, constitution, intelligence, charisma, armorClass;
    /**
     * Inventory functions as follows: limited inventory slots (3 for now, change later?)
     * row0: Item names eg. Spear, Flail,Fire Spell
     * row1: Item rolls eg. D8, D10, D6,
     */
    protected ArrayList<Object> inventory = new ArrayList<>();
    protected ArrayList<Object> inventoryDie = new ArrayList<>();
    /**
     * Initializes an entity of any type (player, mob) and stores its values
     * Character is a child class that behaves slightly differently with the ability to level up (XP)
     * Mob is a child class that rolls health stats based on the level of the player character
     * DO NOT CALL ENTITY MANAGER ON ITS OWN, ONLY CHILD CLASSES NEED TO BE CALLED
     **/

    //BONUS CALLS ARE CURRENTLY NOT USED AND WILL BE IMPLEMENTED IN A LATER VERSION
    public EntityManager(String name){
        this.name = name;
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

    protected void setInventory(EntityClass entityClass) {
        switch (entityClass) {
            case BARBARIAN:
                inventory.add(NormalWeapon.Axe);
                inventoryDie.add(Dice.D12);
                inventory.add(NormalWeapon.Club);
                inventoryDie.add(Dice.D8);
                break;
            case FIGHTER:
                inventory.add(NormalWeapon.Longsword);
                inventoryDie.add(Dice.D10);
                inventory.add(NormalWeapon.Dagger);
                inventoryDie.add(Dice.D6);
                break;
            case ROGUE:
                inventory.add(NormalWeapon.Rapier);
                inventoryDie.add(Dice.D10);
                inventory.add(NormalWeapon.Dagger);
                inventoryDie.add(Dice.D6);
                break;
            case DRUID:
                inventory.add(NormalWeapon.Shortsword);
                inventoryDie.add(Dice.D8);
                inventory.add(MagicWeapon.Vine);
                inventoryDie.add(Dice.D8);
                inventory.add(MagicWeapon.Splinter);
                inventoryDie.add(Dice.D10);
                break;
            case WARLOCK:
                inventory.add(NormalWeapon.Shortsword);
                inventoryDie.add(Dice.D8);
                inventory.add(MagicWeapon.Fireball);
                inventoryDie.add(Dice.D10);
                inventory.add(MagicWeapon.Plague);
                inventoryDie.add(Dice.D10);
                break;
        }
    }
}
enum NormalWeapon {
    Club, Longsword, Axe, Dagger, Rapier, Shortsword
}
enum MagicWeapon {
    Fireball, Vine, Plague, Splinter
}

