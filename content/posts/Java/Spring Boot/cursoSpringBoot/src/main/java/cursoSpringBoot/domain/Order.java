package cursoSpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "T_Order")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private Long orderNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    @ManyToMany
//    Esta notacion se define en la entidad controladora o padre de la relacion
    @JoinTable(
            name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id")},/* Define la columna dentro de la tabla de union de la
                                                                tabla duenna */
            inverseJoinColumns = { @JoinColumn(name = "product_id")} /* Define la columna de la otra tabla en la relacion
                                                                        en la tabla de union.*/
    )
    private List<Product> products;
}
