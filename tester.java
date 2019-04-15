import java.util.Random;
public class tester
{
    public static void main(String[] args)
    {
        Random randy = new Random();
        int heads, tails, sum = 0;
        
        for(int j = 0; j < 1000; j++)
        {
            heads=0;
            tails=0;
        for(int i = 0; i < 1000000; i++) {
            if(randy.nextInt(2) == 0)
                heads++;
            else
                tails++;
        }
        sum += heads;
        sum -= tails;
        if(j%10 == 0)
                System.out.println(j/10 + "% done");
        }
        System.out.println((sum)/1000.0);
    }
}
