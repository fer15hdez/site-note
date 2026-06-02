package cursoSpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRecord(
         Integer id,
         String name,
         Double price,
         Integer stock
) {


}
