import java.util.*;
/**
 * Mao
 * Gavin's playhouse
 */
public class Mao{
    public static void playMao(){
        //Let's get ready!
        Scanner searcher = new Scanner(System.in);
        Deck deck =  new Deck();
        ArrayList<Card> pile = new ArrayList<Card>();
        ArrayList<Integer> game = new ArrayList<Integer>();
        pile.add(deck.deal());
        Boolean gameOn = true;
        
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
                      
            //Human thinks...
            System.out.println("What would like to do?");
            System.out.println("Type a number and then press enter.");
            System.out.println("0 - Do nothing");
            System.out.println("1 - Play a card");
            System.out.println("2 - Draw a card");
            System.out.println("3 - Penalize a player for breaking a rule");
            int choice = searcher.nextInt();
            switch(choice){
                case 0: break;
                case 1: System.out.println("Which card would you like to play?");
                        System.out.println("Type a number from 1 to "+hhand.size()+" indicating a position in your hand and then press enter.");
                        int cardPlay = searcher.nextInt()-1;
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
                        switch(why){
                            case 1: switch(criminal){
                                        case 1: ahand.add(pile.get(pile.size()-1));
                                        ahand.add(deck.deal());
                                        case 2: bhand.add(pile.get(pile.size()-1));
                                        bhand.add(deck.deal());
                                        case 3: chand.add(pile.get(pile.size()-1));
                                        chand.add(deck.deal());
                            }
                            case 2: switch(criminal){
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
    //Finding the suit of a card.
    public static int numberSuit(Card card){
        switch(card.getSuit().toString()){
            case "spades": return 4;
            case "hearts": return 3;
            case "diamonds": return 2;
            case "clubs": return 1;
            default: return 0;
        }
    }
    //Translating a 5-digit integer representing a move to a string.
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
                    switch((move/100)%10){
                        case 1: str += " you"; break;
                        case 2: str += " Alice"; break;
                        case 3: str += " Bob"; break;
                        case 4: str += " Charlie"; break;
                    }
                    switch(move%10){
                        case 1: str += " for playing out of turn."; break;
                        case 2: str += " for playing an incorrect card."; break;
                        case 3: str += " for drawing out of turn."; break;
                        case 4: str += " for false judgement."; break;
                    }
                    return str;
            default: return "Sorry.";
        }
    }
    //Translating a 3-digit integer representing a type of card to a string.
    public static String cardType(int type){
        String str = "";
        switch(type/100){
            case 0: return " any "+type;
            case 1: switch(type%10){
                        case 1: return " any red card";
                        case 2: return " any black card";
                    }
                    return str;
            case 2: switch(type%10){
                        case 1: return " any club";
                        case 2: return " any diamond";
                        case 3: return " any heart";
                        case 4: return " any spade";
                    }
                return "Sorry";
            case 3: if(type==319)
                        return " any numbered card";
                    return " any card with rank between "+(type/10)%10+" and "+(type%10+2);
            case 4: return " any card with rank divisible by "+type%10;
            case 5: return " any face card";
            case 6: return " any ace";
            case 7: return " any king";
            case 8: return " any queen";
            case 9: return " any jack";
            default: return "Sorry.";
        }
    }
    //Translating a 10-digit integer representing a rule to a string.
    public static String stringRule(int rule){
        String str = "Rule created by";
        switch((rule/100000000)%10){
            case 1: str += " Human:"; break;
            case 2: str += " Alice:"; break;
            case 3: str += " Bob:"; break;
            case 4: str += " Charlie:"; break;
        }
        switch(rule/1000000000){
            case 0: switch((rule/10000000)%10){
                        case 1:str += " Skip the turn of 1 player when"; break;
                        default:str += " Skip the turn of "+(rule/10000000)%10+" players when"; break;
            }
            switch((rule/1000000)%10){
                case 1: str += cardType((rule%1000))+" is played.";
                        return str;
                case 2: str += cardType((rule/1000)%1000)+" is followed by"+cardType((rule%1000))+".";
                        return str;
                default: return str;
            }
            case 1: str += " Now,"+cardType(((rule%1000000)/1000))+" may and must be followed by"+cardType((rule%1000))+".";
                    return str;
        }
        return "Sorry.";
    }
    //Making up a rule.
    public static int randyRules(int player){
        Random randall = new Random();
        int skipOrPlay = randall.nextInt(2);
        switch(skipOrPlay){
            case 0: int howManyPeeps = randall.nextInt(3)+1;
                    int howManyCards = randall.nextInt(2)+1;
                    switch(howManyCards){
                        case 1: return player*100000000+howManyPeeps*10000000+howManyCards*1000000+randomCardType();
                        case 2: return player*100000000+howManyPeeps*10000000+howManyCards*1000000+randomCardType()*1000+randomCardType();
                    }
            case 1: return 1000000000+player*100000000+randomCardType()*1000+randomCardType();
        }
        return 0;
    }
    //Picking random type of card.
    public static int randomCardType(){
        Random randall = new Random();
        int category = randall.nextInt(10);
        switch(category){
            case 0: return randall.nextInt(8)+2;
            case 1: return 100+randall.nextInt(2)+1;
            case 2: return 200+randall.nextInt(4)+1;
            case 3: int min = randall.nextInt(8)+1;
                    return 300+min*10+(randall.nextInt(9-min)+min+1);
            case 4: return 400+randall.nextInt(4)+2;
            default: return category*100;
        }
    }
    //Checking whether move jibes with a rule.
    public static Boolean jibes(int prevPlaya, Card top, int move, int rule){
        switch(rule/1000000000){
            case 0: 
        }
        return true;
    }
    //Checking whether a certain card is of a certain type.
    public static Boolean itsAMatch(Card card, int type){
        switch(type/100){
            case 0: return(card.getRank()==type%10);
            case 1: if(numberSuit(card)==2||numberSuit(card)==3)
                        return type%10==1;
                    else
                        return type%10==2;
            case 2: return numberSuit(card)==type%10;
            case 3: return card.getRank()>(type/10)%10&&card.getRank()<type%10+2;
            case 4: return card.getRank()>1&&card.getRank()<11&&card.getRank()%(type%10)==0;
            case 5: return card.getRank()>10&&card.getRank()<14;
            case 6: return card.getRank()==1;
            case 7: return card.getRank()==13;
            case 8: return card.getRank()==12;
            case 9: return card.getRank()==11;
            default: return false;
        }
    }
}