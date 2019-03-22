import java.util.*;

/**
 * Get as close to 21 as possible without going over 21.
 *
 * @aj
 * @blackjack
 * 
 * I might be doing this instead of Texas Hold'em for the sake of simplicity.
 * The checks are definitely proving to be more difficult to properly handle
 * than what they're worth.
 * 
 */
public class Blackjack
{
    private Deck deck;
    private ArrayList<Card> p1Hand;
    private ArrayList<Card> p2Hand;
    
    public Blackjack(){
        init();
    }
    
    private void init(){
        deck = new Deck();
        deck.shuffle();
        p1Hand = new ArrayList<Card>();
        p2Hand = new ArrayList<Card>();
    }
    
    public void dealHand(){
        p1Hand.add(deck.deal());
        p1Hand.add(deck.deal());
        p2Hand.add(deck.deal());
        p2Hand.add(deck.deal());
        System.out.println(p1Hand);
        System.out.println(p2Hand);
    }
    
    // 'Get' methods
    public void getHands(){
        System.out.println(p1Hand);
        System.out.println(p2Hand);
    }
}
