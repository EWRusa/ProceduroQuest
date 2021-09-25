public class BossManager extends MobManager{

    public BossManager(int level, String name){
        super.name = name;
        setBossClass(name);
        setInventory(entityClass);
        setArmorClass(entityClass);
        super.entityCurrHP = 0;
        for (int i = 0; i < level; i++) {
            super.entityCurrHP += DiceRoll.healthRoll(entityClass); //adds health based on player level & mob class
        }
        super.entityCurrHP = (int)(super.entityCurrHP * 2.5);
    }

    private void setBossClass(String name){
        switch (name) {
            case "MegaOrc":
                super.entityClass = EntityClass.BARBARIAN;
                break;
            case "Snakeman":
                super.entityClass = EntityClass.FIGHTER;
                break;
            case "Mage":
                super.entityClass = EntityClass.WARLOCK;
                break;
            case "Brandon":
                super.entityClass = EntityClass.ROGUE;
                break;
            default:
                super.entityClass = EntityClass.DRUID;
                break;
        }
    }
}
