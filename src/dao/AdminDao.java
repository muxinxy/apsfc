package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.Admin;
import util.DBUtil;

public class AdminDao {
	// 根据用户名和密码查询
	public Admin findByNameAndPwd(String name, String pwd) {
		Connection conn = DBUtil.getConn();
		String sql = "select id,name,pwd from admin where name=? && pwd=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Admin admin = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				admin = new Admin();
				admin.setId(rSet.getInt(1));
				admin.setName(rSet.getString(2));
				admin.setPwd(rSet.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return admin;
	}
	//根据用户名和查询
	public Admin findByName(String name) {
		Connection conn = DBUtil.getConn();
		String sql = "select * from admin where name=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Admin admin = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				admin = new Admin();
				admin.setName(rSet.getString(1));
				admin.setPwd(rSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return admin;
	}
	//修改
	public int chg(int id,Admin admin) {
		Connection conn = DBUtil.getConn();
		String sql = "update admin set name=?,pwd=? where id=?";
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPwd());
			pstmt.setInt(3, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return result;
	}
}
