package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import po.Menus;
import po.Page;
import util.PageUtil;
import vo.MenusInfo;

public class MenusDaoTest {
	MenusDao menusDao=new MenusDao();

	@Test
	public void testAdd() {
		
	}
	
	@Test
	public void testDel() {
		
	}
	
	@Test
	public void testChg() {
		Menus menus=new Menus();
		menus.setName("水煮鱼11");
		int result=menusDao.chg(12, menus);
		System.out.println(result);
	}
	
	@Test
	public void testCount() {
		System.out.println(menusDao.count());
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindByPage() {
		long totalcount=menusDao.count();
		Page<MenusInfo> page=PageUtil.createPage(2,(int)totalcount,4);
		page=menusDao.findByPage(page);
		List<MenusInfo> list=page.getList();
		for(MenusInfo menusInfo:list){
			System.out.println(menusInfo);
		}
	}
	
	@Test
	public void testFindById() {
		Menus menus=new Menus();
		menus=menusDao.findById(15);
		System.out.println(menus);
	}
	
	@Test
	public void testFindByName() {
		
	}

}
