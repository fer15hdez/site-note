package cursoSpringBoot.domain;

public record ProductResponseDTO(
        Integer serial,
        String name,
        Double price,
        Integer stock
        ) {
}
