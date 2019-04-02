import java.util.*;
/**
 * Mao
 * Gavin's playhouse
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
            case 1: str += "You"; break;
            case 2: str += "Alice"; break;
            case 3: str += "Bob"; break;
            case 4: str += "Charle"; break;
        }
        switch((move/1000)%10){
            case 1: str += " played";
                    switch(move%100){
                        case 1: str += " an ace"; break;
                        case 2: str += " a two"; break;
                        case 3: str += " a three"; break;
                        case 4: str += " a four"; break;
                        case 5: str += " a five"; break;
                        case 6: str += " a six"; break;
                        case 7: str += " a seven"; break;
                        case 8: str += " an eight"; break;
                        case 9: str += " a nine"; break;
                        case 10: str += " a ten"; break;
                        case 11: str += " a jack"; break;
                        case 12: str += " a queen"; break;
                        case 13: str += " a king"; break;
                    }
                    switch((move/100)%10){
                        case 1: str += " of clubs."; break;
                        case 2: str += " of diamonds."; break;
                        case 3: str += " of hearts."; break;
                        case 4: str += " of spades."; break;
                    }
                    return str;
            case 2: str += " drew a card.";
                    return str;
            case 3: str += " penalized";
                    switch ((move/100)%10){
                        case 1: str += " you"; break;
                        case 2: str += " Alice"; break;
                        case 3: str += " Bob"; break;
                        case 4: str += " Charlie"; break;
                    }
                    switch (move%10){
                        case 1: str += " for playing out of turn."; break;
                        case 2: str += " for playing an incorrect card."; break;
                        case 3: str += " for drawing out of turn."; break;
                        case 4: str += " for false judgement."; break;
                    }
                    return str;
            default: return "Sorry.";
        }
    }
    public static String cardType(int type){
        String str = "";
        switch (type/100){
            case 1: switch (type%10){
                        case 1: str += " a red card"; break;
                        case 2: str += " a black card"; break;
            }
            return str;
            case 2: switch (type%10){
                        case 1: str += " a club"; break;
                        case 2: str += " a diamond"; break;
                        case 3: str += " a heart"; break;
                        case 4: str += " a spade"; break;
            }
            return str;
            case 3: str += " a numbered card with rank greater than";
                    switch ((type/10)%10){
                        case 1: str += " 1"; break;
                        case 2: str += " 2"; break;
                        case 3: str += " 3"; break;
                        case 4: str += " 4"; break;
                        case 5: str += " 5"; break;
                        case 6: str += " 6"; break;
                        case 7: str += " 7"; break;
                        case 8: str += " 8"; break;
                        case 9: str += " 9"; break;   
                    }
                    str += " and less than";
                    switch(type%10){
                        case 0: str += " 10";
                        case 1: str += " 11";
                        case 3: str += " 3";
                        case 4: str += " 4";
                        case 5: str += " 5";
                        case 6: str += " 6";
                        case 7: str += " 7";
                        case 8: str += " 8";
                        case 9: str += " 9";
                    }
                    return str;
        }
        return "wip";
    }
}

