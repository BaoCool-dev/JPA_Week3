package LTW3.Controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import LTW3.Entity.Category;
import LTW3.Entity.User;
import LTW3.Service.CategoryService;
import LTW3.Service.Impl.CategoryServiceImpl;
import LTW3.Util.Constant;
import LTW3.Util.UploadUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/category", "/category/create", "/category/update", "/category/edit", "/category/delete",
		"/category/reset" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 25 // 25MB
)
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(Constant.SESSION_ACCOUNT);

		if (currentUser == null) {
			resp.sendRedirect("login");
			return;
		}

		int roleId = currentUser.getRole().getRoleId();

		if (url.contains("create")) {
			// Chỉ Manager hoặc Admin được tạo
			if (roleId == 2 || roleId == 3) {
				req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
				return;
			}
		} else if (url.contains("delete")) {
			if (roleId == 2 || roleId == 3) {
				delete(req, resp, currentUser);
			}
		} else if (url.contains("edit")) {
			if (roleId == 2 || roleId == 3) {
				edit(req, resp);
				req.getRequestDispatcher("/views/category/edit.jsp").forward(req, resp);
				return;
			}
		}

		// Load list category
		loadCategoryByRole(req, resp, currentUser);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(Constant.SESSION_ACCOUNT);

		if (currentUser == null) {
			response.sendRedirect("login");
			return;
		}

		int roleId = currentUser.getRole().getRoleId();

		if (url.contains("create") && (roleId == 2 || roleId == 3)) {
			insert(request, response, currentUser);
		} else if (url.contains("update") && (roleId == 2 || roleId == 3)) {
			update(request, response, currentUser);
		} else if (url.contains("delete") && (roleId == 2 || roleId == 3)) {
			delete(request, response, currentUser);
		}

		loadCategoryByRole(request, response, currentUser);
	}

	// ========== Load category theo role ==========
	private void loadCategoryByRole(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		try {
			List<Category> list;
			int roleId = currentUser.getRole().getRoleId();

			if (roleId == 1) { // User xem toàn bộ
				list = categoryService.findAll();
				request.setAttribute("contentPage", "/views/user/home_user.jsp");
			} else if (roleId == 2) { // Manager xem của chính mình
				list = categoryService.findByUserId(currentUser.getUserId());
				request.setAttribute("contentPage", "/views/manager/home_manager.jsp");
			} else { // Admin
				list = categoryService.findAll();
				request.setAttribute("contentPage", "/views/admin/home_admin.jsp");
			}

			request.setAttribute("categories", list);
			request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}
	}

	// ========== INSERT ==========
	protected void insert(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			Category category = new Category();
			BeanUtils.populate(category, request.getParameterMap());

			category.setCategoryId(0);
			category.setUser(currentUser); // Gán người tạo

			String fileName = UploadUtils.processUpload("images", request, Constant.DIR + "\\category",
					category.getCategoryCode() + "_" + System.currentTimeMillis());
			if (!fileName.isEmpty()) {
				category.setImages(fileName);
			}

			categoryService.insert(category);
			request.setAttribute("message", "Đã thêm thành công");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	// ========== EDIT ==========
	protected void edit(HttpServletRequest request, HttpServletResponse response) {
		try {
			String categoryId = request.getParameter("categoryId");
			Category category = categoryService.findById(Integer.parseInt(categoryId));
			request.setAttribute("category", category);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	// ========== DELETE ==========
	protected void delete(HttpServletRequest request, HttpServletResponse response, User currentUser) {
		try {
			String categoryId = request.getParameter("categoryId");
			Category category = categoryService.findById(Integer.parseInt(categoryId));

			if (category == null)
				return;

			// Manager chỉ được xóa của mình
			if (currentUser.getRole().getRoleId() == 2 && category.getUser().getUserId() != currentUser.getUserId()) {
				request.setAttribute("error", "Bạn không có quyền xóa category này!");
				return;
			}

			if (category.getImages() != null) {
				File oldFile = new File(Constant.DIR + "\\category\\" + category.getImages());
				if (oldFile.exists())
					oldFile.delete();
			}

			categoryService.delete(category.getCategoryId());
			request.setAttribute("message", "Đã xóa thành công");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	// ========== UPDATE ==========
	protected void update(HttpServletRequest request, HttpServletResponse response, User currentUser) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			int id = Integer.parseInt(request.getParameter("categoryId"));
			Category oldcate = categoryService.findById(id);

			if (oldcate == null) {
				request.setAttribute("error", "Không tìm thấy Category cần cập nhật");
				return;
			}

			// Manager chỉ được sửa category của mình
			if (currentUser.getRole().getRoleId() == 2 && oldcate.getUser().getUserId() != currentUser.getUserId()) {
				request.setAttribute("error", "Bạn không có quyền sửa category này!");
				return;
			}

			oldcate.setCategoryCode(request.getParameter("categoryCode"));
			oldcate.setCategoryName(request.getParameter("categoryName"));
			oldcate.setStatus("true".equals(request.getParameter("status")));

			String fileName = UploadUtils.processUpload("images", request, Constant.DIR + "\\category",
					oldcate.getCategoryCode() + "_" + System.currentTimeMillis());
			if (!fileName.isEmpty()) {
				if (oldcate.getImages() != null) {
					File oldFile = new File(Constant.DIR + "\\category\\" + oldcate.getImages());
					if (oldFile.exists())
						oldFile.delete();
				}
				oldcate.setImages(fileName);
			}

			categoryService.update(oldcate);
			request.setAttribute("category", oldcate);
			request.setAttribute("message", "Cập nhật thành công");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
}
