import java.util.ArrayList;

public class CombatEngine {
    ArrayList<MobManager> fightableMobs = new ArrayList<>();
    public CombatEngine() {
        //initialize character entity here for health checking
    }

    public void combatEncounter() {

    }
    public void mobEncounter() {

    }
    public void bossEncounter(){

    }
    /**
     * Create methods to start new combat encounters, check entity healths, check hits and damage
     *
     **/
}
enum mobName {
    Goblin, Skeleton, Orc, Bandit
}
enum bossName {
    MegaOrc, Basilisk, Mage
}
