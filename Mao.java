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
        Card ontop = deck.deal();
        Boolean jibes = false;
        Boolean gameOn = true;
        int choice;
        int cardPlay;
        int criminal;
        
        ArrayList<Card> ahand = new ArrayList<Card>();
        ArrayList<Card> bhand = new ArrayList<Card>();
        ArrayList<Card> chand = new ArrayList<Card>();
        ArrayList<Card> hhand = new ArrayList<Card>();
        ArrayList<Integer> rules = new ArrayList<Integer>();
        ArrayList<Integer> ahypos = new ArrayList<Integer>();
        ArrayList<Integer> bhypos = new ArrayList<Integer>();
        ArrayList<Integer> chypos = new ArrayList<Integer>();
        for(int i = 0; i<7; i++){
            ahand.add(deck.deal());
            bhand.add(deck.deal());
            chand.add(deck.deal());
            hhand.add(deck.deal());
        }
        
        while(gameOn){
            //Alice thinks...
            jibes = false;
            //Alice figures out whose turn it is...
            for(int i=0; i<ahypos.size(); i++){
                
            }
            while(!jibes){
            }
            //Bob thinks...
    
            //Charlie thinks...
    
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
                        System.out.println("Type a number from 1 to "+hhand.size()+" indicating a position in your hand and then press enter");
                        cardPlay = searcher.nextInt();
                case 2: hhand.add(deck.deal());
                case 3: System.out.println("Who are you penalizing?");
                        System.out.println("Type a number and then press enter.");
                        System.out.println("1 - Alice");
                        System.out.println("2 - Bob");
                        System.out.println("3 - Charlie");
                        criminal = searcher.nextInt();
                        System.out.println("What for?");
                        System.out.println("Type a number and then press enter.");
                        System.out.println("1 - Playing out of turn");
                        System.out.println("2 - Playing an incorrect card");
                        System.out.println("3 - Drawing out of turn");
                        System.out.println("4 - False judgement");
            }
        }
    }
}
