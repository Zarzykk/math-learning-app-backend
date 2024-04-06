package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.HomeworkAnswer;
import demo.mathapp.repository.HomeworkAnswerRepository;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.transferobject.HomeworkAnswerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkAnswerService {
    private final HomeworkAnswerRepository answerRepository;
    private final HomeworkResultRepository resultRepository;

    public HomeworkAnswer createAnswer(HomeworkAnswer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public HomeworkAnswer updateAnswer(Long id, HomeworkAnswer answer) {
        HomeworkAnswer oldAnswer = (HomeworkAnswer) answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        oldAnswer.setAnswer(answer.getAnswer());
        oldAnswer.setPoints(answer.getPoints());
        return answerRepository.save(oldAnswer);
    }

    public HomeworkAnswer findAnswerById(Long id) {
        return (HomeworkAnswer) answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));
    }
}
