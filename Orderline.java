/**
 * This class creates orderline objects for the application.
 * @author Ralph Julsaint
 */

public class Orderline{

  private Order order;
  private Item item;
  private int orderQty;
	
	public Orderline(Order order, Item item, int orderQty){
	  this.order = order;
	  this.item = item;
	  this.orderQty = orderQty;	
	}
	
	public void setOrderQty(int orderQty){
	  this.orderQty = orderQty;	
	}
	
	public Order getOrder(){
	  return order;	
	}
	
	public Item getItem(){
	  return item;	
	}
}
