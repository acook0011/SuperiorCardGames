import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * We will battle our holdem Machines and see who
 * will win in the end
 *
 * @author GGGGGGGGGGG
 * @version April Fools Day 2019
 */

public class HoldEm
{
   // I use arrayLists and arrays so that I can keep the number
   // of players dynamic
   private ArrayList<Card[]> hands;         // keeps track of all the hands
   
   private ArrayList<Integer> money;        // keeps track of all the money
   
   private int numPlayers;                  // keeps track of the number of players
   
   Scanner input = new Scanner(System.in);  // scanner for reading user input throughout
                                            // the game
                                            
   private Deck d;                          // the deck of the game
   
   private Card[] river;                    // the river that is dealt out and any
                                            // player can use and play off of
                                            
   private int dealer;                      // integer representation of the dealer
   
   private int turn;                        // represents which player's turn it is
   
   private int blind;                       // represents the value of the big blind
   
   private Random randy = new Random();     // used for random chance stuff
   
   private boolean[] allIn;                 // keeps track of whether each player
                                            // is all in, iniitalized to all false
                                            // at the beginning of every hand
   
   private boolean[] folded;                // keeps track of whether each player
                                            // has folded during each hand
                                            
   private int pot;
   
   private int[] inForHand;                 // keeps track of how far in each player
                                            // is during each dealt hand
   
   private int[] rdBet;                     // keeps track of how far in each players
                                            // is during each round of betting
                                            
   /** default constructor that makes a new game
    * 
    */
   public HoldEm()
   {
       this(2);
   }
   
   /** constructor that makes a new game given a number of players
    * 
    *  default number of players is 2, one human and one computer
    *  numPlayers cannot be greater than 8 or less than 2
    */
   public HoldEm(int i) 
   {
       this.numPlayers = i;
       
       //START INIT MESSAGE (maybe make it into it's own method later)
       //this prints out our welcome message and instantiates the variables 
       //hands, money, and bigBlind
       System.out.println("Welcome to Texas Hold'Em");
       while(true) 
       {
           System.out.println("Would you like to start with " + numPlayers + " players?");
           if(input.next().substring(0,1).equalsIgnoreCase("n"))
           {
               System.out.println("Terribly sorry for the mix-up, how many players would you like?");
               this.numPlayers = input.nextInt();
               System.out.println();
           }
           else
               break;
       }
       
       //affirms numPlayers is greater than 1 and less than 9
       while(numPlayers < 2 || numPlayers > 8)
       {
           System.out.println("The number of player cannot be less than 2 or greater than");
           System.out.println("8, please enter a value that is valid, in the inclusive range");
           System.out.println("[2,8]");
           numPlayers = input.nextInt();
           System.out.println();
       }
       
       while(true) {
           money = new ArrayList<Integer>(numPlayers);
           System.out.println("Okay, now that we have " + numPlayers + " players, should they all");
           System.out.println("start with the same amount of chips (y/n)");
           if(input.next().substring(0,1).equalsIgnoreCase("n"))
           {
               for(int j = 0; j < numPlayers; j++)
               {
                   System.out.println();
                   System.out.println("How many chips is Player " + (j+1) + " starting with?");
                   System.out.println("Hint: The value should be in the range [10, 100000]");
                   money.add(input.nextInt());
                   while(money.get(j) < 10 || money.get(j) > 100000)
                   {
                       System.out.println("The value is invalid, try again");
                       money.set(j,input.nextInt());
                   }
               }
           } else
           {
               System.out.println();
               System.out.println("How many chips should everyone start with?");
               System.out.println("Hint: The value should be in the range [10, 100000]");
               int temp = input.nextInt();
               while(temp < 10 || temp > 100000)
               {
                       System.out.println("The value is invalid, try again");
                       temp = input.nextInt();
               }
               for(int k = 0; k < numPlayers; k++)
               {
                   money.add(temp);
               }
           }
           
           System.out.println();
           System.out.println("So here are the starting chip values: ");
           for(int f = 0; f < numPlayers; f++)
           {
               System.out.println("Player " + (f+1) + " has " + money.get(f) + " chips");
           }
           System.out.println("Is this correct?");
           if(input.next().substring(0,1).equalsIgnoreCase("n"))
                System.out.println("Okay, we will start over!");
           else break;
        }
        
           System.out.println();
           System.out.println("What would you like the big blind value to be?");
           System.out.println("(This value must be even and greater than 0)");
           blind = input.nextInt();
           while(blind < 2 || blind%2!=0)
           {
               System.out.println("That value was invalid, please enter an even number greater");
               System.out.println("than 0");
               blind = input.nextInt();
           }
           System.out.println("Okay, blinds are " + blind + ". (Hit [enter] to continue)");
           String temp = input.next();
           
           System.out.println();
           System.out.println("Okay, here are the players: ");
           System.out.println("The human/Player 1 has " + money.get(0) + " chips");
           for(int f = 1; f < numPlayers; f++)
           {
               System.out.println("Player " + (f+1) + " has " + money.get(f) + " chips");
           }
       //END INIT MESSAGE
       
       hands = new ArrayList<Card[]>(numPlayers);
       d = new Deck();
       allIn = new boolean[numPlayers];
       folded = new boolean[numPlayers];
       dealer = randy.nextInt(numPlayers);
       System.out.println("Beginning first hand!");
       inForHand = new int[numPlayers];
       rdBet = new int[numPlayers];
       this.play();
    }
   
