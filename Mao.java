import java.util.*;
/**
 * Mao
 */
public class Mao
{
    public static void playMao(){
        //Let's get ready!
        Scanner searcher = new Scanner(System.in);
        Deck deck =  new Deck();
        ArrayList<Card> pile = new ArrayList<Card>();
        ArrayList<Integer> game = new ArrayList<Integer>();
        pile.add(deck.deal());
        Boolean gameOn = true;
        int choice;
        int cardPlay;
        
        ArrayList<Card> ahand = new ArrayList<Card>();
        ArrayList<Card> bhand = new ArrayList<Card>();
        ArrayList<Card> chand = new ArrayList<Card>();
        ArrayList<Card> hhand = new ArrayList<Card>();
        ArrayList<Integer> rules = new ArrayList<Integer>();
        ArrayList<Integer> ahypos = new ArrayList<Integer>();
        ArrayList<Integer> bhypos = new ArrayList<Integer>();
        ArrayList<Integer> chypos = new ArrayList<Integer>();
        
        //Dealing the cards...
        for(int i = 0; i<7; i++){
            ahand.add(deck.deal());
            bhand.add(deck.deal());
            chand.add(deck.deal());
            hhand.add(deck.deal());
        }
        
        //Here is one round...
        while(gameOn){
            //Charlie thinks...
            
            //Bob thinks...
            
            //Alice thinks...
            Boolean jibes = false;
            //Alice determines whether what just happened was legal...
            for(int i=0; i<ahypos.size(); i++){
                // 
            }
            //Alice figures out whose turn it is...
            for(int i=0; i<ahypos.size(); i++){
                
            }
            //Alice chooses a card to play...
            while(!jibes){
            }
            
            //Human thinks...
            System.out.println("What would like to do?");
            System.out.println("Type a number and then press enter.");
            System.out.println("0 - Do nothing");
            System.out.println("1 - Play a card");
            System.out.println("2 - Draw a card");
            System.out.println("3 - Penalize a player for breaking a rule");
            choice = searcher.nextInt();
            switch (choice){
                case 0: break;
                case 1: System.out.println("Which card would you like to play?");
                        System.out.println("Type a number from 1 to "+hhand.size()+" indicating a position in your hand and then press enter.");
                        cardPlay = searcher.nextInt()-1;
                        Card playedCard = hhand.remove(cardPlay);
                        game.add(10000+1000+numberSuit(playedCard)*100+playedCard.getRank());
                case 2: hhand.add(deck.deal());
                        game.add(10000+2000);
                case 3: System.out.println("Who are you penalizing?");
                        System.out.println("Type a number and then press enter.");
                        System.out.println("1 - Alice");
                        System.out.println("2 - Bob");
                        System.out.println("3 - Charlie");
                        int criminal = searcher.nextInt();
                        System.out.println("Whatever for?");
                        System.out.println("Type a number and then press enter.");
                        System.out.println("1 - Playing out of turn");
                        System.out.println("2 - Playing an incorrect card");
                        System.out.println("3 - Drawing out of turn");
                        System.out.println("4 - False judgement");
                        int why = searcher.nextInt();
                        switch (why){
                            case 1: switch (criminal){
                                        case 1: ahand.add(pile.get(pile.size()-1));
                                        ahand.add(deck.deal());
                                        case 2: bhand.add(pile.get(pile.size()-1));
                                        bhand.add(deck.deal());
                                        case 3: chand.add(pile.get(pile.size()-1));
                                        chand.add(deck.deal());
                            }
                            case 2: switch (criminal){
                                        case 1: ahand.add(pile.get(pile.size()-1));
                                        ahand.add(deck.deal());
                                        case 2: bhand.add(pile.get(pile.size()-1));
                                        bhand.add(deck.deal());
                                        case 3: chand.add(pile.get(pile.size()-1));
                                        chand.add(deck.deal());
                                    }
                        }
                        game.add(10000+3000+(criminal+1)*100+why);
            }
        }
    }
    //Finding the rank of a card.
    public static int numberSuit(Card card){
        switch (card.getSuit().toString()){
            case "spades": return 4;
            case "hearts": return 3;
            case "diamonds": return 2;
            case "clubs": return 1;
            default: return 0;
        }
    }
    public static String stringMove(int move){
        String str = "";
        switch(move/10000){
            case 1: str += "You";
            case 2: str += "Alice";
            case 3: str += "Bob";
            case 4: str += "Charle";
        }
        switch((move/1000)%10){
            case 1: str += " played";
                    switch(move%100){
                        case 1: str += " an ace";
                        case 2: str += " a two";
                        case 3: str += " a three";
                        case 4: str += " a four";
                        case 5: str += " a five";
                        case 6: str += " a six";
                        case 7: str += " a seven";
                        case 8: str += " an eight";
                        case 9: str += " a nine";
                        case 10: str += " a ten";
                        case 11: str += " a jack";
                        case 12: str += " a queen";
                        case 13: str += " a king";
                    }
                    switch((move/100)%100){
                        case 1: str += " of clubs.";
                        case 2: str += " of diamonds.";
                        case 3: str += " of hearts.";
                        case 4: str += " of spades.";
                    }
                    return str;
            case 2: str += " drew a card.";
                    return str;
            case 3: str += " accused";
                    switch ((move/100)%100){
                        case 1: str += " you";
                        case 2: str +=
        }
    }
}