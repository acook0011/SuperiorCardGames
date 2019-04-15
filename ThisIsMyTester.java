
/**
 * Temp Tester for War.
 *
 * @Makai Romero
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ThisIsMyTester
{
    public static void main (String[] args){
        Scanner reader = new Scanner(System.in);
        //String ask = reader.nextLine();
        War war = new War();
        ArrayList<String> DeckMain = new ArrayList<String>();
        DeckMain = war.getDeck();
        List Deck1 = DeckMain.subList(0,26); //The other half will be the opponent's.
        //System.out.println(Deck1.size());
        List Deck2 = DeckMain.subList(26,52);
        //System.out.println(Deck2.size());
        System.out.println("Here is your deck: \n" + Deck1);
        
        System.out.println("Ready for War!?");
        if (reader.nextLine().toLowerCase().equals("yes")){
            war.play(Deck1, Deck2);
        }
    }
}
