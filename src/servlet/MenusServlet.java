package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

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
	private MenusService menusService = new MenusService();
	private TypesService typesService = new TypesService();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenusServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		long totalCount = menusService.count();
		// 创建一个Page对象 1.每页显示的条数 2.总条数 3.页数
		Page<MenusInfo> page = PageUtil.createPage(5, (int) totalCount, currentPage);
		page = menusService.findByPage(page);
		// 把page保存到域中
		request.setAttribute("menusPage", page);
		// 转发到menus.jsp
		request.getRequestDispatcher("/admin/menus.jsp").forward(request, response);
	}

	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		int result = menusService.del(id);
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.print("<script>" + "alert('删除成功');window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage'" + "</script>");
		} else {
			out.print("<script>" + "alert('删除失败，请稍后再试');window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage'" + "</script>");
		}
	}

	protected void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Menus menu = menusService.findById(id);
		// 查询所有的菜品类别
		ArrayList<Types> typesList = typesService.findAll();
		// 将查询到的内容存放在域对象中
		request.setAttribute("menu", menu);
		request.setAttribute("typesList", typesList);
		// 转发到menus_update.jsp
		request.getRequestDispatcher("/admin/menus_update.jsp").forward(request, response);
	}

	protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String name = request.getParameter("name");
		String burden = request.getParameter("burden");
		String price = request.getParameter("price");
		String price1 = request.getParameter("price1");
		String brief = request.getParameter("brief");
		String typeid = request.getParameter("typeid");
		// 将请求参数封装到对象中
		Menus menu = new Menus();
		menu.setId(id);
		menu.setName(name);
		menu.setBurden(burden);
		menu.setPrice(price);
		menu.setPrice1(price1);
		menu.setBrief(brief);
		menu.setTypeid(typeid);
		// 修改
		int result = menusService.chg(id, menu);
		PrintWriter out = response.getWriter();
		// 处理结果
		if (result == 1) {
			out.print("<script>" + "alert('修改成功');" + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage'" + "</script>");
		} else if (result == -1) {
			out.print("<script>" + "alert('菜品名称与已有名称重复，修改失败');" + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage'" + "</script>");
		} else {
			out.print("<script>" + "alert('修改失败，请稍后再试');" + "window.location.href='" + request.getContextPath()
					+ "/MenusServlet?method=findByPage'" + "</script>");
		}
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 获取请求参数，保存在menus表中 保存上传的文件
		 */
		// 新建SmartUpload对象
		SmartUpload smartUpload = new SmartUpload();
		ServletConfig config = this.getServletConfig();
		// 初始化配置
		smartUpload.initialize(config, request, response);
		// 获取请求对象
		SmartRequest req = smartUpload.getRequest();

		// 上传图片
		try {
			smartUpload.upload();
			SmartFile picFile = smartUpload.getFiles().getFile(0);
			// 获取文件名
			picFile.getFileName();
			// 获取请求参数
			String name = req.getParameter("name");
			String burden = req.getParameter("burden");
			String price = req.getParameter("price");
			String price1 = req.getParameter("price1");
			String brief = req.getParameter("brief");
			String typeid = req.getParameter("typeid");
			String imgpath = "img/" + picFile.getFileName();

			Menus menu = new Menus();
			menu.setName(name);
			menu.setBurden(burden);
			menu.setPrice(price);
			menu.setPrice1(price1);
			menu.setBrief(brief);
			menu.setTypeid(typeid);
			menu.setImgpath(imgpath);

			int result = menusService.add(menu);
			PrintWriter out = response.getWriter();

			if (result == 1) {
				smartUpload.save("img/");
				out.print("<script>" + "alert('添加成功');window.location.href='" + request.getContextPath()
						+ "/MenusServlet?method=findByPage'" + "</script>");
			} else {
				out.print("<script>" + "alert('添加失败，请稍后再试');window.location.href='" + request.getContextPath()
						+ "/MenusServlet?method=findByPage'" + "</script>");
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void preAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Types> typesList = typesService.findAll();
		request.setAttribute("typesList", typesList);
		request.getRequestDispatcher("/admin/menus_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String method = request.getParameter("method");
		if (method.equals("findByPage")) {
			findByPage(request, response);
		} else if (method.equals("del")) {
			del(request, response);
		} else if (method.equals("findById")) {
			findById(request, response);
		} else if (method.equals("chg")) {
			chg(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("preAdd")) {
			preAdd(request, response);
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
