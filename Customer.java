
/**
 * This class provides a definition of Customer objects for our application.
 * @author Ralph Julsaint
 */
public class Customer {
  private int customerNo;
  private String customerName;
  private String customerAddress;
  private String customerCity;
  private String customerState;
  private int customerZip;
  private String customerEmail;
  private int customerPhone;
  
  public Customer(int customerNo, String customerName, String customerAddress,
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
  
  public void setCustomerNo(int customerNo){
    this.customerNo = customerNo;
  }
  
  public void setCustomerName(String customerName){
    this.customerName = customerName;
  }
  
  public void setCustomerAddress(String customerAddress){
    this.customerAddress = customerAddress;
  }
  
  
  public void setCustomerCity(String customerCity){
    this.customerCity = customerCity;
  }
  
  public void setCustomerState(String customerState){
    this.customerState = customerState;
  }
  
  public void setCustomerZip(int customerZip){
    this.customerZip = customerZip;
  }
  
  public void setCustomerEmail(String customerEmail){
    this.customerEmail = customerEmail;
  }
  
  public void setCustomerPhone(int customerPhone){
    this.customerPhone = customerPhone;
  }
  
  public int getCustomerNo(){
    return customerNo;
  }
  
  public String getCustomerName(){
    return customerName;
  }
  
  public String getCustomerAddress(){
    return customerAddress;
  }
  
  
  public String getCustomerCity(){
    return customerCity;
  }
  
  public String getCustomerState(){
    return customerState;
  }
  
  public int getCustomerZip(){
    return customerZip;
  }
  
  public String getCustomerEmail(){
    return customerEmail;
  }
  
  public int getCustomerPhone(){
    return customerPhone;
  }
  
  
}
