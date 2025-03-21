package be.machigan.renova.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PromoRequest {
    @NotEmpty(message = "The name cannot be empty")
    private String name;
    private String description;
    private LocalDate endDate;
}
