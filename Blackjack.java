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
    private static ArrayList<Card> p1;
    private static ArrayList<Card> comp;
    private static ArrayList<ArrayList<Card>> p;
    private static int[] score;
    private static int[] cash;
    private static boolean[] bust;
    
    public static void Jack(){
        deck = new Deck();
        p = new ArrayList<ArrayList<Card>>(); // p.get(0) = Dealer
        
        p1 = new ArrayList<Card>();
        comp = new ArrayList<Card>();
        Scanner reader = new Scanner(System.in);
        
        deck.shuffle();
        System.out.println("How many will be gambling against The Dealer? ");
        int players = reader.nextInt();
        score = new int[players+1];
        bust = new boolean[players+1];
        for (int i = 0; i < bust.length; i++){
            bust[i] = false;
        }
        dealHand(p, players);
        System.out.println("\nDealer's visible hand: \n" + 
                           "- " + p.get(0).get(0) + "\n");
        for (int i = 1; i < players+1; i++){
            System.out.println("Player " + i + "'s Hand: \n" +
                               "- " + p.get(i).get(0) + "\n" +
                               "- " + p.get(i).get(1) + "\n");
        }
        
        play(p, score, bust);
        // OLD BELOW I still need to implement proper multiplayer
        
        // Deals initial hands
        deck.shuffle();
        p1.add(deck.deal());
        p1.add(deck.deal());
        comp.add(deck.deal());
        comp.add(deck.deal());
        
        
        Boolean playing = true;
        int score = score(p1);
        System.out.println(score);
        System.out.println("Dealer's visible hand: \n" + 
                           "- " + comp.get(0));
        if (score > 21){
            System.out.println("Player has a Natural!");
        }
        
        int count = 0;
        while (playing){
          // Displays current hand
          score = score(p1);
          System.out.println("Your current hand: ");
          for (int i = 0; i < p1.size(); i++){
              System.out.println("- " + p1.get(i));
          }
          System.out.println("Score: " + score(p1));
          
          // Checks if 21
          if (score == 21){
              playing = false;
              System.out.println("You got 21!");
          }
          // Checks if over 21
          else if (score > 21){
              playing = false;
              System.out.println("YOU B U S T E D");
              System.out.println();
          }
          // Continues to Play
          else {
              System.out.println("Hit? [Y/N]");
              Boolean valid = false;
              while (!valid){
                  String reply = reader.nextLine();
                  System.out.println();
                  if (reply.toUpperCase().equals("Y")){
                      p1.add(deck.deal());
                      valid = true;
                  } else if (reply.toUpperCase().equals("N")){
                      playing = false;
                      valid = true;
                  } else {
                      System.out.println("Respond with Y or N.");
                  }
              }
          }
          
          count++;
        }
        
        // Computer/Dealer's turn
        int scoreD = score(comp);
        playing = true;
        count = 0;
        while (playing){
          // Displays current hand
          scoreD = score(comp);
          System.out.println("Dealer's current hand: ");
          for (int i = 0; i < comp.size(); i++){
              System.out.println("- " + comp.get(i));
          }
          System.out.println("Score: " + score(comp));
          
          // Checks if 21
          if (scoreD == 21){
              playing = false;
          }
          // Checks if over 21
          else if (scoreD > 21){
              playing = false;
          }
          // Stops hitting
          else if (scoreD >= 17){
              System.out.println("Dealer stays.");
              playing = false;
          } 
          // Continues playing
          else{
              comp.add(deck.deal());
          }
          
          count++;
        }
        
        System.out.println("\nThe Dealer has a score of " + scoreD + " with the hand: ");
        for (int i = 0; i < comp.size(); i++){
            System.out.println("- " + comp.get(i));
        }
        System.out.println(score(comp));
        System.out.println("The Player has a score of " + score + " with the hand: ");
        for (int i = 0; i < p1.size(); i++){
            System.out.println("- " + p1.get(i));
        }
        System.out.println(score(p1));

    }
    
    public static ArrayList<ArrayList<Card>> dealHand(ArrayList<ArrayList<Card>> p, int players){
        // 0 = The Dealer
        for (int i = 0; i < players+1; i++){
            p.add(new ArrayList<Card>());
        }
        for (int i = 0; i < players+1; i++){
            p.get(i).add(deck.deal());
            p.get(i).add(deck.deal());
        }
        return p;
    }
    
    public static ArrayList<ArrayList<Card>> play(ArrayList<ArrayList<Card>> p, 
                                                  int[] score, boolean[] bust){                                                      
        Scanner reader = new Scanner(System.in);
        Boolean[] stay = new Boolean[bust.length];
        for (int i = 0; i < stay.length; i++){
            stay[i] = false;
        }
        Boolean playing = true;
        // Players
        while (playing){
         for (int x = 1; x < p.size(); x++){
          
          if (!bust[x] || !stay[x]){
           // Displays current hand
           System.out.println("~~Player " + x + "'s turn~~"); 
           score[x] = score(p.get(x));
           System.out.println("Player " + x + "'s current hand: ");
           for (int i = 0; i < p.get(x).size(); i++){
              System.out.println("- " + p.get(x).get(i));
           }
           System.out.println("Score: " + score(p.get(x)));
           
           System.out.println("Hit? [Y/N]");
           Boolean valid = false;
           while (!valid){
               String reply = reader.nextLine();
               if (reply.toUpperCase().equals("Y")){
                   p.get(x).add(deck.deal());
                   score[x] = score(p.get(x));
                   System.out.println("Player " + x + " was dealt a " + 
                               p.get(x).get(p.get(x).size()-1) +
                              "\nNew Score: " + score[x]);
                   // Checks if 21
                   if (score[x] == 21){
                       System.out.println("Player " + x + " reached 21!");
                       stay[x] = true;
                   }
                   // Checks if over 21
                   else if (score[x] > 21){
                       System.out.println("YOU B U S T E D");
                       bust[x] = true;
                   } 
                   valid = true;
                } else if (reply.toUpperCase().equals("N")){
                   stay[x] = true;
                   valid = true;
                } else {
                      System.out.println("Respond with Y or N.");
                }
            }
            System.out.println();
           }
         }
         int a = p.size(); // Amount of players who have not busted or stayed
         for (int j = 1; j < p.size(); j++){
            if (!bust[j] || !stay[j]){
                a--;
            }
         }
         if (a == 0){
            playing = false;
         }
       }
       return p;
    }
    
    public static int score(ArrayList<Card> hand){
        int score = 0;
        for (int i = 0; i < hand.size(); i++){
            score += value(hand.get(i));
        }
        
        if (score > 21){ // Checks if there are Aces
            int aceCount = 0;
            for (int i = 0; i < p1.size(); i++){
                if (score > 21 && p1.get(i).getRank() == 1){
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
