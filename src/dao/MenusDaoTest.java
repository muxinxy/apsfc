package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import po.Page;
import util.PageUtil;
import vo.MenusInfo;

public class MenusDaoTest {
	MenusDao menusDao=new MenusDao();

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

}
