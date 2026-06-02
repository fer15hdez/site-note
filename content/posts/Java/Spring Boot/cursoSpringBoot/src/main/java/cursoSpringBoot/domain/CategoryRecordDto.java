package cursoSpringBoot.domain;

import java.util.List;

public record CategoryRecordDto(
        String nombre,
        String description,
        List<Product> products
) {
}
