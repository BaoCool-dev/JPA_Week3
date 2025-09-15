package LTW3.Controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import LTW3.Entity.User;
import LTW3.Service.UserService;
import LTW3.Service.Impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService = new UserServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			req.getSession().setAttribute("user", user);
			switch (user.getRoleID()) {
			case 1 -> resp.sendRedirect(req.getContextPath() + "/user/home");
			case 2 -> resp.sendRedirect(req.getContextPath() + "/manager/home");
			case 3 -> resp.sendRedirect(req.getContextPath() + "/admin/home");
			default -> resp.sendRedirect(req.getContextPath() + "/login?error=role");
			}
		} else {
			req.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
			req.getRequestDispatcher("/views/admin/cate/login.jsp").forward(req, resp);
		}
	}
}
