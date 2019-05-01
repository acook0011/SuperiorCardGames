
public class GoFishMove
{
   private int makerID;
   private int recieverID;
   private int cardRank;
   private Boolean success;
   private static int numMoves;
   
   public GoFishMove(int m, int r, int cRnk)
   {
       makerID = m;
       recieverID = r;
       cardRank = cRnk;
   }
   
   public void setSuccess(boolean s)
   {
       success=s;
    }
   
   public boolean equals(GoFishMove other)
   {
       if(makerID != other.makerID)
        return false;
       if(recieverID != other.recieverID)
        return false;
       if(cardRank != other.cardRank)
        return false;
       return true;
   }
   
   public String toString()
   {
       String res="";
       res+="PlayerID#" + makerID;
       {
           if(success != null) {
            if(success)
                res+= " successfully";
            if(!success)
                res+= " unsuccessfully";
            }
           res+=" asked PlayerID#" + recieverID;
           res+=" for a " + Card.getCardByRank(cardRank);
       }
       return res;
   }
   
   public int makerID()
   {
       return makerID;
   }
   
   public int recieverID()
   {
       return recieverID;
   }
   
   public int cardRank()
   {
       return cardRank;
   }
   
   public boolean success()
   {
       return success;
   }
}
