package demo.mathapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum SchoolType {
        @JsonProperty("PRIMARY")
        PRIMARY,
        @JsonProperty("SECONDARY")
        SECONDARY


}
