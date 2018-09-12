package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.Admin;
import po.Menus;
import po.Users;
import util.DBUtil;

public class UsersDao {
	//添加
	public int add(Users user) {
		//获取连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="insert into users(name,pwd,realname,sex,age,card,address,phone,email,code,type) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getRealname());
			pstmt.setString(4, user.getSex());
			pstmt.setString(5, user.getAge());
			pstmt.setString(6, user.getCard());
			pstmt.setString(7, user.getAddress());
			pstmt.setString(8, user.getPhone());
			pstmt.setString(9, user.getEmail());
			pstmt.setString(10, user.getCode());
			pstmt.setString(11, user.getType());
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
	// 根据用户名和密码查询
	public Users findByNameAndPwd(String name, String pwd) {
		Connection conn = DBUtil.getConn();
		String sql = "select * from users where name=? && pwd=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Users user = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				user = new Users();
				user.setId(rSet.getInt(1));
				user.setName(rSet.getString(2));
				user.setPwd(rSet.getString(3));
				user.setRealname(rSet.getString(4));
				user.setSex(rSet.getString(5));
				user.setAge(rSet.getString(6));
				user.setCard(rSet.getString(7));
				user.setAddress(rSet.getString(8));
				user.setPhone(rSet.getString(9));
				user.setEmail(rSet.getString(10));
				user.setCode(rSet.getString(11));
				user.setType(rSet.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return user;
	}

	// 根据用户名查询
	public Users findByName(String name) {
		Connection conn = DBUtil.getConn();
		String sql = "select * from users where name=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Users user = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				user = new Users();
				user.setId(rSet.getInt(1));
				user.setName(rSet.getString(2));
				user.setPwd(rSet.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return user;
	}

	// 修改
	public int chg(int id, Users user) {
		Connection conn = DBUtil.getConn();
		String sql = "update users set name=?,pwd=?,realname=?,sex=?,age=?,card=?,address=?,phone=?,email=?,code=?,type=? where id=?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getRealname());
			pstmt.setString(4, user.getSex());
			pstmt.setString(5, user.getAge());
			pstmt.setString(6, user.getCard());
			pstmt.setString(7, user.getAddress());
			pstmt.setString(8, user.getPhone());
			pstmt.setString(9, user.getEmail());
			pstmt.setString(10, user.getCode());
			pstmt.setString(11, user.getType());
			pstmt.setInt(12, id);
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
}
