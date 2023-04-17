package demo.mathapp.DTO.TestTask;

import demo.mathapp.model.Material;
import demo.mathapp.model.Test;
import lombok.Data;

@Data
public class CreateTestTask {
    private String text;
    private double points;
    private Test test;
//    private Material material;
}
