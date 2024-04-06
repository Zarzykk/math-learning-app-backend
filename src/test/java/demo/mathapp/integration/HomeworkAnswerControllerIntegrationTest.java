package demo.mathapp.integration;

import demo.mathapp.MathAppApplication;
import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.repository.HomeworkAnswerRepository;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.service.impl.HomeworkAnswerService;
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
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {MathAppApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class HomeworkAnswerControllerIntegrationTest {

    private HomeworkAnswerService systemUnderTest;
    @Autowired
    private HomeworkAnswerRepository homeworkAnswerRepository;
    @Autowired
    private HomeworkResultRepository homeworkResultRepository;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new HomeworkAnswerService(homeworkAnswerRepository, homeworkResultRepository);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        HomeworkAnswer answer = systemUnderTest.createAnswer(getAnswer());

        HomeworkAnswer result = systemUnderTest.findAnswerById(answer.getId());

        Assertions.assertEquals(answer, result);
    }

    @Test
    void shouldDeleteSuccessfully() {
        HomeworkAnswer answer = systemUnderTest.createAnswer(getAnswer());

        systemUnderTest.deleteAnswer(answer.getId());

        Assertions.assertThrows(ResponseStatusException.class, () -> systemUnderTest.findAnswerById(answer.getId()));
    }



    private HomeworkAnswer getAnswer() {
        HomeworkAnswer answer = new HomeworkAnswer();
        answer.setAnswer("Lorem ipsum dolor sit amet");
        answer.setPoints(3);
        answer.setResult(null);
        return answer;
    }
}
