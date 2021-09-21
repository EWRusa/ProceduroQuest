import java.util.Random;

public class MobManager extends EntityManager {
    protected EntityClass mobClass;
    public MobManager() {
        this(1);
    }
    public MobManager(int level) {
        super();
        super.entityCurrHP = 0;
        setMobClass();
        for (int i = 0; i < level; i++) {
            super.entityCurrHP += DiceRoll.healthRoll(mobClass); //adds health based on player level & mob class
        }

    }

    protected void setMobClass() {
        Random random = new Random();
        int classSelector = random.nextInt(5);

        switch (classSelector) {
            case 0:
                mobClass = EntityClass.BARBARIAN;
                break;
            case 1:
                mobClass = EntityClass.FIGHTER;
                break;
            case 2:
                mobClass = EntityClass.ROGUE;
                break;
            case 3:
                mobClass = EntityClass.DRUID;
                break;
            case 4:
            default:
                mobClass = EntityClass.WARLOCK;
                break;
        }
    }
}
