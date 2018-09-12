package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.Users;
import service.UsersService;

/**
 * Servlet implementation class UsersServlet
 */
public class UsersServlet extends HttpServlet {
	private UsersService usersService = new UsersService();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		// 登录
		Users user = usersService.login(name, pwd);
		// 成功——/qiantai/login.jsp
		if (user != null) {
			// 将用户信息存放在session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			// 转发
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		// 失败——/qiantai/login.jsp
		else {
			PrintWriter out = response.getWriter();
			out.print("<script>" + "alert('用户名或密码错误');" + "window.location.href='" + request.getContextPath()
					+ "/qiantai/login.jsp';" + "</script>");
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从session删除用户信息
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		// 跳转到登录页面
		PrintWriter out = response.getWriter();
		out.print(
				"<script>" + "window.parent.location.href='" + request.getContextPath() + "/index.jsp';" + "</script>");
	}

	protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String card = request.getParameter("card");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		Users user = new Users();
		user.setId(id);
		user.setName(name);
		user.setPwd(pwd);
		user.setRealname(realname);
		user.setSex(sex);
		user.setAge(age);
		user.setCard(card);
		user.setAddress(address);
		user.setPhone(phone);
		user.setEmail(email);
		user.setCode(code);
		user.setType("1");
		int result = usersService.chg(id, user);
		PrintWriter out = response.getWriter();
		if (result == 1) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			out.print("<script>" + "alert('修改成功,请重新登录');" + "window.parent.location.href='" + request.getContextPath()
					+ "/qiantai/login.jsp';" + "</script>");
		} else {
			out.print("<script>" + "alert('修改失败');" + "window.location.href='" + request.getContextPath()
					+ "/qiantai/center.jsp';" + "</script>");
		}
	}

	protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String card = request.getParameter("card");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		Users user = new Users();
		user.setName(name);
		user.setPwd(pwd);
		user.setRealname(realname);
		user.setSex(sex);
		user.setAge(age);
		user.setCard(card);
		user.setAddress(address);
		user.setPhone(phone);
		user.setEmail(email);
		user.setCode(code);
		user.setType("1");
		int result = usersService.reg(user);
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.print("<script>" + "alert('注册成功');" + "window.location.href='" + request.getContextPath()
					+ "/qiantai/login.jsp';" + "</script>");
		} else {
			out.print("<script>" + "alert('注册失败');" + "window.location.href='" + request.getContextPath()
					+ "/qiantai/reg.jsp';" + "</script>");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("logout")) {
			logout(request, response);
		} else if (method.equals("chg")) {
			chg(request, response);
		} else if (method.equals("reg")) {
			reg(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
