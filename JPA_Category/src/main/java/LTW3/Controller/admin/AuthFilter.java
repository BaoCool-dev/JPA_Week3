package LTW3.Controller.admin;

import java.io.IOException;

import LTW3.Entity.User;
import LTW3.Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		String path = req.getRequestURI();

		if (path.contains("/login") || path.contains("/register") || path.contains("/forgotpass")
				|| path.contains("/VerifyCode") || path.contains("/home") || path.contains("/waiting")
				|| path.startsWith(req.getContextPath() + "/assets")) {
			chain.doFilter(request, response);
			return;
		}

		// Kiểm tra đăng nhập
		User user = (session != null) ? (User) session.getAttribute(Constant.SESSION_ACCOUNT) : null;
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// Dùng khóa ngoại roleID
		int roleId = user.getRoleID();

		// Chặn truy cập theo role
		if (path.startsWith(req.getContextPath() + "/admin") && roleId != 3) {
			resp.sendRedirect(req.getContextPath() + "/home");
			return;
		}

		if (path.startsWith(req.getContextPath() + "/manager") && roleId != 2) {
			resp.sendRedirect(req.getContextPath() + "/home");
			return;
		}

		chain.doFilter(request, response);
	}
}