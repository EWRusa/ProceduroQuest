import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
        System.out.print("You come across a new encounter! Up ahead you see ");
        if (fightableMobs.size() > 1) {
            for (int i = 0; i < fightableMobs.size() - 1; i++) {
                System.out.print("a " + fightableMobs.get(i).getName());
                if (fightableMobs.size() > 2) {
                    System.out.print(", ");
                }
            }
            if (fightableMobs.size() > 2) System.out.println("and a " + fightableMobs.get(fightableMobs.size() - 1).getName() + "!");
            else System.out.println(" and a " + fightableMobs.get(fightableMobs.size() - 1).getName() + "!");
        } else {
            System.out.println("a " + fightableMobs.get(0).getName() + "!");
        }
        encounterEngine();
    }

    private void bossEncounter(){

        int nameCount = DiceRoll.roll(Dice.D4);
        String name;
        switch (nameCount) {
            case 1:
                name = String.valueOf(bossName.MEGAORC);
                break;
            case 2:
                name = String.valueOf(bossName.SNAKEMAN);
                break;
            case 3:
                name = String.valueOf(bossName.MAGE);
                break;
            case 4:
            default:
                name = String.valueOf(bossName.BRANDON);
                break;
        }
        fightableMobs.add(new BossManager(playerCharacter.getCurrLevel(), name));
        System.out.printf("BOSS BATTLE! UP AHEAD YOU SEE A %s\n", fightableMobs.get(0).getName());
        encounterEngine();
    }

    private void encounterEngine(){
        if (!fightableMobs.isEmpty()) {
            int choice = playerChoice();
            if (choice == 1) {
                playerFight();
            } else {
                playerHeal();
            }

            //create loop to call enemy attack turn (I'm not making them heal that's dumb)
            for (int i = 0; i < fightableMobs.size(); i++) {
                monsterFight(i);
            }
            //LAST LINE CALLS UNTIL ALL ENEMIES ARE DEAD
            encounterEngine();
        } else {
            //Starts again, keeping program going until player dies
            newEncounter();
        }
    }

    private int playerChoice() {
        Scanner scnr = new Scanner(System.in);
        int i;
        try {
            System.out.print("What would you like to do?\n" +
                    "1: FIGHT\n" +
                    "2: HEAL\n");
            i = scnr.nextInt();
            if (i != 1 && i != 2) {
                System.out.println("Please enter a valid input (1 or 2)");
                i = playerChoice();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid input (i.e. 1,2)");
            i = playerChoice();
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid input (i.e. 1,2)");
            i = playerChoice();
        }
        return i;
    }
    //method for player to damage a mob(defaults to mob 0)
    private void playerFight() {
        Scanner scnr = new Scanner(System.in);
        try {
            System.out.println("Choose which weapon you wish to use:");
            int j, i;
            for (j = 0; j < playerCharacter.inventory.size(); j++) {
                System.out.printf("%d: %s\n", j + 1, playerCharacter.inventory.get(j));
            }
            i = scnr.nextInt();
            if (i < j + 1 && i > 0) {
                int toHit = DiceRoll.roll(Dice.D20);
                int hitDamage = DiceRoll.roll(playerCharacter.inventoryDie.get(i - 1));
                if (toHit >= fightableMobs.get(0).getArmorClass()) {
                    System.out.printf("You hit the %s with your %s for %d damage! %d enemies remain.\n", fightableMobs.get(0).getName(),
                            playerCharacter.inventory.get(i-1), hitDamage, fightableMobs.size());
                    fightableMobs.get(0).setEntityCurrHP(hitDamage * -1);
                    if (fightableMobs.get(0).isDead()) {
                        String bossCheck = fightableMobs.get(0).getName();
                        System.out.printf("You killed the %s! %d enemies remain.\n", fightableMobs.remove(0).getName(),
                                fightableMobs.size());
                        switch (bossCheck) {
                            case "MEGAORC":
                            case "SNAKEMAN":
                            case "MAGE":
                            case "BRANDON":
                                playerCharacter.addXP(20);
                                break;
                            default:
                                playerCharacter.addXP(DiceRoll.roll(Dice.D8));
                                break;
                        }
                    }
                } else System.out.printf("You missed! %d enemies remain\n", fightableMobs.size());

            } else {
                playerFight();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Please input a valid number.");
            playerFight();
        } catch (InputMismatchException ex) {
            System.out.println("Please input a valid number.");
            playerFight();
        }
    }
    //method to heal player if the player chooses to do so
    private void playerHeal() {
        int hpBefore = playerCharacter.getEntityCurrHP();
        int hpChange = DiceRoll.roll(Dice.D10);
        playerCharacter.setEntityCurrHP(hpChange);
        int hpAfter = playerCharacter.getEntityCurrHP();
        System.out.printf("You healed for %d hit points! Putting you at %d hit points!\n", hpAfter - hpBefore, hpAfter);
    }

    //method for monster to attack player
    private void monsterFight(int monster) {
        Random monsterRoll = new Random();
        int itemSelection = monsterRoll.nextInt(fightableMobs.get(monster).inventory.size());
        int toHit = DiceRoll.roll(Dice.D20);
        int hitDamage = DiceRoll.roll(fightableMobs.get(monster).inventoryDie.get(itemSelection));
        if (toHit >= playerCharacter.getArmorClass()) {
            System.out.printf("%s hit you with their %s for %d damage!\n", fightableMobs.get(monster).getName(),
                    fightableMobs.get(monster).inventory.get(itemSelection), hitDamage);
            playerCharacter.setEntityCurrHP(hitDamage * -1);
            System.out.printf("%d hit points left!\n", playerCharacter.getEntityCurrHP());
        } else System.out.printf("%s missed! %d hit points left!\n", fightableMobs.get(monster).getName(),
                playerCharacter.getEntityCurrHP());
    }
    /*public static void main(String[] args){
        CombatEngine testCombat = new CombatEngine("testo", EntityClass.BARBARIAN);
    }*/
}
enum mobName {
    Goblin, Skeleton, Orc, Human
}
enum bossName {
    MEGAORC, SNAKEMAN, MAGE, BRANDON
}
