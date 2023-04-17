package demo.mathapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ClassYear {
    @JsonProperty("I")
    I,
    @JsonProperty("II")
    II,
    @JsonProperty("III")
    III,
    @JsonProperty("IV")
    IV,
    @JsonProperty("V")
    V,
    @JsonProperty("VI")
    VI,
    @JsonProperty("VII")
    VII,
    @JsonProperty("VIII")
    VIII
}
