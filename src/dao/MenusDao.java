package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.glass.ui.Menu;

import po.Menus;
import po.Page;
import sun.security.krb5.internal.rcache.MemoryCache;
import util.DBUtil;
import vo.MenusInfo;

public class MenusDao {
	//添加
	public int add(Menus menus) {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="insert into menus(name,typeid,burden,brief,price,sums,price1,sums1,imgpath) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, menus.getName());
			pstmt.setString(2, menus.getTypeid());
			pstmt.setString(3, menus.getBurden());
			pstmt.setString(4, menus.getBrief());
			pstmt.setString(5, menus.getPrice());
			pstmt.setString(6, menus.getSums());
			pstmt.setString(7, menus.getPrice1());
			pstmt.setString(8, menus.getSums1());
			pstmt.setString(9, menus.getImgpath());
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
	//删除
	public int del(int id) {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="delete from menus where id=?";
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	//修改
	public int chg(int id,Menus menus) {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="update menus set name=?,typeid=?,burden=?,brief=?,price=?,sums=?,price1=?,sums1=? where id=?";
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, menus.getName());
			pstmt.setString(2, menus.getTypeid());
			pstmt.setString(3, menus.getBurden());
			pstmt.setString(4, menus.getBrief());
			pstmt.setString(5, menus.getPrice());
			pstmt.setString(6, menus.getSums());
			pstmt.setString(7, menus.getPrice1());
			pstmt.setString(8, menus.getSums1());
			pstmt.setInt(9, id);
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
	//查询一共有多少条
	public long count(){
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="SELECT COUNT(*) FROM menus m,types t WHERE m.typeid=t.id";
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
	//查询所有
	public ArrayList<MenusInfo> findAll() {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="SELECT "
				+ "m.id AS id,"
				+ "t.name AS name,"
				+ "t.id AS typeid,"
				+ "t.name AS typename,"
				+ "m.burden AS burden,"
				+ "m.brief AS brief,"
				+ "m.price AS price,"
				+ "m.price1 AS price1,"
				+ "m.sums AS sums,"
				+ "m.sums1 AS sums1,"
				+ "m.imgpath AS imgpath "
				+ "FROM "
				+ "menus m,types t "
				+ "WHERE "
				+ "m.typeid = t.id";
		PreparedStatement pstmt=null;
		ResultSet rSet=null;
		ArrayList<MenusInfo> list=new ArrayList<MenusInfo>();
		try {
			pstmt=conn.prepareStatement(sql);
			rSet=pstmt.executeQuery();
			while (rSet.next()) {
				MenusInfo menusInfo=new MenusInfo();
				menusInfo.setId(rSet.getInt(1));
				menusInfo.setName(rSet.getString(2));
				menusInfo.setTypeid(rSet.getString(3));
				menusInfo.setTypename(rSet.getString(4));
				menusInfo.setBurden(rSet.getString(5));
				menusInfo.setBrief(rSet.getString(6));
				menusInfo.setPrice(rSet.getString(7));
				menusInfo.setPrice1(rSet.getString(8));
				menusInfo.setSums(rSet.getString(9));
				menusInfo.setSums1(rSet.getString(10));
				menusInfo.setImgpath(rSet.getString(11));
				list.add(menusInfo);
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
	//分页查询
	public Page<MenusInfo> findByPage(Page<MenusInfo> page) {
		//获取连接
				Connection conn=DBUtil.getConn();
				//sql语句
				String sql="SELECT "
						+ "m.id AS id,"
						+ "m.name AS name,"
						+ "t.id AS typeid,"
						+ "t.name AS typename,"
						+ "m.burden AS burden,"
						+ "m.brief AS brief,"
						+ "m.price AS price,"
						+ "m.price1 AS price1,"
						+ "m.sums AS sums,"
						+ "m.sums1 AS sums1,"
						+ "m.imgpath AS imgpath "
						+ "FROM "
						+ "menus m,types t "
						+ "WHERE "
						+ "m.typeid = t.id limit ?,?";
				PreparedStatement pstmt=null;
				ResultSet rSet=null;
				ArrayList<MenusInfo> list=new ArrayList<MenusInfo>();
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,page.getBeginIndex());
					pstmt.setInt(2, page.getEveryPage());
					rSet=pstmt.executeQuery();
					while (rSet.next()) {
						MenusInfo menusInfo=new MenusInfo();
						menusInfo.setId(rSet.getInt(1));
						menusInfo.setName(rSet.getString(2));
						menusInfo.setTypeid(rSet.getString(3));
						menusInfo.setTypename(rSet.getString(4));
						menusInfo.setBurden(rSet.getString(5));
						menusInfo.setBrief(rSet.getString(6));
						menusInfo.setPrice(rSet.getString(7));
						menusInfo.setPrice1(rSet.getString(8));
						menusInfo.setSums(rSet.getString(9));
						menusInfo.setSums1(rSet.getString(10));
						menusInfo.setImgpath(rSet.getString(11));
						list.add(menusInfo);
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
	//根据id查询
	public Menus findById(int id) {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="select * from menus where id=?";
		PreparedStatement pstmt=null;
		ResultSet rSet=null;
		Menus menus=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rSet=pstmt.executeQuery();
			if(rSet.next()){
				menus=new Menus();
				menus.setId(rSet.getInt(1));
				menus.setName(rSet.getString(2));
				menus.setTypeid(rSet.getString(3));
				menus.setBurden(rSet.getString(4));
				menus.setBrief(rSet.getString(5));
				menus.setPrice(rSet.getString(6));
				menus.setSums(rSet.getString(7));
				menus.setPrice1(rSet.getString(8));
				menus.setSums1(rSet.getString(9));
				menus.setImgpath(rSet.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return menus;
	}
	//根据name查询
	public Menus findByName(String name) {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="select * from menus where name=?";
		PreparedStatement pstmt=null;
		ResultSet rSet=null;
		Menus menus=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rSet=pstmt.executeQuery();
			if(rSet.next()){
				menus=new Menus();
				menus.setId(rSet.getInt(1));
				menus.setName(rSet.getString(2));
				menus.setTypeid(rSet.getString(3));
				menus.setBurden(rSet.getString(4));
				menus.setBrief(rSet.getString(5));
				menus.setPrice(rSet.getString(6));
				menus.setSums(rSet.getString(7));
				menus.setPrice1(rSet.getString(8));
				menus.setSums1(rSet.getString(9));
				menus.setImgpath(rSet.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return menus;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
