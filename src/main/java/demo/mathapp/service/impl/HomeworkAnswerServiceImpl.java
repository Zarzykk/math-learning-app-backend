package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.repository.HomeworkAnswerRepository;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.service.HomeworkAnswerService;
import demo.mathapp.transferobject.HomeworkAnswerTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkAnswerServiceImpl implements HomeworkAnswerService {
    private final HomeworkAnswerRepository answerRepository;
    private final HomeworkResultRepository resultRepository;

    @Override
    public HomeworkAnswer createAnswer(HomeworkAnswer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public HomeworkAnswer updateAnswer(Long id, HomeworkAnswer answer) {
        HomeworkAnswer oldAnswer = (HomeworkAnswer) answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        oldAnswer.setAnswer(answer.getAnswer());
        oldAnswer.setPoints(answer.getPoints());
        return answerRepository.save(oldAnswer);
    }

    private HomeworkAnswerTO entityToTransfer(HomeworkAnswer homeworkAnswer) {
        HomeworkAnswerTO to = new HomeworkAnswerTO();
        to.setId(homeworkAnswer.getId());
        to.setAnswer(homeworkAnswer.getAnswer());
        to.setPoints(homeworkAnswer.getPoints());
        to.setHomeworkResultId(homeworkAnswer.getResult().getId());
        return to;
    }

    private HomeworkAnswer transferToEntity(HomeworkAnswerTO to) {
        HomeworkAnswer homeworkAnswer = new HomeworkAnswer();
        homeworkAnswer.setId(to.getId());
        homeworkAnswer.setAnswer(to.getAnswer());
        homeworkAnswer.setPoints(to.getPoints());
        homeworkAnswer.setResult(resultRepository.findById(to.getHomeworkResultId())
                .orElseThrow(() -> new ResourceNotFoundException("Homework result for Answer with id=" + to.getId() + " not found")));
        return homeworkAnswer;
    }
}
