package demo.mathapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = "TEST")
public class Test extends Work{

    @Column(columnDefinition = "integer default 1")
    private Integer maxTries;
}
