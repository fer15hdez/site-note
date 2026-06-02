package cursoSpringBoot.service;

import cursoSpringBoot.domain.*;
import cursoSpringBoot.domain.Specification.ProductSpecification;
import cursoSpringBoot.exceptions.DeleteEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceBoualiali {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceBoualiali(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /*public void createRandomProducts(){
        Faker faker = new Faker();
    }*/

    public ProductResponseDTO createResponseDtoProduct( ProductRecordDto productDto ) {
        var product = productMapper.toProduct(productDto);
        var saveProduct = productRepository.save(product); // Inserta el producto en la bd.

        return productMapper.toProductResponseDTO(saveProduct); // Convierte el Product guardado en el ProductResponseDTO.
    }
    // Crea el Product usando el patron DTO.
    public Product createDtoProduct(ProductRecordDto productRecordDto) {
        var product = this.productMapper.toProduct(productRecordDto);

        return this.productRepository.save(product);

    }

    // Crea el Product. Aqui no se hace uso del patron DTO.
    // Solo para ver su funcinamiento
    public Product createProduct( Product product ){
        return this.productRepository.save(product); // Inserta el producto en la bd.

    }

     // Se le pasa un objeto con los datos que se van a actualizar y se incluye el ID
    // de la entidad. Spring se encarga de buscar la entidad y por ID y actualizar los campos.  
    @PutMapping("/update")
    public ProductResponseDTO updateProduct(@RequestBody ProductRecordDto productDto){
        Product product = this.productMapper.toProduct(productDto);
        return this.productMapper.toProductResponseDTO(this.productRepository.save(product));
    }

    public Product getProduct( Integer id) {
        return this.productRepository.findById(id)
                .orElse(null);
    }

    public List<ProductResponseDTO> findAllProducts() {
        return this.productRepository.
                findAll()
                .stream() // Aplica a cada elemento de la lista que devuelve
                // Convierte cada elemento a ProductResponseDTO.
                // Es una forma simplificada de escribir una expresión lambda para llamar a un método
                .map(productMapper::toProductResponseDTO) 
                .collect(Collectors.toList());

    }

/*    public List<Product> findAllProductsByName( String name){
        return this.productRepository.findAllByNameContaining(name);
    }
*/
    public List<Product> findAllProductsStartWith(String name){
        return this.productRepository.findAllByNameStartingWithIgnoreCase(name);
    }

    public void deleteProduct( Integer id ){
        this.productRepository.deleteById(id);
    }

    // Elimina una entidad y si no existe lanza una excepcion.
    public void deleteProductHandleException(Integer id){
        this.productRepository.findById(id).orElseThrow(
                // Recibe una lambda function como parametro.
                // Se llama a la excepcion personalizada (DeleteEntityNotFoundException) que
                // recibe un el texto de respuesta.
                // Puede ser una brecha de serguridad exponer la info al user final
                () -> new DeleteEntityNotFoundException("Not found entity with id: " + id)
        );

        this.productRepository.deleteById(id);

    }

    public void deleteProductHandleEntityNotFoundException(Integer id){
        this.productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity Not found. ID: " + id)
        );

        this.productRepository.deleteById(id);
    }

    // Para hacer consultas complejas
    // Usando Specification tool
    public List<Product> findAllProductByNameAndStock(String name, Integer stock){
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasStock(stock)) // Se llama a la clase ProductSpecification para usar las
                                                             // las consultas precredas.
                .or(ProductSpecification.nameLike(name)) // Se pueden usar varias consultas
                ;

        return productRepository.findAll(specification); // El metodo findAll() recibe como parametro un Specification type o null
    }

//    PAGINACION
    public Page<Product> listProductPaginados(Pageable pageable){

        return this.productRepository.findAll(pageable);
    }
}
