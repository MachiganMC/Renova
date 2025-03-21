package be.machigan.renova.repository;

import be.machigan.renova.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = """
        SELECT (COUNT(u) > 0) FROM User u
    """)
    boolean existsOne();
}
