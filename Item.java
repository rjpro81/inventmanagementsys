/**
 * class Item creates Item objects for the application.
 * @author Ralph Julsaint
 */
 
 public class Item implements RFID{
   private String itemNo;
   private String itemName;
   private String itemDesc;
   private String itemQOH;
   private String itemPrice;
   private String suppNo;	 
   
   public Item (String itemNo, String itemName, String itemDesc,
   String itemQOH, String itemPrice, String suppNo){
     this.itemNo = itemNo;
     this.itemName = itemName;
     this.itemDesc = itemDesc;
     this.itemQOH = itemQOH;
     this.itemPrice = itemPrice;
     this.suppNo = suppNo;   
   }
   
   public void setItemNo(String itemNo){
	 this.itemNo = itemNo;   
   }
   
   public void setItemName(String itemName){
	 this.itemName = itemName;   
   }
   
   public void setItemDesc(String itemDesc){
	 this.itemDesc = itemDesc;   
   }
   
   public void setItemQOH(String itemQOH){
	 this.itemQOH = itemQOH;   
   }
   
   public void setItemPrice(String itemPrice){
	 this.itemPrice = itemPrice;   
   }
   
   public void setSuppNo(String suppNo){
	 this.suppNo = suppNo;   
   }
   
   public String getItemNo(){
	 return itemNo;   
   }
   
   public String getItemName(){
	 return itemName;   
   }
   
   public String getItemDesc(){
	 return itemDesc;   
   }
   
   public String getItemQOH(){
	 return itemQOH;   
   }
   
   public String getItemPrice(){
	 return itemPrice;   
   }
   
   public String getSuppNo(){
	 return suppNo;   
   }
   
   /*
    * This method is used when item is scanned using an RFID reader 
    */
   @Override
   public void scan(){
	   
   }
	 
 }
