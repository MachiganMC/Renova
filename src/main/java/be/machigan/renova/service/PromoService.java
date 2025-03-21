package be.machigan.renova.service;

import be.machigan.renova.dto.request.PromoRequest;
import be.machigan.renova.entity.Promo;
import be.machigan.renova.repository.PromoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromoService {
    private final PromoRepository promoRepository;

    public List<Promo> findAll() {
        return this.promoRepository.findAll();
    }

    public Promo findById(Integer id) {
        return this.promoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The promos with id " + id + " hasn't been found"));
    }

    public void addPromo(PromoRequest promoRequest) {
        this.promoRepository.save(Promo
                .builder()
                .name(promoRequest.getName())
                .description(promoRequest.getDescription().isEmpty() ? null : promoRequest.getDescription())
                .endDate(promoRequest.getEndDate())
                .build()
        );
    }

    public void deletePromo(Integer id) {
        this.promoRepository.delete(this.findById(id));
    }

    public List<Promo> findCurrentPromos() {
        return this.promoRepository.findNotEndedPromo();
    }
}
