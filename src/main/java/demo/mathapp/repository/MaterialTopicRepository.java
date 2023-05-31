package demo.mathapp.repository;

import demo.mathapp.model.MaterialTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialTopicRepository extends JpaRepository<MaterialTopic,Long> {

    Optional<MaterialTopic> findMaterialTopicByName(String topicName);
}
