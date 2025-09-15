package LTW3.Util;

public class Constant {
	public static final String SESSION_USERNAME = "username";
	public static final String SESSION_ACCOUNT = "account";
	public static final String COOKIE_REMEMBER = "COOKIE_REMEMBER";
	public static final String UPLOAD_DIRECTORY = "E:\\upload";
	public static final String DEFAULT_FILENAME = "default.file";
	public static final String DIR = "E:\\Upload";

	public static class Path {
		// ==== AUTH ====
		public static final String LOGIN = "/views/login.jsp";
		public static final String REGISTER = "/views/register.jsp";
		public static final String DASHBOARD = "/views/dashboard.jsp";
		public static final String WAITING = "/views/waiting.jsp";
		public static final String ERROR = "/views/error.jsp";
		public static final String FORGOTPASSWORD = "/views/forgotpassword.jsp";
		public static final String VERIFYCODE = "/views/verifycode.jsp";

		// ==== ADMIN ====
		public static final String ADMIN_HOME = "/views/admin/home_admin.jsp";

		// ==== CATEGORY CRUD ====
		public static final String CATEGORY_LIST = "/views/admin/category/list.jsp";
		public static final String CATEGORY_ADD = "/views/admin/category/add.jsp";
		public static final String CATEGORY_EDIT = "/views/admin/category/edit.jsp";

		// ==== USER ROLE CRUD ====
		public static final String USERROLE_LIST = "/views/admin/userrole/list.jsp";
		public static final String USERROLE_ADD = "/views/admin/userrole/add.jsp";
		public static final String USERROLE_EDIT = "/views/admin/userrole/edit.jsp";

		// ==== MANAGER ====
		public static final String MANAGER_HOME = "/views/admin/manager/home_manager.jsp";

		// ==== USER ====
		public static final String USER_HOME = "/views/admin/user/home_user.jsp";
		public static final String USER_PROFILE = "/views/admin/user/profile.jsp";
	}
}
