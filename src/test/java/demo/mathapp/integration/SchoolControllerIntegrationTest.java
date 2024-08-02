package demo.mathapp.integration;

import demo.mathapp.SchoolType;
import demo.mathapp.model.School;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Teacher;
import demo.mathapp.repository.SchoolRepository;
import demo.mathapp.repository.TeacherRepository;
import demo.mathapp.service.impl.SchoolService;
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
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class SchoolControllerIntegrationTest {

    private SchoolService systemUnderTest;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new SchoolService(schoolRepository);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        School school = systemUnderTest.createSchool(createSchool());

        School result = systemUnderTest.findSchoolById(school.getId());

        Assertions.assertEquals(school, result);
    }

    @Test
    void shouldUpdateSchool() {
        School school = systemUnderTest.createSchool(createSchool());
        Assertions.assertNotEquals(SchoolType.TECHNICAL, school.getSchoolType());
        school.setSchoolType(SchoolType.TECHNICAL);
        systemUnderTest.updateSchool(school.getId(), school);

        School result = systemUnderTest.findSchoolById(school.getId());

        school.setId(result.getId());
        Assertions.assertEquals(school, result);
    }

    @Test
    void shouldDeleteSchool() {
        School school = systemUnderTest.createSchool(createSchool());
        systemUnderTest.deleteSchool(school.getId());

    }

    private Teacher getTeacher(String firstName, String lastName, String email) {
        Teacher teacher = new Teacher();
        teacher.setPassword("1234");
        teacher.setEmail(email);
        teacher.setLastName(lastName);
        teacher.setFirstName(firstName);
        teacher.setClasses(getClasses(teacher));
        return teacherRepository.save(teacher);
    }

    private School createSchool() {
        return School.builder()
                .schoolClasses(getClasses(getTeacher("Sebastian", "Zarzycki", "szarzycki@gmail.com")))
                .schoolType(SchoolType.SECONDARY)
                .schoolName("III Liceum Ogólnokształcące w Rzeszowie")
                .build();
    }

    private List<SchoolClass> getClasses(Teacher teacher) {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            SchoolClass schoolClass = getSchoolClass(i, "a");
            schoolClass.setTeacher(teacher);
            schoolClasses.add(schoolClass);
        }
        return schoolClasses;
    }

    private SchoolClass getSchoolClass(int year, String index) {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassYear(year);
        schoolClass.setClassIndex(index);
        return schoolClass;
    }
}
