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
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 25)
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(Constant.SESSION_ACCOUNT);

		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + Constant.Path.LOGIN);
			return;
		}

		if (url.contains("create")) {
			req.getRequestDispatcher(Constant.Path.CATEGORY_ADD).forward(req, resp);
			return;
		} else if (url.contains("delete")) {
			delete(req, currentUser);
		} else if (url.contains("edit")) {
			edit(req);
			req.getRequestDispatcher(Constant.Path.CATEGORY_EDIT).forward(req, resp);
			return;
		}

		loadCategoryByRole(req, resp, currentUser);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(Constant.SESSION_ACCOUNT);

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + Constant.Path.LOGIN);
			return;
		}

		if (url.contains("create")) {
			insert(request, currentUser);
		} else if (url.contains("update")) {
			update(request, currentUser);
		} else if (url.contains("delete")) {
			delete(request, currentUser);
		}

		loadCategoryByRole(request, response, currentUser);
	}

	private void loadCategoryByRole(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		try {
			List<Category> list;
			int roleId = currentUser.getRole().getRoleId();

			if (roleId == 1) { // User
				list = categoryService.findAll();
				request.setAttribute("contentPage", Constant.Path.USER_HOME);
			} else if (roleId == 2) { // Manager
				list = categoryService.findByUserId(currentUser.getUserId());
				request.setAttribute("contentPage", Constant.Path.MANAGER_HOME);
			} else { // Admin
				list = categoryService.findAll();
				request.setAttribute("contentPage", Constant.Path.ADMIN_HOME);
			}

			request.setAttribute("categories", list);
			request.getRequestDispatcher(Constant.Path.DASHBOARD).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			request.getRequestDispatcher(Constant.Path.ERROR).forward(request, response);
		}
	}

	private void insert(HttpServletRequest request, User currentUser) {
		try {
			Category category = new Category();
			BeanUtils.populate(category, request.getParameterMap());
			category.setCategoryId(0);
			category.setUser(currentUser);

			String fileName = UploadUtils.processUpload("images", request, Constant.DIR + "\\category",
					category.getCategoryCode() + "_" + System.currentTimeMillis());
			if (!fileName.isEmpty())
				category.setImages(fileName);

			categoryService.insert(category);
			request.setAttribute("message", "Đã thêm thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

	private void edit(HttpServletRequest request) {
		try {
			String categoryId = request.getParameter("categoryId");
			Category category = categoryService.findById(Integer.parseInt(categoryId));
			request.setAttribute("category", category);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

	private void delete(HttpServletRequest request, User currentUser) {
		try {
			String categoryId = request.getParameter("categoryId");
			Category category = categoryService.findById(Integer.parseInt(categoryId));
			if (category == null)
				return;

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
			request.setAttribute("error", e.getMessage());
		}
	}

	private void update(HttpServletRequest request, User currentUser) {
		try {
			int id = Integer.parseInt(request.getParameter("categoryId"));
			Category oldcate = categoryService.findById(id);
			if (oldcate == null)
				return;

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
			request.setAttribute("error", e.getMessage());
		}
	}
}
