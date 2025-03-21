package be.machigan.renova.dto.template;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class Contact {
    @Value("${application.contact.phone}")
    private String phoneNumber;

    @Value("${application.contact.location}")
    private String location;

    @Value("${application.contact.tva}")
    private String tva;

    @Value("${application.contact.instagram}")
    private String instagram;

    @Value("${application.message.quote}")
    private String quoteMessage;
}
