package demo.mathapp.DTO.TestTask;

import demo.mathapp.model.Material;
import demo.mathapp.model.TestAnswer;
import lombok.Data;

import java.util.List;

@Data
public class GetTestTask {
    private String text;
    private double points;
    private Material material;
}
