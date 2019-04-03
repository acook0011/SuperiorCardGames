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
           System.out.println("Okay, blinds are " + blind + ". (Hit [enter] to continue");
           input.nextLine();
       //END INIT MESSAGE
       
       hands = new ArrayList<Card[]>(numPlayers);
       d = new Deck();
       allIn = new boolean[numPlayers];
       folded = new boolean[numPlayers];
       dealer = randy.nextInt(numPlayers);
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
       
       /*
       // deal all of the cards
       this.dealHands();
       
       while(true) {
           // does the first round of betting
           this.firstRoundBet();
           
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
        }
        */
        
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
        return printPossibleHuman(rotateDealer(0));
    }
    
    private String getSBName() {
        return printPossibleHuman(rotateDealer(1));
    }
    
    private String getBBName() {
        return printPossibleHuman(rotateDealer(2));
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

    private String printPossibleHuman(int i)
    {
        if(i == 0) return "YOU";
        return "Player " + (i+1);
    }
   
   private void placeBlinds() {
       
    }
}
