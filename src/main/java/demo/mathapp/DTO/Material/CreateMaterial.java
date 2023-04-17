package demo.mathapp.DTO.Material;

import demo.mathapp.ClassYear;
import demo.mathapp.DTO.Topic.CreateTopic;
import demo.mathapp.SchoolType;
import lombok.Data;

import java.util.List;

@Data
public class CreateMaterial {
    private SchoolType schoolType;
    private ClassYear classYear;
    private List<CreateTopic> materialTopics;

}
