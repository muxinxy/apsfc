package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.Page;
import util.DBUtil;
import vo.MenusInfo;

public class MenusDao {
	//添加
	//删除
	//修改
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
	//查询所有——分页查询
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
	//根据name查询

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
