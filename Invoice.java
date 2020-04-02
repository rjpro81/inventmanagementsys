/**
 * This class creates invoice objects for the application.
 * @author Ralph Julsaint
 */

public class Invoice {
  private String invoiceNo;
  private String invoiceDate;
  private String suppNo;
  private String orderDate;
  private String itemNo;
  private String orderNo;

  
  public Invoice(String invoiceDate, String invoiceNo, String suppNo,
 String orderNo, String orderDate, String itemNo){
	  this.invoiceNo = invoiceNo;
	  this.suppNo = suppNo;
	  this.invoiceDate = invoiceDate;
	  this.orderNo = orderNo;
	  this.orderDate = orderDate;
	  this.itemNo = itemNo;
  }
  
  public void setInvoiceNo(String invoiceNo){
	this.invoiceNo = invoiceNo;  
  }
  
  public void setInvoiceDate(String invoiceDate){
	this.invoiceDate = invoiceDate;  
  }
  
  public void setSuppNo(String suppNo){
	this.suppNo = suppNo;  
  }
  
  public void setOrderNo(String orderNo){
	this.orderNo = orderNo;  
  }
  
  public void setOrderDate(String orderDate){
	this.orderDate = orderDate;  
  }
  
  public void setItemNo(String itemNo){
	this.itemNo = itemNo;  
  }
  
  public String getInvoiceNo(){
	return invoiceNo;  
  }
 
  
  public String getInvoiceDate(){
	return invoiceDate;  
  }

  public String getSuppNo(){
	return suppNo;  
  }
  
  public String getOrderDate(){
	return orderDate;  
  }
  
  public String getOrderNo(){
	return orderNo;  
  }
  
  public String getItemNo(){
	return itemNo;  
  }
}
