package entities;

import java.sql.Timestamp;

public class category {
	private int id;
	private String name;
	private String image;
	private Timestamp create_at;
	private Timestamp update_at;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public category(int id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}
	public category(String name, String image) {
		this.name = name;
		this.image = image;
	}
	
}
