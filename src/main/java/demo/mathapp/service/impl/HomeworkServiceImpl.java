package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Homework;
import demo.mathapp.model.SchoolClass;
import demo.mathapp.model.Task;
import demo.mathapp.model.Work;
import demo.mathapp.repository.HomeworkRepository;
import demo.mathapp.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;

    @Override
    public Homework createHomework(Homework homework) {
        homework.setMaxPoints(calculateMaxPoints(homework.getTasks()));
        homework.setMaxWorkTime(calculateMaxWorkTime(
                homework.getActivationTime(),
                homework.getDeactivationTime()
        ));
        return homeworkRepository.save(homework);
    }

    @Override
    public void deleteHomework(Long id) {
        homeworkRepository.deleteById(id);
    }

    @Override
    public Homework updateHomework(Long id, Homework homework) {
        Homework oldHomework = (Homework) homeworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found"));
        oldHomework.setActivationTime(homework.getActivationTime());
        oldHomework.setDeactivationTime(homework.getDeactivationTime());
        oldHomework.setTasks(homework.getTasks());
        oldHomework.setMaxPoints(calculateMaxPoints(oldHomework.getTasks()));
        oldHomework.setMaxWorkTime(calculateMaxWorkTime(
                oldHomework.getActivationTime(),
                oldHomework.getDeactivationTime()
        ));
        return homeworkRepository.save(oldHomework);
    }

    @Override
    public List<Homework> findHomeworksBySchoolClass(Long schoolClassId) {
        return homeworkRepository.findWorksByWorkType("HOMEWORK")
                .stream()
                .filter(work -> work.getSchoolClass().getId() == schoolClassId)
                .map(work -> modelMapper.map(work, Homework.class))
                .collect(Collectors.toList());
    }

    private double calculateMaxPoints(List<Task> tasks) {
        double points = 0;
        for (Task t : tasks) {
            points += t.getPoints();
        }
        return points;
    }

    private long calculateMaxWorkTime(Date start, Date end) {
        long diff = Math.abs(end.getTime() - start.getTime());
        return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
