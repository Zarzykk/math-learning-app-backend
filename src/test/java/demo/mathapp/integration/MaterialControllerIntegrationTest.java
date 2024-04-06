package demo.mathapp.integration;

import demo.mathapp.MathAppApplication;
import demo.mathapp.SchoolType;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Material;
import demo.mathapp.model.MaterialTopic;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Teacher;
import demo.mathapp.repository.MaterialRepository;
import demo.mathapp.repository.MaterialTopicRepository;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.repository.TeacherRepository;
import demo.mathapp.service.impl.MaterialService;
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
import java.util.Arrays;
import java.util.Collections;
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
public class MaterialControllerIntegrationTest {

    private MaterialService systemUnderTest;

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private MaterialTopicRepository materialTopicRepository;
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new MaterialService(materialRepository);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        Material material = systemUnderTest.createMaterial(getMaterial());

        Material result = systemUnderTest.findMaterialById(material.getId());

        Assertions.assertEquals(material, result);
    }

    @Test
    void shouldUpdateMaterial() {
        Material material = systemUnderTest.createMaterial(getMaterial(
                1, SchoolType.PRIMARY, Collections.emptyList(), getMaterialTopics()));

        List<MaterialTopic> materialTopics = material.getMaterialTopics();
        Assertions.assertEquals(3, materialTopics.size());

        materialTopics.add(MaterialTopic.builder().name("Dzielenie").build());
        material.setMaterialTopics(materialTopics);

        systemUnderTest.updateMaterial(material.getId(), material);

        Material result = systemUnderTest.findMaterialById(material.getId());
        Assertions.assertEquals(4, result.getMaterialTopics().size());
        Assertions.assertEquals(material, result);
    }

    @Test
    void shouldDeleteMaterial() {
        Material material = systemUnderTest.createMaterial(getMaterial(
                1, SchoolType.PRIMARY, getSchoolClasses(null), getMaterialTopics()));

        List<MaterialTopic> materialTopics = material.getMaterialTopics();
        List<SchoolClass> classList = material.getClassList();
        systemUnderTest.deleteMaterial(material.getId());

        List<SchoolClass> allSchoolClasses = schoolClassRepository.findAll();
        Assertions.assertEquals(classList.size(), allSchoolClasses.size());

        List<MaterialTopic> allMaterialTopics = materialTopicRepository.findAll();
        Assertions.assertEquals(materialTopics.size(), allMaterialTopics.size());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> systemUnderTest.findMaterialById(material.getId()));
    }


    private Material getMaterial(int classYear, SchoolType schoolType, List<SchoolClass> schoolClasses, List<MaterialTopic> topics) {
        return Material.builder()
                .classYear(classYear)
                .schoolType(schoolType)
                .materialTopics(topics)
                .classList(schoolClasses)
                .build();
    }


    private Material getMaterial() {
        return getMaterial(3, SchoolType.PRIMARY, Collections.emptyList(), Collections.emptyList());
    }

    private List<MaterialTopic> getMaterialTopics() {
        List<MaterialTopic> materialTopics = new ArrayList<>();
        List<String> topicNames = Arrays.asList("Dodawanie", "Odejmowanie", "Mnożenie");
        for (String topicName : topicNames) {
            MaterialTopic materialTopic = MaterialTopic.builder()
                    .name(topicName)
                    .build();
            materialTopics.add(materialTopic);
        }
        return materialTopics;
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
