package be.machigan.renova.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    private String description;

    private LocalDate endDate;

    @Override
    public String toString() {
        return this.name + (this.endDate != null && this.endDate.isBefore(LocalDate.now()) ? " [DÉPASSÉ]" : "");
    }

    public Long toMillisecond() {
        return this.endDate != null ?
                this.endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() :
                null
                ;
    }
}
