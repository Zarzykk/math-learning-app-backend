package demo.mathapp.service.impl;

import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.repository.HomeworkAnswerRepository;
import demo.mathapp.service.HomeworkAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkAnswerServiceImpl implements HomeworkAnswerService {
    private final HomeworkAnswerRepository answerRepository;

    @Override
    public HomeworkAnswer createAnswer(HomeworkAnswer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {

    }

    @Override
    public HomeworkAnswer updateAnswer(Long id, HomeworkAnswer answer) {
        return null;
    }
}
