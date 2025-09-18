package ltw.vn.Controller;


import jakarta.servlet.http.HttpSession;
import ltw.vn.Entity.Users;
import ltw.vn.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    // hiển thị form profile
    @GetMapping
    public String profile(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username"); // lấy từ session khi login
        Users user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile"; // JSP: /WEB-INF/views/profile.jsp
    }

    // xử lý update
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") Users formUser,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session,
                                Model model) throws IOException {

        String username = (String) session.getAttribute("username");
        Users user = userService.findByUsername(username);

        // cập nhật các field
        user.setFullName(formUser.getFullName());
        user.setPhone(formUser.getPhone());

        // xử lý upload ảnh
        if (!file.isEmpty()) {
            String uploadDir = "src/main/webapp/uploads/"; // hoặc /resources/static/uploads/
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));

            user.setImages(fileName);
        }

        userService.updateProfile(user);
        model.addAttribute("user", user);
        model.addAttribute("success", "Cập nhật thành công!");

        return "profile";
    }
}