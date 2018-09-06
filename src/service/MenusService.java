package service;

import dao.MenusDao;
import po.Menus;
import po.Page;
import vo.MenusInfo;

public class MenusService {
	//添加
	private MenusDao menusDao=new MenusDao();
	public int add(Menus menu) {
		//根据菜品名称查询
		Menus m=menusDao.findByName(menu.getName());
		if (m!=null) {
			return -1;
		}
		return menusDao.add(menu);
	}
	//删除
	public int del(int id) {
		return menusDao.del(id);
	}
	//修改
	public int chg(int id,Menus menu) {
		//根据修改之后的菜品名称查询
		Menus m=menusDao.findByName(menu.getName());
		//能够查到，查到的菜品id和想要修改的菜品id不一致，不允许修改
		if (m!=null && m.getId()!=id) {
			return -1;
		}
		return menusDao.chg(id,menu);
	}
	//查询一共有多少条
	public long count() {
		return menusDao.count();
	}
	//分页查询
	public Page<MenusInfo> findByPage(Page<MenusInfo> page) {
		return menusDao.findByPage(page);
	}

	public Menus findById(int id) {
		return menusDao.findById(id);
	}
	public Menus findByName(String name) {
		return menusDao.findByName(name);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
