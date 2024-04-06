package demo.mathapp.service.impl;

import demo.mathapp.config.SecurityConfig;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Admin;
import demo.mathapp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final SecurityConfig passwordEncoder;

    public List<Admin> getAdmins() {
        return adminRepository.findAllAdmins();
    }

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encodePassword(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin updateAdmin(Long id, Admin admin) {
        admin.setId(id);
        return adminRepository.save(admin);
    }

    public Admin getAdminByEmail(String email) {
        return (Admin) adminRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    public Admin getAdminById(Long id) {
        return (Admin) adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
    }
}
