package be.machigan.renova.dto.template;

import be.machigan.renova.dto.template.service.Services;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reason {
    private String name;
    private String description;
    public static final List<Services> ALL;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ALL = objectMapper.readValue(new ClassPathResource("./data/reasons.json", Reason.class.getClassLoader()).getInputStream(), objectMapper.getTypeFactory().constructCollectionType(List.class, Reason.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
