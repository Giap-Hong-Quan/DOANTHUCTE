package entities;

public class cart_detail {
private int id;
private double price;
private int quantity;
private int cart_id;
private int product_id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getCart_id() {
	return cart_id;
}
public void setCart_id(int cart_id) {
	this.cart_id = cart_id;
}
public int getProduct_id() {
	return product_id;
}
public void setProduct_id(int product_id) {
	this.product_id = product_id;
}
public cart_detail(int id, double price, int quantity, int cart_id, int product_id) {
	super();
	this.id = id;
	this.price = price;
	this.quantity = quantity;
	this.cart_id = cart_id;
	this.product_id = product_id;
}
public cart_detail() {
	
}
}
