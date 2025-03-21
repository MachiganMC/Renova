package be.machigan.renova.dto.template.service;

import be.machigan.renova.dto.template.Reason;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    private String id;
    private String name;
    private String icon;
    private String color;
    private List<ServiceTask> tasks;

    public static final List<Services> ALL;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ALL = objectMapper.readValue(new ClassPathResource("./data/services.json", Services.class.getClassLoader()).getInputStream(), objectMapper.getTypeFactory().constructCollectionType(List.class, Services.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getHtmlId() {
        return "services-" + this.id;
    }

    public String getHtmlSelector() {
        return "#" + this.getHtmlId();
    }
}
