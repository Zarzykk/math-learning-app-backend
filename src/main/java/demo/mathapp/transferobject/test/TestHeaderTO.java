package demo.mathapp.transferobject.test;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestHeaderTO {

    private long id;
    private String className;
    private String materialName;
    private String activationTime;
}
