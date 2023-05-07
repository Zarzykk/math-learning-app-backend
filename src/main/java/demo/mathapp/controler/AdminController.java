package demo.mathapp.controler;

import demo.mathapp.model.Admin;
import demo.mathapp.model.User;
import demo.mathapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @PutMapping("/update/{adminId}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable Long adminId,
                                                 @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(adminId, admin));
    }

    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok().build();
    }

}
