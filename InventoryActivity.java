/**
 * This is a super class that provides a higher level abstraction class for printing recent application activity.
 * @author Ralph Julsaint
 */
public class InventoryActivity {
   private String activity;
   
   public InventoryActivity(String activity){
     this.activity = activity;
   }
   
   public void setActivity(String activity){
     this.activity = activity;
   }
   
   public String getActivity(){
     return activity;
   }
   
   @Override
   public String toString(){
     return String.format("Activity: " + getActivity());
   }
  
}
