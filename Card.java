// Case Study 14.1: The Card class

public class Card implements Comparable{
  
   private Suit suit;
   private int rank;
   private boolean faceUp;

   public Card(Suit suit, int rank){
      this.suit = suit;
      this.rank = rank;
      faceUp = false;
   }

   public boolean equals(Object other){
      if (this == other)
         return true;
      else if (! (other instanceof Card))
         return false;
      else{
         Card otherCard = (Card)other;
         return rank == otherCard.rank;
      }
   }

   public int compareTo(Object other){
      if (! (other instanceof Card))
         throw new IllegalArgumentException("Parameter must be a Card");
      Card otherCard = (Card)other;
      return rank - otherCard.rank;
   }

   public int getRank(){
      return rank;
   }

   public Suit getSuit(){
      return suit;
   }

   public boolean isFaceUp(){
      return faceUp;
   }

   public boolean isRed(){
      return suit == Suit.heart || suit == Suit.diamond;
   }

   public void turn(){
      faceUp = ! faceUp;
   }

   public String toString(){
      return rankToString() + " of " + suit;
   }

   public String rankToString(){
      if (rank == 1)
         return "Ace";
      else if (rank == 11)
         return "Jack";
      else if (rank == 12)
         return "Queen";
      else if (rank == 13)
         return "King";
      else
         return "" + rank;
   }
}
