package service;

import org.junit.Test;

import po.Menus;

public class MenusServiceTest {
	private MenusService menusService=new MenusService();

	@Test
	public void testAdd() {
		Menus menus=new Menus();
		menus.setName("111111");
		int result=menusService.add(menus);
		System.out.println(result);
	}

	@Test
	public void testChg() {
		Menus menus=new Menus();
		menus.setName("水煮鱼11");
		int result=menusService.chg(12, menus);
		System.out.println(result);
	}

}
