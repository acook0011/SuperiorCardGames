
/**
 * the
 *
 * @Makai Romero
 * @0.0
 */

import java.util.ArrayList;
import java.util.Arrays; //I need this to convert Array to ArrayList.
public class War
{
    //Deck deck = new Deck();
    //Card[] card = deck.deal(26);

    public static void main (String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        Card[] card = deck.deal(52);
        ArrayList<Card> curd = new ArrayList<Card>(Arrays.asList(card));
        
        System.out.println(curd);
        System.out.println(curd.subList(0,25));
        System.out.println(curd.subList(25,51));
    }
    
    public ArrayList getDeck(){
        Deck deck = new Deck();
        deck.shuffle();
        Card[] card = deck.deal(52);
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(card));
        
        return newDeck;
    }
}
