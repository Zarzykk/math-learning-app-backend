package demo.mathapp.repository;

import demo.mathapp.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends WorkRepository{
}
