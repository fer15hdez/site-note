package cursoSpringBoot.service;

import cursoSpringBoot.domain.Category;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.domain.ProductRecordDto;
import cursoSpringBoot.domain.ProductResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    // Para convertir un ProductoDTO en un product entity.
    // Patron DTO (Crea una capa de abstraccion en el acceso a la entidad)
    // Se pueden crear diferentes metodos con diferentes tipos de datos para exponer.
    // La finalidad de este patron es exponer solo los datos que sean necesarios exponer.
    public Product toProduct(ProductRecordDto productDto){
        if(productDto == null) {
            throw new NullPointerException("The Product Dto should not be null");
        }

        var product = new Product();

        // Es necesario crear un objeto de tipo Category utilizando el ID que se pasa
        // con el productDto, sino da problemas en la deserializacion.
        var category = new Category();
        category.setId(productDto.categoryId());

        product.setSerial(productDto.serial());
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setStock(productDto.stock());
        product.setSome_colum(productDto.some_colum());
        product.setImages(productDto.images());
        product.setCategory(category);


        return product;
    }

    // Utilizando la entidad Producto, permite devolver solo los datos deseados.
    public ProductResponseDTO toProductResponseDTO(Product product){
        return new ProductResponseDTO(
                product.getSerial(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
