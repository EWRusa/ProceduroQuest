public class CharacterManager extends EntityManager {
    protected int currXP, currLevel, nextLevelXP;
    /**
     * Inventory functions as follows: limited inventory slots (4 for now, change later?)
     * row0: Item names eg. Spear, Flail,Fire Spell
     * row1: Item rolls eg. D8, D10, D6,
     */
    protected Object[][] inventory = new Object[2][4];
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
        setInventory(playerClass);
        setArmorClass(playerClass);
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
     */

    private void setInventory(EntityClass playerClass) {
        switch (playerClass) {
            case BARBARIAN:
                inventory[0][0] = NormalWeapon.Axe;
                inventory[1][0] = Dice.D12;
                inventory[0][1] = NormalWeapon.Club;
                inventory[1][1] = Dice.D8;
                break;
            case FIGHTER:
                inventory[0][0] = NormalWeapon.Longsword;
                inventory[1][0] = Dice.D10;
                inventory[0][1] = NormalWeapon.Dagger;
                inventory[1][1] = Dice.D6;
                break;
            case ROGUE:
                inventory[0][0] = NormalWeapon.Rapier;
                inventory[1][0] = Dice.D10;
                inventory[0][1] = NormalWeapon.Dagger;
                inventory[1][1] = Dice.D6;
                break;
            case DRUID:
                inventory[0][0] = NormalWeapon.Shortsword;
                inventory[1][0] = Dice.D8;
                inventory[0][1] = MagicWeapon.Vine;
                inventory[1][1] = Dice.D8;
                inventory[0][2] = MagicWeapon.Splinter;
                inventory[1][2] = Dice.D10;
                break;
            case WARLOCK:
                inventory[0][0] = NormalWeapon.Shortsword;
                inventory[1][0] = Dice.D8;
                inventory[0][1] = MagicWeapon.Fireball;
                inventory[1][1] = Dice.D10;
                inventory[0][2] = MagicWeapon.Plague;
                inventory[1][2] = Dice.D10;
                break;
        }
    }

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
}
enum NormalWeapon {
    Club, Longsword, Axe, Dagger, Rapier, Shortsword
}
enum MagicWeapon {
    Fireball, Vine, Plague, Splinter
}
