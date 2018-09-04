package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.CommonDialogs.Type;

import po.Types;
import service.TypesService;

/**
 * Servlet implementation class TypesServlet
 */
public class TypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TypesService typesService=new TypesService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ArrayList<Types> typesList=typesService.findAll();
    	//把查询到的信息添加到request域中
    	request.setAttribute("typesList", typesList);
    	//转发到type.jsp
    	request.getRequestDispatcher("/admin/type.jsp").forward(request, response);
    }
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idStr=request.getParameter("id");
    	int id=Integer.parseInt(idStr);
    	int result=typesService.del(id);
    	if (result==1) {
			findAll(request, response);
		}
    }
    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*
    	 * 获取id
    	 * 根据id查询
    	 * 将查询到的内容存放在域中
    	 * 转发到修改页
    	 */
    	String idStr=request.getParameter("id");
    	int id=Integer.parseInt(idStr);
    	Types type=typesService.findById(id);
    	request.setAttribute("type", type);
    	request.getRequestDispatcher("/admin/type_update.jsp").forward(request, response);
    }
    protected void chg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String method=request.getParameter("method");
		if (method.equals("findAll")) {
			findAll(request, response);
		}else if (method.equals("del")) {
			del(request, response);
		}else if (method.equals("findById")) {
			findById(request, response);
		}else if (method.equals("chg")) {
			chg(request, response);
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
