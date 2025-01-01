package demo.mathapp.controller;

import demo.mathapp.service.AssignmentService;
import demo.mathapp.transferobject.AssignmentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/{assignmentType}/{teacherId}")
    public ResponseEntity<List<AssignmentDto>> getTeacherAssignmentsByType(@PathVariable("assignmentType") String assignmentType,
                                                                           @PathVariable("teacherId") Long teacherId) {
        return ResponseEntity.ok(assignmentService.getTeacherAssignmentsByType(assignmentType, teacherId));
    }
}
