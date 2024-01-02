package demo.mathapp.transferobject;

import lombok.Data;

import java.util.List;

@Data
public class ClassTO {

    private long id;
    private String classYearAndIndex;
    private List<StudentTO> students;
}
