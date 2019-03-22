
/**
 * Write a description of class TestGames here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestGames
{
    public static void main(String [] args){
        TexasHoldem Holdem = new TexasHoldem();
        Holdem.dealHand();
        Holdem.getHands();
        Holdem.dealRiver();
        Holdem.checkOdds();
        
        Blackjack Jack = new Blackjack();
        Jack.dealHand();
        
    }
}
