import java.util.Random;

public class MobManager extends EntityManager {
    //Constructors for generating a random mob
    public MobManager() {
        this(1, "Skeleton");
    }
    public MobManager(int level, String name) {
        super(name);
        super.entityCurrHP = 0;
        setMobClass();
        setArmorClass(entityClass);
        for (int i = 0; i < level; i++) {
            super.entityCurrHP += DiceRoll.healthRoll(entityClass); //adds health based on player level & mob class
        }
        super.entityMaxHP = super.entityCurrHP;

    }

    //randomly chooses mob class based on a randomly selected integer
    private void setMobClass() {
        Random random = new Random();
        int classSelector = random.nextInt(5);

        switch (classSelector) {
            case 0:
                entityClass = EntityClass.BARBARIAN;
                break;
            case 1:
                entityClass = EntityClass.FIGHTER;
                break;
            case 2:
                entityClass = EntityClass.ROGUE;
                break;
            case 3:
                entityClass = EntityClass.DRUID;
                break;
            case 4:
            default:
                entityClass = EntityClass.WARLOCK;
                break;
        }
    }

    //Sets mob armor class based on entity class (lower than respective player armor class)
    protected void setArmorClass(EntityClass playerClass){
        switch (playerClass) {
            case BARBARIAN:
                armorClass = 9;
                break;
            case FIGHTER:
                armorClass = 7;
                break;
            case ROGUE:
                armorClass = 11;
                break;
            case DRUID:
                armorClass = 5;
                break;
            case WARLOCK:
                armorClass = 6;
                break;
        }
    }
}
