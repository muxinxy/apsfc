package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.Types;
import util.DBUtil;

public class TypesDao {
	//添加
	public int add(Types type) {
		//创建连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="insert into types(name) values(?)";
		//创建PrepareStatement
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, type.getName());
			//发送语句
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
		//创建连接
				Connection conn=DBUtil.getConn();
				//sql语句
				String sql="delete from types where id=?";
				//创建PrepareStatement
				PreparedStatement pstmt=null;
				int result=0;
				try {
					pstmt=conn.prepareStatement(sql);
					//设置参数
					pstmt.setInt(1, id);
					//发送语句
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
	public int chg(int id,String name) {//创建连接
		Connection conn=DBUtil.getConn();
		//sql语句
		String sql="update types set name=? where id=?";
		//创建PrepareStatement
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			//发送语句
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
	//查询所有
	public ArrayList<Types> findAll() {
		//创建连接
				Connection conn=DBUtil.getConn();
				//sql语句
				String sql="select * from types";
				//创建PrepareStatement
				PreparedStatement pstmt=null;
				ResultSet rSet=null;
				ArrayList<Types> list=new ArrayList<Types>();
				try {
					pstmt=conn.prepareStatement(sql);
					//发送语句
					rSet=pstmt.executeQuery();
					//处理结果 结果集——ArrayList
					while (rSet.next()) {
						Types type = new Types();
						type.setId(rSet.getInt(1));
						type.setName(rSet.getString(2));
						list.add(type);
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
	//根据id查询******
	public Types findById(int id) {
		//创建连接
				Connection conn=DBUtil.getConn();
				//sql语句
				String sql="select * from types where id=?";
				//创建PrepareStatement
				PreparedStatement pstmt=null;
				ResultSet rSet=null;
				Types type=null;
				try {
					pstmt=conn.prepareStatement(sql);
					//设置参数
					pstmt.setInt(1, id);
					//发送语句
					rSet=pstmt.executeQuery();
					if (rSet.next()) {
						type = new Types();
						type.setId(rSet.getInt(1));
						type.setName(rSet.getString(2));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.closeRst(rSet);
					DBUtil.closePstmt(pstmt);
					DBUtil.closeConn(conn);
				}
				return type;
	}
	//根据name查询
	public Types findByName(String name) {
		//创建连接
				Connection conn=DBUtil.getConn();
				//sql语句
				String sql="select * from types where name=?";
				//创建PrepareStatement
				PreparedStatement pstmt=null;
				ResultSet rSet=null;
				Types type=null;
				try {
					pstmt=conn.prepareStatement(sql);
					//设置参数
					pstmt.setString(1, name);
					//发送语句
					rSet=pstmt.executeQuery();
					if (rSet.next()) {
						type = new Types();
						type.setId(rSet.getInt(1));
						type.setName(rSet.getString(2));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.closeRst(rSet);
					DBUtil.closePstmt(pstmt);
					DBUtil.closeConn(conn);
				}
				return type;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
