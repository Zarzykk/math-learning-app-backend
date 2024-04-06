package demo.mathapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value = "HOMEWORK")
@ToString(callSuper = true)
public class HomeworkAnswer extends WorkAnswer{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeworkAnswer that = (HomeworkAnswer) o;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getAnswer(), that.getAnswer()) &&
                Objects.equals(this.getPoints(), that.getPoints()) &&
                Objects.equals(this.getResult(), that.getResult());
    }
}
