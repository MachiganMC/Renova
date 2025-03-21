package be.machigan.renova.controller;

import be.machigan.renova.dto.request.UpdateAccountRequest;
import be.machigan.renova.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void changeUsername(@RequestBody @Valid UpdateAccountRequest request) {
        this.userService.update(request);
    }
}
