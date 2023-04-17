package demo.mathapp.service;

import demo.mathapp.DTO.Topic.CreateTopic;
import demo.mathapp.model.Material;
import demo.mathapp.model.MaterialTopic;
import demo.mathapp.repository.MaterialTopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialTopicService {
    private final MaterialTopicRepository topicRepository;
    private final ModelMapper modelMapper;

    public MaterialTopic createTopic(CreateTopic topic) {
        return topicRepository.save(modelMapper.map(topic, MaterialTopic.class));
    }

    public void setMaterialTopics(List<MaterialTopic> materialTopics, long id) {
        Material material = new Material();
        material.setId(id);
        for (MaterialTopic topic : materialTopics) {
            topic.setMaterial(material);
        }
        topicRepository.saveAll(materialTopics);
    }


}
