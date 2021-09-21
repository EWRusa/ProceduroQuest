import java.util.Random;

public class DiceRoll {
    static Random random = new Random();
    public static int roll (Dice dice){
        int result;

        switch (dice) {
            case D2:
                result = random.nextInt(2) + 1;
                break;
            case D4:
                result = random.nextInt(4) + 1;
                break;
            case D6:
                result = random.nextInt(6) + 1;
                break;
            case D8:
                result = random.nextInt(8) + 1;
                break;
            case D10:
                result = random.nextInt(10) + 1;
                break;
            case D12:
                result = random.nextInt(12) + 1;
                break;
            case D20:
                result = random.nextInt(20) + 1;
                break;
            default:
                result = 1;
        }


        return result;
    }
    //method for determining bonus rolls based on a D20
    public static int bonusRoll(){
        int roll = random.nextInt(20) + 1;
        if (roll == 1) return -5;
        else if (roll <= 3) return -4;
        else if (roll <= 5) return -3;
        else if (roll <= 7) return -2;
        else if (roll <= 9) return -1;
        else if (roll <= 11) return 0;
        else if (roll <= 13) return 1;
        else if (roll <= 15) return 2;
        else if (roll <= 17) return 3;
        else if (roll <= 19) return 4;
        else if (roll <= 21) return 5;
        else return 0;
    }

    public static int healthRoll(CharacterClass player){
        //TODO return player health based on player class
        return 0;
    }

    public static void main(String[] args){
        for (int i = 0; i < 20; i++) {
            System.out.println(roll(Dice.D20));
        }
    }


}
