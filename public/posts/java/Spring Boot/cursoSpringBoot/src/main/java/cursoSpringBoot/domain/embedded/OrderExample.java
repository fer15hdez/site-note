package cursoSpringBoot.domain.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderExample {

    @EmbeddedId
    private OrderId orderId;
    private String orderInfo;
    private String anotherField;
}
