package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.Parent;
import po.Orders;
import po.OrdersSearch;
import po.Page;
import util.DBUtil;
import vo.OrdersInfo;
import vo.OrdersStatistics;

public class OrdersDao {
	// 添加
	public int add(Orders order) {
		Connection conn = DBUtil.getConn();
		String sql = "insert into orders(userid,menuid,menusum,times,delivery) values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getUserid());
			pstmt.setString(2, order.getMenuid());
			pstmt.setString(3, order.getMenusum());
			pstmt.setString(4, order.getTimes());
			pstmt.setString(5, order.getDelivery());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}

	// 删除
	public int del(int id) {
		Connection conn = DBUtil.getConn();
		String sql = "delete from orders where id=?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}

	// 查询一共有多少条
	public long count() {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "SELECT COUNT(*) FROM orders,users,menus where orders.userid=users.id && orders.menuid=menus.id";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		long count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				count = rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return count;
	}

	// 查询所有
	public ArrayList<Orders> findAll() {
		// 创建连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "SELECT " + "o.id," + "o.userid," + "u.realname," + "u.phone," + "u.address," + "m.name,"
				+ "o.menusum," + "m.price," + "o.menusum * m.price," + "o.times," + "o.delivery " + "FROM "
				+ "orders o,users u,menus m " + "WHERE " + "o.userid = u.id && o.menuid = m.id";
		// 创建PrepareStatement
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<Orders> list = new ArrayList<Orders>();
		try {
			pstmt = conn.prepareStatement(sql);
			// 发送语句
			rSet = pstmt.executeQuery();
			// 处理结果 结果集——ArrayList
			while (rSet.next()) {
				Orders order = new Orders();
				order.setId(rSet.getInt(1));
				order.setUserid(rSet.getString(2));
				order.setRealname(rSet.getString(3));
				order.setPhone(rSet.getString(4));
				order.setAdress(rSet.getString(5));
				order.setMenuname(rSet.getString(6));
				order.setMenusum(rSet.getString(7));
				order.setPrice(rSet.getString(8));
				order.setSum(rSet.getString(9));
				order.setTimes(rSet.getString(10));
				order.setDelivery(rSet.getString(11));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;
	}

	// 分页查询
	public Page<Orders> findByPage(Page<Orders> page) {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "SELECT " + "o.id," + "o.userid," + "u.realname," + "u.phone," + "u.address," + "m.name,"
				+ "o.menusum," + "m.price," + "o.menusum * m.price," + "o.times," + "o.delivery " + "FROM "
				+ "orders o,users u,menus m " + "WHERE " + "o.userid = u.id && o.menuid = m.id limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<Orders> list = new ArrayList<Orders>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				Orders order = new Orders();
				order.setId(rSet.getInt(1));
				order.setUserid(rSet.getString(2));
				order.setRealname(rSet.getString(3));
				order.setPhone(rSet.getString(4));
				order.setAdress(rSet.getString(5));
				order.setMenuname(rSet.getString(6));
				order.setMenusum(rSet.getString(7));
				order.setPrice(rSet.getString(8));
				order.setSum(rSet.getString(9));
				order.setTimes(rSet.getString(10));
				order.setDelivery(rSet.getString(11));
				list.add(order);
			}
			page.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return page;
	}

	// 修改
	public int chg(int id, Orders orders) {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "update orders set delivery=? where id=?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orders.getDelivery());
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}

	// 搜索——分页查询
	public Page<OrdersInfo> find(Page<OrdersInfo> page, OrdersSearch search) {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "SELECT " + "o.id," + "o.userid," + "u.realname," + "u.phone," + "u.address," + "m.name,"
				+ "o.menusum," + "m.price," + "o.menusum * m.price," + "o.times," + "o.delivery " + "FROM "
				+ "orders o,users u,menus m " + "WHERE " + "o.userid = u.id && o.menuid = m.id ";
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<OrdersInfo> list = new ArrayList<OrdersInfo>();
		if (search != null) {
			if (search.getUserid() != null && search.getUserid().trim().length() != 0) {
				sql = sql + "&& u.id=? ";
				params.add(search.getUserid());
			}
			if (search.getMenuname() != null && search.getMenuname().trim().length() != 0) {
				sql = sql + "&& m.name=? ";
				params.add(search.getMenuname());
			}
			if (search.getDate() != null && search.getDate().trim().length() != 0) {
				sql = sql + "&& o.times like ? ";
				params.add(search.getDate() + "%");
			}
			if (search.getDelivery() != null && search.getDelivery().trim().length() != 0) {
				sql = sql + "&& o.delivery=? ";
				params.add(search.getDelivery());
			}
		}
		sql = sql + "limit ?,?";
		params.add(page.getBeginIndex());
		params.add(page.getEveryPage());
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		OrdersInfo info = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int index = 0; index < params.size(); index++) {
				pstmt.setObject(index+1, params.get(index));
			}
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				info = new OrdersInfo();
				info.setId(rSet.getInt(1));
				info.setUserid(rSet.getString(2));
				info.setRealname(rSet.getString(3));
				info.setPhone(rSet.getString(4));
				info.setAddress(rSet.getString(5));
				info.setMenuname(rSet.getString(6));
				info.setMenusum(rSet.getString(7));
				info.setPrice(rSet.getString(8));
				info.setSum(rSet.getString(9));
				info.setTimes(rSet.getString(10));
				info.setDelivery(rSet.getString(11));
				list.add(info);
			}
			page.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return page;
	}

