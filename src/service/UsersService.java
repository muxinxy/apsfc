package service;

import dao.UsersDao;
import po.Users;

public class UsersService {
	private UsersDao usersDao = new UsersDao();

	// 登录
	public Users login(String name, String pwd) {
		return usersDao.findByNameAndPwd(name, pwd);
	}

	// 修改
	public int chg(int id, Users user) {
		Users a = usersDao.findByName(user.getName());
		if (a != null && a.getId() != id) {
			return -1;
		}
		return usersDao.chg(id, user);
	}
}
