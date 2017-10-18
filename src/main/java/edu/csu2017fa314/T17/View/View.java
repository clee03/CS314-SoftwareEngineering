package edu.csu2017fa314.T17.View;
public class View
{
   private int totalDistance;

   public void setTotalDistance(int distance) 
   {
       totalDistance = distance; 
   }

   public int getTotalDistance() 
   {
      return totalDistance;
   }

  public static String fileExtender(String fileName, String extension){
     return fileName.split("[.]")[0] + "." + extension;
  }

}
