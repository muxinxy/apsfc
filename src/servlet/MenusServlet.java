package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.Menus;
import po.Page;
import po.Types;
import service.MenusService;
import service.TypesService;
import util.PageUtil;
import vo.MenusInfo;

/**
 * Servlet implementation class MenusServlet
 */
public class MenusServlet extends HttpServlet {
	private MenusService menusService=new MenusService();
	private TypesService typesService=new TypesService();
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
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);
		int result=menusService.del(id);
		PrintWriter out=response.getWriter();
		if (result==1) {
			out.print("<script>"
					+ "alert('删除成功');window.location.href='"
					+request.getContextPath()
					+"/MenusServlet?method=findByPage'"
					+ "</script>");
		}else {
			out.print("<script>"
					+ "alert('删除失败');window.location.href='"
					+request.getContextPath()
					+"/MenusServlet?method=findByPage'"
					+ "</script>");
		}
	}
    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);
		Menus menu=menusService.findById(id);
		//查询所有的菜品类别
		ArrayList<Types> typesList=typesService.findAll();
		//将查询到的内容存放在域对象中
		request.setAttribute("menu", menu);
		request.setAttribute("typesList", typesList);
		//转发到menus_update.jsp
		request.getRequestDispatcher("/admin/menus_update.jsp").forward(request, response);
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
		}else if (method.equals("del")) {
			del(request, response);
		}else if (method.equals("findById")) {
			findById(request, response);
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
