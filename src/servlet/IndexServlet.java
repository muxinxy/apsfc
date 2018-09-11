package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import po.CarItem;
import po.Menus;
import po.Orders;
import po.Page;
import service.MenusService;
import util.PageUtil;
import vo.MenusInfo;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenusService menusService=new MenusService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void allInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if (currentPage>page.getTotalPage() && currentPage!=1) {
			currentPage=page.getTotalPage();
		}
		page = PageUtil.createPage(5, (int) totalCount, currentPage);
		page = menusService.findByPage(page);
		// 把page保存到域中
		request.setAttribute("menusPage", page);
		// 转发到index.jsp
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
	}
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*
    	 * 获取menu的id
    	 * 获取表示餐车的对象
    	 * 	如果获取不到餐车
    	 * 		创建餐车
    	 * 		根据id查询menu
    	 * 		根据menu的信息创建CarItem的对象，并将数量设置成1
    	 * 		将餐车添加到session中
    	 * 	如果获取到餐车
    	 * 		根据id查询是否有对应的CarItem
    	 * 			找到，数量加1
    	 * 			找不到
    	 * 				根据id创建menu
    	 * 				根据menu的信息创建CarItem的对象，并将数量设置成1
    	 */
    	String idStr=request.getParameter("id");
    	int id=Integer.parseInt(idStr);
    	HttpSession session=request.getSession();
    	ArrayList<CarItem> carList=(ArrayList<CarItem>) session.getAttribute("carList");
    	if (carList==null) {
			carList=new ArrayList<CarItem>();
			Menus menus=menusService.findById(id);
			CarItem item=new CarItem();
			item.setMenusid(id);
			item.setMenusname(menus.getName());
			item.setPrice(menus.getPrice());
			item.setCount(1);
			carList.add(item);
			session.setAttribute("carList", carList);
		}else {
			boolean flag=false;
			for(CarItem carItem:carList){
				if (carItem.getMenusid()==id) {
					carItem.setCount(carItem.getCount()+1);
					flag=true;
				}
			}
			if (!flag) {
				//确实没有找到
				Menus menus = menusService.findById(id);
				//根据menu创建CarItem的对象，并将数量设置成1
				CarItem item = new CarItem();
				item.setMenusid(id);
				item.setMenusname(menus.getName());
				item.setPrice(menus.getPrice());
				item.setCount(1);
				//将CarItem添加到餐车中
				carList.add(item);
			}
		}
    	allInfo(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		if (method.equals("allInfo")) {
			allInfo(request, response);
		}else if (method.equals("addItem")) {
			addItem(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
