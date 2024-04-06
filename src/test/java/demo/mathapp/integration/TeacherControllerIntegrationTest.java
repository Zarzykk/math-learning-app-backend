package demo.mathapp.integration;

import demo.mathapp.MathAppApplication;
import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Teacher;
import demo.mathapp.repository.TeacherRepository;
import demo.mathapp.service.impl.SchoolClassService;
import demo.mathapp.service.impl.TeacherService;
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
import java.util.List;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Tag("slow")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {MathAppApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class TeacherControllerIntegrationTest {

    private TeacherService systemUnderTest;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SecurityConfig passwordEncoder;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new TeacherService(teacherRepository, passwordEncoder, schoolClassService);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        //given
        Teacher teacher = getTeacher();

        //when
        systemUnderTest.createTeacher(teacher);
        Teacher result = systemUnderTest.getTeacherByEmail(teacher.getEmail());

        //then
        teacher.setId(result.getId());
        Assertions.assertEquals(teacher, result);
    }

    @Test
    void shouldUpdateTeacher() {
        Teacher teacher = getTeacher();

        Teacher teacher1 = systemUnderTest.createTeacher(teacher);
        Assertions.assertNotEquals(teacher1.getFirstName(), "Sebastian");
        teacher.setFirstName("Sebastian");

        systemUnderTest.updateTeacher(teacher.getId(), teacher);
        Teacher result = systemUnderTest.getTeacherByEmail(teacher.getEmail());

        teacher.setId(result.getId());
        Assertions.assertEquals(teacher, result);
    }

    @Test
    void shouldDeleteTeacher() {
        Teacher teacher = systemUnderTest.createTeacher(getTeacher());
        Teacher tempTeacher = getTeacher("Sebastian", "Zarzycki", "szarzycki@gmail.com");
        tempTeacher.setClasses(new ArrayList<>());
        Teacher substituteTeacher = systemUnderTest.createTeacher(tempTeacher);
        List<Long> classIds = teacher.getClasses().stream().map(SchoolClass::getId).toList();
        systemUnderTest.deleteTeacher(teacher.getId(),substituteTeacher.getId());
        substituteTeacher = systemUnderTest.getTeacherById(substituteTeacher.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> systemUnderTest.getTeacherById(teacher.getId()));
        List<Long> resultIds = substituteTeacher.getClasses().stream().map(SchoolClass::getId).toList();
        Assertions.assertTrue(resultIds.containsAll(classIds));
    }


    private Teacher getTeacher(String firstName, String lastName,String email) {
        Teacher teacher = new Teacher();
        teacher.setPassword("1234");
        teacher.setEmail(email);
        teacher.setLastName(lastName);
        teacher.setFirstName(firstName);
        teacher.setClasses(getSchoolClasses(teacher));
        return teacher;
    }


    private Teacher getTeacher() {
        return getTeacher("Jakub","Ruchel","jruchel@gmail.com");
    }

    private List<SchoolClass> getSchoolClasses(Teacher teacher) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            SchoolClass schoolClass = getSchoolClass(i,"a");
            schoolClass.setTeacher(teacher);
            schoolClasses.add(schoolClass);
        }
        return schoolClasses;
    }

    private SchoolClass getSchoolClass(int year,String index) {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassYear(year);
        schoolClass.setClassIndex(index);
        return schoolClass;
    }

}
