package entities;

import java.sql.Timestamp;

public class user {
	private int id;
	private String name;
	private String email;
	private String google_id ;
	private String facebook_id ;
	private String phone  ;
	private String password;
	private String avatar;
	private int role_id;
	private String roleName;
	public user(int id, String name, String email, String google_id, String facebook_id, String phone, String password,
			String avatar, int role_id, String roleName, Timestamp create_at, Timestamp update_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.google_id = google_id;
		this.facebook_id = facebook_id;
		this.phone = phone;
		this.password = password;
		this.avatar = avatar;
		this.role_id = role_id;
		this.roleName = roleName;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private Timestamp create_at;
	private Timestamp update_at;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGoogle_id() {
		return google_id;
	}
	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
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
	public user(int id, String name, String email, String google_id, String facebook_id, String phone, String password,
			String avatar, int role_id, Timestamp create_at, Timestamp update_at) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.google_id = google_id;
		this.facebook_id = facebook_id;
		this.phone = phone;
		this.password = password;
		this.avatar = avatar;
		this.role_id = role_id;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", email=" + email + ", google_id=" + google_id + ", facebook_id="
				+ facebook_id + ", phone=" + phone + ", password=" + password + ", avatar=" + avatar + ", role_id="
				+ role_id + ", create_at=" + create_at + ", update_at=" + update_at + "]";
	}
	public user( String name, String email, String google_id, String facebook_id, String phone, String password,
			String avatar, int role_id) {
		this.name = name;
		this.email = email;
		this.google_id = google_id;
		this.facebook_id = facebook_id;
		this.phone = phone;
		this.password = password;
		this.avatar = avatar;
		this.role_id = role_id;
	}
	public user(String name, String email, String phone, String avatar, int role_id) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.role_id = role_id;
	}
	public user() {
		// TODO Auto-generated constructor stub
	}
	
}
