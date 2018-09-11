package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.Menus;
import po.Orders;
import po.OrdersSearch;
import po.Page;
import po.Types;
import service.OrdersService;
import util.PageUtil;
import vo.MenusInfo;
import vo.OrdersInfo;
import vo.OrdersStatistics;

/**
 * Servlet implementation class OrdersServlet
 */
public class OrdersServlet extends HttpServlet {
	private OrdersService ordersService = new OrdersService();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Orders> ordersList = ordersService.findAll();
		// 把查询到的信息添加到request域中
		request.setAttribute("ordersList", ordersList);
		// 转发到type.jsp
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}

	protected void findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPageStr = request.getParameter("currentPage");
		int currentPage;
		// 如果没有currentPage,默认查询第一页
		if (currentPageStr == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		// 总条数
		long totalCount = ordersService.count();
		// 创建一个Page对象 1.每页显示的条数 2.总条数 3.页数
		Page<Orders> page = PageUtil.createPage(5, (int) totalCount, currentPage);
		if (currentPage>page.getTotalPage() && currentPage!=1) {
			currentPage=page.getTotalPage();
		}
		page = PageUtil.createPage(5, (int) totalCount, currentPage);
		page = ordersService.findByPage(page);
		// 把page保存到域中
		request.setAttribute("ordersPage", page);
		// 转发到order.jsp
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}

	protected void yes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String currentPageStr=request.getParameter("currentPage");
		int id = Integer.parseInt(idStr);
		Orders order = new Orders();
		order.setDelivery("1");
		int result = ordersService.chg(id, order);
		PrintWriter out=response.getWriter();
		if (result == 1) {
			out.print("<script>"
    				+ "alert('确认订单成功');"
        			+ "window.location.href='"
        			+ request.getContextPath()
        			+ "/OrdersServlet?method=findByPage&currentPage="
        			+ currentPageStr
        			+ "';</script>");
		}else {
			out.print("<script>"
    				+ "alert('确认订单失败');"
        			+ "window.location.href='"
        			+ request.getContextPath()
        			+ "/OrdersServlet?method=findByPage&currentPage="
        			+ currentPageStr
        			+ "';</script>");
		}
	}

	protected void no(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String currentPageStr=request.getParameter("currentPage");
		int id = Integer.parseInt(idStr);
		int result = ordersService.del(id);
		PrintWriter out=response.getWriter();
		if (result == 1) {
			out.print("<script>"
    				+ "alert('取消订单成功');"
        			+ "window.location.href='"
        			+ request.getContextPath()
        			+ "/OrdersServlet?method=findByPage&currentPage="
        			+ currentPageStr
        			+ "';</script>");
		}else {
			out.print("<script>"
    				+ "alert('取消订单失败');"
        			+ "window.location.href='"
        			+ request.getContextPath()
        			+ "/OrdersServlet?method=findByPage&currentPage="
        			+ currentPageStr
        			+ "';</script>");
		}
	}

	protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPageStr = request.getParameter("currentPage");
		String userid = request.getParameter("userid");
		String menuname = request.getParameter("menuname");
		String date = request.getParameter("date");
		int currentPage;
		// 如果没有currentPage,默认查询第一页
		if (currentPageStr == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		OrdersSearch search = new OrdersSearch();
		search.setUserid(userid);
		search.setMenuname(menuname);
		search.setDate(date);
		// 总条数
		long totalCount = ordersService.findCount(search);
		// 创建一个Page对象 1.每页显示的条数 2.总条数 3.页数
		Page<OrdersInfo> page = PageUtil.createPage(5, (int) totalCount, currentPage);
		page = ordersService.find(page, search);
		// 将查询到的内容存放在域对象中
		request.setAttribute("ordersSearchPage", page);
		request.setAttribute("search", search);
		// 转发到order_search.jsp
		request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
	}

	protected void findStatistics(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<OrdersStatistics> ordersStatistics = ordersService.findStatistics();
		request.setAttribute("orderStatistics", ordersStatistics);
		request.getRequestDispatcher("/admin/order_statistic.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if (method.equals("findAll")) {
			findAll(request, response);
		} else if (method.equals("findByPage")) {
			findByPage(request, response);
		} else if (method.equals("yes")) {
			yes(request, response);
		} else if (method.equals("no")) {
			no(request, response);
		} else if (method.equals("findById")) {
			find(request, response);
		} else if (method.equals("findStatistics")) {
			findStatistics(request, response);
		} else if (method.equals("find")) {
			find(request, response);
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
