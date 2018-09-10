package dao;

import org.junit.Test;

import po.Admin;

public class AdminDaoTest {
	private AdminDao adminDao=new AdminDao();

	@Test
	public void testChg() {
		Admin admin=new Admin();
		admin.setName("admin");
		admin.setPwd("123");
		int result=adminDao.chg(2, admin);
		System.out.println(result);
	}

}
