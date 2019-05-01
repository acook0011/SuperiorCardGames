import java.util.ArrayList;

public class GoFishPlayer
{
    private ArrayList<Card> hand; // represents the hand that will be dealt
    private String name;
    private static int numPlayers = 0;
    private boolean isHuman;
    private static final String[] defaultNames = {"Arnold", "Bertha", "Christy", "Donald", "Eddie", "Flavio", "Gertrude", "Hattie"};
    private int playerID;
    
    public GoFishPlayer()
    {
        this("XXADMINXX",false);
    }
    
    public GoFishPlayer(String name, boolean human)
    {
        if(name.equals("XXADMINXX")) {
            if(numPlayers < 8) {
                this.name = defaultNames[numPlayers];
            }else{
                this.name = "Error 101, too many names";
            }
        }else{
            this.name = name;
        }
        playerID = numPlayers;
        hand = new ArrayList<Card>();
        numPlayers++;
        isHuman = human;
    }
    
    public void dealCards(Card[] cards)
    {
        for(int i = 0; i < cards.length; i++)
            hand.add(cards[i]);
    }
    
    public boolean hasCard(Card card)
    {
        for(Card c : hand)
            if(c.getRank() == card.getRank())
                return true;
        return false;
    }
    
    public boolean hasCard(int i)
    {
        for(Card c : hand)
            if(c.getRank() == i)
                return true;
        return false;
    }
    
    public ArrayList<Card> returnAllMatches(int rank)
    {
        ArrayList<Card> res = new ArrayList<Card>(3);
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getRank() == rank)
            {
                res.add(hand.remove(i));
                i--;
            }
        }
        return res;
    }
    
    public int numCards()
    {
        return hand.size();
    }
    
    public boolean human()
    {
        return isHuman;
    }
    
    public void giveCard(Card c)
    {
        hand.add(c);
    }
    
    public int getID()
    {
        return playerID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public GoFishMove makeMove(GoFishGame g)
    {
        if(isHuman)
            return GoFishGame.getHumanMove(g);
        
        int target = ((int)(Math.random() * g.players.size()-1));
        if(target > playerID-1)
            target++;
        
        if(hand.size() == 0) return null;
        int cardRank = hand.get((int)Math.random() * hand.size()).getRank();
        return new GoFishMove(playerID, target, cardRank);
    }
    
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public boolean hasMatches()
    {
        for(int r = 0; r < hand.size()-1; r++)
            for(int c = r+1; c < hand.size(); c++)
                if(hand.get(r).getRank() == hand.get(c).getRank())
                    return true;
        return false;
    }
    
    public void layDownAllMatches(GoFishGame g)
    {
        for(int r = 0; r < hand.size()-1; r++)
            for(int c = r+1; c < hand.size(); c++)
                if(hand.get(r).getRank() == hand.get(c).getRank()) {
                    ArrayList<Card> layedDown = new ArrayList<Card>();
                    int rank = hand.get(r).getRank();
                    for(int i = 0; i < hand.size(); i++)
                    {
                        if(rank == hand.get(i).getRank()) {
                            layedDown.add(hand.remove(i));
                            i--;
                        }
                    }
                    GoFishGame.print(name + " layed down " + layedDown.size() + " " + Card.getCardByRank(rank) + "s");
                    r = -1;
                    c = 100;
                }
        GoFishGame.print(name + " has " + hand.size() + " card(s) left in their hand");
    }
}
