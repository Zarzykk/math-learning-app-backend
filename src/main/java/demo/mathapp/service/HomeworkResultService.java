package demo.mathapp.service;


import demo.mathapp.model.HomeworkResult;
import org.springframework.stereotype.Service;

@Service
public interface HomeworkResultService {

    HomeworkResult createResult(HomeworkResult result);

    void deleteResult(Long id);

    HomeworkResult updateResult(Long id,HomeworkResult result);
}
