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
        
        //Everybody's hands
        ArrayList<Card> ahand = new ArrayList<Card>();
        ArrayList<Card> bhand = new ArrayList<Card>();
        ArrayList<Card> chand = new ArrayList<Card>();
        ArrayList<Card> hhand = new ArrayList<Card>();
        
        //The real rules
        ArrayList<Integer> arules = new ArrayList<Integer>();
        ArrayList<Integer> brules = new ArrayList<Integer>();
        ArrayList<Integer> crules = new ArrayList<Integer>();
        ArrayList<Integer> hrules = new ArrayList<Integer>();
        
        //Alice's hypotheses for the most recent rule created by each player.
        int abhypo;
        int achypo;
        int ahhypo;
        //All of Alice's hypotheses.
        ArrayList<Integer> ahypo = new ArrayList<Integer>();
        
        //Bob's hypotheses for the most recent rule created by each player.
        int bahypo;
        int bchypo;
        int bhhypo;
        //All of Bob's hypotheses.
        ArrayList<Integer> bhypo = new ArrayList<Integer>();
        
        //Charlie's hypotheses for the most recetn rule created by each player.
        int cahypo;
        int cbhypo;
        int chhypo;
        //All of Charlie's hypotheses.
        ArrayList<Integer> chypo = new ArrayList<Integer>();
        
        //Dealing the cards...
        for(int i = 0; i<7; i++){
            ahand.add(deck.deal());
            bhand.add(deck.deal());
            chand.add(deck.deal());
            hhand.add(deck.deal());
        }
        
        System.out.println("Welcome, friend. Do you know how to play Computer Mao?");
        System.out.println("1 - Yes, and I want to play now.");
        System.out.println("2 - Yes, but I don't want to play now.");
        System.out.println("3 - No, but I want to play now.");
        System.out.println("4 - No, and I don't want to play now.");
        int gamer = searcher.nextInt();
        switch(gamer){
            case 1: mao();
                    break;
            case 2: System.out.println("I get it. No worries. Just come on back anytime if you change your mind.");
                    break;
            case 3: System.out.println("Pull up chair, fella. I can teach you.");
                    letsLearn();
                    break;
            case 4: System.out.println("That's fine. There are plenty of other card games out there.");
                    break;
        }
    }
    public static void mao(){
        
    }
    public static void letsLearn(){
        Scanner spot = new Scanner(System.in);
        int understand = 2;
        while(understand==2){
            System.out.println();
            System.out.println("Mao is a shedding card game. That means that the object of the game is to get rid of all the cards in your hand.");
            System.out.println("Every player starts with a hand of seven cards. There is one card face-up in the center of the table.");
            System.out.println("We take turns. It goes you, Alice, Bob, Charlie, you, Alice, Bob, Charlie, you, Alice, Bob, Charlie, and so on.");
            System.out.println("When it's your turn, you can place a card from your hand on top of the card in the center.");
            System.out.println("The card you play must be either the same suit or the same rank as the card in the center.");
            System.out.println("If you are unable to or don't want to play, take a card from the draw pile and add it to your hand instead of playing.");
            System.out.println("When someone empties their hand, they win, and the round is over.");
            System.out.println("The player who won creates a new secret rule, and the next round begins with the new rule in effect.");
            System.out.println("It is up to the other players to try to figure out the rule and play accordingly.");
            System.out.println("You may play as many rounds as you want, and all rules stay in effect as long as no other rule contradicts them.");
            System.out.println("Sometimes, a player may do something that you believe to be against the rules.");
            System.out.println("If this happens, punish them! Their action will be undone, and they will recieve a one-card penalty.");
            System.out.println("During the game, I (the computer) will ask you what you want to do.");
            System.out.println("If it's your turn, play or draw. If not, you can penalize another player or just sit tight and do nothing.");
            System.out.println("I think that just about covers everything. Get it?");
            System.out.println();
            System.out.println();
            System.out.println("1 - I understand and agree to the rules you just said. Let's play!");
            System.out.println("2 - I don't understand. Can you please repeat?");
            understand = spot.nextInt();
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
            case 4: str += "Charlie"; break;
        }
        switch((move/1000)%10){
            case 1: str += " played";
                    switch(move%100){
                        case 1: str += " an ace"; break;
                        case 8: str += " an 8"; break;
                        case 11: str += " a jack"; break;
                        case 12: str += " a queen"; break;
                        case 13: str += " a king"; break;
                        default: str += " a "+move%100;
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
    //Finding out who's turn it is.
    public static int whoTurn(int move1, int move2, ArrayList<Integer> rules){
        for(int i = rules.size()-1; i>=0; i--){
            if(rules.get(i)/1000000000==0){
                switch((rules.get(i)/1000000)%10){
                    case 1: if(itsAMatch(move2%1000,rules.get(i)%1000))
                                return (move2/10000+(rules.get(i)/10000000)%10)%4+1;
                    case 2: if((itsAMatch(move2%1000,rules.get(i)%1000))&&(itsAMatch(move1%1000,(rules.get(i)/1000)%1000)))
                                return (move2/10000+(rules.get(i)/10000000)%10)%4+1;
                }
            }
        }
        return (move2/10000)%4+1;
    }
    //Finding out what kind of card is playable.
    public static ArrayList<Integer> whatNext(int move, ArrayList<Integer> rules){
        ArrayList<Integer> veryGoodCards = new ArrayList<Integer>();
        for(int i = rules.size()-1; i>=0; i--){
            if(rules.get(i)/1000000000==1){
                if(itsAMatch(move%1000,(rules.get(i)/1000)%1000)){
                    veryGoodCards.add(rules.get(i)%1000);
                    return veryGoodCards;
                }
            }
        }
        veryGoodCards.add(200+(move/100)%10);
        int rank = 0;
        switch(move%100){
            case 1: rank = 600; break;
            case 13: rank = 700; break;
            case 12: rank = 800; break;
            case 11: rank = 900; break;
            default: rank = move%100; break;
        }
        veryGoodCards.add(rank);
        return veryGoodCards;
    }
    //Checking whether a certain card is of a certain type.
    public static Boolean itsAMatch(Card card, int type){
        switch(type/100){
            case 0: return card.getRank()==type%10;
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
    public static Boolean itsAMatch(int card, int type){
        switch(type/100){
            case 0: return card%100==type%10;
            case 1: if(card/100==2||card/100==3)
                        return type%10==1;
                    else
                        return type%10==2;
            case 2: return card/100==type%10;
            case 3: return card%100>(type/10)%10&&card%100<type%10+2;
            case 4: return card%100>1&&card%100<11&&card%100%(type%10)==0;
            case 5: return card%100>10&&card%100<14;
            case 6: return card%100==1;
            case 7: return card%100==13;
            case 8: return card%100==12;
            case 9: return card%100==11;
            default: return false; 
        }
    }
    public static String numToPlaya(int num){
        switch(num){
            case 1: return "Human";
            case 2: return "Alice";
            case 3: return "Bob";
            case 4: return "Charlie";
            default: return "Nobody";
        }
    }
    //Getting a rule from the human.
    public static int humanRule(){
        Scanner inspector = new Scanner(System.in);
        int rule = 0;
        int yea = 2;
        while(yea==2){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("What type of rule is this going to be?");
            System.out.println("1 - Skipping turns.");
            System.out.println("2 - Changing what type of cards may and must be played.");
            int genre = inspector.nextInt();
            if(genre==1){
                System.out.println("How many players will we be skipping? Type 1, 2, or 3.");
                int losers = inspector.nextInt();
                System.out.println("Under what condition will these players be skipped?");
                System.out.println("1 - When a card of a certain type is played.");
                System.out.println("2 - When a card of a certain type is played on a card of a certain type.");
                int numKillers = inspector.nextInt();
                if(numKillers == 1){
                    System.out.println("So what type of card makes these turns get skipped?");
                    int card1 = humanType();
                    rule = 100000000+losers*10000000+numKillers*1000000+card1;
                }else{
                    System.out.println("So what type of card must be played first?");
                    int card1 = humanType();
                    System.out.println("And what type of card must be played second?");
                    int card2 = humanType();
                    rule =  100000000+losers*10000000+numKillers*1000000+1000*card1+card2;
                }
            }else{
                System.out.println("So what type of card are we changing the requirements for?");
                int card1 = humanType();
                System.out.println("And what type of card must be played on this card?");
                int card2 = humanType();
                rule = 1100000000+card1*1000+card2;
            }
            System.out.println("Your rule is as follows: ");
            System.out.println(stringRule(rule));
            System.out.println("Is this correct?");
            System.out.println("1 - It's perfect! Let's get on with the game!");
            System.out.println("2 - That's not really what I had in mind. Can we please start over the rule-making process, sir?");
            yea = inspector.nextInt();
        }
        return rule;
    }
    //Getting a card type from the human.
    public static int humanType(){
        Scanner retriever = new Scanner(System.in);
        System.out.println("1 - Cards of a specific rank.");
        System.out.println("2 - Cards of a specific color.");
        System.out.println("3 - Cards of a specific suit.");
        System.out.println("4 - Just any face cards.");
        System.out.println("5 - Just any numbered cards.");
        System.out.println("6 - Numbered cards with rank in a specific range.");
        System.out.println("7 - Numbered cards with rank divisible by a specific number.");
        int genre = retriever.nextInt();
        switch(genre){
            case 1: System.out.println("Just type the number indicating the required rank.");
                    System.out.println("TIP: 1 = Ace, 11 = Jack, 12 = Queen, 13 = King.");
                    int rank = retriever.nextInt();
                    switch(rank){
                        case 1: return 600;
                        case 11: return 900;
                        case 12: return 800;
                        case 13: return 700;
                        default: return rank;
                    }
            case 2: System.out.println("What color?");
                    System.out.println("1 - red.");
                    System.out.println("2 - black.");
                    return 100+retriever.nextInt();
            case 3: System.out.println("What suit?");
                    System.out.println("1 - clubs.");
                    System.out.println("2 - diamonds.");
                    System.out.println("3 - hearts.");
                    System.out.println("4 - spades.");
                    return 200+retriever.nextInt();
            case 4: return 500;
            case 5: return 319;
            case 6: System.out.println("What is the minimum (exclusive)?");
                    int min = retriever.nextInt();
                    System.out.println("What is the maximum (exclusive)?");
                    int max = retriever.nextInt();
                    return 300+10*min+max-2;
            case 7: System.out.println("Divisible by what?");
                    System.out.println("TIP: This must be 2, 3, 4, or 5");
                    return 400+retriever.nextInt();
            default: return 0;
        }
    }
    public static Boolean wasThePenaltyRight(int lastMove, int secondLast, int thirdLast, int fourthLast, ArrayList<Integer> rules){
        switch(lastMove%10){
            case 1: return !(whoTurn(fourthLast, thirdLast, rules)==secondLast/10000);
            case 2: return !(whoTurn(fourthLast, thirdLast, rules)==secondLast/10000);
            case 3: return !(itsAMatch(secondLast%1000,whatNext(thirdLast,rules).get(0)))||itsAMatch(secondLast%1000,whatNext(thirdLast,rules).get(1));
            case 4: return true;
            default: return true;
        }
    }
}