   public void play() {
       System.out.println();
       // reset and shuffle the deck of cards
       d.reset();
       d.shuffle();
       
       for(int i = 0; i < numPlayers; i++)
       {
           allIn[i] = false;
           folded[i] = false;
           inForHand[i] = 0;
       }
       pot = 0;
       
       // this method will shift dealer up, or if it's about
       // to spill over it will reset back to 0
       dealer = this.rotateDealer(1);

       // this will print out who is currently big blind, small blind
       // and dealer
       this.printPositions();
           
       // place initial bets for small blinds and big blinds
       // this method needs to check for all ins
       this.placeBlinds();
       
       // deal all of the cards
       this.dealHands();
       
       
       while(true) {
           // does the first round of betting
           this.firstRoundBet();
           break;
           /*
           // deal and reveal first 3 cards;
           this.deal(3);
           
           // does second round of betting
           this.bet();
           
           // deal the next card and reveal them all to this point
           this.deal(1);
           
           // does third round of betting
           this.bet();
           
           // deal the last card and reveal them all to this point
           this.deal(1);
           
           // does last round of betting
           this.bet();
           
           // reveals the non-folded cards
           this.reveal();
           
           // judges the relative hand strength and reveals the winner
           this.judge();
           
           // assigns the pot to the winner
           this.givePot();
           
           // reveals post-game menu
           this.revealPostGameMenu();
           */
        }
        
        
    }
    
    private int rotateDealer(int i)
    {
        return ((dealer+i) % numPlayers);
    }
    
    
    private void printPositions()
    {
        System.out.println("The dealer is " + getDName());
        System.out.println("Small blind is " + getSBName());
        System.out.println("Big blind is " + getBBName());
    }
    
    // these next three methods for for getting the "name" of
    // the dealer, small blind, and big blind
    private String getDName() {
        return pH(rotateDealer(0));
    }
    
    private String getSBName() {
        return pH(rotateDealer(1));
    }
    
    private String getBBName() {
        return pH(rotateDealer(2));
    }
    
    // these next three methods are for getting the "index" of
    // the dealer, small blind, and big blind
    private int getDIndex()
    {
        return dealer;
    }
    
    private int getSBIndex()
    {
        return rotateDealer(1);
    }
    
    private int getBBIndex()
    {
        return rotateDealer(2);
    }   

    //this method returns you if its the human or the player number if it's
    //an AI
    private String pH(int i)
    {
        if(i == 0) return "YOU";
        return "Player " + (i+1);
    }
    
