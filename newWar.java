import java.util.*;
/**
 * War
 */
public class newWar
{
    public static double newWar(){
        Scanner me = new Scanner(System.in);
        ArrayList<Card> one = new ArrayList<Card>();
        ArrayList<Card> two = new ArrayList<Card>();
        ArrayList<Card> three = new ArrayList<Card>();
        Deck deck = new Deck();
        deck.shuffle();
        int multi=0;
        for(int i=0; i<26; i++){
            one.add(deck.deal());
            two.add(deck.deal());
        }
        boolean gameOn=true;
        System.out.println("How much do you bet?");
        double wager = me.nextInt();
        while(gameOn){
            Card h = one.remove(0);
            Card c = two.remove(0);
            System.out.println("You played "+h);
            System.out.println("I played "+c);
            if(h.getRank()>c.getRank()){
                System.out.println("You take cards.");
                one.add(h);
                one.add(c);
                for(int i=three.size()-1; i>-1; i--)
                    one.add(three.remove(i));
            }
            if(c.getRank()>h.getRank()){
                System.out.println("I take the cards.");
                two.add(c);
                two.add(h);
                for(int i=three.size()-1; i>-1; i--)
                    two.add(three.remove(i));
            }
            if(c.getRank()==h.getRank()){
                System.out.println("It's a tie! The cards stay on the table.");
                three.add(c);
                three.add(h);
            }
            if(one.size()==0){
                System.out.println("I win.");
                multi=-1;
                gameOn=false;
            }
            if(two.size()==0){
                System.out.println("You win.");
                multi=1;
                gameOn=false;
            }
        }
        return wager*multi;
    }
}
