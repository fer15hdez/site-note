package cursoSpringBoot.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Esta propiedad esta en modo experimental
@Entity
@DiscriminatorValue("L")
public class Laptop extends ElectronicDevices{

    private double sizeScreen;
    private int processor;
}
