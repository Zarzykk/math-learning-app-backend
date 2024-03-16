package demo.mathapp;

import demo.mathapp.model.*;
import demo.mathapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final SchoolService schoolService;
    private final SchoolClassService schoolClassService;
    private final TestService testService;

    @Override
    public void run(String... args) throws Exception {
        School school = createSchool();
        Teacher teacher = createTeacher();
        SchoolClass firstClass = createFirstClass(school, teacher);
        createFirstClassStudents(firstClass);
        createFirstClassTests(firstClass);
        SchoolClass secondClass = createSecondClass(school, teacher);
        createSecondClassStudents(secondClass);
        createSecondClassTests(secondClass);
    }

    private void createSecondClassTests(SchoolClass secondClass) {
        List<Test> tests = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Test test = new Test();
            test.setActivationTime(LocalDateTime.now().plusHours(i));
            test.setDeactivationTime(LocalDateTime.now().plusHours(12 + i));
            test.setSchoolClass(secondClass);
            test.setMaxTries(i);
            List<Task> tasks = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                Task task = new Task();
                task.setText("2"+i + "*" +"3"+j);
                task.setPoints((i+j)*2);
                tasks.add(task);
            }
            test.setTasks(tasks);
            tests.add(test);
        }
        for (Test test : tests) {
            testService.createTest(test);
        }
    }

    private void createSecondClassStudents(SchoolClass secondClass) {
        Student s1 = new Student();
        s1.setFirstName("Jan");
        s1.setLastName("Kowalski");
        s1.setEmail("jkowalski@gmail.com");
        s1.setPassword("123456");
        s1.setStudentClass(secondClass);
        studentService.createStudent(s1);
        Student s2 = new Student();
        s2.setFirstName("Adam");
        s2.setLastName("Nowak");
        s2.setEmail("anowak@gmail.com");
        s2.setPassword("123456");
        s2.setStudentClass(secondClass);
        studentService.createStudent(s2);
    }

    private SchoolClass createSecondClass(School school, Teacher teacher) {
        SchoolClass sc1 = new SchoolClass();
        sc1.setSchool(school);
        sc1.setClassYear(ClassYear.IV);
        sc1.setClassIndex("a");
        sc1.setTeacher(teacher);
        return schoolClassService.createSchoolClass(sc1);
    }


    private Teacher createTeacher() {
        Teacher t1 = new Teacher();
        t1.setFirstName("Sebastian");
        t1.setLastName("Zarzycki");
        t1.setEmail("szarzycki@gmail.com");
        t1.setPassword("123456");
        return teacherService.createTeacher(t1);
    }

    private void createFirstClassStudents(SchoolClass schoolClass) {
        Student s1 = new Student();
        s1.setFirstName("Arkadiusz");
        s1.setLastName("Fluda");
        s1.setEmail("afluda@gmail.com");
        s1.setPassword("123456");
        s1.setStudentClass(schoolClass);
        studentService.createStudent(s1);
        Student s2 = new Student();
        s2.setFirstName("Paweł");
        s2.setLastName("Kolasa");
        s2.setEmail("pkolasa@gmail.com");
        s2.setPassword("123456");
        s2.setStudentClass(schoolClass);
        studentService.createStudent(s2);
    }
    private School createSchool(){
        School s1 = new School();
        s1.setSchoolName("3 Liceum Ogólnokształcące");
        s1.setSchoolType(SchoolType.SECONDARY);
        return schoolService.createSchool(s1);
    }

    private SchoolClass createFirstClass(School school, Teacher teacher){
        SchoolClass sc1 = new SchoolClass();
        sc1.setSchool(school);
        sc1.setClassYear(ClassYear.III);
        sc1.setClassIndex("a");
        sc1.setTeacher(teacher);
        return schoolClassService.createSchoolClass(sc1);
    }

    private void createFirstClassTests(SchoolClass schoolClass){
        List<Test> tests = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Test test = new Test();
            test.setActivationTime(LocalDateTime.now().plusHours(i));
            test.setDeactivationTime(LocalDateTime.now().plusHours(24 + i));
            test.setSchoolClass(schoolClass);
            test.setMaxTries(i);
            List<Task> tasks = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                Task task = new Task();
                task.setText(i + "+" +j);
                task.setPoints((i*j)+1);
                tasks.add(task);
            }
            test.setTasks(tasks);
            tests.add(test);
        }
        for (Test test : tests) {
            testService.createTest(test);
        }
    }

}
