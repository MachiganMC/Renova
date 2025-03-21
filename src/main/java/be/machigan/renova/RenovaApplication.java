package be.machigan.renova;

import be.machigan.renova.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RenovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenovaApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserService userService) {
        return args -> userService.createDefaultUserIfNoUser();
    }
}
