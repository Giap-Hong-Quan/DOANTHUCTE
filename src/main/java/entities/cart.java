package entities;

public class cart {
private int id;
private int sum;
private int user_id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getSum() {
	return sum;
}
public void setSum(int sum) {
	this.sum = sum;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public cart(int id, int sum, int user_id) {

	this.id = id;
	this.sum = sum;
	this.user_id = user_id;
}
public cart() {
	
}


}
