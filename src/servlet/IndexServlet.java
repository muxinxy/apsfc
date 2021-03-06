package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.CarItem;
import po.Menus;
import po.Notice;
import po.Orders;
import po.Page;
import service.MenusService;
import service.NoticeService;
import service.OrdersService;
import util.PageUtil;
import vo.MenusInfo;
import vo.OrdersInfo;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenusService menusService = new MenusService();
	private OrdersService ordersService = new OrdersService();
	private NoticeService noticeService = new NoticeService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void allInfo(HttpServletRequest request, HttpServletResponse response)
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
		long totalCount = menusService.count();
		// 创建一个Page对象 1.每页显示的条数 2.总条数 3.页数
		Page<MenusInfo> page = PageUtil.createPage(5, (int) totalCount, currentPage);
		if (currentPage > page.getTotalPage() && currentPage != 1) {
			currentPage = page.getTotalPage();
		}
		page = PageUtil.createPage(5, (int) totalCount, currentPage);
		page = menusService.findByPage(page);
		// 把page保存到域中
		request.setAttribute("menusPage", page);
		ArrayList<Notice> newsList = noticeService.findAll();
		// 把查询到的信息添加到request域中
		request.setAttribute("newsList", newsList);
		ArrayList<OrdersInfo> ordersRankList=ordersService.ordersRank();
		request.setAttribute("ordersRankList", ordersRankList);
		// 转发到index.jsp
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);

	}

	protected void addItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 获取menu的id 获取表示餐车的对象 如果获取不到餐车 创建餐车 根据id查询menu
		 * 根据menu的信息创建CarItem的对象，并将数量设置成1 将餐车添加到session中 如果获取到餐车
		 * 根据id查询是否有对应的CarItem 找到，数量加1 找不到 根据id创建menu
		 * 根据menu的信息创建CarItem的对象，并将数量设置成1
		 */
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		HttpSession session = request.getSession();
		ArrayList<CarItem> carList = (ArrayList<CarItem>) session.getAttribute("carList");
		if (carList == null) {
			carList = new ArrayList<CarItem>();
			Menus menus = menusService.findById(id);
			CarItem item = new CarItem();
			item.setMenusid(id);
			item.setMenusname(menus.getName());
			item.setPrice(menus.getPrice());
			item.setCount(1);
			carList.add(item);
			session.setAttribute("carList", carList);
		} else {
			boolean flag = false;
			for (CarItem carItem : carList) {
				if (carItem.getMenusid() == id) {
					carItem.setCount(carItem.getCount() + 1);
					flag = true;
				}
			}
			if (!flag) {
				// 确实没有找到
				Menus menus = menusService.findById(id);
				// 根据menu创建CarItem的对象，并将数量设置成1
				CarItem item = new CarItem();
				item.setMenusid(id);
				item.setMenusname(menus.getName());
				item.setPrice(menus.getPrice());
				item.setCount(1);
				// 将CarItem添加到餐车中
				carList.add(item);
			}
		}
		allInfo(request, response);
	}

	protected void removeItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menusidStr = request.getParameter("menusid");
		int menusid = Integer.parseInt(menusidStr);

		HttpSession session = request.getSession();
		ArrayList<CarItem> carList = (ArrayList<CarItem>) session.getAttribute("carList");

		for (CarItem carItem : carList) {
			if (carItem.getMenusid() == menusid) {
				carList.remove(carItem);
				break;
			}
		}
		session.setAttribute("carList", carList);
		allInfo(request, response);
	}

	protected void removeAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<CarItem> carList = (ArrayList<CarItem>) session.getAttribute("carList");
		session.removeAttribute("carList");
		allInfo(request, response);
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<CarItem> carList = (ArrayList<CarItem>) session.getAttribute("carList");
		String userid = request.getParameter("userid");
		Orders order = new Orders();
		int result = 1;
		for (CarItem carItem : carList) {
			order.setUserid(userid);
			int menuid = carItem.getMenusid();
			String menuidStr = String.valueOf(menuid);
			order.setMenuid(menuidStr);
			int menusum = carItem.getCount();
			String menusumStr = String.valueOf(menusum);
			order.setMenusum(menusumStr);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String times = sdf.format(d);
			order.setTimes(times);
			order.setDelivery("0");
			int res = ordersService.add(order);
			if (res != 1) {
				result = 0;
			}
		}
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.print("<script>" + "alert('提交成功');" + "window.location.href='" + request.getContextPath()
					+ "/qiantai/index.jsp';" + "</script>");
		} else {
			out.print("<script>" + "alert('提交失败');" + "window.location.href='" + request.getContextPath()
					+ "/qiantai/index.jsp';" + "</script>");
		}
		removeAll(request, response);
	}

	protected void news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String times = request.getParameter("times");
		Notice notice = new Notice();
		notice.setId(id);
		notice.setName(name);
		notice.setContent(content);
		notice.setTimes(times);
		request.setAttribute("news", notice);
		request.getRequestDispatcher("/qiantai/news.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if (method.equals("allInfo")) {
			allInfo(request, response);
		} else if (method.equals("addItem")) {
			addItem(request, response);
		} else if (method.equals("removeItem")) {
			removeItem(request, response);
		} else if (method.equals("removeAll")) {
			removeAll(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("news")) {
			news(request, response);
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
