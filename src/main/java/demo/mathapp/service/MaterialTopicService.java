package demo.mathapp.service;

import demo.mathapp.model.MaterialTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialTopicService {

    MaterialTopic createTopic(MaterialTopic topic);
    void deleteTopic(Long id);
    MaterialTopic updateTopic(Long id, MaterialTopic topic);
    MaterialTopic findTopicByName(String topicName);

}
