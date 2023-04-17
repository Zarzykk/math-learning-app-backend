package demo.mathapp.service;

import demo.mathapp.DTO.Test.CreateTest;
import demo.mathapp.DTO.Test.GetTest;
import demo.mathapp.model.Task;
import demo.mathapp.model.Test;
import demo.mathapp.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final ModelMapper modelMapper;

    public TestService(TestRepository testRepository, ModelMapper modelMapper) {
        this.testRepository = testRepository;
        this.modelMapper = modelMapper;
    }

    public Test createTest(CreateTest createTest) {
        Test test = modelMapper.map(createTest, Test.class);
        double maxPoints = 0;
        for (Task task:test.getTasks()) {
          maxPoints += task.getPoints();
        }
        test.setMaxPoints(maxPoints);
        return testRepository.save(test);
    }

    public GetTest getClassTest(Long id){
        return modelMapper.map(testRepository.getById(id), GetTest.class);
    }

    public void deleteTest(Long id){
        testRepository.deleteById(id);
    }

    public Test getTest(Long id) {
        return testRepository.getById(id);
    }
}
