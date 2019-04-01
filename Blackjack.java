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
    
    public static void Jack(){
        deck = new Deck();
        p1 = new ArrayList<Card>();
        comp = new ArrayList<Card>();
        
        // Deals initial hands
        deck.shuffle();
        p1.add(deck.deal());
        p1.add(deck.deal());
        comp.add(deck.deal());
        comp.add(deck.deal());
        
        System.out.println("You were dealt: \n" +
                           "- " + p1.get(0) + "\n" +
                           "- " + p1.get(1) + "\n");
        int score = value(p1.get(0)) + value(p1.get(1));
        System.out.println(score(p1));
        
        
        Boolean over = false;
        Boolean playing = true;
        int totalAce = 0;
        while (playing){
          // Checks if 21
          if (score == 21){
              playing = false;
              System.out.println("You got 21!");
          }
          // Checks if over 21
           else if (score > 21){
              // Checks if there are Aces
              int aceCount = 0;
              for (int i = 0; i < p1.size(); i++){
                  if (p1.get(i).getRank() == 1){
                      aceCount++;
                      if (totalAce < aceCount){
                          totalAce++;
                          score -= 10;
                          i = p1.size();
                      }
                  }
              }
              // If still over 21 after accounting for Aces
              if (score > 21){
                  playing = false;
                  System.out.println("You B U S T E D !");
              }
          }
          // 
          else {
              
          }
        }
    }
    
    public static int score(ArrayList<Card> hand){
        int score = 0;
        for (int i = 0; i < hand.size(); i++){
            score += value(hand.get(i));
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
