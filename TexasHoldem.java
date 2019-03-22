import java.util.*;

/**
 * Play a good old game of Texas hold 'em.
 *
 * @aj
 * @poker
 */
public class TexasHoldem
{
    private Deck deck;
    private ArrayList<Card> p1Hand;
    private ArrayList<Card> p2Hand;
    private ArrayList<Card> river;
    
    public TexasHoldem(){
        init();
    }
    
    private void init(){
        deck = new Deck();
        deck.shuffle();
        p1Hand = new ArrayList<Card>(2);
        p2Hand = new ArrayList<Card>(2);
        river = new ArrayList<Card>(5);
    }
    
    public void dealHand(){
        p1Hand.add(deck.deal());
        p1Hand.add(deck.deal());
        p2Hand.add(deck.deal());
        p2Hand.add(deck.deal());
        System.out.println(p1Hand);
        System.out.println(p2Hand);
    }
    
    public void dealRiver(){
        for (int i = 0; i <= 5; i++){
            river.add(deck.deal());
        }
        System.out.println(river);
    }
    
    public void checkOdds(){
        ArrayList<Card> check = new ArrayList<Card>(7);
        // Adds Hand
        for (int i = 0; i < p1Hand.size(); i++){
            check.add(p1Hand.get(i));
        }
        // Adds River
        for (int i = 0; i < river.size(); i++){
            check.add(river.get(i));
        }
        Collections.sort(check, Collections.reverseOrder());
        // This whole 'Checking' section needs to be reworked
        // Booleans aren't going to work as well as I thought they would
        if (checkFourKind(check)){
            System.out.println("You have Four of a Kind!");
        }
        if (checkHouse(check)){
            System.out.println("You have a Full House!");
        }
        if (checkStraight(check)){
            System.out.println("You have a Straight!");
        }
        if (checkFlush(check)){
            System.out.println("You have a Flush!");
        }
        if (checkThreeKind(check)){
            System.out.println("You have Three of a Kind!");
        }
        if (checkTwoPair(check)){
            System.out.println("You have Two Pair!");
        }
        if (checkPair(check)){
            System.out.println("You have a Pair!");
        }
    }
    
    // Four of a Kind
    public boolean checkFourKind(ArrayList<Card> x){
        int count = 0;
        for (int i = 0; i < x.size(); i++){
            for (int j = 0; j < x.size(); j++){
                if (x.get(i).getRank() == x.get(j).getRank()){
                    count++;
                }
                if (count == 4){
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }
    
    // Full House
    public boolean checkHouse(ArrayList<Card> x){
        int count = 0;
        for (int i = 0; i < x.size(); i++){
            count = 0;
            for (int j = 0; j < x.size(); j++){
                if (x.get(i).getRank() == x.get(j).getRank()){
                    count++;
                }
                if (count == 3){
                    i = x.size();
                    j = x.size();
                }
            }
        }
        if (count == 3){
            
        }
        return false;
    }
    
    // Straight
    public boolean checkStraight(ArrayList<Card> x){
        int count = 1; 
        for (int i = 1; i < x.size(); i++){
            if (x.get(i).getRank() == x.get(i-1).getRank()+1){
                count++;
            }else{ // Resets
                count = 1;
            }
        }
        if (count >= 5){
            return true;
        }else{
            return false;
        }
    }
    
    // Flush
    public boolean checkFlush(ArrayList<Card> x){
        int count = 1;
        for (int i = 1; i < x.size(); i++){
            if (x.get(i).getSuit().equals(x.get(i-1).getSuit())){
                count++;
            }else{ // Resets
                count = 1;
            }
        }
        if (count >= 5){
            return true;
        }else{
            return false;
        }
    }
    
    // Three of a Kind
    public boolean checkThreeKind(ArrayList<Card> x){
        int count = 0;
        for (int i = 0; i < x.size(); i++){
            for (int j = 0; j < x.size(); j++){
                if (x.get(i).getRank() == x.get(j).getRank()){
                    count++;
                }
                if (count == 3){
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }
    
    // Two-Pair
    public boolean checkTwoPair(ArrayList<Card> x){
        int count = 0;
        int pcount = 0;
        for (int i = 0; i < x.size(); i++){
            for (int j = 0; j < x.size(); j++){
                if (x.get(i).getRank() == x.get(j).getRank()){
                    count++;
                }
                if (count == 2){
                    //x.remove(i);
                    //x.remove(j);
                    //i--;
                    //j = x.size()-1;
                    //pcount++;
                }
            }
            count = 0;
        }
        if (pcount == 2){
            return true;
        }else{
            return false;
        }
    }
    
    // Pair
    public boolean checkPair(ArrayList<Card> x){
        int count = 0;
        for (int i = 0; i < x.size(); i++){
            for (int j = 0; j < x.size(); j++){
                if (x.get(i).getRank() == x.get(j).getRank()){
                    count++;
                }
                if (count == 2){
                    return true;
                }
            }
            count = 0;
        }
        return false;
    }
    
    // 'Get' methods
    public void getHands(){
        System.out.println(p1Hand);
        System.out.println(p2Hand);
    }
    
    public void getRiver(){
        System.out.println(river);
    }
}
