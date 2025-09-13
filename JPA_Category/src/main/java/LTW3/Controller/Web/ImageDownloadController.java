package LTW3.Controller.Web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import LTW3.Util.Constant;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/images/*")
public class ImageDownloadController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2036735801069285412L;
	private static final String UPLOAD_DIR = Constant.DIR + "\\category";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = request.getPathInfo().substring(1); // lấy tên file
		File file = new File(UPLOAD_DIR, filename);

		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		response.setContentType(getServletContext().getMimeType(file.getName()));
		response.setContentLengthLong(file.length());

		try (FileInputStream in = new FileInputStream(file); ServletOutputStream out = response.getOutputStream()) {
			byte[] buffer = new byte[8192];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		}
	}
}
