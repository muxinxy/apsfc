package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.Menus;
import po.Notice;
import po.Page;
import util.DBUtil;
import vo.MenusInfo;

public class NoticeDao {
	// 添加
	public int add(Notice notice) {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "insert into notice(name,content,times) values(?,?,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getName());
			pstmt.setString(2, notice.getContent());
			pstmt.setString(3, notice.getTimes());
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
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "delete from notice where id=?";
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

	// 查询所有
	public ArrayList<Notice> findAll() {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "select id,name,content,times from notice";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			pstmt = conn.prepareStatement(sql);
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				Notice notice = new Notice();
				notice.setId(rSet.getInt(1));
				notice.setName(rSet.getString(2));
				notice.setContent(rSet.getString(3));
				notice.setTimes(rSet.getString(4));
				list.add(notice);
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

	public Notice findByName(String name) {
		// 获取连接
		Connection conn = DBUtil.getConn();
		// sql语句
		String sql = "select * from notice where name=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Notice notice=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rSet=pstmt.executeQuery();
			if (rSet.next()) {
				notice=new Notice();
				notice.setId(rSet.getInt(1));
				notice.setName(rSet.getString(2));
				notice.setContent(rSet.getString(3));
				notice.setTimes(rSet.getString(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return notice;
	}
}
