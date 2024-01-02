package demo.mathapp.repository;

import demo.mathapp.model.HomeworkAnswer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkAnswerRepository extends WorkAnswerRepository{

    List<HomeworkAnswer> findAllByResult_Id(long resultId);
}
