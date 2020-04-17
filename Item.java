package com.ralph.inventmanagementsys;

/**
 * class Item creates Item objects for the application.
 * @author Ralph Julsaint
 */

public class Item implements RFID{
	private String itemNo;
	   private String itemName;
	   private String itemExpDate;
	   private String itemQOH;
	   private String itemPrice;
	   private String suppNo;

           Item(){

           }	 
	   
	   Item (String itemNo, String itemName, String itemExpDate,
	   String itemQOH, String itemPrice, String suppNo){
	     this.itemNo = itemNo;
	     this.itemName = itemName;
	     this.itemExpDate = itemExpDate;
	     this.itemQOH = itemQOH;
	     this.itemPrice = itemPrice;
	     this.suppNo = suppNo;   
	   }
	   
	   void setItemNo(String itemNo){
	     this.itemNo = itemNo;   
	   }
	   
	   void setItemName(String itemName){
	     this.itemName = itemName;   
	   }
	   
	   void setItemExpDate(String itemExpDate){
	     this.itemExpDate = itemExpDate;   
	   }
	   
	   void setItemQOH(String itemQOH){
	     this.itemQOH = itemQOH;   
	   }
	   
	   void setItemPrice(String itemPrice){
	     this.itemPrice = itemPrice;   
	   }
	   
	   void setSuppNo(String suppNo){
             this.suppNo = suppNo;   
	   }
	   
	   String getItemNo(){
             return itemNo;   
	   }
	   
	   String getItemName(){
	     return itemName;   
	   }
	   
	   String getItemExpDate(){
	     return itemExpDate;   
	   }
	   
	   String getItemQOH(){
	     return itemQOH;   
	   }
	   
	   String getItemPrice(){
	     return itemPrice;   
	   }
	   
	   String getSuppNo(){
             return suppNo;   
	   }
	   
	   /*
	    * This method is used when item is scanned using an RFID reader 
	    */
	   @Override
	   public void scan(){
		   
	   }
}
