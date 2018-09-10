package vo;

public class OrdersStatistics {
	private int menuid;
	private String menuname;
	private String menusum;
	private String price;
	private String sum;
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
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
	
	public String getMenusum() {
		return menusum;
	}
	public void setMenusum(String menusum) {
		this.menusum = menusum;
	}
	@Override
	public String toString() {
		return "OrdersStatistics [menuid=" + menuid + ", menuname=" + menuname + ", menusum=" + menusum + ", price="
				+ price + ", sum=" + sum + "]";
	}
	
}
