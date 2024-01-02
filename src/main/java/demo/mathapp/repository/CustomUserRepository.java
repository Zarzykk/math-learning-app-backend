package demo.mathapp.repository;

import demo.mathapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends UserRepository, JpaRepository<User, Long> {
}
