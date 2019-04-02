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
        Scanner reader = new Scanner(System.in);
        // Deals initial hands
        deck.shuffle();
        p1.add(deck.deal());
        p1.add(deck.deal());
        comp.add(deck.deal());
        comp.add(deck.deal());
        
        int score = value(p1.get(0)) + value(p1.get(1));
        System.out.println(score);
        System.out.println("Dealer's visible hand: \n" + 
                           "- " + comp.get(1));
        
        Boolean over = false;
        Boolean playing = true;
        int count = 0;
        int totalAce = 0;
        while (playing){
          // Displays current hand 
          System.out.println("Your current hand: ");
          for (int i = 0; i < p1.size(); i++){
              System.out.println("- " + p1.get(i));
          }
          System.out.println("Score: " + score);
          
          // Checks if 21
          if (score == 21){
              playing = false;
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
              }
          }
          // Continues to Play
          else {
              System.out.println("Hit? [Y/N]");
              Boolean valid = false;
              while (!valid){
                  String reply = reader.nextLine();
                  if (reply.toUpperCase().equals("Y")){
                      p1.add(deck.deal());
                      score += value(p1.get(count+2));
                      valid = true;
                  } else if (reply.toUpperCase().equals("N")){
                      valid = true;
                      playing = false;
                  } else {
                      System.out.println("Respond with Y or N.");
                  }
              }
          }
          
          
          count++;
        }
        
        // Computer/Dealer's turn
        int scoreD = value(comp.get(0)) + value(comp.get(1));
        over = false;
        playing = true;
        count = 0;
        totalAce = 0;
        while (playing){
          // Displays current hand 
          System.out.println("Your current hand: ");
          for (int i = 0; i < comp.size(); i++){
              System.out.println("- " + comp.get(i));
          }
          System.out.println("Score: " + scoreD);
          
          // Checks if 21
          if (scoreD == 21){
              playing = false;
          }
          // Checks if over 21
           else if (scoreD > 21){
              // Checks if there are Aces
              int aceCount = 0;
              for (int i = 0; i < p1.size(); i++){
                  if (p1.get(i).getRank() == 1){
                      aceCount++;
                      if (totalAce < aceCount){
                          totalAce++;
                          scoreD -= 10;
                          i = p1.size();
                      }
                  }
              }
              // If still over 21 after accounting for Aces
              if (scoreD > 21){
                  playing = false;
              }
          }
          // Continues to Play
          else if (scoreD >= 17){
              System.out.println("Dealer stays.");
              playing = false;
          } else{
              comp.add(deck.deal());
          }
          
          count++;
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
