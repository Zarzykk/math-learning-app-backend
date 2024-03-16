package demo.mathapp.service;

import demo.mathapp.model.Test;
import demo.mathapp.transferobject.test.TestBodyTO;
import demo.mathapp.transferobject.test.TestHeaderTO;
import demo.mathapp.transferobject.test.TestTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TestService {

    Test createTest(Test test);

    void deleteTest(Long id);

    Test updateTest(Long id, Test test);

    List<Test> findTestsBySchoolClass(Long schoolClassId);

    List<Test> findAllTestsPassedOrFailedForStudent(Long studentId, boolean passed);

    List<TestHeaderTO> findTeacherTests(Long teacherId, Optional<Long> classId);

    TestBodyTO getTestDetails(Long testId);

    TestTO getTest(Long testId);
}
