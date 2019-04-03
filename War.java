
/**
 * War
 *
 * @Makai Romero
 * @0.0
 */

import java.util.ArrayList;
import java.util.Arrays; //I need this to convert Array to ArrayList.
public class War
{
    public ArrayList getDeck(){
        Deck deck = new Deck();
        deck.shuffle();
        Card[] card = deck.deal(52);
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(card));
        return newDeck;
    }
}
