package cursoSpringBoot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Esta propiedad esta en modo experimental
//@MappedSuperclass // Identifica la clase como una superclase. Esta clase solo va a estar en el codigo,
                  // no se crea una tabla. Tampoco se puede insertar esta clase en la DB, ni se pueden hacer consultas
                  // u otro tipo de consulta.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // SINGLE_TABLE strategy crea una sola tabla con los atributos de
//las clases hijas y los de la clase padre. El nombre de la tabla es el de la clase padre.
@Entity
public class ElectronicDevices {

    @Id
    @GeneratedValue
    private Integer id;
    private String marca;
    private String modelo;
}
