package demo.mathapp.mapper.homework;

import demo.mathapp.DTO.Homework.HomeworkAnswerCreate;
import demo.mathapp.DTO.Homework.HomeworkCreate;
import demo.mathapp.DTO.Homework.HomeworkResultCreate;
import demo.mathapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class HomeworkMapper {

    private HomeworkResult mapHomeworkResultTOtoHomeworkResult(HomeworkResultCreate dto) {
        HomeworkResult homeworkResult = new HomeworkResult();
        homeworkResult.setAnswers(dto.getAnswers());
        homeworkResult.setHomework(new Homework(dto.getHomeworkId()));
        homeworkResult.setStudent(new Student(dto.getStudentId()));
        return homeworkResult;
    }

    private Homework mapHomeworkTOtoHomework(HomeworkCreate dto) {
        Homework homework = new Homework();
        homework.setDeadline(dto.getDeadline());
        homework.setSchoolClass(new SchoolClass(dto.getSchoolClassId()));
        List<Task> tasks = new ArrayList<>();
        dto.getTasks().forEach(
                e -> tasks.add(new Task(
                        e.getText(),
                        e.getPoints(),
                        new Homework(e.getHomeworkId()),
                        homeworkAnswerTOListToHomeworkAnswerList(e.getAnswers()),
                        new MaterialTopic(e.getMaterialTopicId())
                ))
        );
        homework.setTasks(tasks);
        homework.setHomeworkResults(dto.getHomeworkResults());
        return homework;
    }

    private HomeworkAnswer homeworkAnswerTOtoHomeworkAnswer(HomeworkAnswerCreate dto) {
        HomeworkAnswer answer = new HomeworkAnswer();
        answer.setAnswer(dto.getAnswer());
        answer.setPoints(dto.getPoints());
        return answer;
    }

    private List<HomeworkAnswer> homeworkAnswerTOListToHomeworkAnswerList(List<HomeworkAnswerCreate> dtoList) {
        List<HomeworkAnswer> answerList = new ArrayList<>();
        dtoList.forEach(
                e -> answerList.add(homeworkAnswerTOtoHomeworkAnswer(e))
        );
        return answerList;
    }


}
