package com.ralph.inventmanagementsys;

class Customer {
  private int customerNo;
  private String customerName;
  private String customerAddress;
  private String customerCity;
  private String customerState;
  private int customerZip;
  private String customerEmail;
  private int customerPhone;
	 
  Customer(){

  }
 
  Customer(int customerNo, String customerName, String customerAddress,
    String customerCity, String customerState, int customerZip, String customerEmail, int customerPhone){
    this.customerNo = customerNo;
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    this.customerCity = customerCity;
    this.customerState = customerState;
    this.customerZip = customerZip;
    this.customerEmail = customerEmail;
    this.customerPhone = customerPhone;
  }
	  
  void setCustomerNo(int customerNo){
    this.customerNo = customerNo;
  }
	  
  void setCustomerName(String customerName){
    this.customerName = customerName;
  }
	  
  void setCustomerAddress(String customerAddress){
    this.customerAddress = customerAddress;
  }
	  
	  
  void setCustomerCity(String customerCity){
    this.customerCity = customerCity;
  }
	  
  void setCustomerState(String customerState){
    this.customerState = customerState;
  }
	  
  void setCustomerZip(int customerZip){
    this.customerZip = customerZip;
  }
	  
  void setCustomerEmail(String customerEmail){
    this.customerEmail = customerEmail;
  }
	  
  void setCustomerPhone(int customerPhone){
    this.customerPhone = customerPhone;
  }
	  
  int getCustomerNo(){
    return customerNo;
  }
	  
  String getCustomerName(){
    return customerName;
  }
	  
  String getCustomerAddress(){
    return customerAddress;
  }
	  
  String getCustomerCity(){
    return customerCity;
  }
	  
  String getCustomerState(){
    return customerState;
  }
	  
  int getCustomerZip(){
    return customerZip;
  }
	  
  String getCustomerEmail(){
    return customerEmail;
  }
	  
  int getCustomerPhone(){
    return customerPhone;
  }
	  
  @Override
  public String toString(){
    return String.format("%s", getCustomerName());  
  }

  Customer getObj(){
    return this;
  }
}
