import java.util.*;

/**
 * Get as close to 21 as possible without going over 21.
 *
 * @aj
 * @blackjack
 * 
 */
public class Blackjack
{
    private static Deck deck;
    private static ArrayList<ArrayList<Card>> p;
    private static String[] names = Casino.names;
    private static int[] score;
    private static int[] cash = Casino.money;
    private static boolean[] bust;
    
    public static void Jack(){
        deck = new Deck();
        p = new ArrayList<ArrayList<Card>>(); // p.get(0) = Dealer
        Scanner reader = new Scanner(System.in);

        deck.shuffle();
        System.out.println("How many will be gambling against The Dealer? ");
        int players = Casino.players;
        score = new int[players+1];
        bust = new boolean[players+1];
        for (int i = 0; i < bust.length; i++){
            bust[i] = false;
        }
        dealHand(p, players);
        System.out.println("\nDealer's visible hand: \n" + 
                           "- " + p.get(0).get(0) + "\n");
        for (int i = 1; i < players+1; i++){
            System.out.println(names[i-1] + "'s Hand: \n" +
                               "- " + p.get(i).get(0) + "\n" +
                               "- " + p.get(i).get(1) + "\n");
        }
        // Calculates Starting Score
        for (int i = 0; i < players+1; i++){
            score[i] = score(p.get(i));
        }
        play(p, score, bust);
        
        if (bust[0]){
            System.out.println("The Dealer busted at " + score[0]);
        } else {
            System.out.println("The Dealer scored " + score[0]);
        }
        
        for (int x = 1; x < players+1; x++){
            if (bust[x]){
                System.out.println(names[x-1] + " busted at " + score[x]);
            } else {
                System.out.println(names[x-1] + " scored " + score[x]);
            }
        }
        
        
        
    }
    
    public static void dealHand(ArrayList<ArrayList<Card>> p, 
                                int players){
        // 0 = The Dealer
        for (int i = 0; i < players+1; i++){
            p.add(new ArrayList<Card>());
        }
        for (int i = 0; i < players+1; i++){
            p.get(i).add(deck.deal());
            p.get(i).add(deck.deal());
        }
    }
    
    public static void play(ArrayList<ArrayList<Card>> p, 
                            int[] score, boolean[] bust){                                                      
        Scanner reader = new Scanner(System.in);
        Boolean[] stay = new Boolean[bust.length];
        for (int i = 0; i < stay.length; i++){
           if ((score[i] == 21) && (i != 0)){ //Checks for Naturals
               System.out.println("!" + names[i-1] + " has a Natural 21 !\n");
               stay[i] = true;
           } else {
               stay[i] = false;
           }
        }
        Boolean playing = true;
        // Players
        while (playing){
         for (int x = 1; x < p.size(); x++){
          
          if (!bust[x] && !stay[x]){
           // Displays current hand
           System.out.println("~~" + names[x-1] + "'s turn~~"); 
           score[x] = score(p.get(x));
           System.out.println(names[x-1] + "'s current hand: ");
           for (int i = 0; i < p.get(x).size(); i++){
              System.out.println("- " + p.get(x).get(i));
           }
           System.out.println("Score: " + score(p.get(x)));
           
           System.out.println("Hit? [Y/N]");
           Boolean valid = false;
           while (!valid){
               String reply = reader.nextLine();
               if (reply.toUpperCase().equals("Y") || reply.toUpperCase().equals("YES")
                   || reply.toUpperCase().equals("HIT")){
                   p.get(x).add(deck.deal());
                   score[x] = score(p.get(x));
                   System.out.println(names[x-1] + " was dealt a " + 
                               p.get(x).get(p.get(x).size()-1) +
                              "\nNew Score: " + score[x]);
                   // Checks if 21
                   if (score[x] == 21){
                       System.out.println(names[x-1] + " reached 21!");
                       stay[x] = true;
                   }
                   // Checks if over 21
                   else if (score[x] > 21){
                       System.out.println("YOU B U S T E D");
                       bust[x] = true;
                   } 
                   valid = true;
                } else if (reply.toUpperCase().equals("N") || reply.toUpperCase().equals("NO")
                           || reply.toUpperCase().equals("STAY")){
                   stay[x] = true;
                   valid = true;
                } else {
                      System.out.println("Respond with Y or N.");
                }
            }
            System.out.println();
           }
         }
         int a = p.size()-1; // Amount of players who have not busted or stayed
         for (int j = 1; j < p.size(); j++){
            if (bust[j] || stay[j]){
                a--;
            }
         }
         if (a == 0){
            playing = false;
         }
       }
       
       // Dealer's Turn
       System.out.println("~~The Dealer's Turn~~");
       while (!bust[0] && !stay[0]){
           score[0] = score(p.get(0));
           System.out.println("The Dealer's current hand: ");
           for (int i = 0; i < p.get(0).size(); i++){
              System.out.println("- " + p.get(0).get(i));
           }
           System.out.println("Score: " + score(p.get(0)));
           
           if (score[0] >= 17){
               System.out.println("The Dealer has to stay.");
               stay[0] = true;
           } else if (score[0] < 17){
               p.get(0).add(deck.deal());
               score[0] = score(p.get(0));
               System.out.println("The Dealer was dealt a " + 
                                   p.get(0).get(p.get(0).size()-1) +
                                  "\nNew Score: " + score[0]);
               if (score[0] > 21){
                   System.out.println("The Dealer has B U S T E D");
                   bust[0] = true;
               }
           }
           System.out.println();
       }
    }
    
    public static int score(ArrayList<Card> hand){
        int score = 0;
        for (int i = 0; i < hand.size(); i++){
            score += value(hand.get(i));
        }
        
        if (score > 21){ // Checks if there are Aces
            int aceCount = 0;
            for (int i = 0; i < hand.size(); i++){
                if (score > 21 && hand.get(i).getRank() == 1){
                    score -= 10;   
                }
            }
        }
        return score;
    } 
    
    public static int value(Card card){
        int value = 0;
        
        if (card.getRank() > 10){
            value = 10;
        } else if (card.getRank() == 1) {
            value = 11;
        } else {
            value = card.getRank();
        }
        return value;
    }
}
