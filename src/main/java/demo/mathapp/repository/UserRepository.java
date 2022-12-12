package demo.mathapp.repository;

import demo.mathapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(String role);
    Optional<User> findByEmail(String email);

}
