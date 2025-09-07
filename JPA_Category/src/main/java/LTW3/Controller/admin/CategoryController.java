package LTW3.Controller.admin;

import java.io.File;
import java.io.IOException;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import LTW3.Entity.Category;
import LTW3.Service.CategoryService;
import LTW3.Service.Impl.CategoryServiceImpl;
import LTW3.Util.Constant;
import LTW3.Util.UploadUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin-category", "/admin-category/create", "/admin-category/update",
		"/admin-category/edit", "/admin-category/delete", "/admin-category/reset" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		Category category = null;
		if (url.contains("create")) {
			req.getRequestDispatcher("/views/admin/cate/add.jsp").forward(req, resp);

		} else if (url.contains("delete")) {
			delete(req, resp);
			category = new Category();
			req.setAttribute("category", category);
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			category = new Category();
			req.setAttribute("category", category);
		}
		findAll(req, resp);
		req.setAttribute("tag", "cate");
		req.getRequestDispatcher("/views/admin/cate/list.jsp").forward(req, resp);

	}

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();

		if (url.contains("create")) {
			insert(request, response);

		} else if (url.contains("update")) {
			update(request, response);

		} else if (url.contains("delete")) {
			delete(request, response);

		} else if (url.contains("reset")) {
			request.setAttribute("category", new Category());
		}

		findAll(request, response);

		request.getRequestDispatcher("/views/admin/cate/list.jsp").forward(request, response);
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			Category category = new Category();

			BeanUtils.populate(category, request.getParameterMap());

			String fileName = category.getCategoryCode() + System.currentTimeMillis();
			category.setImages(UploadUtils.processUpload("images", request, Constant.DIR + "\\category",fileName));

			// category.setStatus(true);
			categoryService.insert(category);
			request.setAttribute("message", "Đã thêm thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Category> list = categoryService.findAll();
			request.setAttribute("categorys", list);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("categoryId");

			Category category = categoryService.findById(Integer.parseInt(categoryId));

			request.setAttribute("category", category);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("categoryId");

			categoryService.delete(Integer.parseInt(categoryId));

			request.setAttribute("message", "Đã xóa thành công");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			Category category = new Category();
			BeanUtils.populate(category, request.getParameterMap());

			Category oldcate = categoryService.findById(category.getCategoryId());

			if (request.getPart("images").getSize() == 0) {
				category.setImages(oldcate.getImages());
			} else {
				if (oldcate.getImages() != null) {
					String fileName = oldcate.getImages();
					File file = new File(Constant.DIR + "\\category\\" + fileName);
					if (file.delete()) {
						System.out.println("Đã xóa thành công ảnh cũ");
					} else {
						System.out.println("Không thể xóa ảnh cũ: " + Constant.DIR + "\\category\\" + fileName);
					}
				}

				String fileName = category.getCategoryCode() + System.currentTimeMillis();
				category.setImages(UploadUtils.processUpload("images", request, Constant.DIR + "\\category",fileName));
			}

			categoryService.update(category);

			request.setAttribute("category", category);
			request.setAttribute("message", "Cập nhật thành công");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

}
