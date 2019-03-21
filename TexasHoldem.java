import java.util.*;

/**
 * Play a good old game of Texas hold 'em.
 *
 * @aj
 * @poker
 */
public class TexasHoldem
{
    private Deck deck;
    private ArrayList<Card> p1Hand;
    private ArrayList<Card> p2Hand;
    private ArrayList<Card> river;
    public TexasHoldem(){
        init();
    }
    
    private void init()
    {
        deck = new Deck();
	deck.shuffle();
	p1Hand = new ArrayList<Card>(2);
	p2Hand = new ArrayList<Card>(2);
	river = new ArrayList<Card>(5);
    }
    
    public void dealHand(){
        p1Hand.add(deck.deal());
        p1Hand.add(deck.deal());
        p2Hand.add(deck.deal());
        p2Hand.add(deck.deal());
        System.out.println(p1Hand);
        System.out.println(p2Hand);
    }
    
    public void dealRiver(){
        river.add(deck.deal());
        river.add(deck.deal());
        river.add(deck.deal());
        river.add(deck.deal());
        river.add(deck.deal());
        System.out.println(river);
    }
}
