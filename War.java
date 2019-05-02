
/**
 * War
 *
 * @Makai Romero
 * @1.0
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
        String card1 = d1.get(0).toString();
        String card2 = d2.get(0).toString();

        System.out.println("You play " + card1 + ".");
        System.out.println("Your opponent plays " + card2 + ".");
        evaluate(card1, card2, d1, d2);
        //d1.remove(0);
        //d2.remove(0);
        //System.out.println(d1);
        
    }

    public void evaluate(String c1, String c2, List d1, List d2){
        int rank1 = getRank(c1);
        int rank2 = getRank(c2);
        
        if (rank1 > rank2){
            System.out.println("Your card triumphs!");
            d1.add(c1);
            d1.add(c2);
            System.out.println("Take both cards into your deck.");
        }else
        if (rank2 > rank1){
            System.out.println("Your card fails!");
            d2.add(c1);
            d2.add(c2);
            System.out.println("Lose both cards to opponent.");
        }
        
        d1.remove(c1); //Throws exception when fails.
        d2.remove(c2); //Throws exception when triumphs.
        //System.out.println(d1);
        //System.out.println("Your deck: " + d1.size());
    }

    public int getRank(String card){
        int rank = 0;
        if (card.contains("Ace"))
            rank = 12;
        if (card.contains("King"))
            rank = 11;
        if (card.contains("Queen"))
            rank = 10;
        if (card.contains("Jack"))
            rank = 9;
        if (card.contains("10"))
            rank = 8;
        if (card.contains("9"))
            rank = 7;
        if (card.contains("8"))
            rank = 6;
        if (card.contains("7"))
            rank = 5;
        if (card.contains("6"))
            rank = 4;
        if (card.contains("5"))
            rank = 3;
        if (card.contains("4"))
            rank = 2;
        if (card.contains("3"))
            rank = 1;
        if (card.contains("2"))
            rank = 0;
        return rank;
    }
}
