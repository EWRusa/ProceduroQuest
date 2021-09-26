import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public Main() {
        String[] args = {"restart"};
        main(args);
    }
    //Main used to initialize combat engine with player inputted values and start infinite combat until the player dies
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please input your character name or \"quit\" to quit:");
        String name = scnr.nextLine();
        if (name.equals("quit") || name.equals("\"quit\"")) System.exit(1);
        EntityClass playerClass = selectClass();
        CombatEngine combatEngine = new CombatEngine(name, playerClass);
    }

    private static EntityClass selectClass() {
        int i;
        try {
            Scanner scnr = new Scanner(System.in);
                System.out.print("Select a class:\n" +
                        "1: BARBARIAN\n" +
                        "2: FIGHTER\n" +
                        "3: ROGUE\n" +
                        "4: DRUID\n" +
                        "5: WARLOCK\n");
                i = scnr.nextInt();
        } catch (InputMismatchException ex) {
            return selectClass();
        }
        switch (i) {
            case 1:
                return EntityClass.BARBARIAN;
            case 2:
                return EntityClass.FIGHTER;
            case 3:
                return EntityClass.ROGUE;
            case 4:
                return EntityClass.DRUID;
            case 5:
                return EntityClass.WARLOCK;
            default:
                return selectClass();
        }
    }
}
