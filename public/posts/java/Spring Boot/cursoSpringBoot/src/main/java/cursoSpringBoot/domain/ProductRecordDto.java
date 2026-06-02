package cursoSpringBoot.domain;

import jakarta.validation.constraints.NotEmpty;

public record ProductRecordDto(
        Integer serial,

        @NotEmpty(message = "This field must be filled")
        String name,
        Double price,
        Integer stock,
        String some_colum,
        Images images,
        // La relacion de la entidad con Category se representa con un Integer,
        // no con un valor de tipo de la entidad (ej. Category categoryId)
        Integer categoryId
) {
}
