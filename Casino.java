import java.util.*;

/**
 * Eventually, the collective player of all the games.
 *
 * @aj
 * @casino
 */

public class Casino
{
    public static Scanner reader = new Scanner(System.in);
    // Universal
    public static ArrayList<Player> guests;   // All registered players
    public static ArrayList<Player> playing;  // Players who will play next game.
    public static int players;
    public static String[] names;
    public static int[] money;
    public static boolean[] ingame;
    
    // Specific Players
    
    public static void main(String [] args){
        guests = new ArrayList<Player>();
        
        System.out.println("How many players will be in the casino tonight?");
        players = reader.nextInt();
        System.out.println("Do you want to give your names? [Y/N]"); reader.nextLine();
        if (choice()){
            int count = 0;
            for (int i = 0; i < players; i++){
                count ++;
                System.out.println("What is Player " + count + "'s name?");
                String name = reader.nextLine();
                guests.add(new Player(count, name));
                System.out.println(guests.get(i).getName() + ", you are Player " + guests.get(i).getPlayer() + ".");
            }
        } else {
            int count = 0;
            for (int i = 0; i < players; i++){
                count++;
                guests.add(new Player(count));
                System.out.println(guests.get(i).getName() + ", you are Player " + guests.get(i).getPlayer() + ".");
            }
        }
        
        menu();
    }
    
    public static void menu(){
        playing = new ArrayList<Player>();
        System.out.println("Everyone starts with $1,000.\n" + 
                           "What game would you like to play?\n" +
                           "1) Blackjack\n" + 
                           "2) Mao\n" +
                           "3) more later :)");
        final int OPTS = 2; // Amount of choices
        int choice = -1;
        Boolean valid = false;
        while (!valid){
            choice = reader.nextInt();
            if (choice > OPTS || choice < 1){
                System.out.println("Please choosen within the range: [1-" + OPTS + "]");
            } else{
                valid = true;
            }
        }
        if (choice == 1){
            chooseMulti();
            Blackjack.Jack();
        } else if(choice == 2){
            chooseMulti();
            Mao.playMao();
        } else {
            System.out.println("! !CHOICE ERROR! !\n! !PASSED RANGE CHECK! !");
        }
        
    }
    
    public static void chooseMulti(){
        System.out.println("Will everyone be playing?"); reader.nextLine();
        if (choice()){
            for (int i = 0; i < players; i++){
                guests.get(i).nowIG();
                playing.add(guests.get(i));
            }
        } else {
            for (int i = 0; i < players; i++){
                System.out.println("Will " + guests.get(i).getName() + " be playing? [Y/N]");
                if (choice()){
                    guests.get(i).nowIG();
                    playing.add(guests.get(i));
                }
            }
        }
    }
    
    public static boolean choice(){
        Boolean valid = false;
        while (!valid){ 
            String reply = reader.nextLine();
            if (reply.toUpperCase().equals("Y") || reply.toUpperCase().equals("YES")){
                return true;
            } else if (reply.toUpperCase().equals("N") || reply.toUpperCase().equals("NO")){
                return false;
            } else {
                System.out.println("Respond with Y or N.");
            }
        }
        return false;
    }
}
