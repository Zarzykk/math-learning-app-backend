package demo.mathapp.integration;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.model.HomeworkResult;
import demo.mathapp.model.Student;
import demo.mathapp.model.WorkAnswer;
import demo.mathapp.repository.HomeworkAnswerRepository;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.service.impl.HomeworkResultService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class HomeworkResultControllerIntegrationTest {

    private HomeworkResultService systemUnderTest;

    @Autowired
    private HomeworkResultRepository homeworkResultRepository;

    @Autowired
    private HomeworkAnswerRepository homeworkAnswerRepository;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new HomeworkResultService(homeworkResultRepository);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        HomeworkResult homeworkResult = systemUnderTest.createResult(getHomeworkResult());

        HomeworkResult testResult = systemUnderTest.findResultById(homeworkResult.getId());

        Assertions.assertEquals(homeworkResult, testResult);
    }

    @Test
    void shouldDeleteSuccessfully() {
        HomeworkResult homeworkResult = systemUnderTest.createResult(getHomeworkResult());

        systemUnderTest.deleteResult(homeworkResult.getId());
        List<WorkAnswer> allAnswers = homeworkAnswerRepository.findAll();
        Assertions.assertThrows(ResourceNotFoundException.class, () -> systemUnderTest.findResultById(homeworkResult.getId()));
        Assertions.assertEquals(0, allAnswers.size());
    }

    @Test
    void shouldUpdateHomeworkResult() {
        HomeworkResult homeworkResult = systemUnderTest.createResult(getHomeworkResult());

        Assertions.assertNull(homeworkResult.getStudent());
        homeworkResult.setStudent(getStudent("Sebastian", "Zarzycki", "szarzycki@gmail.com"));

        systemUnderTest.updateResult(homeworkResult.getId(), homeworkResult);
        HomeworkResult updatedResult = systemUnderTest.findResultById(homeworkResult.getId());

        homeworkResult.setId(updatedResult.getId());
        Assertions.assertEquals(homeworkResult, updatedResult);
    }

    private Student getStudent(String firstName, String lastName, String email) {
        Student student = new Student();
        student.setPassword("1234");
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        return student;
    }

    public HomeworkResult getHomeworkResult() {
        HomeworkResult result = new HomeworkResult();
        result.setHomeworkAnswers(getAnswers());
        result.setPoints(9);
        result.setPassed(true);
        result.setWorkTimeResult(30);
        return result;
    }

    private List<HomeworkAnswer> getAnswers() {
        List<HomeworkAnswer> answers = new ArrayList<>();
        for (int i=0; i<3; i++) {
            HomeworkAnswer answer = getAnswer(i);
            answers.add(answer);
        }
        return answers;
    }

    private HomeworkAnswer getAnswer(int points) {
        HomeworkAnswer answer = new HomeworkAnswer();
        answer.setAnswer("Lorem ipsum dolor sit amet");
        answer.setPoints(points);
        answer.setResult(null);
        return answer;
    }

}
