package LTW3.Controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import LTW3.Entity.Role;
import LTW3.Entity.User;
import LTW3.Service.RoleService;
import LTW3.Service.UserService;
import LTW3.Service.Impl.RoleServiceImpl;
import LTW3.Service.Impl.UserServiceImpl;

@WebServlet(urlPatterns = { 
    "/admin-userrole", 
    "/admin-userrole/create", 
    "/admin-userrole/update",
    "/admin-userrole/edit", 
    "/admin-userrole/delete", 
    "/admin-userrole/reset" 
})
public class UserRoleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserService userService = new UserServiceImpl();
    RoleService roleService = new RoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getRequestURL().toString();

        if (url.contains("create")) {
            req.getRequestDispatcher("/views/admin/userrole/add.jsp").forward(req, resp);
            return;
        } else if (url.contains("edit")) {
            edit(req, resp);
            req.getRequestDispatcher("/views/admin/userrole/edit.jsp").forward(req, resp);
            return;
        } else if (url.contains("delete")) {
            delete(req, resp);
        } else if (url.contains("reset")) {
            req.setAttribute("selectedUser", new User());
        }

        findAll(req, resp);
        req.getRequestDispatcher("/views/admin/userrole/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getRequestURL().toString();

        if (url.contains("create")) {
            assignRole(req, resp);
        } else if (url.contains("update")) {
            updateRole(req, resp);
        } else if (url.contains("delete")) {
            delete(req, resp);
        } else if (url.contains("reset")) {
            req.setAttribute("selectedUser", new User());
        }

        findAll(req, resp);
        req.getRequestDispatcher("/views/admin/userrole/list.jsp").forward(req, resp);
    }

    // ========== FIND ALL ==========
    private void findAll(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<User> users = userService.findAll();
            List<Role> roles = roleService.findAll();
            req.setAttribute("users", users);
            req.setAttribute("roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }

    // ========== EDIT ==========
    private void edit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            User user = userService.findById(userId);
            req.setAttribute("selectedUser", user);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }

    // ========== CREATE (Assign Role) ==========
    private void assignRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            int roleId = Integer.parseInt(req.getParameter("roleId"));

            userService.assignRole(userId, roleId);
            req.setAttribute("message", "Gán quyền thành công");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }

    // ========== UPDATE ==========
    private void updateRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            int roleId = Integer.parseInt(req.getParameter("roleId"));

            userService.assignRole(userId, roleId);
            req.setAttribute("message", "Cập nhật quyền thành công");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }

    // ========== DELETE ==========
    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            userService.removeRole(userId);
            req.setAttribute("message", "Xóa quyền thành công");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error: " + e.getMessage());
        }
    }
}
