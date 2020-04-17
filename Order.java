package com.ralph.inventmanagementsys;

/**
 * This class creates Order objects for the application
 */

class Order {
  private String orderNo;
  private String orderDate;
  private User user;
  private String orderAddress;
  private String orderCity;
  private String orderState;
  private String orderZip;
	 
  Order(){

  } 
  Order (String orderNo, String orderDate, User user,String orderAddress, 
    String orderCity, String orderState, String orderZip){
    this.orderNo = orderNo;
    this.orderDate = orderDate;
    this.user = user;
    this.orderAddress = orderAddress;
    this.orderCity = orderCity;
    this.orderState = orderState;
    this.orderZip = orderZip;  
  }	
	  
  void setOrderNo(String orderNo){
    this.orderNo = orderNo;  
  }
	  
  void setOrderDate(String orderDate){
    this.orderDate = orderDate;  
  }
	  
  void setOrderAddress(String orderAddress){
    this.orderAddress = orderAddress;  
  }
	  
  void setOrderCity(String orderCity){
    this.orderCity = orderCity;  
  }
	  
  void setOrderState(String orderState){
    this.orderState = orderState;  
  }
	  
  void setOrderZip(String orderZip){
    this.orderZip = orderZip;  
  }
	  
  String getOrderNo(){
    return orderNo;  
  }
	  
  String getOrderDate(){
    return orderDate;  
  }
	  
  User getUser(){
    return user;  
  }
	  
  String getOrderAddress(){
    return orderAddress;  
  }
	  
  String getOrderCity(){
    return orderCity;  
  }
	  
  String getOrderState(){
    return orderState;  
  }
	  
  String getOrderZip(){
    return orderZip;  
  }
}
