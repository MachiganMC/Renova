package be.machigan.renova.service;

import be.machigan.renova.dto.request.AuthenticationRequest;
import be.machigan.renova.dto.request.UpdateAccountRequest;
import be.machigan.renova.entity.User;
import be.machigan.renova.exception.NameAlreadyInUseException;
import be.machigan.renova.exception.OperationNotPermittedException;
import be.machigan.renova.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Value("${application.default-user.name}")
    private String defaultUserName;
    @Value("${application.default-user.password}")
    private String defaultUserPassword;

    public User findById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public boolean checkPassword(User user, String plainPassword) {
        return this.passwordEncoder.matches(plainPassword, user.getPassword());
    }

    public void createNewUser(AuthenticationRequest request) {
        if (this.userRepository.existsByUsername(request.getUsername()))
            throw new NameAlreadyInUseException("The username " + request.getUsername() + " is already taken");
        this.userRepository.save(User
                .builder()
                .username(request.getUsername())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .build()
        );
    }

    public void createDefaultUserIfNoUser() {
        if (this.userRepository.existsOne()) return;
        this.userRepository.save(User
                .builder()
                .username(this.defaultUserName)
                .password(this.passwordEncoder.encode(this.defaultUserPassword))
                .canBeDeleted(false)
                .build()
        );
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User getCurrentUser() {
        return this.userRepository
                .getByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void disable(Long userId) {
        User user = this.findById(userId);
        if (!user.canBeDeleted())
            throw new OperationNotPermittedException("You cannot disable this account");
        this.userRepository.delete(this.findById(userId));
    }

    public void update(UpdateAccountRequest request) {
        User user = this.getCurrentUser();
        if (!user.getUsername().equals(request.getUsername())) {
            if (this.userRepository.existsByUsername(request.getUsername()))
                throw new NameAlreadyInUseException("This username is already used by another user.");
            user.setUsername(request.getUsername());
        }
        if (!request.getPassword().isEmpty())
            user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        this.userRepository.save(user);
    }
}
