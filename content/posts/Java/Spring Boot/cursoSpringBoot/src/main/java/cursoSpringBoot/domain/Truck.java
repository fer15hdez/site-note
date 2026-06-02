package cursoSpringBoot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Truck extends Vehicle{

    private int capacityLoad; // capacidadCarga
    private int quantityAxes; //  cantidadEjes

}
