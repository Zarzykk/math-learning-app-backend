package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.MaterialTopic;
import demo.mathapp.repository.MaterialTopicRepository;
import demo.mathapp.service.MaterialTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialTopicServiceImpl implements MaterialTopicService {
    private final MaterialTopicRepository topicRepository;

    @Override
    public MaterialTopic createTopic(MaterialTopic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public MaterialTopic updateTopic(Long id, MaterialTopic topic) {
        MaterialTopic oldMaterial = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
        oldMaterial.setName(topic.getName());
        oldMaterial.setTasks(topic.getTasks());
        return topicRepository.save(oldMaterial);
    }

    @Override
    public MaterialTopic findTopicByName(String topicName) {
        return topicRepository.findMaterialTopicByName(topicName)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }
}
