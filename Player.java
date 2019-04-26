
/**
 * Might replace all the arrays in Casino with Player Class
 *
 * @aj
 * @version (a version number or a date)
 */
public class Player
{
    private int player;
    private String name;
    private int money;
    private boolean ingame;
    
    public Player(){
        player = 0;
        name = "Player 0";
        money = 1000;
        ingame = false;
    }
    
    public Player(int num){
        player = num;
        name = "Player " + Integer.toString(num);
        money = 1000;
        ingame = false;
    }
    
    public Player(int num, String nm){
        player = num;
        name = nm;
        money = 1000;
        ingame = false;
    }
    
    public int getPlayer(){
        return player;
    }

    public String getName(){
        return name;
    }
    
    public int getMoney(){
        return money;
    }
    
    public boolean isIG(){
        return ingame;
    }
    
    public void nowIG(){
        ingame = true;
    }
}
