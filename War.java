
/**
 * War
 *
 * @Makai Romero
 * @0.0
 */

import java.util.ArrayList;
import java.util.Arrays; //I need this to convert Array to ArrayList.
import java.util.List;

public class War
{
    public ArrayList getDeck(){
        Deck deck = new Deck();
        deck.shuffle();
        Card[] card = deck.deal(52);
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(card));
        return newDeck;
    }
    
    public void play(List d1, List d2){
        System.out.println("You play " + d1.get(0) + ".");
        System.out.println("Your opponent plays " + d2.get(0) + ".");
        evaluate(d1,d2);
    }
    
    public void evaluate(List d1, List d2){
        
    }
}
