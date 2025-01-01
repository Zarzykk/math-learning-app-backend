package demo.mathapp.service;

import demo.mathapp.repository.AssignmentResultRepository;
import demo.mathapp.transferobject.AssignmentResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentResultService {

    private final AssignmentResultRepository assignmentResultRepository;

    public List<AssignmentResultDto> getStudentResults(Long studentId) {
        return assignmentResultRepository.findAllByStudent_Id(studentId).stream()
                .map(result -> AssignmentResultDto.builder()
                        .id(result.getId())
                        .points(result.getPoints())
                        .passed(result.getPassed())
                        .studentId(result.getStudent().getId())
                        .studentName(result.getStudent().getFirstName() +" "+result.getStudent().getLastName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AssignmentResultDto> getStudentResult(Long assignmentId, Long studentId) {
        return assignmentResultRepository.findAllByStudent_IdAndAssignment_Id(studentId, assignmentId)
                .stream()
                .map(result -> AssignmentResultDto.builder()
                        .id(result.getId())
                        .points(result.getPoints())
                        .passed(result.getPassed())
                        .build())
                .collect(Collectors.toList());
    }
}
