package be.machigan.renova.controller;

import be.machigan.renova.dto.template.Banner;
import be.machigan.renova.dto.template.Contact;
import be.machigan.renova.dto.template.Reason;
import be.machigan.renova.dto.template.service.Services;
import be.machigan.renova.service.PictureService;
import be.machigan.renova.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PictureService pictureService;
    private final Contact contact;
    private final PromoService promoService;
    private final Banner banner;

    @GetMapping
    public String home(Model model) {
        this.pictureService.addPicturesToModel(model);
        model.addAttribute("services", Services.ALL);
        model.addAttribute("reasons", Reason.ALL);
        model.addAttribute("banner", this.banner);
        model.addAttribute("contact", this.contact);
        model.addAttribute("promos", this.promoService.findCurrentPromos());
        model.addAttribute("isAdmin", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        return "home/home";
    }
}
