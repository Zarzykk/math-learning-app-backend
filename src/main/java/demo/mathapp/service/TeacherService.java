package demo.mathapp.service;

import demo.mathapp.DTO.Teacher.CreateTeacher;
import demo.mathapp.model.Teacher;
import demo.mathapp.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {
    private final UserService userService;

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public Teacher createTeacher(CreateTeacher createTeacher){
        Teacher teacher = modelMapper.map(createTeacher,Teacher.class);
        teacher.setUser(userService.createUser(createTeacher.getUser()));
        return teacherRepository.save(teacher);
    }
}
