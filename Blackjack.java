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
        p1 = new ArrayList<Card>();
        comp = new ArrayList<Card>();
        Scanner reader = new Scanner(System.in);
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
