package demo.mathapp.repository;

import demo.mathapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {


}
