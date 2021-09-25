import java.util.ArrayList;

public class CombatEngine {
    ArrayList<MobManager> fightableMobs = new ArrayList<>();
    CharacterManager playerCharacter;
    public CombatEngine(String name, EntityClass playerClass) {
        playerCharacter = new CharacterManager(name, playerClass);

        //call infinite loop of rolling random combat encounters until player dies
        newEncounter();
    }

    /**
     * newEncounter used to initialize a new combat encounter and choose if the player is fighting a mob or boss
     */
    private void newEncounter(){
        int encounterType = DiceRoll.roll(Dice.D20);
        if (encounterType == 20) {
            bossEncounter();
        } else {
            mobEncounter();
        }
    }

    /**
     * mobEncounter generates a random list of 1-4 mobs with random classes and weapons and adds to the list of
     * fightableMobs
     */
    private void mobEncounter(){
        int mobCount = DiceRoll.roll(Dice.D4);
        for (int i = 0; i < mobCount; i++) {
            int nameCount = DiceRoll.roll(Dice.D4);
            String name;
            switch (nameCount){
                case 1:
                    name = String.valueOf(mobName.Goblin);
                    break;
                case 2:
                    name = String.valueOf(mobName.Skeleton);
                    break;
                case 3:
                    name = String.valueOf(mobName.Orc);
                    break;
                case 4:
                default:
                    name = String.valueOf(mobName.Human);
                    break;
            }
            fightableMobs.add(new MobManager(playerCharacter.getCurrLevel(), name));
        }
        encounterEngine();
    }

    private void bossEncounter(){
        int mobCount = DiceRoll.roll(Dice.D4);
        for (int i = 0; i < mobCount; i++) {
            int nameCount = DiceRoll.roll(Dice.D4);
            String name;
            switch (nameCount){
                case 1:
                    name = String.valueOf(bossName.MegaOrc);
                    break;
                case 2:
                    name = String.valueOf(bossName.Snakeman);
                    break;
                case 3:
                    name = String.valueOf(bossName.Mage);
                    break;
                case 4:
                default:
                    name = String.valueOf(bossName.Brandon);
                    break;
            }
            fightableMobs.add(new BossManager(playerCharacter.getCurrLevel(), name));
        }
        encounterEngine();
    }

    private void encounterEngine(){

    }
}
enum mobName {
    Goblin, Skeleton, Orc, Human
}
enum bossName {
    MegaOrc, Snakeman, Mage, Brandon
}
