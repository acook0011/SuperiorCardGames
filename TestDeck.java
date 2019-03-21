// Case Study 14.1: Test a deck

public class TestDeck{

    public static void main(String[] args){
	Deck deck = new Deck();
	deck.shuffle();
	int count = 0;
	while (! deck.isEmpty()){
	    count++;
	    Card card = deck.deal();
	    System.out.println(count + ": " + card);
	}
    }
}
	
