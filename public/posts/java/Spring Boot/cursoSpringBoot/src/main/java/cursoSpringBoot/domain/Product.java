package cursoSpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;


@Data // Incluye las anotaciones @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Builder // ??Permite crear y diseñar objetos utilizando el patron de diseño Builder???
@Entity // Especifica que es esta clase es una entidad.
//@Setter
//@Getter
@NoArgsConstructor // Crea el constructor sin parametros
@AllArgsConstructor // Crea el constructor con todos los parametros
@Table(name = "T_PRODUCT") // Permite definir un nombre para la tabla. Si no se define la anotacion el nombre que adopta
// es el nombre de la clase (ej. Product). El valor de la propiedad "ddl-auto: update" está en la configuracion del proyecto
// creará una nueva tabla, si es 'create' solo se sobreescribe.

// Permite crear una consulta. Luego en el ProductRepository se usa: List<Product> findByNameQuery(@Param("stock") Integer stock);
@NamedQuery(
        name = "Product.findByNameQuery",
        query = "select a from Product a where a.stock <= :stock"
)
public class Product {

    @JsonProperty("id")
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence" // Es el nombre del secuenciador (se define con la notacion @SequenceGenerator)
    ) // Solo se debe usar en Primary Key. Genera el valor incremental.
    @SequenceGenerator(
            name = "product_sequence", // Debe ser igual al valor del 'generator' en @GeneratedValue.
            sequenceName = "product_sequence",
            allocationSize = 1, // Es el valor que incrementa en el id.
            initialValue = 5 // Es el valor donde comienza la generacion del valor
    )
    private Integer id;

    @Column(unique = true) // Configura el campo como un valor unico dentro de la BD.
    private Integer serial;



    @JsonProperty("c-name") //Permite crear un valor personalizado para la deserialization. Este valor
    //es el que se debe enviar desde el cliente. Is case sensitive
    @Column(
            name = "c_name", // Define el nombre del campo en la DB. Si no se define se toma como valor el nombre del campo.
            length = 20 // define el número de caracteres que va a tener el campo
    )

//    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;
    @JsonProperty("c-price")
    private Double price;
    @JsonProperty("c-stock")
    private Integer stock;
    @Column(updatable = false)
    private String some_colum;
    @Column(updatable = false) // No permite que el valor se actualice.
    private LocalDateTime createAt;
    @Column(insertable = false) // Solo permite actualizar el valor.
    private LocalDateTime lastModified;

    //Relacion uno a uno
    @OneToOne(mappedBy = "product" // Este valor debe ser igual al campo de la entidad de la relacion.
            //cascade = CascadeType.ALL
    )
    private Images images;

    @ManyToOne
    // En esta relacion siempre debe estar la notacion @JoinColumn para definir el campo
    // que identifica la relacion.
    @JoinColumn(name = "category_id") // Esta es la columna que se crea en la tabla
    // para hacer refencia al campo "category".
    @JsonBackReference // Evita que se cree un loop entre la entidad padre-hijo (Category-Product).
    // Se debe poner la anotacion @JsonManagedReference en el campo (category) de Product que crea el link.
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "bar_code_id") // Esta columna se crea en la tabla para hacer referencia al campo 'barCode'
    private BarCode barCode;

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, Double price, Integer stock, String some_colum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.some_colum = some_colum;
    }


}
