package cursoSpringBoot.service;

import cursoSpringBoot.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceBoualialiTest {

    // Service to test
    @InjectMocks
    private ProductServiceBoualiali productService;

    // Dependencies
    @Mock
    private  ProductRepository productRepository;
    @Mock
    private  ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba el metodo de insertar un producto "createResponseDtoProduct"
    @Test
    public void should_successfully_save_a_product() {
        // Given
        ProductRecordDto productDto = new ProductRecordDto( 55,
                "Phone",
                99.9,
                100,
                "some_colum",
                new Images(),
                304);

        Product product = new Product(
                55,
                "Phone",
                99.9,
                100,
                "some_colum"
                );
        Product saveProduct = new Product(
                55,
                "Phone",
                99.9,
                100,
                "some_colum"
        );
        saveProduct.setId(1);

        // Mock calls
        Mockito.when(productMapper.toProduct(productDto)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(saveProduct);
        Mockito.when(productMapper.toProductResponseDTO(saveProduct))
                .thenReturn(new ProductResponseDTO(
                        55,
                        "Phone",
                        99.9,
                        10));
        // When
        ProductResponseDTO productResponseDTO = productService.createResponseDtoProduct(productDto);
        // Then
        assertEquals(productDto.serial(), productResponseDTO.serial());
        assertEquals(productDto.name(), productResponseDTO.name());
        assertEquals(productDto.price(), productResponseDTO.price());

        // Verificar que se ejecuta una sola vez las lineas de código que participan en la insercion
        Mockito.verify(productMapper, Mockito.times(1)).toProduct(productDto);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
        Mockito.verify(productMapper, Mockito.times(1)).toProductResponseDTO(saveProduct);

    }

    @Test
    public void should_return_all_product() {
        // Given
        List<Product> products = new ArrayList<>();
        products.add(
                new Product(
                        55,
                        "Phone",
                        99.9,
                        100,
                        "some_colum"
                )
        );

        // Mock the calls
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productMapper.toProductResponseDTO(ArgumentMatchers.any(Product.class)))
                .thenReturn(new ProductResponseDTO(
                        1,
                        "Phone",
                        99.9,
                        10
                ));
        // When
        // Then

    }
}