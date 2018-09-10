package service;

import java.util.ArrayList;

import dao.OrdersDao;
import po.Orders;
import po.Page;
import vo.OrdersStatistics;

public class OrdersService {
	private OrdersDao ordersDao = new OrdersDao();

	// 查询所有
	public ArrayList<Orders> findAll() {
		return ordersDao.findAll();
	}

	// 查询一共有多少条
	public long count() {
		return ordersDao.count();
	}

	// 分页查询
	public Page<Orders> findByPage(Page<Orders> page) {
		return ordersDao.findByPage(page);
	}
	//修改
	public int chg(int id,Orders orders) {
		return ordersDao.chg(id, orders);
	}
	//根据用户id查询
	public Orders find(String userid) {
		return ordersDao.find(userid);
	}
	//统计
	public ArrayList<OrdersStatistics> findStatistics() {
		return ordersDao.findStatistics();
	}
}
