package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.Orders;
import po.Page;
import util.DBUtil;
import vo.OrdersStatistics;

public class OrdersDao {
	//查询一共有多少条
		public long count(){
			//获取连接
			Connection conn=DBUtil.getConn();
			//sql语句
			String sql="SELECT COUNT(*) FROM orders";
			PreparedStatement pstmt=null;
			ResultSet rSet=null;
			long count=0;
			try {
				pstmt=conn.prepareStatement(sql);
				rSet=pstmt.executeQuery();
				if (rSet.next()) {
					count=rSet.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
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
		String sql = "SELECT "
				+ "o.id,"
				+ "o.userid,"
				+ "u.realname,"
				+ "u.phone,"
				+ "u.address,"
				+ "m.name,"
				+ "o.menusum,"
				+ "m.price,"
				+ "o.menusum * m.price,"
				+ "o.times,"
				+ "o.delivery "
				+ "FROM "
				+ "orders o,users u,menus m "
				+ "WHERE "
				+ "o.userid = u.id && o.menuid = m.id";
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
	//分页查询
		public Page<Orders> findByPage(Page<Orders> page) {
			//获取连接
			Connection conn=DBUtil.getConn();
			// sql语句
			String sql = "SELECT "
					+ "o.id,"
					+ "o.userid,"
					+ "u.realname,"
					+ "u.phone,"
					+ "u.address,"
					+ "m.name,"
					+ "o.menusum,"
					+ "m.price,"
					+ "o.menusum * m.price,"
					+ "o.times,"
					+ "o.delivery "
					+ "FROM "
					+ "orders o,users u,menus m "
					+ "WHERE "
					+ "o.userid = u.id && o.menuid = m.id limit ?,?";
			PreparedStatement pstmt=null;
			ResultSet rSet=null;
			ArrayList<Orders> list=new ArrayList<Orders>();
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,page.getBeginIndex());
				pstmt.setInt(2, page.getEveryPage());
				rSet=pstmt.executeQuery();
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
			}finally {
				DBUtil.closeRst(rSet);
				DBUtil.closePstmt(pstmt);
				DBUtil.closeConn(conn);
			}
			return page;
		}
		//修改
		public int chg(int id,Orders orders) {
			//获取连接
			Connection conn=DBUtil.getConn();
			//sql语句
			String sql="update orders set delivery=? where id=?";
			PreparedStatement pstmt=null;
			int result=0;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, orders.getDelivery());
				pstmt.setInt(2, id);
				result=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.closePstmt(pstmt);
				DBUtil.closeConn(conn);
			}
			return result;
		}
		//查询
		public Orders find(String userid) {
			//获取连接
			Connection conn=DBUtil.getConn();
			//sql语句
			String sql = "SELECT "
					+ "o.id,"
					+ "o.userid,"
					+ "u.realname,"
					+ "u.phone,"
					+ "u.address,"
					+ "m.name,"
					+ "o.menusum,"
					+ "m.price,"
					+ "o.menusum * m.price,"
					+ "o.times,"
					+ "o.delivery "
					+ "FROM "
					+ "orders o,users u,menus m "
					+ "WHERE "
					+ "o.userid = u.id && o.menuid = m.id && o.userid=?";
			PreparedStatement pstmt=null;
			ResultSet rSet=null;
			Orders order=null;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rSet=pstmt.executeQuery();
				if(rSet.next()){
					order = new Orders();
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
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.closeRst(rSet);
				DBUtil.closePstmt(pstmt);
				DBUtil.closeConn(conn);
			}
			return order;
		}

	public ArrayList<OrdersStatistics> findStatistics() {
		Connection conn=DBUtil.getConn();
		String sql="SELECT "
				+ "m.id,m.name,SUM(o.menusum),m.price,SUM(o.menusum) * m.price "
				+ "FROM "
				+ "orders o,menus m "
				+ "WHERE "
				+ "o.menuid = m.id && times LIKE ? "
				+ "GROUP BY "
				+ "m.id";
		PreparedStatement pstmt=null;
		OrdersStatistics ordersStatistics=null;
		ResultSet rSet=null;
		ArrayList<OrdersStatistics> list=new ArrayList<OrdersStatistics>();
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=sf.format(date);
		dateStr=dateStr+"%";
		try {
			pstmt=conn.prepareStatement(sql);
			ordersStatistics=new OrdersStatistics();
			pstmt.setString(1,dateStr);
			rSet=pstmt.executeQuery();
			while(rSet.next()){
				ordersStatistics=new OrdersStatistics();
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
		}finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;
}
}
