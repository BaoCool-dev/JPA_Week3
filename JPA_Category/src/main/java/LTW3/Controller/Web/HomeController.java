package LTW3.Controller.Web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import LTW3.Entity.User;
import LTW3.Service.*;
import LTW3.Service.Impl.*;
import LTW3.Util.Constant;
import LTW3.Util.Email;

@WebServlet(urlPatterns = { "/home", "/login", "/register", "/forgotpass", "/waiting", "/VerifyCode", "/logout" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("register")) {
			getRegister(req, resp);
		} else if (url.contains("login")) {
			getLogin(req, resp);
		} else if (url.contains("forgotpass")) {
			req.getRequestDispatcher(Constant.Path.FORGOTPASSWORD).forward(req, resp);
		} else if (url.contains("waiting")) {
			getWaiting(req, resp);
		} else if (url.contains("VerifyCode")) {
			req.getRequestDispatcher(Constant.Path.VERIFYCODE).forward(req, resp);
		} else if (url.contains("logout")) {
			doLogout(req, resp);
		} else {
			homePage(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("register")) {
			postRegister(req, resp);
		} else if (url.contains("login")) {
			postLogin(req, resp);
		} else if (url.contains("forgotpass")) {
			postForgotPassword(req, resp);
		} else if (url.contains("VerifyCode")) {
			postVerifyCode(req, resp);
		}
	}

	// ==================== LOGIN ====================
	private void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
	}

	private void postLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username"); // ⚠️ form phải đặt name="username"
		String password = req.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("userLogin", user);

			// Ghi nhớ đăng nhập nếu người dùng chọn
			if ("on".equals(req.getParameter("remember"))) {
				saveRememberMe(resp, username);
			}

			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			req.setAttribute("alert", "Sai tài khoản hoặc mật khẩu!");
			req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
		}
	}

	// ==================== REGISTER ====================
	private void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
	}

	private void postRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("userName"); // form phải đồng bộ
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullName");
		String phone = req.getParameter("phone");

		String alertMsg = null;

		if (userService.checkExistEmail(email)) {
			alertMsg = "Email đã tồn tại!";
		} else if (userService.checkExistUsername(username)) {
			alertMsg = "Tên đăng nhập đã tồn tại!";
		} else if (userService.checkExistPhone(phone)) {
			alertMsg = "Số điện thoại đã tồn tại!";
		}

		if (alertMsg != null) {
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			return;
		}

		// gửi email xác thực
		Email sm = new Email();
		String code = sm.getRandom();
		User tempUser = new User(username, email, fullname, code);

		boolean sent = sm.sendEmail(tempUser);
		if (sent) {
			HttpSession session = req.getSession();
			session.setAttribute("account", tempUser);
			// Tạm thời lưu mật khẩu và phone trong session để tạo sau khi xác thực
			session.setAttribute("password", password);
			session.setAttribute("phone", phone);

			resp.sendRedirect(req.getContextPath() + "/VerifyCode");
		} else {
			req.setAttribute("alert", "Không thể gửi email xác thực!");
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
		}
	}

	// ==================== VERIFY CODE ====================
	private void postVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String code = req.getParameter("authcode");
		HttpSession session = req.getSession();

		User tempUser = (User) session.getAttribute("account");
		String password = (String) session.getAttribute("password");
		String phone = (String) session.getAttribute("phone");

		if (tempUser != null && code.equals(tempUser.getCode())) {
			// lưu user vào DB
			boolean success = userService.register(tempUser.getUserName(), password, tempUser.getEmail(),
					tempUser.getFullName(), phone);

			// Xóa session tạm
			session.removeAttribute("account");
			session.removeAttribute("password");
			session.removeAttribute("phone");

			if (success) {
				req.setAttribute("successMsg", "Xác thực thành công, vui lòng đăng nhập!");
				req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
			} else {
				req.setAttribute("alert", "Đăng ký thất bại, lỗi hệ thống!");
				req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			}
		} else {
			req.setAttribute("alert", "Mã xác thực không đúng!");
			req.getRequestDispatcher(Constant.Path.VERIFYCODE).forward(req, resp);
		}
	}

	// ==================== FORGOT PASSWORD ====================
	private void postForgotPassword(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String email = req.getParameter("email");

		if (!userService.checkExistEmail(email)) {
			req.setAttribute("alert", "Email không tồn tại!");
			req.getRequestDispatcher(Constant.Path.FORGOTPASSWORD).forward(req, resp);
			return;
		}

		User user = userService.get(email);
		if (user == null) {
			req.setAttribute("alert", "Không tìm thấy tài khoản!");
			req.getRequestDispatcher(Constant.Path.FORGOTPASSWORD).forward(req, resp);
			return;
		}

		Email sm = new Email();
		String code = sm.getRandom();
		user.setCode(code);

		boolean sent = sm.sendEmail(user);
		if (sent) {
			HttpSession session = req.getSession();
			session.setAttribute("resetUser", user);
			resp.sendRedirect(req.getContextPath() + "/VerifyCode");
		} else {
			req.setAttribute("alert", "Không thể gửi email!");
			req.getRequestDispatcher(Constant.Path.FORGOTPASSWORD).forward(req, resp);
		}
	}

	// ==================== HOME + WAITING + LOGOUT ====================
	private void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.Path.HOME).forward(req, resp);
	}

	private void getWaiting(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("userLogin");

		if (u == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (u.getRoleID() == 1) { // User
			resp.sendRedirect(req.getContextPath() + "/user/home");
		} else if (u.getRoleID() == 2) { // Manager
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		} else if (u.getRoleID() == 3) { // Admin
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}

	private void saveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60); // 30 phút
		response.addCookie(cookie);
	}

	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
