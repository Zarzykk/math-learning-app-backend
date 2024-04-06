package demo.mathapp.controller;

import demo.mathapp.model.MaterialTopic;

import demo.mathapp.service.impl.MaterialTopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/topics")
public class MaterialTopicController {
    private final MaterialTopicService topicService;

    @PostMapping("/create")
    public ResponseEntity<MaterialTopic> createTopic(@RequestBody MaterialTopic materialTopic){
        return ResponseEntity.ok(topicService.createTopic(materialTopic));
    }

    @GetMapping("/get/{topicName}")
    public ResponseEntity<MaterialTopic> findTopicByName(@PathVariable String topicName){
        return ResponseEntity.ok(topicService.findTopicByName(topicName));
    }

    @PutMapping("/update/{topicId}")
    public ResponseEntity<MaterialTopic> updateTopic(@PathVariable Long topicId,
                                                     @RequestBody MaterialTopic materialTopic){
        return ResponseEntity.ok(topicService.updateTopic(topicId,materialTopic));
    }

    @DeleteMapping("/delete/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long topicId){
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }
}
