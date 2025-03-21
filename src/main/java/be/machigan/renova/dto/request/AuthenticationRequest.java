package be.machigan.renova.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.nonempty.qual.NonEmpty;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    @NonEmpty
    private String username;
    @NonEmpty
    private String password;
}
