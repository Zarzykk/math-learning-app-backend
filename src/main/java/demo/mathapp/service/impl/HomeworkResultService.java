package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.HomeworkResult;
import demo.mathapp.repository.HomeworkResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkResultService {
    private final HomeworkResultRepository resultRepository;

    public HomeworkResult createResult(HomeworkResult result) {
        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    public HomeworkResult updateResult(Long id, HomeworkResult result) {
        HomeworkResult oldResult = (HomeworkResult) resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        oldResult.setPassed(result.isPassed());
        oldResult.setPoints(result.getPoints());
        return resultRepository.save(oldResult);
    }

}
