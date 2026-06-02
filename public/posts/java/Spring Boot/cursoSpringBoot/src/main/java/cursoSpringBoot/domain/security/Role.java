package cursoSpringBoot.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Roles")
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    // No es necesario tener una relacion ManyToMany bidireccional para con los roles. Solo se hizo para
    // mostrar el uso de @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"}).
    // Esto evita la descerializacion ciclica.
    @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
