package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.Page;
import service.MenusService;
import util.PageUtil;
import vo.MenusInfo;

/**
 * Servlet implementation class MenusServlet
 */
public class MenusServlet extends HttpServlet {
	private MenusService menusService=new MenusService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPageStr=request.getParameter("currentPage");
		int currentPage;
		//如果没有currentPage,默认查询第一页
		if (currentPageStr==null) {
			currentPage=1;
		}else {
			currentPage=Integer.parseInt(currentPageStr);
		}
		//总条数
		long totalCount=menusService.count();
		//创建一个Page对象  1.每页显示的条数 2.总条数 3.页数
		Page<MenusInfo> page=PageUtil.createPage(5, (int)totalCount, currentPage);
		page=menusService.findByPage(page);
		//把page保存到域中
		request.setAttribute("menusPage", page);
		//转发到menus.jsp
		request.getRequestDispatcher("/admin/menus.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String method=request.getParameter("method");
		if (method.equals("findByPage")) {
			findByPage(request, response);
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
