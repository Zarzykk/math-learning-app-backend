package demo.mathapp.service;


import demo.mathapp.model.AssignmentType;
import demo.mathapp.repository.AssignmentRepository;
import demo.mathapp.transferobject.AssignmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public List<AssignmentDto> getTeacherAssignmentsByType(String assignmentType, Long teacherId) {
        return assignmentRepository.findAllBySchoolClass_Teacher_IdAndType(teacherId, AssignmentType.valueOf(assignmentType)).stream()
                .map(assignment -> AssignmentDto.builder()
                        .id(assignment.getId())
                        .activationTime(assignment.getActivationTime())
                        .deactivationTime(assignment.getDeactivationTime())
                        .maxPoints(assignment.getMaxPoints())
                        .schoolClassId(assignment.getSchoolClass().getId())
                        .classIndex(getClassIndex(assignment.getSchoolClass().getClassYear(),
                                assignment.getSchoolClass().getClassIndex()))
                        .materialId(assignment.getMaterial().getId())
                        .materialSection(assignment.getMaterial().getSection())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AssignmentDto> getClassAssignments(Long classId) {
        return assignmentRepository.findAllBySchoolClass_Id(classId).stream()
                .map(assignment -> AssignmentDto.builder()
                        .id(assignment.getId())
                        .type(assignment.getType())
                        .maxPoints(assignment.getMaxPoints())
                        .schoolClassId(assignment.getSchoolClass().getId())
                        .classIndex(getClassIndex(assignment.getSchoolClass().getClassYear(),
                                assignment.getSchoolClass().getClassIndex()))
                        .build())
                .collect(Collectors.toList());
    }

    private String getClassIndex(int classYear, String classIndex) {
        return classYear + classIndex;
    }


}
