package demo.mathapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum SchoolType {
        @JsonProperty("PRIMARY")
        PRIMARY("PRIMARY",8),
        @JsonProperty("SECONDARY")
        SECONDARY("SECONDARY",4),
        @JsonProperty("TECHNICAL")
        TECHNICAL("TECHNICAL",5);

        private final String schoolType;
        private final int maxYears;

        SchoolType(String schoolType, int maxYears) {
                this.schoolType = schoolType;
                this.maxYears = maxYears;
        }


}
