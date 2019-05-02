import java.util.*;

/**
 * Get as close to 21 as possible without going over 21.
 *
 * @aj
 * @blackjack
 * 
 */
public class Blackjack
{
    private static Deck deck;
    private static ArrayList<ArrayList<Card>> p;
    private static ArrayList<Player> plyr;
    private static int total;
    
    // 
    private static int[] score;
    private static double[] bet;
    private static boolean[] bust;
    private static boolean[] natural;
    
    public static void Jack(){
        plyr = Casino.playing;
        deck = new Deck();
        p = new ArrayList<ArrayList<Card>>(); // p.get(total-1) = Dealer
        Scanner reader = new Scanner(System.in);
        plyr.add(new Player(0, "The Dealer"));
        total = plyr.size();
        
        for (int i = 0; i < total; i++){
            System.out.println(plyr.get(i).getName() + " " + plyr.get(i).getPlayer() + " " + i);
        }
        
        deck.shuffle();
        int players = Casino.players;
        score = new int[total];
        bet = new double[total];
        bust = new boolean[total];
        natural = new boolean[total];
        for (int i = 0; i < total-1; i++){
            bust[i] = false;
            natural[i] = false;
        }
        bet[0] = 0;
        for (int i = 0; i < total-1; i++){
            System.out.println(plyr.get(i).getName() + ", how much do you want to bet between $20 and $5,000?");
            
            Boolean valid = false;
            while (!valid){
                int want = reader.nextInt();
                if (want >= 20 && want <= 5000){
                    bet[i] = want;
                    valid = true;
                } else {
                    System.out.println("Please bet between $20 and $5,000.");
                }
            }
        }
        dealHand(p, total);
        System.out.println("\nDealer's visible hand: \n" + 
                           "- " + p.get(total-1).get(0) + "\n");
        for (int i = 0; i < total-1; i++){
            System.out.println(plyr.get(i).getName() + "'s Hand: \n" +
                               "- " + p.get(i).get(0) + "\n" +
                               "- " + p.get(i).get(1) + "\n");
        }
        // Calculates Starting Score
        for (int i = 0; i < total-1; i++){
            score[i] = score(p.get(i));
        }
        play(p, score, bust);
        
        
        if (bust[total-1]){
            System.out.println(plyr.get(total-1).getName() + " busted at " + score[total-1]);
        } else if (natural[total-1]){
            System.out.println(plyr.get(total-1).getName() + " had a natural " + score[total-1]);;
        } else{
            System.out.println(plyr.get(total-1).getName() + " scored " + score[total-1]);
        }
        
        for (int x = 0; x < total-1; x++){
            if (bust[x]){
                System.out.println(plyr.get(x).getName() + " busted at " + score[x]);
                plyr.get(x).loss(bet[x]);
                System.out.println(plyr.get(x).getName() + " " + plyr.get(x).getMoney());
            } else if (natural[x] || score[x] == 21){
                System.out.println(plyr.get(x).getName() + " had " + score[x] + "!");
                plyr.get(x).earn(bet[x] + bet[x] * .5);
                System.out.println(plyr.get(x).getName() + " " + plyr.get(x).getMoney());
            } else{
                System.out.println(plyr.get(x).getName() + " scored " + score[x]);
                if (bust[total-1] || score[x] > score[total-1]){
                    plyr.get(x).earn(bet[x]); // Earns money bet
                } else{
                    plyr.get(x).loss(bet[x]); // Loses money bet
                }
            }
        }
        
        
        
    }
    
    public static void dealHand(ArrayList<ArrayList<Card>> p, 
                                int players){
        // total = The Dealer
        for (int i = 0; i < total; i++){
            p.add(new ArrayList<Card>());
            p.get(i).add(deck.deal());
            p.get(i).add(deck.deal());
        }
    }
    
