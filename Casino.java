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

    public static ArrayList<Player> guests;   // All registered players
    public static ArrayList<Player> playing;  // Players who will play next game.
    public static int players;                // Count of players
    

    
    public static void main(String [] args)throws Exception{
        guests = new ArrayList<Player>();
        
        System.out.println("How many players will be in the casino tonight?");
        players = reader.nextInt();
        if (players == 1){ // Single Person
            System.out.println("Do you want to give your name? [Y/N]");
        } else { // Multiple People
            System.out.println("Do you want to give your names? [Y/N]");
        }
        reader.nextLine();
        
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
        System.out.println("Everyone starts with $10,000.");
        menu();
    }
    
    public static void menu() throws Exception{
        playing = new ArrayList<Player>();
        System.out.println("What would you like to do?\n" +
                           "1) Blackjack\n" + 
                           "2) Mao\n" +
                           "3) Go Fish\n" +
                           "4) War\n" +
                           "5) Check Score\n" +
                           "6) Quit"); // Keep these two at the bottom of list.
        final int OPTS = 6; // Amount of choices: make sure equals final non-temp choice
        int choice = -1;
        boolean wantsToPlay = true; //
        boolean valid = false;
        while (!valid){
            choice = reader.nextInt();
            if (choice > OPTS || choice < 1){
                System.out.println("Please choosen within the range: [1-" + OPTS + "]");
            } else{
                valid = true;
            }
        }
        
        if (choice == 1){ // Play Blackjack
            chooseMulti();
            Blackjack.Jack();
        } else if(choice == 2){ // Play Mao
            chooseSingle();
            playing.get(0).earn(Mao.playMao());
        } else if (choice == 3){ // Play Go Fish
            chooseSingle();
            System.out.println("How much do you want to bet?");
            double bet = reader.nextInt();
            GoFishGame play = new GoFishGame();
            if (play.getWinnerID() == 0){
                playing.get(0).earn(bet*(play.players.size()-1));
            }
            
        } else if (choice == 4){ // Play War
            
        
        } else if (choice == 5){ // Check Scores
            menuScore();
        } else if (choice == 6){ // Quit Game
            System.out.println("Come gamble your life away with us next time!");
            wantsToPlay = false;
        } else{ // Error Message
            System.out.println("! !CHOICE ERROR! !\n! !PASSED RANGE CHECK! !");
        }
        
        if (wantsToPlay){
            System.out.println("\nDo you want to do something else? [Y/N]");
            if (choice()){ // Recalls menu to make another choice
                int rem = playing.size()-1;
                for (int i = rem; i >= 0; i--){ // Clears 'Playing' ArrayList
                    playing.remove(i);
                }
                for (int i = 0; i < playing.size(); i++){
                    System.out.println("Playing: " + i + " " + playing.get(i).getName());                
                }
                for (int i = 0; i < guests.size(); i++){
                    System.out.println("Guests: " + i + " " + guests.get(i).getName());
                }
                menu();
            } else { // Ends Program
                System.out.println("Come gamble your life away with us next time!");
                wantsToPlay = false;
            }
        }
    }
    
    public static void menuScore(){
        System.out.println("~~~Current Scores~~~");
        for (int i = 0; i < players; i++){
            System.out.println(guests.get(i).getName() + ": $" + guests.get(i).getMoney());
        }
    }
    
    public static void chooseMulti(){
        if (players == 1){ // Doesn't ask if single
            playing.add(guests.get(0));
        } else{
            System.out.println("Will everyone be playing? [Y/N]"); reader.nextLine();
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
    }
    
    public static void chooseSingle(){
        if (players == 1){ // If one person, auto selects
            playing.add(guests.get(0));
        } else{
            System.out.println("Who will be playing?"); reader.nextLine();
            for (int i = 0; i < players; i++){ // Makes list of players
                int x = i + 1;
                System.out.println(x + ") " + guests.get(i).getName());
            }
            boolean valid = false; 
            int choice = -1;
            while (!valid){ //
                choice = reader.nextInt();
                if (choice > players || choice < 1){ // Out of Range
                    System.out.println("Please choosen within the range: [1-" + players + "]");
                } else{ // In Range, break loop
                    valid = true;
                }
            }
            
            playing.add(guests.get(choice-1)); // Adds to 'playing' list
        }
    }
    
    public static boolean choice(){
        // True = Y | False = N
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
