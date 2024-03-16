package demo.mathapp.transferobject.test;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestBodyTO {

    private int numberOfCompletedTests;
    private int numberOfExpectedTests;
    private double maxPoints;
    private String deactivationTime;
}
