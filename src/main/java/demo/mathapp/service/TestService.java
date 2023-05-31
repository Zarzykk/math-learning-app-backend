package demo.mathapp.service;

import demo.mathapp.model.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {

    Test createTest(Test test);

    void deleteTest(Long id);

    Test updateTest(Long id,Test test);

    List<Test> findTestsBySchoolClass(Long schoolClassId);
}
