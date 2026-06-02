package cursoSpringBoot.domain;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Se le pasa a JpaRepository<Name_entity, data_type_pk>
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    //Creando consultas

    Product findByName(String name);
    List<Product> findAllByName(String name);
    List<Product> findAllByNameIgnoreCase(String name);

    List<Product> findAllByNameContaining(String name);

    // select * from Product where name like '%param%'
    // IgnoreCase: Indica que no tiene en cuenta las mayusculas y minusculas
    List<Product> findAllByNameContainingIgnoreCase(String name);

    // Select * from Product where name like 'start%'
    List<Product> findAllByNameStartingWithIgnoreCase(String name);

    // Select * from Product where name like '%end'
//    List<Product> findAllByNameEndWithIgnoreCase(String name);

    // Select * from Product where name in ('ali', 'buu', 'more')
//    List<Product> findAllByNameInIgnoreCase(List<String> names);

//    Update product
    @Modifying // La notacion @Query no soporta la sentencia update para que hacer que funcione debe incluir la
//    notacion @Modifying
    @Transactional
    @Query("update Product p set p.name = :name where p.id = :id ") // No soporta la query update. Debe tener las notaciones
//    @Modifying, @Transactional.
    int updateproduct(String name, Integer id);

    @Modifying // La notacion @Query no soporta la sentencia update para que hacer que funcione debe incluir la
//    notacion @Modifying
    @Transactional
    @Query("update Product p set p.name = :name") // No soporta la query update. Debe tener las notaciones
//    @Modifying, @Transactional.
    void updateAllProduct(String name);

//    Consulta creada en la entidad Producto con notacion @NamedQuery
    @Transactional
    List<Product> findByNameQuery(@Param("stock") Integer stock);
}
