package entities;

import java.sql.Date;
import java.util.List;

public class orders {
	private int id;
    private int user_id;
    private Date order_date;
    private double total_price;
    private int status_id;
    private String name;
    private String address;
    private String phone;
    private int payment_method; // "cod" hoặc "online"
    private int payment_status; // "chưa thanh toán" hoặc "đã thanh toán"
    private String transaction_id; // cho thanh toán online
    private List<order_items> order_items;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(int payment_method) {
		this.payment_method = payment_method;
	}
	public int getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public List<order_items> getOrder_items() {
		return order_items;
	}
	public void setOrder_items(List<order_items> order_items) {
		this.order_items = order_items;
	}
	public orders(int id, int user_id, Date order_date, double total_price, int status_id, String name, String address,
			String phone, int payment_method, int payment_status, String transaction_id,
			List<entities.order_items> order_items) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.order_date = order_date;
		this.total_price = total_price;
		this.status_id = status_id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.transaction_id = transaction_id;
		this.order_items = order_items;
	}
	public orders() {
		super();
	}
	public orders(int user_id,Date date, double total_price, int status_id, String name, String address,
			String phone, int payment_method, int payment_status, String transaction_id) {
		super();
		this.user_id = user_id;
		this.order_date = date;
		this.total_price = total_price;
		this.status_id = status_id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.transaction_id = transaction_id;
	}
	
}
