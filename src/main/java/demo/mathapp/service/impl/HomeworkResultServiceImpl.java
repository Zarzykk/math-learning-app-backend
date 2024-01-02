package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.HomeworkResult;
import demo.mathapp.repository.HomeworkAnswerRepository;
import demo.mathapp.repository.HomeworkRepository;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.repository.StudentRepository;
import demo.mathapp.service.HomeworkResultService;
import demo.mathapp.transferobject.HomeworkResultTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkResultServiceImpl implements HomeworkResultService {
    private final HomeworkResultRepository resultRepository;
    private final HomeworkAnswerRepository answerRepository;
    private final HomeworkRepository homeworkRepository;
    private final StudentRepository studentRepository;

    @Override
    public HomeworkResult createResult(HomeworkResult result) {
        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    @Override
    public HomeworkResult updateResult(Long id, HomeworkResult result) {
        HomeworkResult oldResult = (HomeworkResult) resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        oldResult.setPassed(result.isPassed());
        oldResult.setPoints(result.getPoints());
        return resultRepository.save(oldResult);
    }

    private HomeworkResultTO entityToTransfer(HomeworkResult homeworkResult){
        HomeworkResultTO to = new HomeworkResultTO();
        to.setId(homeworkResult.getId());
        to.setHomeworkId(homeworkResult.getWork().getId());
        to.setPoints(homeworkResult.getPoints());
        to.setPassed(homeworkResult.isPassed());
        to.setHomeworkTimeResult(homeworkResult.getWorkTimeResult());
        return to;
    }

    private HomeworkResult transferToEntity(HomeworkResultTO to){
        HomeworkResult homeworkResult = new HomeworkResult();
        homeworkResult.setId(to.getId());
        homeworkResult.setHomeworkAnswers(answerRepository.findAllByResult_Id(to.getId()));
        homeworkResult.setStudent(studentRepository.findStudentById(to.getStudentId()).orElseThrow());
        homeworkResult.setWork(homeworkRepository.findById(to.getHomeworkId()).orElseThrow());
        homeworkResult.setWorkTimeResult(to.getHomeworkTimeResult());
        homeworkResult.setPoints(to.getPoints());
        homeworkResult.setPassed(to.isPassed());

        return homeworkResult;
    }
}
