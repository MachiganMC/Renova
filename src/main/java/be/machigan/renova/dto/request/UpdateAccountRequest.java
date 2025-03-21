package be.machigan.renova.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.nonempty.qual.NonEmpty;

@AllArgsConstructor
@Getter
public class UpdateAccountRequest {
    @NonEmpty()
    private final String username;
    private String password;
}
