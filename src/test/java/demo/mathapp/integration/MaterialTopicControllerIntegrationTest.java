package demo.mathapp.integration;

import demo.mathapp.SchoolType;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Material;
import demo.mathapp.model.MaterialTopic;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Task;
import demo.mathapp.repository.MaterialTopicRepository;
import demo.mathapp.repository.TaskRepository;
import demo.mathapp.service.impl.MaterialTopicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class MaterialTopicControllerIntegrationTest {

    private MaterialTopicService systemUnderTest;
    @Autowired
    private MaterialTopicRepository materialTopicRepository;
    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new MaterialTopicService(materialTopicRepository);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        //given
        MaterialTopic materialTopic = getMaterialTopic(Collections.emptyList());

        materialTopic = systemUnderTest.createTopic(materialTopic);
        MaterialTopic result = systemUnderTest.findTopicById(materialTopic.getId());

        materialTopic.setId(result.getId());
        Assertions.assertEquals(materialTopic, result);
    }

    @Test
    void shouldUpdateMaterialTopic() {
        MaterialTopic materialTopic = getMaterialTopic(Collections.emptyList());

        materialTopic = systemUnderTest.createTopic(materialTopic);
        Assertions.assertNotEquals("Odejmowanie", materialTopic.getName());
        materialTopic.setName("Odejmowanie");

        systemUnderTest.updateTopic(materialTopic.getId(), materialTopic);
        MaterialTopic result = systemUnderTest.findTopicById(materialTopic.getId());

        Assertions.assertEquals(materialTopic, result);
    }

    @Test
    void shouldDeleteMaterialTopic() {
        MaterialTopic materialTopic = systemUnderTest.createTopic(getMaterialTopic(getTasks()));

        List<Task> tasks = materialTopic.getTasks();
        systemUnderTest.deleteTopic(materialTopic.getId());

        List<Task> allTasks = taskRepository.findAll();
        Assertions.assertEquals(tasks.size(), allTasks.size());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> systemUnderTest.findTopicById(materialTopic.getId()));
    }

    private List<Task> getTasks() {
        Task task = new Task();
        task.setPoints(10);
        task.setText("Lorem ipsum dolor sit amet");
        return Collections.singletonList(task);
    }

    private MaterialTopic getMaterialTopic(List<Task> tasks) {
        MaterialTopic materialTopic = MaterialTopic.builder()
                .material(getMaterial())
                .name("Dodawanie")
                .tasks(tasks)
                .build();
        return materialTopic;
    }

    private Material getMaterial(int classYear, SchoolType schoolType, List<SchoolClass> schoolClasses) {
        return Material.builder()
                .classYear(classYear)
                .schoolType(schoolType)
                .classList(schoolClasses)
                .build();
    }
    private Material getMaterial() {
        return getMaterial(1, SchoolType.PRIMARY, Collections.emptyList());
    }
}
