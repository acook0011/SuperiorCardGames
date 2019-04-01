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

public class GabeHoldsEmToo
{
   // I use arrayLists and arrays so that I can keep the number
   // of players dynamic
   private ArrayList<Card[]> hands;         // keeps track of all the hands
   private ArrayList<Integer> money;        // keeps track of all the money
   private int numPlayers;                  // keeps track of the number of players
   Scanner input = new Scanner(System.in);  // scanner for reading user input throughout
                                            // the game
   
   /** default constructor that makes a new game
    * 
    */
   public GabeHoldsEmToo()
   {
       this(2);
   }
   
   /** constructor that makes a new game given a number of players
    * 
    *  default number of players is 2, one human and one computer
    *  numPlayers cannot be greater than 8 or less than 2
    */
   public GabeHoldsEmToo(int i) 
   {
       this.numPlayers = i;
       
       //START INIT MESSAGE (maybe make it into it's own method later)
       //this prints out our welcome message and instantiates the variables 
       //hands, money, and bigBlind
       System.out.println("Welcome to Texas Hold'Em");
       while(true) 
       {
           System.out.println("You want to start with" + numPlayers + " players, correct?");
           if(input.nextLine().substring(0,1).equalsIgnoreCase("n"))
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
           System.out.println("Okay, now that we have " + numPlayers + " players, should they all");
           System.out.println("start with the same amount of chips (y/n)");
           if(input.nextLine().substring(0,1).equalsIgnoreCase("n"))
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
           else
            break;
       }
       //END INIT MESSAGE
       
       
   }
   
   
   
}
