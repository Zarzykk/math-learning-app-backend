package demo.mathapp.service;

import demo.mathapp.model.Admin;
import demo.mathapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<Admin> getAdmins();

    Admin createAdmin(Admin admin);

    void deleteAdmin(Long id);

    Admin updateAdmin(Long id,Admin admin);
}
