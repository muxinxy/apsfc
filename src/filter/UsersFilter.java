package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import po.Users;

/**
 * Servlet Filter implementation class UsersFilter
 */
public class UsersFilter implements Filter {
	private FilterConfig config = null;
    /**
     * Default constructor. 
     */
    public UsersFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		/*
		 * 如果没有登录——index.jsp 已经登录——放行
		 */
		//读取初始化配置
		String indexPath=this.config.getInitParameter("indexPath");
		// 获取session
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		// 获取登录对象
		Users user = (Users) session.getAttribute("user");
		// 获取用户请求地址
		String path = req.getServletPath();
		// 判断用户是否登录
		if (user != null || path.equals(indexPath) || path.endsWith(".css") || path.endsWith(".js")
				|| path.endsWith(".gif") || path.endsWith(".jpg") || path.equals("/qiantai/reg.jsp")
				 || path.equals("/qiantai/index.jsp") || path.equals("/qiantai/notice.jsp")
				 || path.equals("/qiantai/our.jsp") || path.equals("/qiantai/copyright.jsp") 
				 || path.equals("/qiantai/carry.jsp") || path.equals("/qiantai/news_more.jsp") 
				 || path.equals("/qiantai/news.jsp")) {
			chain.doFilter(request, response);
		} else {
			req.getRequestDispatcher("/qiantai/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config=fConfig;
	}

}
