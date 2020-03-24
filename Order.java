
public class Order{
  private String orderNo;
  private String orderDate;
  private User user;
  private String orderAddress;
  private String orderCity;
  private String orderState;
  private String orderZip;
  
  public Order (String orderNo, String orderDate, User user,
  String orderAddress, String orderCity, String orderState, 
  String orderZip){
	this.orderNo = orderNo;
	this.orderDate = orderDate;
	this.user = user;
	this.orderAddress = orderAddress;
	this.orderCity = orderCity;
	this.orderState = orderState;
	this.orderZip = orderZip;  
  }	
  
  public void setOrderNo(String orderNo){
	this.orderNo = orderNo;  
  }
  
  public void setOrderDate(String orderDate){
	this.orderDate = orderDate;  
  }
  
  public void setOrderAddress(String orderAddress){
	this.orderAddress = orderAddress;  
  }
  
  public void setOrderCity(String orderCity){
	this.orderCity = orderCity;  
  }
  
  public void setOrderState(String orderState){
	this.orderState = orderState;  
  }
  
  public void setOrderZip(String orderZip){
	this.orderZip = orderZip;  
  }
  
  public String getOrderNo(){
	return orderNo;  
  }
  
  public String getOrderDate(){
	return orderDate;  
  }
  
  public User getUser(){
	return user;  
  }
  
  public String getOrderAddress(){
	return orderAddress;  
  }
  
  public String getOrderCity(){
	return orderCity;  
  }
  
  public String getOrderState(){
	return orderState;  
  }
  
  public String getOrderZip(){
	return orderZip;  
  }
}
