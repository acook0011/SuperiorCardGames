
/**
 * the
 *
 * @Makai Romero
 * @0.0
 */

import java.util.ArrayList;
import java.util.Arrays;
public class untitled
{
    //Deck deck = new Deck();
    //Card[] card = deck.deal(26);

    public static void main (String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        Card[] card = deck.deal(52);
        ArrayList<Card> curd = new ArrayList<Card>(Arrays.asList(card));
        
        System.out.println(curd);
    }
}
