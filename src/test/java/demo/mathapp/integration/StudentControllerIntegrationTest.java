package demo.mathapp.integration;

import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.*;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.repository.StudentRepository;
import demo.mathapp.service.impl.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class StudentControllerIntegrationTest {

    private StudentService systemUnderTest;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolClassRepository schoolClassRepository;
    @Autowired
    private HomeworkResultRepository homeworkResultRepository;
    @Autowired
    private SecurityConfig passwordEncoder;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new StudentService(studentRepository, passwordEncoder, schoolClassRepository);
    }

    @Test
    void shouldSaveAndRetrieveStudent() {
        Student student = systemUnderTest.createStudent(getStudent());

        Student result = systemUnderTest.getStudentById(student.getId());

        Assertions.assertEquals(student, result);
    }

    @Test
    void shouldDeleteStudent() {
        Student tempStudent = getStudent();
        tempStudent.setWorkResults(Collections.singletonList(getHomeworkResult()));
        Student student = systemUnderTest.createStudent(tempStudent);

        systemUnderTest.deleteStudent(student.getId());

        List<WorkResult> allHomeworks = homeworkResultRepository.findAll();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> systemUnderTest.getStudentById(student.getId()));
        Assertions.assertEquals(0, allHomeworks.size());
        Assertions.assertNotNull(schoolClassRepository.findAll());
    }

    private Student getStudent() {
        return getStudent("Sebastian", "Zarzycki", "szarzycki@gmail.com");
    }

    private Student getStudent(String firstName, String lastName, String email) {
        Student student = new Student();
        student.setPassword("1234");
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setStudentClass(getSchoolClass(3, "b", null));
        return student;
    }


    private SchoolClass getSchoolClass(int year, String index, Teacher teacher) {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassYear(year);
        schoolClass.setClassIndex(index);
        schoolClass.setTeacher(teacher);
        return schoolClass;
    }

    public HomeworkResult getHomeworkResult() {
        HomeworkResult result = new HomeworkResult();
        result.setHomeworkAnswers(getAnswers());
        result.setPoints(9);
        result.setPassed(true);
        result.setWorkTimeResult(30);
        return result;
    }

    private List<HomeworkAnswer> getAnswers() {
        List<HomeworkAnswer> answers = new ArrayList<>();
        for (int i=0; i<3; i++) {
            HomeworkAnswer answer = getAnswer(i);
            answers.add(answer);
        }
        return answers;
    }

    private HomeworkAnswer getAnswer(int points) {
        HomeworkAnswer answer = new HomeworkAnswer();
        answer.setAnswer("Lorem ipsum dolor sit amet");
        answer.setPoints(points);
        answer.setResult(null);
        return answer;
    }

}
