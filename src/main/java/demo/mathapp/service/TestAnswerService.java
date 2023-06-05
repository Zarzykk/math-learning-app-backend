package demo.mathapp.service;


import demo.mathapp.model.TestAnswer;
import org.springframework.stereotype.Service;

@Service
public interface TestAnswerService {

    TestAnswer createAnswer(TestAnswer answer);

    void deleteAnswer(Long id);

    TestAnswer updateAnswer(Long id,TestAnswer answer);
}
