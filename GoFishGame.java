import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class GoFishGame
{
    ArrayList<GoFishPlayer> players;
    private static String[] outputLines;
    int turn;
    Scanner s;
    Scanner input;
    Deck d;
    int winnerID;
    
    public int getWinnerID()
    {
        return winnerID;
    }
    
    public GoFishGame() throws IOException
    {
        //init scanners
        input = new Scanner(System.in);
        s = new Scanner(new File("input.txt"));
        
        //init outputLines
        outputLines = new String[10];
        for(int i = 0; i < 10; i++)
            outputLines[i] = s.nextLine();
        
        //explain rules if need be
        print(outputLines[0]);                       //asking if they need to explain rules
        System.out.println("  1 - Yes, I already know the rules");
        System.out.println("  2 - No, please explain");
        int temp = input.nextInt();
        if(temp%2 != 1) {
            while(true) {
                print(outputLines[1]);               //text explaining rules
                System.out.println(outputLines[2]);  //menu asking if they are ready to continue
                temp = input.nextInt();
                if(temp == 1 || temp != 1)
                    break;
            }                                          
        }
        
        //begins getting players in
        players = new ArrayList<GoFishPlayer>();
        print(outputLines[3]);                      //asks the player for their own name
        String name = input.next();
        players.add(new GoFishPlayer(name, true));
        print(outputLines[4]);                      //asks the players for how many AI's [2-7]
        int totAIs = input.nextInt();
        for(int i = 0; i < totAIs; i++)
        {
            players.add(new GoFishPlayer());
        }
        
        d = new Deck();
        d.shuffle();
        //deal the initial cards
        for(int i = 0; i < players.size(); i++)
            players.get(i).dealCards(d.deal(5));
        
        System.out.println();
        intro();
    }
    
    public boolean noWinner()
    {
        for(GoFishPlayer p : players)
        {
            if(p.numCards() == 0)
                return false;
        }
        return true;
    }
    
    private void intro() {
        
        System.out.println("\n\n\n");
        System.out.println("There are " + players.size() + " players at the table");
        System.out.println("Here are their names: ");
        for(int i = 0; i < players.size(); i++)
            System.out.println(players.get(i).getName() + " who is player #" + (i+1));
        
        System.out.println();
        print("The dealer passed out 5 cards to each player. Here are your cards: ");
        System.out.println(players.get(0).getHand());
        System.out.println();

        print("The computer will randomly chooose one player to go first");
        turn = (int)(Math.random() * players.size());
        print("The computer selected " + players.get(turn).getName() + " to go first");
        
        GoFishMove lastMove;
        
        System.out.println();
        while(noWinner())
        {
            //print(players.get(turn).getHand().toString()); this is for debugging
            if(!players.get(turn).human() && players.get(turn).hasMatches())
            {
                players.get(turn).layDownAllMatches(this);
            }
            lastMove = players.get(turn).makeMove(this);
            if(lastMove == null)
            {
                winnerID = turn;
                break;
            }
            int reciever = lastMove.recieverID();
            
            //print out the move
            print(replacePlayerIDs(lastMove.toString()));
            
            if(players.get(reciever).hasCard(lastMove.cardRank()))
            {
                lastMove.setSuccess(true);
                
                ArrayList<Card> removedCards = players.get(reciever).returnAllMatches(lastMove.cardRank());
                int numOfCards = removedCards.size();
                
                print("This move was successful, " + players.get(reciever).getName() + " gave " + players.get(turn).getName() + " " +
                       numOfCards + " " + Card.getCardByRank(lastMove.cardRank()) + maybeAddS(numOfCards));
                       
                int cardsLayedDown = players.get(turn).returnAllMatches(lastMove.cardRank()).size();
                
                if(cardsLayedDown + numOfCards < 2) {} else
                    print(players.get(turn).getName() + " lays down " + (cardsLayedDown+numOfCards) + " of their " + Card.getCardByRank(lastMove.cardRank()) +
                       ". They now have " + players.get(turn).numCards() + " cards left in their hand");
                       
                if(players.get(turn).numCards() != 0) {
                    print("Since they made a successful move, " + players.get(turn).getName() + " is able to go again");
                   turn --;
                } else {
                    print(players.get(turn).getName() + " has officially won the game!");
                    winnerID = turn;
                }
            } else {
                lastMove.setSuccess(false);
                
                print(players.get(reciever).getName() + " said GO FISH! " + players.get(turn).getName() + " has to draw from the pile of cards");
                players.get(turn).dealCards(d.deal(1));
            }
            
            turn = (turn+1) % players.size();
            System.out.println();
            print("It is " + players.get(turn).getName() + "'s turn. Here is your hand:");
            System.out.println(players.get(0).getHand().toString());
            System.out.println("Enter any single string to continue");
            String temp = input.next();
            System.out.println();
        }
        
        System.out.println("We have a winner!");
        System.out.println("Congrats to " + players.get(winnerID).getName());
    }
    
    private String replacePlayerIDs(String s) {
        int index = s.indexOf("PlayerID#");
        
        while(index != -1)
        {
            s = s.substring(0,index) + players.get(Integer.parseInt(s.substring(index+9,index+10))).getName() + s.substring(index+10);
            index = s.indexOf("PlayerID#");
        }
        return s;
    }
    
    private String maybeAddS(int i)
    {
        if(i == 1) return "";
        return "s";
    }
    
    public static GoFishMove getHumanMove(GoFishGame g)
    {
        Scanner input = new Scanner(System.in);
        
        print("It's your turn, you must make a move!");
        print("NOTE:enter the move exactly according to guidelines to avoid errors or mistakes");
        if(g.players.get(0).hasMatches()) {
            print("First would you like to lay down your existing matches?");
            System.out.println("   1 - yes");
            System.out.println("   2 - no");
            int temp = input.nextInt();
            if(temp % 2 == 1)
                g.players.get(0).layDownAllMatches(g);
        }
        
        print("Now, which player are you targeting?");
        ArrayList<GoFishPlayer> players = g.players;
        for(int i = 1; i < players.size(); i++)
        {
            System.out.println("   " + i + " - " + players.get(i).getName());
        }
        int target = input.nextInt() % players.size();
        
        print("And what card will you ask them for?");
        for(int i = 1; i < 14; i++)
        {
            System.out.println("  " + i + " - " + Card.getCardByRank(i));
        }
        int cardRank = input.nextInt() % 14;
        
        return new GoFishMove(0, target, cardRank);
    }
    
    public static void print(String s)
    {
        final int maxCharPerLine = 70;          // this is the maximum amount of chars we will include
                                                // in each line, it can be easily adjusted here
                                                
                                                
        String responseWithLineBreaks = "";     // these are little helper strings for the loop
        String cur;                             // execution
        
        Scanner reader = new Scanner(s);        // this Scannner object scans the inputted
                                                // String from the parameter and will seperated the
                                                // words to make it easier to handle
                                                
        int lastLineIndex;                      // another helper variable for program execution
        
        
        // Main whileLoop, will read through each seperate word, decide if there
        // is enough space on each line for it, if so it will add it, if not
        // it will create a new line and put it there
        while(reader.hasNext())
        {
            cur = reader.next();
            lastLineIndex = responseWithLineBreaks.lastIndexOf("\n") + 2;
            if(lastLineIndex == 1 && (responseWithLineBreaks.length() + cur.length()) < maxCharPerLine)
            {
                if(responseWithLineBreaks.length() == 0) {
                    responseWithLineBreaks += cur;
                } else {
                    responseWithLineBreaks += " " + cur;
                }
            } else if(lastLineIndex == 1)
            {
                responseWithLineBreaks = responseWithLineBreaks + "\n" + cur;
            } else if((responseWithLineBreaks.substring(lastLineIndex).length() + cur.length()) < maxCharPerLine)
            {
                responseWithLineBreaks += " " + cur;
            } else
            {
                responseWithLineBreaks = responseWithLineBreaks + "\n" + cur;
            }
        }
        
        // returns the final response with the line breaks now included
        System.out.println(responseWithLineBreaks);
        
    }
}

