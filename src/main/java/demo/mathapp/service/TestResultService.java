package demo.mathapp.service;

import demo.mathapp.DTO.TestResult.CreateTestResult;
import demo.mathapp.model.TestResult;
import demo.mathapp.repository.TestResultRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestResultService {

    private final TestResultRepository resultRepository;
    private final ModelMapper modelMapper;

    public TestResult createTestResult(CreateTestResult result){
        return resultRepository.save(modelMapper.map(result,TestResult.class));
    }

}
