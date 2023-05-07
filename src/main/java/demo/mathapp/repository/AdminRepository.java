package demo.mathapp.repository;

import demo.mathapp.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends UserRepository {
    @Query("SELECT a from Admin a")
    List<Admin> findAllAdmins();
    Optional<Admin> findAdminById(Long id);
}
