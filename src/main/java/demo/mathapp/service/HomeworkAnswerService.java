package demo.mathapp.service;

import demo.mathapp.model.HomeworkAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeworkAnswerService {

    HomeworkAnswer createAnswer(HomeworkAnswer answer);

    void deleteAnswer(Long id);

    HomeworkAnswer updateAnswer(Long id,HomeworkAnswer answer);

}
