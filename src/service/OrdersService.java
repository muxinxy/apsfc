package service;

import java.util.ArrayList;

import dao.OrdersDao;
import po.Orders;
import po.OrdersSearch;
import po.Page;
import vo.OrdersInfo;
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
	//删除
	public int del(int id) {
		return ordersDao.del(id);
	}
	//搜索——分页查询
	public Page<OrdersInfo> find(Page<OrdersInfo> page, OrdersSearch search) {
		return ordersDao.find(page,search);
	}
	public long findCount(OrdersSearch search) {
		return ordersDao.findCount(search);
	}
	//统计
	public ArrayList<OrdersStatistics> findStatistics() {
		return ordersDao.findStatistics();
	}
}