    public static void play(ArrayList<ArrayList<Card>> p, 
                            int[] score, boolean[] bust){                                                      
        Scanner reader = new Scanner(System.in);
        Boolean[] stay = new Boolean[total];
        for (int i = 0; i < total; i++){
           if ((score[i] == 21) && (i != 0)){ //Checks for Naturals
               System.out.println("!" + plyr.get(i).getName() + " has a Natural 21 !\n");
               natural[i] = true;
               stay[i] = true;
           } else {
               stay[i] = false;
           }
        }
        Boolean playing = true;
        // Players
        while (playing){
         for (int x = 0; x < total-1; x++){
          
          if (!bust[x] && !stay[x]){
           // Displays current hand
           System.out.println("~~" + plyr.get(x).getName() + "'s turn~~"); 
           score[x] = score(p.get(x));
           System.out.println(plyr.get(x).getName() + "'s current hand: ");
           for (int i = 0; i < p.get(x).size(); i++){
              System.out.println("- " + p.get(x).get(i));
           }
           System.out.println("Score: " + score(p.get(x)));
           
           System.out.println("Hit? [Y/N]");
           Boolean valid = false;
           while (!valid){
               String reply = reader.nextLine();
               if (reply.toUpperCase().equals("Y") || reply.toUpperCase().equals("YES")
                   || reply.toUpperCase().equals("HIT")){
                   p.get(x).add(deck.deal());
                   score[x] = score(p.get(x));
                   System.out.println(plyr.get(x).getName() + " was dealt a " + 
                               p.get(x).get(p.get(x).size()-1) +
                              "\nNew Score: " + score[x]);
                   // Checks if 21
                   if (score[x] == 21){
                       System.out.println(plyr.get(x).getName() + " reached 21!");
                       stay[x] = true;
                   }
                   // Checks if over 21
                   else if (score[x] > 21){
                       System.out.println("YOU B U S T E D");
                       bust[x] = true;
                   } 
                   valid = true;
                } else if (reply.toUpperCase().equals("N") || reply.toUpperCase().equals("NO")
                           || reply.toUpperCase().equals("STAY")){
                   stay[x] = true;
                   valid = true;
                } else {
                      System.out.println("Respond with Y or N.");
                }
            }
            System.out.println();
           }
         }
         int a = total-1; // Amount of players who have not busted or stayed
         for (int j = 0; j < total-1; j++){
            if (bust[j] || stay[j]){
                a--;
            }
         }
         if (a == 0){
            playing = false;
         }
       }
       
       // Dealer's Turn
       System.out.println("~~The Dealer's Turn~~");
       while (!bust[total-1] && !stay[total-1]){
           score[total-1] = score(p.get(total-1));
           System.out.println("The Dealer's current hand: ");
           for (int i = 0; i < p.get(total-1).size(); i++){
              System.out.println("- " + p.get(total-1).get(i));
           }
           System.out.println("Score: " + score(p.get(total-1)));
           
           if (score[total-1] >= 17){
               System.out.println("The Dealer has to stay.");
               stay[total-1] = true;
           } else if (score[total-1] < 17){
               p.get(total-1).add(deck.deal());
               score[total-1] = score(p.get(total-1));
               System.out.println(plyr.get(total-1).getName() + " was dealt a " + 
                                   p.get(total-1).get(p.get(total-1).size()-1) +
                                  "\nNew Score: " + score[total-1]);
               if (score[total-1] > 21){
                   System.out.println("The Dealer has B U S T E D");
                   bust[total-1] = true;
               }
           }
           System.out.println();
       }
    }
    
    public static int score(ArrayList<Card> hand){
        int score = 0;
        for (int i = 0; i < hand.size(); i++){
            score += value(hand.get(i));
        }
        
        if (score > 21){ // Checks if there are Aces
            int aceCount = 0;
            for (int i = 0; i < hand.size(); i++){
                if (score > 21 && hand.get(i).getRank() == 1){
                    score -= 10;   
                }
            }
        }
        return score;
    } 
    
    public static int value(Card card){
        int value = 0;
        
        if (card.getRank() > 10){
            value = 10;
        } else if (card.getRank() == 1) {
            value = 11;
        } else {
            value = card.getRank();
        }
        return value;
    }
}
