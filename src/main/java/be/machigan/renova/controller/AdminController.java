package be.machigan.renova.controller;

import be.machigan.renova.dto.template.AdminLink;
import be.machigan.renova.entity.User;
import be.machigan.renova.service.PictureService;
import be.machigan.renova.service.PromoService;
import be.machigan.renova.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final PictureService pictureService;
    private final PromoService promoService;
    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxFileSize;

    @ModelAttribute(name = "links")
    public List<AdminLink> links() {
        return AdminLink.LINKS;
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = this.userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", this.userService.getCurrentUser());
        return "admin/users";
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("currentUser", this.userService.getCurrentUser());
        return "admin/account";
    }

    @GetMapping("/pictures")
    public String pictures(Model model) {
        this.pictureService.addPicturesToModel(model);
        model.addAttribute("maxFileSize", this.maxFileSize);
        return "admin/pictures";
    }

    @GetMapping("/promos")
    public String promos(Model model) {
        model.addAttribute("promos", this.promoService.findAll());
        return "admin/promos";
    }
}
