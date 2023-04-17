package demo.mathapp.repository;

import demo.mathapp.model.MaterialTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialTopicRepository extends JpaRepository<MaterialTopic,Long> {
}
