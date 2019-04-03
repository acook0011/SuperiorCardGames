
/**
 * Temp Tester for War.
 *
 * @Makai Romero
 */

import java.util.ArrayList;

public class ThisIsMyTester
{
    public static void main (String[] args){
        War war = new War();
        ArrayList<String> Deck1 = new ArrayList<String>();
        Deck1 = war.getDeck();
        System.out.println("Here is your deck: \n" + Deck1.subList(0,25)); //The other half will be the opponent's.
        
    }
}
