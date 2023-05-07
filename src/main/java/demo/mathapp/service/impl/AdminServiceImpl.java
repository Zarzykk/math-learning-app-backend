package demo.mathapp.service.impl;

import demo.mathapp.PasswordEncoder;
import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Admin;
import demo.mathapp.repository.AdminRepository;
import demo.mathapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAllAdmins();
    }

    @Override
    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encodePassword(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Admin oldAdmin = adminRepository.findAdminById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        oldAdmin.setPassword(passwordEncoder.encodePassword(admin.getPassword()));
        oldAdmin.setEmail(admin.getEmail());
        return adminRepository.save(oldAdmin);
    }


}
