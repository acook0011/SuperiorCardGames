
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
        System.out.println("Ready for War!?");
        if (reader.nextLine().toLowerCase().equals("yes")){
            War war = new War();
            ArrayList<String> DeckMain = new ArrayList<String>();
            DeckMain = war.getDeck();
            List Deck1 = DeckMain.subList(0,25); //The other half will be the opponent's.
            List Deck2 = DeckMain.subList(25,52);
            System.out.println("Here is your deck: \n" + Deck1); 
        }
    }
}
