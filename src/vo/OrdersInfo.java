package vo;
//表示订单信息的实体类
public class OrdersInfo {
	private int id;
	private String userid;
	private String realname;
	private String username;
	private String phone;
	private String address;
	private String menuname;
	private String menusum;
	private String price;
	private String sum;
	private String times;
	private String delivery;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenusum() {
		return menusum;
	}
	public void setMenusum(String menusum) {
		this.menusum = menusum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	@Override
	public String toString() {
		return "OrdersInfo [id=" + id + ", userid=" + userid + ", realname=" + realname + ", username=" + username
				+ ", phone=" + phone + ", address=" + address + ", menuname=" + menuname + ", menusum=" + menusum
				+ ", price=" + price + ", sum=" + sum + ", times=" + times + ", delivery=" + delivery + "]";
	}
	
}
