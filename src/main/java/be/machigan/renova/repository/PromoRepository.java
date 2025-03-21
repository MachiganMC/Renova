package be.machigan.renova.repository;

import be.machigan.renova.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromoRepository extends JpaRepository<Promo, Integer> {
    @Query(value = """
        SELECT * FROM promo WHERE end_date IS NULL OR end_date > now()
    """, nativeQuery = true)
    List<Promo> findNotEndedPromo();
}
