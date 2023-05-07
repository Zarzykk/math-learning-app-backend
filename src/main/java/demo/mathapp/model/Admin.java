package demo.mathapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = "ADMIN")
public class Admin extends User{

}
