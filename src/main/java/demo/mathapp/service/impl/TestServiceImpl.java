package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Student;
import demo.mathapp.model.Task;
import demo.mathapp.model.Test;
import demo.mathapp.repository.TestRepository;
import demo.mathapp.service.StudentService;
import demo.mathapp.service.TestService;
import demo.mathapp.transferobject.TaskTO;
import demo.mathapp.transferobject.test.TestBodyTO;
import demo.mathapp.transferobject.test.TestHeaderTO;
import demo.mathapp.transferobject.test.TestTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        test.getTasks().forEach(task -> {
            if (task.getWork() == null) {
                task.setWork(test);
            }
        });
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

    @Override
    public List<TestHeaderTO> findTeacherTests(Long teacherId, Optional<Long> classId) {
        List<Test> collect = testRepository.findAllBySchoolClass_Teacher_Id(teacherId)
                .stream()
                .map(work -> modelMapper.map(work, Test.class))
                .toList();
        if (classId.isPresent()) {
            collect = collect.stream()
                    .filter(test -> test.getSchoolClass().getId() == classId.get())
                    .toList();
        }
        List<TestHeaderTO> headers = new ArrayList<>();
        for (Test test : collect) {
            Task task = test.getTasks().get(0);
            String material = null;
            if (task.getMaterialTopic() != null) {
                material = task.getMaterialTopic().getName();
            }
            TestHeaderTO testHeaderTO = TestHeaderTO
                    .builder()
                    .id(test.getId())
                    .materialName(material)
                    .className(test.getSchoolClass().getClassName())
                    .activationTime(test.getActivationTime().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")))
                    .build();
            headers.add(testHeaderTO);
        }
        return headers;
    }

    @Override
    public TestBodyTO getTestDetails(Long testId) {
        Test test = modelMapper.map(testRepository.getById(testId),Test.class);
        TestBodyTO bodyTO = TestBodyTO
                .builder()
                .maxPoints(test.getMaxPoints())
                .deactivationTime(test.getDeactivationTime().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")))
                .numberOfExpectedTests(test.getSchoolClass().getStudents().size())
                .numberOfCompletedTests(test.getTestResults().size())
                .build();
        return bodyTO;
    }

    @Override
    public TestTO getTest(Long testId) {
        Test test = modelMapper.map(testRepository.getById(testId), Test.class);
        TestTO to = new TestTO();
        to.setId(test.getId());
        to.setTasks(test.getTasks().stream().map(task -> modelMapper.map(task, TaskTO.class)).toList());
        to.setMaxTries(test.getMaxTries());
        return to;
    }

    private double calculateMaxPoints(List<Task> tasks) {
        double points = 0;
        for (Task t : tasks) {
            points += t.getPoints();
        }
        return points;
    }

    private long calculateMaxWorkTime(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds();
    }


}
