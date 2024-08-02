package demo.mathapp.integration;


import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Admin;
import demo.mathapp.repository.AdminRepository;
import demo.mathapp.service.impl.AdminService;
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
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(SAME_THREAD)
@ActiveProfiles("integration")
@Transactional
public class AdminControllerIntegrationTest {

    private AdminService systemUnderTest;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SecurityConfig passwordEncoder;

    @BeforeEach
    public void beforeEach() {
        systemUnderTest = new AdminService(adminRepository, passwordEncoder);
    }

    @Test
    void shouldSaveAndRetrieveSuccessfully() {
        //given
        Admin admin = getAdmin();

        //when
        systemUnderTest.createAdmin(admin);
        Admin result = systemUnderTest.getAdminByEmail(admin.getEmail());

        //then
        admin.setId(result.getId());
        Assertions.assertEquals(admin, result);
    }


    @Test
    void shouldDeleteAdmin() {
        //given
        Admin admin = systemUnderTest.createAdmin(getAdmin());

        //when
        systemUnderTest.deleteAdmin(admin.getId());

        //then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> systemUnderTest.getAdminById(admin.getId()));
    }

    @Test
    void shouldUpdateAdmin() {
        Admin admin = systemUnderTest.createAdmin(getAdmin());

        Assertions.assertNotEquals("Arkadiusz",admin.getFirstName());
        admin.setFirstName("Arkadiusz");

        systemUnderTest.updateAdmin(admin.getId(), admin);
        Admin result = systemUnderTest.getAdminByEmail(admin.getEmail());

        admin.setId(result.getId());
        Assertions.assertEquals(admin, result);
    }

    private Admin getAdmin() {
        return getAdmin("Sebastian", "Zarzycki", "szarzycki@gmail.com");
    }

    private Admin getAdmin(String firstName, String lastName, String email) {
        Admin admin = new Admin();
        admin.setPassword("1234");
        admin.setEmail(email);
        admin.setLastName(lastName);
        admin.setFirstName(firstName);
        return admin;
    }

}
