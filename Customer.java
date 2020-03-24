
/**
 * This class provides a definition of Customer objects for our application.
 * @author Ralph Julsaint
 */
public class Customer {
  private String customerNo;
  private String customerName;
  private String customerAddress;
  private String customerCity;
  private String customerState;
  private String customerZip;
  private String customerEmail;
  private String customerPhone;
  
  public Customer(String customerNo, String customerName, String customerAddress,
  String customerCity, String customerState, String customerZip, String customerEmail, String customerPhone){
    this.customerNo = customerNo;
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    this.customerCity = customerCity;
    this.customerState = customerState;
    this.customerZip = customerZip;
    this.customerEmail = customerEmail;
    this.customerPhone = customerPhone;
  }
  
  public void setCustomerNo(String customerNo){
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
  
  public void setCustomerZip(String customerZip){
    this.customerZip = customerZip;
  }
  
  public void setCustomerEmail(String customerEmail){
    this.customerEmail = customerEmail;
  }
  
  public void setCustomerPhone(String customerPhone){
    this.customerPhone = customerPhone;
  }
  
  public String getCustomerNo(){
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
  
  public String getCustomerZip(){
    return customerZip;
  }
  
  public String getCustomerEmail(){
    return customerEmail;
  }
  
  public String getCustomerPhone(){
    return customerPhone;
  }
  
  
}
