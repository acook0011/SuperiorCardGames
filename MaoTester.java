/**
 * Does Mao work?!?!?
 */
import java.util.*;
public class MaoTester
{
    public static void main(String [] args){
        Scanner thunter = new Scanner(System.in);
        String str;
        System.out.println("1 - Cook up some random rules.");
        System.out.println("2 - Check whether a random card is of a random type.");
        System.out.println("3 - Check out whoTurn.");
        System.out.println("Anything else - Leave immediately.");
        str = thunter.nextLine();
        System.out.println();
        System.out.println();
        System.out.println();
        while(str.equals("1")||str.equals("2")){
            switch(str){
                case "1":   int human = Mao.randyRules(1);
                            int alice = Mao.randyRules(2);
                            int bob = Mao.randyRules(3);
                            int charlie = Mao.randyRules(4);
                            System.out.println("Here are some random rules.");
                            System.out.println(human+": "+Mao.stringRule(human));
                            System.out.println(alice+": "+Mao.stringRule(alice));
                            System.out.println(bob+": "+Mao.stringRule(bob));
                            System.out.println(charlie+": "+Mao.stringRule(charlie));
                            break;
                case "2":   Deck deck = new Deck();
                            deck.shuffle();
                            Card card = deck.deal();
                            int type = Mao.randomCardType();
                            System.out.println("Card: "+card);
                            System.out.println(type+":"+Mao.cardType(type));
                            System.out.println(Mao.itsAMatch(card,type));
                            break;
                case "3":   System.out.println("Enter a move as a 5-digit integer");
                            int move1 = thunter.nextInt();
                            System.out.println("Enter the move that followed it.");
                            int move2 = thunter.nextInt();
                            System.out.println("Now, make up some rules.");
                default:    break;
            }
            str = thunter.nextLine();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}