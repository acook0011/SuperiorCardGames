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
    public static String[] names;
    public static int[] money;
    public static boolean[] ingame;
    public static Scanner reader = new Scanner(System.in);
    
    public static void main(String [] args){
        System.out.println("How many players will be in the casino tonight?");
        players = reader.nextInt();
        money = new int[players];
        for (int i = 0; i < money.length; i++){
            money[i] = 10000;
        }
        names = new String[players];
        System.out.println("Do you want to give your names? [Y/N]"); reader.nextLine();
        String reply = reader.nextLine();
        if (reply.toUpperCase().equals("Y") || reply.toUpperCase().equals("YES")){
            int count = 0;
            for (int i = 0; i < players; i++){
                count++;
                System.out.println("What is Player " + count + "'s name?");
                names[i] = reader.nextLine();
            }
        } else if (reply.toUpperCase().equals("N") || reply.toUpperCase().equals("NO")){
            
        } else {
            System.out.println("Respond with Y or N.");
        }
        ingame = new boolean[players];
        for (int i = 0; i < ingame.length; i++){
            ingame[i] = false;
        }
        
        menu();
    }
    
    public static void menu(){
        System.out.println("Everyone starts with $10,000.\n" + 
                           "What game would you like to play?\n" +
                           "1) Blackjack\n" + 
                           "2) more later :)");
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
            chooseMulti();
            Blackjack.Jack();
        } else {
            System.out.println("! !CHOICE ERROR! !\n! !PASSED RANGE CHECK! !");
        }
    }
    
    public static void chooseMulti(){
        System.out.println("Will everyone be playing?");
        Boolean valid = false;
        while (!valid){
            String reply = reader.nextLine();
            if (reply.toUpperCase().equals("Y") || reply.toUpperCase().equals("YES")){
                for (int i = 0; i < ingame.length; i++){
                    ingame[i] = true;
                }
                valid = true;
            } else if (reply.toUpperCase().equals("N") || reply.toUpperCase().equals("NO")){
                for (int i = 0; i < ingame.length; i++){
                    valid = false;
                    int count = i+1;
                    System.out.println("Will Player " + count + " be playing? [Y/N]");
                    while (!valid){
                        reply = reader.nextLine();
                        if (reply.toUpperCase().equals("Y") || reply.toUpperCase().equals("YES")){
                            ingame[i] = true;
                            valid = true;
                        } else if (reply.toUpperCase().equals("N") || reply.toUpperCase().equals("NO")){
                            ingame[i] = false;
                            valid = true;
                        } else {
                            System.out.println("Respond with Y or N.");
                        }
                    }
                }
                valid = true;
            } else {
                System.out.println("Respond with Y or N.");
            }
        }
        
        
    }
}
