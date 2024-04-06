package demo.mathapp.integration;


import demo.mathapp.MathAppApplication;
import demo.mathapp.model.Homework;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Task;
import demo.mathapp.repository.HomeworkRepository;
import demo.mathapp.service.impl.HomeworkService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {MathAppApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class HomeworkControllerIntegrationTest {

    private HomeworkService systemUnderTest;
    @Autowired
    private HomeworkRepository homeworkRepository;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new HomeworkService(homeworkRepository);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        Homework homework = systemUnderTest.createHomework(getHomework());

        Homework result = systemUnderTest.findHomeworkById(homework.getId());

        Assertions.assertEquals(homework, result);
    }

    @Test
    void shouldUpdateHomework() {
        Homework homework = systemUnderTest.createHomework(getHomework(getTasks(), getSchoolClass()));

        Assertions.assertNotEquals(homework.getMaxPoints(),15);

        List<Task> tasks = homework.getTasks();
        tasks.add(getTask(5, "Drugie zadnie"));
        homework.setTasks(tasks);
        systemUnderTest.updateHomework(homework.getId(), homework);

        Assertions.assertEquals(2, homework.getTasks().size());
        Assertions.assertEquals(15, homework.getMaxPoints());
    }

    private List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(getTask(10, "Lorem ipsum dolor sit amet"));
        return tasks;
    }

    private Task getTask(int points, String text) {
        return Task.builder().points(points).text(text).build();
    }

    private Homework getHomework() {
        return getHomework(Collections.emptyList(), getSchoolClass());
    }

    private SchoolClass getSchoolClass() {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassYear(2);
        schoolClass.setClassIndex("a");
        return schoolClass;
    }

    private Homework getHomework(List<Task> tasks, SchoolClass schoolClass) {
        Homework homework = new Homework();
        homework.setTasks(tasks);
        homework.setActivationTime(LocalDateTime.now());
        homework.setDeactivationTime(LocalDateTime.now().plusHours(1L));
        homework.setSchoolClass(schoolClass);
        return homework;
    }
}
