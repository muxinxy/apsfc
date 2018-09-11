package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.Admin;
import po.Users;
import util.DBUtil;

public class UsersDao {
	// 根据用户名和密码查询
	public Users findByNameAndPwd(String name, String pwd) {
		Connection conn = DBUtil.getConn();
		String sql = "select id,name,pwd from users where name=? && pwd=?";
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
				user.setName(rSet.getString(1));
				user.setPwd(rSet.getString(2));
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
		String sql = "update users set name=?,pwd=?,realname=?,sex=?,age=?,card=?,address=?,phone=?,email=?,code=? where id=?";
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
			pstmt.setInt(11, id);
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
