package service;

import dao.MenusDao;
import po.Page;
import vo.MenusInfo;

public class MenusService {
	//查询一共有多少条
	private MenusDao menusDao=new MenusDao();
	public long count() {
		return menusDao.count();
	}
	//分页查询
	public Page<MenusInfo> findByPage(Page<MenusInfo> page) {
		return menusDao.findByPage(page);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
