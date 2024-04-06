package demo.mathapp.integration;

import demo.mathapp.MathAppApplication;
import demo.mathapp.config.SecurityConfig;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Student;
import demo.mathapp.model.Teacher;
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

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {MathAppApplication.class},
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
}
