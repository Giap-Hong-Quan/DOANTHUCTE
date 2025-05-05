package entities;

public class order_items {
	private int id;
   	private int order_id;
   	private int product_id;
    private int quantity;
    private double price;
    private double subtotal;
    private String productName; // Thêm tên sản phẩm để hiển thị
    private String productImage; // Thêm hình ảnh sản phẩm để hiển thị
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public order_items(int id, int order_id, int product_id, int quantity, double price, double subtotal,
			String productName, String productImage) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
		this.productName = productName;
		this.productImage = productImage;
	}
	public order_items() {
		super();
	}
	@Override
	public String toString() {
		return "order_items [id=" + id + ", order_id=" + order_id + ", product_id=" + product_id + ", quantity="
				+ quantity + ", price=" + price + ", subtotal=" + subtotal + ", productName=" + productName
				+ ", productImage=" + productImage + "]";
	}
    
}
