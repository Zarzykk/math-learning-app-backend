package demo.mathapp.service.impl;

import demo.mathapp.exception.ResourceNotFoundException;
import demo.mathapp.model.Homework;
import demo.mathapp.model.Task;
import demo.mathapp.repository.HomeworkRepository;
import demo.mathapp.repository.HomeworkResultRepository;
import demo.mathapp.repository.SchoolClassRepository;
import demo.mathapp.repository.TaskRepository;
import demo.mathapp.service.HomeworkService;
import demo.mathapp.transferobject.HomeworkTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final TaskRepository taskRepository;
    private final HomeworkResultRepository homeworkResultRepository;
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

    private HomeworkTO entityToTransfer(Homework homework){
        HomeworkTO to = new HomeworkTO();
        to.setActivationTime(homework.getActivationTime());
        to.setDeactivationTime(homework.getDeactivationTime());
        to.setMaxPoints(homework.getMaxPoints());
        to.setMaxWorkTime(homework.getMaxWorkTime());
        to.setId(homework.getId());
        to.setSchoolClassId(homework.getSchoolClass().getId());
        return to;
    }

    private Homework transferToEntity(HomeworkTO to){
        Homework homework = new Homework();
        homework.setActivationTime(to.getActivationTime());
        homework.setDeactivationTime(to.getDeactivationTime());
        homework.setMaxWorkTime(to.getMaxWorkTime());
        homework.setId(to.getId());
        homework.setMaxPoints(to.getMaxPoints());
//        homework.setHomeworkResults();
        homework.setTasks(taskRepository.findTasksByWork_Id(homework.getId()));
        homework.setSchoolClass(schoolClassRepository.findById(to.getSchoolClassId())
                .orElseThrow());
        return homework;
    }
}
