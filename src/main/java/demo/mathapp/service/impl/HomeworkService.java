package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Homework;
import demo.mathapp.model.Task;
import demo.mathapp.repository.HomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public Homework createHomework(Homework homework) {
        homework.setMaxPoints(calculateMaxPoints(homework.getTasks()));
        homework.setMaxWorkTime(calculateMaxWorkTime(
                homework.getActivationTime(),
                homework.getDeactivationTime()));
        return homeworkRepository.save(homework);
    }

    public void deleteHomework(Long id) {
        homeworkRepository.deleteById(id);
    }

    public Homework updateHomework(Long id, Homework homework) {
        homework.setId(id);
        homework.setMaxPoints(calculateMaxPoints(homework.getTasks()));
        homework.setMaxWorkTime(calculateMaxWorkTime(
                homework.getActivationTime(),
                homework.getDeactivationTime()));
        return homeworkRepository.save(homework);
    }

    public Homework findHomeworkById(Long id) {
        return (Homework) homeworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found"));
    }

    public List<Homework> findHomeworksBySchoolClass(Long schoolClassId) {
        return homeworkRepository.findWorksByWorkType("HOMEWORK")
                .stream()
                .filter(work -> work.getSchoolClass().getId() == schoolClassId)
                .map(work -> (Homework) work)
                .collect(Collectors.toList());
    }

    private double calculateMaxPoints(List<Task> tasks) {
        double points = 0;
        for (Task t : tasks) {
            points += t.getPoints();
        }
        return points;
    }

    private long calculateMaxWorkTime(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(end, start);
        return duration.getSeconds();
    }

}
