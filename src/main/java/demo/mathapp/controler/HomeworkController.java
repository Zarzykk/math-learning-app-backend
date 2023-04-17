package demo.mathapp.controler;

import demo.mathapp.service.HomeworkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/homework")
public class HomeworkController {
    private final HomeworkService homeworkService;
}
