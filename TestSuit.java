// Case Study 14.1: Test the suits

public class TestSuit{

   public static void main(String[] args){

      Suit s = Suit.spade;      
      Suit h = Suit.heart;      
      Suit d = Suit.diamond;      
      Suit c = Suit.club;

      // Display "Spades Hearts Diamonds Clubs"
      System.out.println(s + " " + h + " " + d + " " + c);

      System.out.println(s.equals(s));         // Display true     
      System.out.println(s.equals(h));         // Display false
      System.out.println(s.compareTo(s));      // Display 0
      System.out.println(s.compareTo(d));      // Display 2
      System.out.println(d.compareTo(s));      // Display -2
   }
}
