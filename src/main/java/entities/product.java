package entities;

import java.security.Timestamp;

public class product {
	private int id;
	private int category_id;
	private String name;
	private String description;
	private double price;
	private int quantity;
	private Timestamp create_at;
	private Timestamp update_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	public Timestamp getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(Timestamp update_at) {
		this.update_at = update_at;
	}
	public product(int id, int category_id, String name, String description, double price, int quantity,
			Timestamp create_at, Timestamp update_at) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	public product(int category_id, String name, String description, double price, int quantity) {
		super();
		this.category_id = category_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	public product() {
		super();
	}
	public product(int id, int category_id, String name, double price, int quantity) {
		super();
		this.id = id;
		this.category_id  = category_id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
}
