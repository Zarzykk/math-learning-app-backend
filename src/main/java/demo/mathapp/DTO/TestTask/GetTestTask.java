package demo.mathapp.DTO.TestTask;

import demo.mathapp.model.Material;
import lombok.Data;

@Data
public class GetTestTask {
    private String text;
    private double points;
    private Material material;
}
