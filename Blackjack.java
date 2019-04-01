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
    public static void Jack(){
        Deck deck = new Deck();
        ArrayList<Card> p1 = new ArrayList<Card>();
        ArrayList<Card> comp = new ArrayList<Card>();
        
        p1.add(deck.deal());
        p1.add(deck.deal());
        comp.add(deck.deal());
        comp.add(deck.deal());
        System.out.println("Your hand: " + p1);
        
        
    }
}
