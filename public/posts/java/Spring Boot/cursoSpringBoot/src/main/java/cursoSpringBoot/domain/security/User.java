package cursoSpringBoot.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cursoSpringBoot.validation.ExistsByUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
// El nombre de la tabla debe ser diferente de 'user', esta es una palabra reservada de SQL
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExistsByUsername
    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 12)
    private String username;
    @NotBlank
    // Solo permite insertar valor. Como medida de seguridad no muestra el valor del
    // campo cuando se devuelve la entidad en formato json.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // Otra alternativa para ignorar el campo cuando devuelve la entidad. El problema es que esta notacion excluye el
    // el valor tanto para escribir como para leer, entoces cuando creas un usuario nunca recibe el valor password.
//    @JsonIgnore
    private String password;

    // Significa que no es un campo para persistir, es solo un campo de la clase.
    @Transient
    private boolean admin;

    private boolean enabled;

    @PrePersist
    public void prePersist(){
        enabled = true;
    }

    // Evita la serializacion cicliquica en la relacion ManyToMany
    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            // Hace que los valores sean unicos en la tabla de union
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;
}
