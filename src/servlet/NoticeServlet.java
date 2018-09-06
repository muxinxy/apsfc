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

import po.Notice;
import po.Types;
import service.NoticeService;

/**
 * Servlet implementation class NoticeServlet
 */
public class NoticeServlet extends HttpServlet {
	private NoticeService noticeService=new NoticeService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ArrayList<Notice> noticesList=noticeService.findAll();
    	//把查询到的信息添加到request域中
    	request.setAttribute("noticesList", noticesList);
    	//转发到notice.jsp
    	request.getRequestDispatcher("/admin/notice.jsp").forward(request, response);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name=request.getParameter("name");
    	String content=request.getParameter("content");
    	Date d = new Date();
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String times = sdf.format(d);
    	Notice notice=new Notice();
    	notice.setName(name);
    	notice.setContent(content);
    	notice.setTimes(times);
    	int result=noticeService.add(notice);
    	PrintWriter out=response.getWriter();
    	//根据返回结果处理
    	if (result==1) {
			//弹出对话框
    		//跳转到查询所有界面
    		out.print("<script>"
    				+ "alert('添加成功');"
    				+ "window.location.href='"
    				+ request.getContextPath()
    				+ "/NoticeServlet?method=findAll';"
    				+ "</script>");
		}else {
			out.print("<script>"
    				+ "alert('添加失败，请稍后再试');"
    				+ "window.location.href='"
    				+ request.getContextPath()
    				+ "/NoticeServlet?method=findAll';"
    				+ "</script>");
		}
	}
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);
		int result=noticeService.del(id);
		PrintWriter out=response.getWriter();
		if (result==1) {
			//弹出对话框
    		//跳转到查询所有界面
    		out.print("<script>"
    				+ "alert('删除成功');"
    				+ "window.location.href='"
    				+ request.getContextPath()
    				+ "/NoticeServlet?method=findAll';"
    				+ "</script>");
		}else {
			out.print("<script>"
    				+ "alert('删除失败，请稍后再试');"
    				+ "window.location.href='"
    				+ request.getContextPath()
    				+ "/NoticeServlet?method=findAll';"
    				+ "</script>");
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		}else if (method.equals("del")) {
			del(request, response);
		}else if (method.equals("findAll")) {
			findAll(request, response);
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
