package be.machigan.renova.controller;

import be.machigan.renova.dto.request.AuthenticationRequest;
import be.machigan.renova.exception.OperationNotPermittedException;
import be.machigan.renova.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody @Valid AuthenticationRequest request) {
        this.userService.createNewUser(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void disableUser(@PathVariable Long id) {
        if (this.userService.getCurrentUser().getId().equals(id))
            throw new OperationNotPermittedException("You cannot disable your account");
        this.userService.disable(id);
    }
}
