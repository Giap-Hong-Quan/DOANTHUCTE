package entities;

public class productimages {
	private int id;
	private int product_id ;
	private String image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public productimages(int id, int product_id, String image) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.image = image;
	}
	public productimages() {
		super();
	}
	
}
