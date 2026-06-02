package cursoSpringBoot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BarCode {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    @OneToOne
    @JoinColumn(name = "product_id") // Esta columna se crea en la tabla para hacer referencia al campo 'product'
    private Product product;
}