	public long findCount(OrdersSearch search) {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "SELECT COUNT(*) " + "FROM " + "orders o,users u,menus m " + "WHERE "
				+ "o.userid = u.id && o.menuid = m.id ";
		ArrayList<String> params = new ArrayList<String>();
		if (search != null) {
			if (search.getUserid() != null && search.getUserid().trim().length() != 0) {
				sql = sql + "&& u.id=? ";
				params.add(search.getUserid());
			}
			if (search.getMenuname() != null && search.getMenuname().trim().length() != 0) {
				sql = sql + "&& m.name=? ";
				params.add(search.getMenuname());
			}
			if (search.getDate() != null && search.getDate().trim().length() != 0) {
				sql = sql + "&& o.times like ? ";
				params.add(search.getDate() + "%");
			}
			if (search.getDelivery() != null && search.getDelivery().trim().length() != 0) {
				sql = sql + "&& o.delivery=? ";
				params.add(search.getDelivery());
			}
		}
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		long count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int index = 0; index < params.size(); index++) {
				pstmt.setObject(index+1, params.get(index));
			}
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				count = rSet.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return count;
	}

	public ArrayList<OrdersStatistics> findStatistics() {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT " + "m.id,m.name,SUM(o.menusum),m.price,SUM(o.menusum) * m.price " + "FROM "
				+ "orders o,menus m " + "WHERE " + "o.menuid = m.id && times LIKE ? " + "GROUP BY " + "m.id";
		PreparedStatement pstmt = null;
		OrdersStatistics ordersStatistics = null;
		ResultSet rSet = null;
		ArrayList<OrdersStatistics> list = new ArrayList<OrdersStatistics>();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sf.format(date);
		dateStr = dateStr + "%";
		try {
			pstmt = conn.prepareStatement(sql);
			ordersStatistics = new OrdersStatistics();
			pstmt.setString(1, dateStr);
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				ordersStatistics = new OrdersStatistics();
				ordersStatistics.setMenuid(rSet.getInt(1));
				ordersStatistics.setMenuname(rSet.getString(2));
				ordersStatistics.setMenusum(rSet.getString(3));
				ordersStatistics.setPrice(rSet.getString(4));
				ordersStatistics.setSum(rSet.getString(5));
				list.add(ordersStatistics);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;
	}
	public ArrayList<OrdersInfo> ordersRank() {
//		String preTime=null;
//		String currentTime=null;
//		Date date = new Date();
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		currentTime = sf.format(date);
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        if (w < 0)
//            w = 7;
//		calendar.add(Calendar.DATE, -w);
//		preTime = sdf.format(calendar.getTime());
//		preTime = preTime + " 23:59:59";
		Connection conn = DBUtil.getConn();
		String sql = "SELECT "
				+ "m.name,COUNT(o.menusum) "
				+ "FROM menus m,orders o "
				+ "WHERE "
				+ "m.id=o.menuid"
				+ " GROUP BY m.name "
				+ "ORDER BY COUNT(o.menusum) DESC";
//		String sql = "SELECT "
//				+ "m.name,COUNT(o.menusum) "
//				+ "FROM "
//				+ "menus m,orders o "
//				+ "WHERE "
//				+ "m.id=o.menuid && o.times>'"
//				+ preTime
//				+ "' && o.times<'"
//				+ currentTime
//				+ "' GROUP BY m.name "
//				+ "ORDER BY "
//				+ "COUNT(o.menusum) DESC";
		PreparedStatement pstmt = null;
		OrdersInfo ordersInfo=null;
		ResultSet rSet = null;
		ArrayList<OrdersInfo> list = new ArrayList<OrdersInfo>();
		try {
			pstmt = conn.prepareStatement(sql);
			ordersInfo = new OrdersInfo();
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				ordersInfo = new OrdersInfo();
				ordersInfo.setMenuname(rSet.getString(1));
				ordersInfo.setMenusum(rSet.getString(2));
				list.add(ordersInfo);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
