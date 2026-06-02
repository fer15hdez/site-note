package cursoSpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue // Genera el valor de la id.
    private Integer id;
    private String nombre;
    private String description;

    @OneToMany(mappedBy = "category", // Indica que la relación es bidireccional
                                      //y que el mapeo de la relación se encuentra en el campo "category" de
                                      // la entidad Product.
            cascade = CascadeType.ALL, // especifica que las operaciones de persistencia,
                                        // actualización y eliminación realizadas en la entidad Autor
                                        // se propagarán a las entidades relacionadas Libro
            orphanRemoval = true // Indica que si se elimina una Product de la lista de
                                // products de una Category, ese product también se eliminará de la base de datos.
    )
    @JsonManagedReference // Evita que se cree un loop entre la entidad padre-hijo (Category-Product).
    // Se debe poner la anotacion @JsonBackReference en el campo (category) de Product que crea el link.
    private List<Product> products;

    /*public Category(String nombre, String description, List<Product> products) {
        this.nombre = nombre;
        this.description = description;
        this.products = products;
    }*/

    public Category(String nombre, String description) {
        this.nombre = nombre;
        this.description = description;
    }

   /* public Category() {
    }*/

    /*public Category(String nombre) {
        this.nombre = nombre;
    }*/

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/
}