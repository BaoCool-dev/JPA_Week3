package LTW3.Controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import LTW3.Entity.User;

import LTW3.Util.Constant;

@WebServlet({ "/profile" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(Constant.SESSION_ACCOUNT);

		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		req.setAttribute("user", currentUser);
		req.getRequestDispatcher(Constant.Path.USER_PROFILE).forward(req, resp);
	}
}
