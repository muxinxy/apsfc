package service;

import org.junit.Test;

import po.Admin;

public class AdminServiceTest {
	private AdminService adminService = new AdminService();

	@Test
	public void testChg() {
		Admin admin = new Admin();
		admin.setName("zs");
		admin.setPwd("123");
		int id = 1;
		int result = adminService.chg(id, admin);
		System.out.println(result);
	}

}
