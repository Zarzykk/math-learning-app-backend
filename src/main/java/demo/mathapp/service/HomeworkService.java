package demo.mathapp.service;

import demo.mathapp.model.Homework;
import demo.mathapp.model.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeworkService {

    Homework createHomework(Homework homework);

    void deleteHomework(Long id);

    Homework updateHomework(Long id, Homework homework);

    List<Homework> findHomeworksBySchoolClass(Long schoolClassId);
}