   //I LEFT OFF ON THIS METHOD
   private void placeBlinds() {
       int sb = getSBIndex();
       int bb = getBBIndex();
       System.out.println();
       System.out.println("Blinds are " + blind/2 + "/" + blind + ".");
       System.out.println(pH(sb) + " owes " + blind/2 + " as small blind");
       if(money.get(sb) == blind/2)
       {
           System.out.println(pH(sb) + " places all " + blind/2 + " in the pot");
           System.out.println(pH(sb) + " is ALL IN");
           pot+= money.get(sb);
           money.set(sb, 0);
           inForHand[sb]+= blind/2;
           allIn[sb] = true;
       }
       else if(money.get(sb) < blind/2)
       {
           System.out.println(pH(sb) + " goes ALL IN with " + money.get(sb) + " chips.");
           allIn[sb] = true;
           pot+= money.get(sb);
           inForHand[sb]+= money.get(sb);
           money.set(sb, 0);
       }
       else
       {
           System.out.println(pH(sb) + " places their blind of " + blind/2 + " chips in the pot");
           pot+=blind/2;
           inForHand[sb]+= blind/2;
           money.set(sb, money.get(sb) - blind/2);
       }
       
       //begin big blind
       System.out.println(pH(bb) + " owes " + blind + " as big blind");
       if(money.get(bb) == blind)
       {
           System.out.println(pH(bb) + " places all " + blind + " in the pot");
           System.out.println(pH(bb) + " is ALL IN");
           pot+= money.get(bb);
           inForHand[bb]+= money.get(bb);
           money.set(bb, 0);
           allIn[bb] = true;
       }
       else if(money.get(bb) < blind)
       {
           System.out.println(pH(bb) + " goes ALL IN with " + money.get(bb) + " chips.");
           allIn[bb] = true;
           inForHand[bb]+= money.get(bb);
           pot+= money.get(bb);
           money.set(bb, 0);
       }
       else
       {
           System.out.println(pH(bb) + " places blind of " + blind + " chips in the pot");
           pot+=blind;
           inForHand[bb]+= blind;
           money.set(bb, money.get(bb) - blind);
       }
       
       System.out.println("Here is the pot after the blinds: " + pot);
    }
    
    private void dealHands() {
       for(int i = 0; i < numPlayers; i++)
            hands.add(d.deal(2)); 
    }
    
    private void firstRoundBet() {
        int sb = this.getSBIndex();
        int bb = this.getBBIndex();
        int curBet;
        int temp;
        if(allIn[bb])
            curBet = inForHand[bb];
        else
            curBet = blind;
        
        for(int i = 0; i < numPlayers; i++)
        {
            rdBet[i] = 0;
        }
        
        rdBet[sb] = inForHand[sb];
        rdBet[bb] = inForHand[bb];
        
        int lastBetter = bb;
            
        turn = rotateDealer(3);
        
        System.out.println("it is " + pH(turn) + "'s turn");
        if(turn == 0 && !allIn[0] && !folded[0])
        {
            System.out.println();
            System.out.println("It's your turn, here some some stats:");
            System.out.println("Pot: " + pot);
            System.out.println("Current bet: " + curBet);
            System.out.println("Money that you have in this hand already: " + inForHand[0]);
            System.out.println("What would you like to do?");
            

            //displays check as a possibility if it is
            if(rdBet[0] == curBet)
                System.out.println(" 1 - Check ");
            else //displays raise as a possibility if it is
                System.out.println(" 1 - Call " + curBet);
            System.out.println(" 2 - Raise ");
            System.out.println(" 3 - Fold ");
            
            temp = input.nextInt();
            if(temp % 3 == 1)
            {
                //code for call/check
            } else if(temp % 3 == 2)
            {
                //code for raise
            } else if(temp % 3 == 0)
            {
                //code for fold
            }
        } else
        {
            //code if it is the AI's turn
        }
    }
}
