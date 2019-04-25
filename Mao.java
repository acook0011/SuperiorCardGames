import java.util.*;
/**
 * Mao.
 * Gavin's playhouse.
 */
public class Mao{
    Deck deck = new Deck();
    //Where you get.
    ArrayList<Card> drawPile = new ArrayList<Card>();
    //Where you play.
    ArrayList<Card> pile = new ArrayList<Card>();
    //Every move in the game.
    ArrayList<Integer> game = new ArrayList<Integer>();
    //Every draw or play in the game that passed judgement. These are moves that count as turns.
    ArrayList<Integer> drawPlay = new ArrayList<Integer>();
    //Everybody's hands.
    ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
    //The real rules, sorted by creator.
    ArrayList<ArrayList<Integer>> rules = new ArrayList<ArrayList<Integer>>();  
    //Each players's hypotheses for the most recent rule created by each player.
    ArrayList<ArrayList<Integer>> hypoSpecif = new ArrayList<ArrayList<Integer>>();
    //All of each player's hypotheses.
    ArrayList<ArrayList<Integer>> hypos = new ArrayList<ArrayList<Integer>>();
    //Parallel ArrayLists containing the situations of every time someone accused someone since the last time that someone created a rule.
    ArrayList<ArrayList<Integer>> m4 = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> m3 = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> m2 = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> m1 = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> topCards = new ArrayList<ArrayList<Integer>>();
    int rounds;
    public static void playMao(){
        Scanner searcher = new Scanner(System.in);
        System.out.println("Welcome, friend. Do you know how to play Computer Mao? It's a little different from traditional Mao.");
        System.out.println("1 - Yes, and I want to play now.");
        System.out.println("2 - Yes, but I don't want to play now.");
        System.out.println("3 - No, but I want to play now.");
        System.out.println("4 - No, and I don't want to play now.");
        int gamer = searcher.nextInt();
        switch(gamer){
            case 1: gamer();
                    break;
            case 2: System.out.println("I get it. No worries. Just come on back anytime if you change your mind.");
                    break;
            case 3: System.out.println("Pull up chair, fella. I can teach you.");
                    letsLearn();
                    gamer();
                    break;
            case 4: System.out.println("That's fine. There are plenty of other card games out there.");
                    break;
        }
    }
    public Mao(){
        //Dealing.
        deck.shuffle();
        ArrayList<Card> fhand = new ArrayList<Card>();
        ArrayList<Card> hhand = new ArrayList<Card>();
        ArrayList<Card> ahand = new ArrayList<Card>();
        ArrayList<Card> bhand = new ArrayList<Card>();
        ArrayList<Card> chand = new ArrayList<Card>();
        for(int i=0; i<7; i++){
            hhand.add(deck.deal());
            ahand.add(deck.deal());
            bhand.add(deck.deal());
            chand.add(deck.deal());
        }
        hands.add(fhand);
        hands.add(hhand);
        hands.add(ahand);
        hands.add(bhand);
        hands.add(chand);
        for(int i=0; i<24; i++)
            drawPile.add(deck.deal());
        pile.add(drawPile.remove(drawPile.size()-1));
        //Getting the ArrayLists ready.
        ArrayList<Integer> frules = new ArrayList<Integer>();
        ArrayList<Integer> hrules = new ArrayList<Integer>();
        ArrayList<Integer> arules = new ArrayList<Integer>();
        ArrayList<Integer> brules = new ArrayList<Integer>();
        ArrayList<Integer> crules = new ArrayList<Integer>();
        rules.add(frules);
        rules.add(hrules);
        rules.add(arules);
        rules.add(brules);
        rules.add(crules);
        ArrayList<Integer> fhypoSpecif = new ArrayList<Integer>();
        ArrayList<Integer> hhypoSpecif = new ArrayList<Integer>();
        for(int i=0; i<6; i++)
            hhypoSpecif.add(0);
        ArrayList<Integer> ahypoSpecif = new ArrayList<Integer>();
        for(int i=0; i<6; i++)
            ahypoSpecif.add(0);
        ArrayList<Integer> bhypoSpecif = new ArrayList<Integer>();
        for(int i=0; i<6; i++)
            bhypoSpecif.add(0);
        ArrayList<Integer> chypoSpecif = new ArrayList<Integer>();
        for(int i=0; i<6; i++)
            chypoSpecif.add(0);
        hypoSpecif.add(fhypoSpecif);
        hypoSpecif.add(hhypoSpecif);
        hypoSpecif.add(ahypoSpecif);
        hypoSpecif.add(bhypoSpecif);
        hypoSpecif.add(chypoSpecif);
        ArrayList<Integer> fhypos = new ArrayList<Integer>();
        ArrayList<Integer> hhypos = new ArrayList<Integer>();
        ArrayList<Integer> ahypos = new ArrayList<Integer>();
        ArrayList<Integer> bhypos = new ArrayList<Integer>();
        ArrayList<Integer> chypos = new ArrayList<Integer>();
        hypos.add(fhypos);
        hypos.add(hhypos);
        hypos.add(ahypos);
        hypos.add(bhypos);
        hypos.add(chypos);
        ArrayList<Integer> fm4 = new ArrayList<Integer>();
        ArrayList<Integer> hm4 = new ArrayList<Integer>();
        ArrayList<Integer> am4 = new ArrayList<Integer>();
        ArrayList<Integer> bm4 = new ArrayList<Integer>();
        ArrayList<Integer> cm4 = new ArrayList<Integer>();
        m4.add(fm4);
        m4.add(hm4);
        m4.add(am4);
        m4.add(bm4);
        m4.add(cm4);
        ArrayList<Integer> fm3 = new ArrayList<Integer>();
        ArrayList<Integer> hm3 = new ArrayList<Integer>();
        ArrayList<Integer> am3 = new ArrayList<Integer>();
        ArrayList<Integer> bm3 = new ArrayList<Integer>();
        ArrayList<Integer> cm3 = new ArrayList<Integer>();
        m3.add(fm3);
        m3.add(hm3);
        m3.add(am3);
        m3.add(bm3);
        m3.add(cm3);
        ArrayList<Integer> fm2 = new ArrayList<Integer>();
        ArrayList<Integer> hm2 = new ArrayList<Integer>();
        ArrayList<Integer> am2 = new ArrayList<Integer>();
        ArrayList<Integer> bm2 = new ArrayList<Integer>();
        ArrayList<Integer> cm2 = new ArrayList<Integer>();
        m2.add(fm2);
        m2.add(hm2);
        m2.add(am2);
        m2.add(bm2);
        m2.add(cm2);
        ArrayList<Integer> fm1 = new ArrayList<Integer>();
        ArrayList<Integer> hm1 = new ArrayList<Integer>();
        ArrayList<Integer> am1 = new ArrayList<Integer>();
        ArrayList<Integer> bm1 = new ArrayList<Integer>();
        ArrayList<Integer> cm1 = new ArrayList<Integer>();
        m1.add(fm1);
        m1.add(hm1);
        m1.add(am1);
        m1.add(bm1);
        m1.add(cm1);
        ArrayList<Integer> ftopCards = new ArrayList<Integer>();
        ArrayList<Integer> htopCards = new ArrayList<Integer>();
        ArrayList<Integer> atopCards = new ArrayList<Integer>();
        ArrayList<Integer> btopCards = new ArrayList<Integer>();
        ArrayList<Integer> ctopCards = new ArrayList<Integer>();
        topCards.add(ftopCards);
        topCards.add(htopCards);
        topCards.add(atopCards);
        topCards.add(btopCards);
        topCards.add(ctopCards);
        rounds=0;
    }
    //Accessors
    public ArrayList<Integer> game(){
        return game;
    }
    public ArrayList<ArrayList<Card>> hands(){
        return hands;
    }
    public ArrayList<Integer> drawPlay(){
        return drawPlay;
    }
    public ArrayList<ArrayList<Integer>> rules(){
        return rules;
    }
    public ArrayList<ArrayList<Integer>> hypoSpecif(){
        return hypoSpecif;
    }
    public ArrayList<ArrayList<Integer>> hypos(){
        return hypos;
    }
    public ArrayList<ArrayList<Integer>> m4(){
        return m4;
    }
    public ArrayList<ArrayList<Integer>> m3(){
        return m3;
    }
    public ArrayList<ArrayList<Integer>> m2(){
        return m2;
    }
    public ArrayList<ArrayList<Integer>> m1(){
        return m1;
    }
    public ArrayList<ArrayList<Integer>> topCards(){
        return topCards;
    }
    public int rounds(){
        return rounds;
    }
    public static void gamer(){
        Scanner stalker = new Scanner(System.in);
        System.out.println("How many rounds would you like to play?");
        int numRounds = stalker.nextInt();
        Mao chairman = new Mao();
        int wins=0; 
        while(chairman.rounds!=numRounds){
            if(chairman.game.size()==0){
                System.out.println();
                System.out.println();
                System.out.println(chairman.pile.get(0)+" is in the middle.");
            }
            //Human moves.
            int currentMove = humanMove(chairman.hands.get(1));
            if((currentMove/1000)%10==1||(currentMove/1000)%10==2){
                chairman.game.add(currentMove%100000);
                chairman.drawPlay.add(currentMove%100000);
                System.out.println(stringMove(currentMove%100000));
                if((currentMove/1000)%10==1)
                    chairman.pile.add(chairman.hands.get(1).remove((currentMove/100000)));
                if((currentMove/1000)%10==2){
                    chairman.hands.get(1).add(chairman.drawPile.remove(chairman.drawPile.size()-1));
                    if(chairman.drawPile.size()==0){
                        chairman.deck.reset();
                        for(int j=0; j<52; j++)
                        chairman.drawPile.add(chairman.deck.deal()); 
                    }
                }
                chairman.humanGetsRect();
            }
            if(chairman.hands.get(1).size()==0){
                System.out.println("You won!");
                wins++;
                chairman.rounds++;
                if(chairman.rounds()!=numRounds){
                    System.out.println("Now you get to create a new rule!");
                    chairman.humanWins();
                }
                continue;
            }
            //Alice moves.
            int aone=0;
            int atwo=0;
            if(chairman.drawPlay.size()>1){
                aone = (int)chairman.drawPlay.get(chairman.drawPlay.size()-1);
                atwo = (int)chairman.drawPlay.get(chairman.drawPlay.size()-2); 
            }
            if(chairman.drawPlay.size()==1){
                aone = (int)chairman.drawPlay.get(chairman.drawPlay.size()-1);
            }
            int aliceMove  = compMove(aone,atwo,numberCard(chairman.pile.get(chairman.pile.size()-1)),chairman.hypos.get(2),chairman.hands.get(2),2);
            if((aliceMove/1000)%10==1||(aliceMove/1000)%10==2){
                chairman.game.add(aliceMove%100000);
                chairman.drawPlay.add(aliceMove%100000);
                System.out.println(stringMove(aliceMove%100000));
                if((aliceMove/1000)%10==1)
                    chairman.pile.add(chairman.hands.get(2).remove((aliceMove/100000)));
                if((currentMove/1000)%10==2){
                    chairman.hands.get(2).add(chairman.drawPile.remove(chairman.drawPile.size()-1));
                    if(chairman.drawPile.size()==0){
                        chairman.deck.reset();
                        for(int j=0; j<52; j++)
                           chairman.drawPile.add(chairman.deck.deal()); 
                    }
                }
                chairman.aliceGetsRect();
            }
            if(chairman.hands.get(2).size()==0){
                System.out.println("Alice won!");
                chairman.rounds++;
                if(chairman.rounds()!=numRounds){
                    System.out.println("Now she gets to create a new rule!");
                    chairman.aliceWins();
                }
                continue;
            }
            //Bob moves.
            int bone=0;
            int btwo=0;
            if(chairman.drawPlay.size()>1){
                bone = (int)chairman.drawPlay.get(chairman.drawPlay.size()-1);
                btwo = (int)chairman.drawPlay.get(chairman.drawPlay.size()-2); 
            }
            if(chairman.drawPlay.size()==1){
                bone = (int)chairman.drawPlay.get(chairman.drawPlay.size()-1);
            }
            int bobMove  = compMove(aone,atwo,numberCard(chairman.pile.get(chairman.pile.size()-1)),chairman.hypos.get(3),chairman.hands.get(3),3);
            if((bobMove/1000)%10==1||(bobMove/1000)%10==2){
                chairman.game.add(bobMove%100000);
                chairman.drawPlay.add(bobMove%100000);
                System.out.println(stringMove(bobMove%100000));
                if((bobMove/1000)%10==1)
                    chairman.pile.add(chairman.hands.get(3).remove((bobMove/100000)));
                if((currentMove/1000)%10==2){
                    chairman.hands.get(3).add(chairman.drawPile.remove(chairman.drawPile.size()-1));
                    if(chairman.drawPile.size()==0){
                        chairman.deck.reset();
                        for(int j=0; j<52; j++)
                            chairman.drawPile.add(chairman.deck.deal()); 
                    }
                }
                chairman.bobGetsRect();
            }
            if(chairman.hands.get(3).size()==0){
                System.out.println("Bob won!");
                chairman.rounds++;
                if(chairman.rounds()!=numRounds){
                    System.out.println("Now he gets to create a new rule!");
                    chairman.bobWins();
                }
                continue;
            }
            //Charlie moves.
            int cone=0;
            int ctwo=0;
            if(chairman.drawPlay.size()>1){
                cone = (int)chairman.drawPlay.get(chairman.drawPlay.size()-1);
                ctwo = (int)chairman.drawPlay.get(chairman.drawPlay.size()-2); 
            }
            if(chairman.drawPlay.size()==1){
                cone = (int)chairman.drawPlay.get(chairman.drawPlay.size()-1);
            }
            int charlieMove  = compMove(cone,ctwo,numberCard(chairman.pile.get(chairman.pile.size()-1)),chairman.hypos.get(4),chairman.hands.get(4),4);
            if((charlieMove/1000)%10==1||(charlieMove/1000)%10==2){
                chairman.game.add(charlieMove%100000);
                chairman.drawPlay.add(charlieMove%100000);
                System.out.println(stringMove(charlieMove%100000));
                if((charlieMove/1000)%10==1)
                    chairman.pile.add(chairman.hands.get(4).remove((charlieMove/100000)));
                if((currentMove/1000)%10==2){
                    chairman.hands.get(4).add(chairman.drawPile.remove(chairman.drawPile.size()-1));
                    if(chairman.drawPile.size()==0){
                        chairman.deck.reset();
                        for(int j=0; j<52; j++)
                           chairman.drawPile.add(chairman.deck.deal()); 
                    }
                }
                chairman.charlieGetsRect();
            }
            if(chairman.hands.get(4).size()==0){
                System.out.println("Charlie won!");
                chairman.rounds++;
                if(chairman.rounds()!=numRounds){
                    System.out.println("Now he gets to create a new rule!");
                    chairman.charlieWins();
                }
                continue;
            }
        }
        System.out.println();
        System.out.println("You won "+wins+" out of "+numRounds+" rounds. Not bad!");
        System.out.println("Here were the secret rules.");
        for(int i=0; i<chairman.rules.size(); i++){
            for(int j=0; j<chairman.rules.get(i).size(); j++)
                System.out.println(chairman.rules.get(i).get(j));
        }
    }
    public static void letsLearn(){
        Scanner spot = new Scanner(System.in);
        int understand = 2;
        while(understand==2){
            System.out.println();
            System.out.println("Mao is a shedding card game. That means that the object of the game is to get rid of all the cards in your hand.");
            System.out.println("Every player starts with a hand of seven cards. There is one card face-up in the center of the table.");
            System.out.println("We take turns. It goes you, Alice, Bob, Charlie, you, Alice, Bob, Charlie, you, Alice, Bob, Charlie, and so on.");
            System.out.println();
            System.out.println("When it's your turn, you can place a card from your hand on top of the card in the center.");
            System.out.println("The card you play must be either the same suit or the same rank as the card in the center.");
            System.out.println("If you are unable to or don't want to play, take a card from the draw pile and add it to your hand instead of playing.");
            System.out.println("Just FYI, when the draw pile is exhausted, we simply grab another deck and keep on truckin'.");
            System.out.println();
            System.out.println("When someone empties their hand, they win, and the round is over.");
            System.out.println("The player who won creates a new secret rule, and the next round begins with the new rule in effect.");
            System.out.println("It is up to the other players to try to figure out the rule and play accordingly.");
            System.out.println("You may play as many rounds as you want, and all rules stay in effect, newer ones overriding older ones.");
            System.out.println();
            System.out.println("Sometimes, a player may do something that you believe to be against the rules.");
            System.out.println("If this happens, punish them! Their action will be undone, and they will recieve a one-card penalty.");
            System.out.println("However, penalty cards are not awarded for drawing out of turn. That would be like arresting someone for breaking into jail.");
            System.out.println("Remember-you can only punish the person who did something most recently.");
            System.out.println();
            System.out.println("During the game, I (the computer) will ask you what you want to do.");
            System.out.println("If you think it's your turn, play or draw. If not, just do nothing.");
            System.out.println();
            System.out.println("One more thing: remember that all but the most fundamental rules are enforced by the players themselves.");
            System.out.println("For this reason, Mao is the most fun if players consider obeying rules their first priority and winning their second priority.");
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
    public static int numberCard(Card card){
        return 100*numberSuit(card)+card.getRank();
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
            case 1: str += " You:"; break;
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
    //Better than that.
    public static ArrayList<Integer> whatNext(ArrayList<Integer> rules, int card){
        ArrayList<Integer> veryGoodCards = new ArrayList<Integer>();
        for(int i = rules.size()-1; i>=0; i--){
            if(rules.get(i)/1000000000==1){
                if(itsAMatch(card,(rules.get(i)/1000)%1000)){
                    veryGoodCards.add(rules.get(i)%1000);
                    return veryGoodCards;
                }
            }
        }
        veryGoodCards.add(200+card/100);
        int rank = 0;
        switch(card%100){
            case 1: rank = 600; break;
            case 13: rank = 700; break;
            case 12: rank = 800; break;
            case 11: rank = 900; break;
            default: rank = card%100; break;
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
            case 1: return "you";
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
    //Was the penalty right?
    public static Boolean wasThePenaltyRight(int lastMove, int secondLast, int thirdLast, int fourthLast, int topCard, ArrayList<Integer> rules){
        switch(lastMove%10){
            case 1: return !(whoTurn(fourthLast, thirdLast, rules)==secondLast/10000);
            case 2: if(whatNext(rules,topCard).size()==1)
                        return !itsAMatch(secondLast%1000, whatNext(rules,topCard).get(0));
                    return !((itsAMatch(secondLast%1000, whatNext(rules, topCard).get(0)))||itsAMatch(secondLast%1000,whatNext(rules,topCard).get(1)));
            case 3: return !(whoTurn(fourthLast, thirdLast, rules)==secondLast/10000);
            case 4: return true;
            default: return true;
        }
    }
    //Returns an int indicating what position in the players hand should be played from. -1 if no card is playable.
    public static int whatToPlay(int topCard, ArrayList<Card> hand, ArrayList<Integer> rules){
        for(int i=0; i<hand.size(); i++){
            if(whatNext(rules,topCard).size()==1){
                if(itsAMatch(hand.get(i),whatNext(rules,topCard).get(0)))
                    return i;
            }else{
                if(itsAMatch(hand.get(i), whatNext(rules,topCard).get(0))||itsAMatch(hand.get(i), whatNext(rules,topCard).get(1)))
                    return i;
            }
        }
        return -1;
    }
    //Returns a rule that makes sense for every situation.
    public static int science(ArrayList<Integer> p1, ArrayList<Integer> p2, ArrayList<Integer> p3, ArrayList<Integer> p4,ArrayList<Integer> ptopCard, ArrayList<Integer> knownRules, int who){
        Boolean done = false;
        int counter = 0;
        while(!done){
            int hypo = randyRules(who);
            knownRules.add(hypo);
            counter++;
            Boolean itsGood = true;
            for(int i=0; i<p1.size(); i++){
                if(!wasThePenaltyRight(p1.get(i), p2.get(i), p3.get(i), p4.get(i), ptopCard.get(i), knownRules))
                    itsGood = false;
            }
            if(itsGood||counter==1000)
                return hypo;
            knownRules.remove(knownRules.size()-1);
        }
        return 0;
    }
    //Undoing a crime.
    public void makeItBetter(int accusation){
        System.out.println(stringMove(accusation));
        if(drawPile.size()==0){
            deck.reset();
            for(int j=0; j<52; j++)
                drawPile.add(deck.deal()); 
        }
        switch(accusation%10){
            case 1: hands.get((accusation/100)%10).add(pile.remove(pile.size()-1));
                    hands.get((accusation/100)%10).add(drawPile.remove(drawPile.size()-1));
                    drawPlay.remove(drawPlay.size()-1);
                    switch((accusation/100)%10){
                        case 1: System.out.println("The card was returned to your hand and you were dealt a penalty card.");
                                break;
                        case 2: System.out.println("The card was returnted to her hand and she was dealt a penalty card.");
                                break;
                        default:System.out.println("The card was returned to his hand and he was dealt a penalty card.");
                                break;
                    }
                    break;
            case 2: hands.get((accusation/100)%10).add(pile.remove(pile.size()-1));
                    hands.get((accusation/100)%10).add(drawPile.remove(drawPile.size()-1));
                    drawPlay.remove(drawPlay.size()-1);
                    switch((accusation/100)%10){
                        case 1: System.out.println("The card was returned to your hand and you were dealt a penalty card.");
                                break;
                        case 2: System.out.println("The card was returnted to her hand and she was dealt a penalty card.");
                                break;
                        default:System.out.println("The card was returned to his hand and he was dealt a penalty card.");
                                break;
                    }
                    break;
            case 3: drawPile.add(hands.get((accusation/100)%10).remove(hands.get((accusation/100)%10).size()-1));
                    System.out.println("The card was returned to the draw pile.");
                    break;
            case 4: makeItEvenBetter(game.size()-2);
                    break;
        }
    }
    //Redoing a crime.
    public void makeItEvenBetter(int positionOfFaultyAccusation){
        if(drawPile.size()==0){
            deck.reset();
            for(int j=0; j<52; j++)
                drawPile.add(deck.deal()); 
        }
        int personWhoWronglyAccused = (game.get(positionOfFaultyAccusation)/10000)%10;
        int personWhoWasWronglyAccused = (game.get(positionOfFaultyAccusation-1)/10000)%10;
        String pwwa = numToPlaya(personWhoWronglyAccused);
        if(personWhoWronglyAccused==0)
            pwwa = "You";
        String pwwwa = numToPlaya(personWhoWasWronglyAccused);
        if(personWhoWasWronglyAccused==0)
            pwwwa = "You";
        switch(game.get(positionOfFaultyAccusation)%10){
            case 1: pile.add(hands.get(personWhoWasWronglyAccused).remove(hands.get(personWhoWasWronglyAccused).size()-2));
                    drawPlay.add(personWhoWasWronglyAccused*10000+1000+numberCard(pile.get(pile.size()-1)));
                    hands.get(personWhoWronglyAccused).add(hands.get(personWhoWasWronglyAccused).remove(hands.get(personWhoWasWronglyAccused).size()-1));
                    hands.get(personWhoWronglyAccused).add(drawPile.remove(drawPile.size()-1));
                    System.out.println("The card played by "+numToPlaya(personWhoWasWronglyAccused)+" was returned to the center of the table.");
                    System.out.println("The penalty card given by "+numToPlaya(personWhoWronglyAccused)+" and an additional penalty card were given to "+numToPlaya(personWhoWronglyAccused)+".");
                    break;
            case 2: pile.add(hands.get(personWhoWasWronglyAccused).remove(hands.get(personWhoWasWronglyAccused).size()-2));
                    drawPlay.add(personWhoWasWronglyAccused*10000+1000+numberCard(pile.get(pile.size()-1)));
                    hands.get(personWhoWronglyAccused).add(hands.get(personWhoWasWronglyAccused).remove(hands.get(personWhoWasWronglyAccused).size()-1));
                    hands.get(personWhoWronglyAccused).add(drawPile.remove(drawPile.size()-1));
                    System.out.println("The card played by "+numToPlaya(personWhoWasWronglyAccused)+" was returned to the center of the table.");
                    System.out.println("The penalty card given by "+numToPlaya(personWhoWronglyAccused)+" and an additional penalty card were given to "+numToPlaya(personWhoWronglyAccused)+".");
                    break;
            case 3: hands.get(personWhoWasWronglyAccused).add(drawPile.remove(drawPile.size()-1));
                    hands.get(personWhoWronglyAccused).add(drawPile.remove(drawPile.size()-1));
                    System.out.println(pwwwa+" got the card back.");
                    System.out.println(pwwa+" got a penalty card.");
                    break;
            case 4: makeItEvenBetter(positionOfFaultyAccusation-2);
                    break;
        }
    }
    //The human can accuse.
    public int humanAccuse(int lastMove){
        Scanner sniffer = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("0 - Do nothing.");
        int result=0;
        switch((lastMove/1000)%10){
            case 1: System.out.println("1 - Penalize "+numToPlaya(lastMove/10000)+" for playing out of turn.");
                    System.out.println("2 - Penalize "+numToPlaya(lastMove/10000)+" for playing an incorrect card.");
                    int choix = sniffer.nextInt();
                    if(choix==1||choix==2)
                        result=13000+(lastMove/10000)*100+choix;
                    break;
            case 2: System.out.println("1 - Penalize "+numToPlaya(lastMove/10000)+" for drawing out of turn.");
                    int choiy = sniffer.nextInt();
                    if(choiy==1)
                        result=13003+(lastMove/10000)*100;
                    break;
            case 3: System.out.println("1 - Penalize "+numToPlaya(lastMove/10000)+" for false judgement.");
                    int choiz = sniffer.nextInt();
                    if(choiz==1)
                        result=13004+(lastMove/10000)*100;
                    break;
        }
        int four=0;
        int three=0;
        int two=0;
        int one=0;
        int top=0;
        if(game.size()>3)
            four=game.get(game.size()-4);
        m4.get(1).add(four);
        if(game.size()>2)
            three=game.get(game.size()-3);
        m3.get(1).add(three);
        if(game.size()>1)
            two=game.get(game.size()-2);
        m2.get(1).add(two);
        m1.get(1).add(game.get(game.size()-1));
        if(pile.size()>0)
            top=numberCard(pile.get(pile.size()-1));
        topCards.get(1).add(top);
        return result;
    }
    //The computer can accuse.
    public int compAccuse(int whoAmI, int fourthLast, int thirdLast, int secondLast, int last, int thirdLastPD, int secondLastPD, int lastPD, int prevTopCard, ArrayList<Integer> rules){
        int result=0;
        switch((last/1000)%10){
            case 1: if(whoTurn(thirdLastPD, secondLastPD, rules)!=last/10000)
                        result=whoAmI*10000+3000+(last/10000)*100+1;
                    if(whatNext(rules,prevTopCard).size()==1){
                        if(!itsAMatch(last%1000,whatNext(rules,prevTopCard).get(0)))
                            result=whoAmI*10000+3000+(last/10000)*100+2;
                    }else{
                        if(!itsAMatch(last%1000,whatNext(rules,prevTopCard).get(0))&&!itsAMatch(last%1000,whatNext(rules,prevTopCard).get(1)))
                            result=whoAmI*10000+3000+(last/10000)*100+2;
                    }
                    break;
            case 2: if(whoTurn(thirdLastPD, secondLastPD, rules)!=last/10000)
                        result=whoAmI*10000+3000+(last/10000)*100+3;
                    break;
            case 3: if(!wasThePenaltyRight(last, secondLast, thirdLast, fourthLast, prevTopCard, rules))
                        result=whoAmI*10000+3000+(last/10000)*100+4;
                    break;
        }
        int four=0;
        int three=0;
        int two=0;
        int one=0;
        int top=0;
        if(game.size()>3)
            four=game.get(game.size()-4);
        m4.get(whoAmI).add(four);
        if(game.size()>2)
            three=game.get(game.size()-3);
        m3.get(whoAmI).add(three);
        if(game.size()>1)
            two=game.get(game.size()-2);
        m2.get(whoAmI).add(two);
        m1.get(whoAmI).add(game.get(game.size()-1));
        if(pile.size()>0)
            top=numberCard(pile.get(pile.size()-1));
        topCards.get(whoAmI).add(top);
        return result;
    }
    //Human move. Returns a six-digit integer where the last five are what happened and the first is what position in hand the player is plaing from.
    public static int humanMove(ArrayList<Card> phand){
        Scanner hawk = new Scanner(System.in);
        System.out.println("Here is your hand:");
        for(int i=0; i<phand.size(); i++){
            System.out.println((i+1)+": "+phand.get(i));
        }
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("0 - Do nothing.");
        System.out.println("1 - Play a card.");
        System.out.println("2 - Draw a card.");
        int desire = hawk.nextInt();
        if(desire==1){
            System.out.println("Type a number indicating what position in your hand you would like to play from.");
            int cardy = hawk.nextInt();
            return (cardy-1)*100000+11000+numberCard(phand.get(cardy-1));
        }
        if(desire==2){
            return 12000;
        }
        return 0;
    }
    //Computer move. Returns a six-digit integer where the last five are what happened and the first is what position in hand the player is playing from.
    public static int compMove(int lastPD, int secondLastPD, int topCard, ArrayList<Integer> rulies, ArrayList<Card> hand, int whoAmI){
        if(whoTurn(secondLastPD, lastPD, rulies)!=whoAmI)
            return 0;
        for(int i=0; i<hand.size(); i++){
            if(whatNext(rulies,topCard).size()==1){
                if(itsAMatch(hand.get(i),whatNext(rulies,topCard).get(0)))
                    return i*100000+whoAmI*10000+1000+numberCard(hand.get(i));
            }else{
                if(itsAMatch(hand.get(i),whatNext(rulies,topCard).get(0))||itsAMatch(hand.get(i),whatNext(rulies,topCard).get(1)))
                    return i*100000+whoAmI*10000+1000+numberCard(hand.get(i));
            }    
        }
        return whoAmI*10000+2000;
    }
    //Everyone can penalize human.
    public void humanGetsRect(){
        int fourthLast=0;
        int thirdLast=0;
        int secondLast=0;
        int dpLast=0;
        int dp2Last=0;
        int dp3Last=0;
        int secondTop=0;
        if(pile.size()>1)
            secondTop=numberCard(pile.get(pile.size()-2));
        if(drawPlay.size()>0)
            dpLast=(int)drawPlay.get(drawPlay.size()-1);
        if(drawPlay.size()>1)
            dp2Last=drawPlay.get(drawPlay.size()-2);
        if(drawPlay.size()>2)
            dp3Last=drawPlay.get(drawPlay.size()-3);
        if(game.size()>1)
            secondLast=(int)game.get(game.size()-2);
        if(game.size()>2)
            thirdLast=(int)game.get(game.size()-3);
        if(game.size()>3)
            fourthLast=(int)game.get(game.size()-4);
        int newAoi=0;
        if(rules.get(1).size()>0){
            newAoi=science(m1.get(1),m2.get(1),m3.get(1),m4.get(1),topCards.get(1),hypos.get(2),1);
            hypoSpecif.get(2).set(1,newAoi);
        }
        for(int i=hypos.get(2).size()-1; i>0; i--){
            if((hypos.get(2).get(i)/100000000)%10==1)
                hypos.get(2).set(i,newAoi);
        }
        int newBoi=0;
        if(rules.get(1).size()>0){
            newAoi=science(m1.get(1),m2.get(1),m3.get(1),m4.get(1),topCards.get(1),hypos.get(3),1);
            hypoSpecif.get(3).set(1,newBoi);
        }
        for(int i=hypos.get(3).size()-1; i>0; i--){
            if((hypos.get(3).get(i)/100000000)%10==1)
                hypos.get(3).set(i,newBoi);
        }
        int newCoi=0;
        if(rules.get(1).size()>0){
            newCoi=science(m1.get(1),m2.get(1),m3.get(1),m4.get(1),topCards.get(1),hypos.get(4),1);
            hypoSpecif.get(4).set(1,newCoi);
        }
        for(int i=hypos.get(4).size()-1; i>0; i--){
            if((hypos.get(4).get(i)/100000000)%10==1)
                hypos.get(4).set(i,newCoi);
        }
        int aliceMove = compAccuse(2,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(2));
        if(aliceMove!=0){
            game.add(aliceMove);
            makeItBetter(aliceMove);
            aliceGetsRect();
        }else{
            int bobMove = compAccuse(3,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(3));
            if(bobMove!=0){
                game.add(bobMove);
                makeItBetter(bobMove);
                bobGetsRect();
            }else{
                int charlieMove = compAccuse(4,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(4));
                if(charlieMove!=0){
                    game.add(charlieMove);
                    makeItBetter(charlieMove);
                    charlieGetsRect();
                }
            }
        }
    }
    public void aliceGetsRect(){
        int fourthLast=0;
        int thirdLast=0;
        int secondLast=0;
        int dpLast=0;
        int dp2Last=0;
        int dp3Last=0;
        int secondTop=0;
        if(pile.size()>1)
            secondTop=numberCard(pile.get(pile.size()-2));
        if(drawPlay.size()>0)
            dpLast=(int)drawPlay.get(drawPlay.size()-1);
        if(drawPlay.size()>1)
            dp2Last=(int)drawPlay.get(drawPlay.size()-2);
        if(drawPlay.size()>2)
            dp3Last=(int)drawPlay.get(drawPlay.size()-3);
        if(game.size()>1)
            secondLast=(int)game.get(game.size()-2);
        if(game.size()>2)
            thirdLast=(int)game.get(game.size()-3);
        if(game.size()>3)
            fourthLast=(int)game.get(game.size()-4);
        int newBoi=0;
        if(rules.get(2).size()>0){
            newBoi=science(m1.get(2),m2.get(2),m3.get(2),m4.get(2),topCards.get(2),hypos.get(3),2);
            hypoSpecif.get(3).set(2,newBoi);
        }
        for(int i=hypos.get(3).size()-1; i>0; i--){
            if((hypos.get(3).get(i)/100000000)%10==2)
                hypos.get(3).set(i,newBoi);
        }
        int newCoi=0;
        if(rules.get(2).size()>0){
            newCoi=science(m1.get(2),m2.get(2),m3.get(2),m4.get(2),topCards.get(2),hypos.get(4),2);
            hypoSpecif.get(4).set(2,newCoi);
        }
        for(int i=hypos.get(4).size()-1; i>0; i--){
            if((hypos.get(4).get(i)/100000000)%10==2)
            hypos.get(4).set(i,newCoi);
        }
        int bobMove = compAccuse(3,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(3));
        if(bobMove!=0){
            game.add(bobMove);
            makeItBetter(bobMove);
            bobGetsRect();
        }else{
            int charlieMove = compAccuse(4,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(4));
            if(charlieMove!=0){
                game.add(charlieMove);
                makeItBetter(charlieMove);
                charlieGetsRect();
            }else{
                int humanMove = humanAccuse(game.get(game.size()-1));
                if(humanMove!=0){
                    game.add(humanMove);
                    makeItBetter(humanMove);
                    humanGetsRect();
                }
            }
        }
    }
    public void bobGetsRect(){
        int fourthLast=0;
        int thirdLast=0;
        int secondLast=0;
        int dpLast=0;
        int dp2Last=0;
        int dp3Last=0;
        int secondTop=0;
        if(pile.size()>1)
            secondTop=numberCard(pile.get(pile.size()-2));
        if(drawPlay.size()>0)
            dpLast=(int)drawPlay.get(drawPlay.size()-1);
        if(drawPlay.size()>1)
            dp2Last=drawPlay.get(drawPlay.size()-2);
        if(drawPlay.size()>2)
            dp3Last=drawPlay.get(drawPlay.size()-3);
        if(game.size()>1)
            secondLast=(int)game.get(game.size()-2);
        if(game.size()>2)
            thirdLast=(int)game.get(game.size()-3);
        if(game.size()>3)
            fourthLast=(int)game.get(game.size()-4);
        int newAoi=0;
        if(rules.get(3).size()>0){
            newAoi=science(m1.get(3),m2.get(3),m3.get(3),m4.get(3),topCards.get(3),hypos.get(2),3);
            hypoSpecif.get(2).set(3,newAoi);
        }
        for(int i=hypos.get(2).size()-1; i>0; i--){
            if((hypos.get(2).get(i)/100000000)%10==3)
            hypos.get(2).set(i,newAoi);
        }
        int newCoi=0;
        if(rules.get(3).size()>0){
            newCoi=science(m1.get(3),m2.get(3),m3.get(3),m4.get(3),topCards.get(3),hypos.get(4),3);
            hypoSpecif.get(4).set(3,newCoi);
        }
        for(int i=hypos.get(4).size()-1; i>0; i--){
            if((hypos.get(4).get(i)/100000000)%10==3)
                hypos.get(4).set(i,newCoi);
        }
        int charlieMove = compAccuse(4,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(4));
        if(charlieMove!=0){
            game.add(charlieMove);
            makeItBetter(charlieMove);
            charlieGetsRect();
        }else{
            int humanMove = humanAccuse(game.get(game.size()-1));
            if(humanMove!=0){
                game.add(humanMove);
                makeItBetter(humanMove);
                humanGetsRect();
            }else{
                int aliceMove = compAccuse(2,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(2));
                if(aliceMove!=0){
                    game.add(aliceMove);
                    makeItBetter(aliceMove);
                    aliceGetsRect();
                }
            }
        }
    }
    public void charlieGetsRect(){
        int fourthLast=0;
        int thirdLast=0;
        int secondLast=0;
        int dpLast=0;
        int dp2Last=0;
        int dp3Last=0;
        int secondTop=0;
        if(pile.size()>1)
            secondTop=numberCard(pile.get(pile.size()-2));
        if(drawPlay.size()>0)
            dpLast=(int)drawPlay.get(drawPlay.size()-1);
        if(drawPlay.size()>1)
            dp2Last=drawPlay.get(drawPlay.size()-2);
        if(drawPlay.size()>2)
            dp3Last=drawPlay.get(drawPlay.size()-3);
        if(game.size()>1)
            secondLast=(int)game.get(game.size()-2);
        if(game.size()>2)
            thirdLast=(int)game.get(game.size()-3);
        if(game.size()>3)
            fourthLast=(int)game.get(game.size()-4);
        int newAoi=0;
        if(rules.get(4).size()>0){
            newAoi=science(m1.get(4),m2.get(4),m3.get(4),m4.get(4),topCards.get(4),hypos.get(2),4);
            hypoSpecif.get(2).set(4,newAoi);
        }
        for(int i=hypos.get(2).size()-1; i>0; i--){
            if((hypos.get(2).get(i)/100000000)%10==4)
            hypos.get(2).set(i,newAoi);
        }
        int newBoi=0;
        if(rules.get(4).size()>0){
            newBoi=science(m1.get(4),m2.get(4),m3.get(4),m4.get(4),topCards.get(4),hypos.get(3),4);
            hypoSpecif.get(3).set(4,newBoi);
        }
        for(int i=hypos.get(3).size()-1; i>0; i--){
            if((hypos.get(3).get(i)/100000000)%10==4)
            hypos.get(3).set(i,newBoi);
        }
        int humanMove = humanAccuse(game.get(game.size()-1));
        if(humanMove!=0){
            game.add(humanMove);
            makeItBetter(humanMove);
            humanGetsRect();
        }else{
            int aliceMove = compAccuse(2,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(2));
            if(aliceMove!=0){
                game.add(aliceMove);
                makeItBetter(aliceMove);
                aliceGetsRect();
            }else{
                int bobMove = compAccuse(3,fourthLast,thirdLast,secondLast,(int)game.get(game.size()-1),dp3Last,dp2Last,dpLast,secondTop,hypos.get(3));
                if(bobMove!=0){
                    game.add(bobMove);
                    makeItBetter(bobMove);
                    bobGetsRect();
                }
            }
        }
    }
    public void humanWins(){
        deck.reset();
        for(int i=1; i<5; i++){
            for(int j=hands.get(i).size()-1; j>-1; j--)
                hands.get(i).remove(j);
        }
        deck.shuffle();
        for(int i=game.size()-1; i>-1; i--)
            game.remove(i);
        for(int i=drawPlay.size()-1; i>0; i--)
            drawPlay.remove(i);
        for(int i=0; i<7; i++){
            for(int j=1; j<5; j++)
                hands.get(j).add(deck.deal());
        }
        rules.get(1).add(humanRule());
        for(int i=2; i<5; i++){
            int guess = randyRules(i);
            hypos.get(i).add(guess);
            hypoSpecif.get(i).set(1,guess);
        }
        for(int i=0; i<24; i++)
            drawPile.add(deck.deal());
        pile.add(drawPile.remove(drawPile.size()-1));
    }
    public void aliceWins(){
        deck.reset();
        for(int i=1; i<5; i++){
            for(int j=hands.get(i).size()-1; j>-1; j--)
                hands.get(i).remove(j);
        }
        deck.shuffle();
        for(int i=game.size()-1; i>-1; i--)
            game.remove(i);
        for(int i=drawPlay.size()-1; i>0; i--)
            drawPlay.remove(i);
        for(int i=0; i<7; i++){
            for(int j=1; j<5; j++)
                hands.get(j).add(deck.deal());
        }
        for(int i=0; i<24; i++)
            drawPile.add(deck.deal());
        pile.add(drawPile.remove(drawPile.size()-1));
        rules.get(2).add(randyRules(2));
        for(int i=3; i<5; i++){
            int guess = randyRules(i);
            hypos.get(i).add(guess);
            hypoSpecif.get(i).set(1, guess);
        }
    }
    public void bobWins(){
        deck.reset();
        for(int i=1; i<5; i++){
            for(int j=hands.get(i).size()-1; j>-1; j--)
                hands.get(i).remove(j);
        }
        deck.shuffle();
        for(int i=game.size()-1; i>-1; i--)
            game.remove(i);
        for(int i=drawPlay.size()-1; i>0; i--)
            drawPlay.remove(i);
        for(int i=0; i<7; i++){
            for(int j=1; j<5; j++)
                hands.get(j).add(deck.deal());
        }
        for(int i=0; i<24; i++)
            drawPile.add(deck.deal());
        pile.add(drawPile.remove(drawPile.size()-1));
        rules.get(3).add(randyRules(3));
        int aguess = randyRules(2);
        hypos.get(2).add(aguess);
        hypoSpecif.get(2).set(3,aguess);
        int cguess = randyRules(4);
        hypos.get(4).add(aguess);
        hypoSpecif.get(4).set(3,aguess);
    }
    public void charlieWins(){
        deck.reset();
        for(int i=1; i<5; i++){
            for(int j=hands.get(i).size()-1; j>-1; j--)
                hands.get(i).remove(j);
        }
        deck.shuffle();
        for(int i=game.size()-1; i>-1; i--)
            game.remove(i);
        for(int i=drawPlay.size()-1; i>0; i--)
            drawPlay.remove(i);
        for(int i=0; i<7; i++){
            for(int j=1; j<5; j++)
                hands.get(j).add(deck.deal());
        }
        for(int i=0; i<24; i++)
            drawPile.add(deck.deal());
        pile.add(drawPile.remove(drawPile.size()-1));
        rules.get(3).add(randyRules(3));
        for(int i=2; i<4; i++){
            int guess = randyRules(i);
            hypos.get(i).add(guess);
            hypoSpecif.get(i).set(4,guess);
        }
    }
}