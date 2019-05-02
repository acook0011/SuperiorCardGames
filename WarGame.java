
/**
 * Player for War!.
 *
 * @Makai Romero
 * @1.0
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class WarGame
{
    public static void playWar(){
        Scanner reader = new Scanner(System.in);
        //String ask = reader.nextLine();
        War war = new War();
        ArrayList<Card> DeckMain = new ArrayList<Card>();
        DeckMain = war.getDeck();
        List<Card> Deck1 = DeckMain.subList(0,26); //The other half will be the opponent's.
        //System.out.println(Deck1.size());
        List<Card> Deck2 = DeckMain.subList(26,52);
        //System.out.println(Deck2.size());
        System.out.println("Here is your deck: \n" + Deck1);
        System.out.println("Your deck: " + Deck1.size());
        System.out.println(Deck1.get(0).getRank());
        
        
        System.out.println("Ready for War!? (Type yes to play.)");
        if (reader.nextLine().toLowerCase().equals("yes")){
            //Iterator<Card> iterator = Deck1.iterator();
            war.play(Deck1, Deck2);
            /* while (iterator.hasNext()){
                Card temp = iterator.next();
                iterator.remove();
                break;
            } */
            //System.out.println(Deck1);
        }
        
        //Deck1.remove(0);
        //Deck2.remove(0);
        //System.out.println(Deck1);
        //System.out.println("Your deck: " + Deck1.size());
    }
}
