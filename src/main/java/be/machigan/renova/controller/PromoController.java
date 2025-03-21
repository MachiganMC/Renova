package be.machigan.renova.controller;

import be.machigan.renova.dto.request.PromoRequest;
import be.machigan.renova.service.PromoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/promos")
@RequiredArgsConstructor
public class PromoController {
    private final PromoService promoService;

    @PostMapping
    public void add(@Valid PromoRequest promoRequest) {
        this.promoService.addPromo(promoRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.promoService.deletePromo(id);
    }
}
