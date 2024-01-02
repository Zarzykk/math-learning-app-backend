package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Student;
import demo.mathapp.model.Task;
import demo.mathapp.model.Test;
import demo.mathapp.repository.TestRepository;
import demo.mathapp.service.StudentService;
import demo.mathapp.service.TestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @Override
    public Test createTest(Test test) {
        test.setMaxPoints(calculateMaxPoints(test.getTasks()));
        test.setMaxWorkTime(calculateMaxWorkTime(
                test.getActivationTime(),
                test.getDeactivationTime()
        ));
        return testRepository.save(test);
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public Test updateTest(Long id, Test test) {
        Test oldTest = (Test) testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found"));
        oldTest.setActivationTime(test.getActivationTime());
        oldTest.setMaxTries(test.getMaxTries());
        oldTest.setDeactivationTime(test.getDeactivationTime());
        oldTest.setTasks(test.getTasks());
        oldTest.setMaxPoints(calculateMaxPoints(oldTest.getTasks()));
        oldTest.setMaxWorkTime(calculateMaxWorkTime(
                oldTest.getActivationTime(),
                oldTest.getDeactivationTime()
        ));
        return testRepository.save(oldTest);
    }

    @Override
    public List<Test> findTestsBySchoolClass(Long schoolClassId) {
        return testRepository.findWorksByWorkType("TEST")
                .stream()
                .filter(work -> work.getSchoolClass().getId() == schoolClassId)
                .map(work -> modelMapper.map(work, Test.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Test> findAllTestsPassedOrFailedForStudent(Long studentId, boolean passed) {
        Student student = studentService.getStudentById(studentId);
        List<Test> tests = new ArrayList<>();
        student.getWorkResults().stream()
                .filter(workResult -> workResult.isPassed()==passed)
                .forEach(workResult -> tests.add(modelMapper.map(workResult.getWork(),Test.class)));
        return tests;
    }

    private double calculateMaxPoints(List<Task> tasks) {
        double points = 0;
        for (Task t : tasks) {
            points += t.getPoints();
        }
        return points;
    }

    private long calculateMaxWorkTime(Date start, Date end) {
        long diff = Math.abs(end.getTime() - start.getTime());
        return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
    }


}
