package demo.mathapp.service;

import demo.mathapp.model.Homework;
import demo.mathapp.repository.HomeworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

//    public Homework createHomework(){
//       return homeworkRepository.save();
//    }
}
