package LTW3.Controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import LTW3.Entity.User;
import LTW3.Service.UserService;
import LTW3.Service.Impl.UserServiceImpl;

@WebServlet({ "/login", "/register", "/logout", "/profile" })
public class UserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4653223141130490761L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		if (uri.contains("login")) {
			req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
		} else if (uri.contains("register")) {
			req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
		} else if (uri.contains("profile")) {
			req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
		} else if (uri.contains("logout")) {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		if (uri.contains("login")) {
			handleLogin(req, resp);
		} else if (uri.contains("register")) {
			handleRegister(req, resp);
		}
	}

	private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("account", user);
			resp.sendRedirect(req.getContextPath() + "/profile");
		} else {
			req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
			req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
		}
	}

	private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname"); 
		String phone = req.getParameter("phone"); 

		boolean success = userService.register(username, password, email, fullname, phone);

		if (success) {
			req.setAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
		} else {
			req.setAttribute("error", "Tên đăng nhập đã tồn tại!");
		}
		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	}

}
