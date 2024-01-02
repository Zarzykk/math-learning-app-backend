package demo.mathapp.repository;

import demo.mathapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true,
            value = "select * from users where user_type = ?1")
    List<User> findUsersByType(String userType);

    Optional<User> findUserByEmail(String email);
}
