import java.util.Scanner;

/**
 * Eventually, the collective player of all the games.
 *
 * @aj
 * @casino
 */
public class Casino
{
    public static int players;
    public static int[] money;
    
    public static void main(String [] args){
        Scanner reader = new Scanner(System.in);
        
        System.out.println("How many players will be in the casino tonight?");
        players = reader.nextInt();
        money = new int[players];
        for (int i = 0; i < money.length; i++){
            money[i] = 10000;
        }
        
        System.out.println("Everyone starts with $10,000.\n" + 
                           "What game would you like to play?\n" +
                           "1) Blackjack\n" + 
                           "2) later :)");
        final int OPTS = 1;
        int choice = -1;
        Boolean valid = false;
        while (!valid){
            choice = reader.nextInt();
            if (choice > OPTS || choice < OPTS){
                System.out.println("Please choosen within the range: [1-" + OPTS + "]");
            } else{
                valid = true;
            }
        }
        if (choice == 1){
            Blackjack.Jack();
        } else {
            System.out.println("! !CHOICE ERROR! !\n! !PASSED RANGE CHECK! !");
        }
        
    }
}
