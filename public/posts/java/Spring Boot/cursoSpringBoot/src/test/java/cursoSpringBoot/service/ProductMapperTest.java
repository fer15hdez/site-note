package cursoSpringBoot.service;

import cursoSpringBoot.domain.Images;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.domain.ProductRecordDto;
import cursoSpringBoot.domain.ProductResponseDTO;
import jakarta.validation.constraints.NotEmpty;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
private ProductMapper mapper;

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll: My first test");
    }

    // Este metodo se ejecuta ANTES que todos los metodos de test.
    // Se usa para inicializar parametros.
    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach: My first test");
        mapper = new ProductMapper();

    }

    // Este metodo se ejecuta DESPUES de los metodos de los test.
    // Normalmente se usa para resetear valores de variables, etc.
    @AfterEach
    void tearDown() {
        System.out.println("The AfterEach");
    }

    // Se ejecuta DESPUES de todos los metodos.
    // Normalmente se usa para eliminar datos que se hallan insertados para los test, etc.
    @AfterAll
    static void afterAll() {
        System.out.println("The AfterAll");
    }

    @Test
    public void testMethod1() {
        System.out.println("My first test");
    }

    @Test
    public void testMethod2() {
        System.out.println("My second test");
    }

    @Test
    public void shouldMapProductDtoToProduct() {
        ProductRecordDto productDto = new ProductRecordDto( 55,
                "Phone",
                99.9,
                100,
                "some_colum",
                new Images(),
                304);
        Product product = mapper.toProduct(productDto);

        assertEquals(productDto.name(), product.getName());
        assertEquals(productDto.serial(), product.getSerial());
        assertEquals(productDto.categoryId(), product.getCategory().getId());
        assertNotNull(product.getCategory().getId());

    }

    // Este test verifica que si se envia un null como parametro al metodo "mapper.toProduct" entonces el metodo lance una excepcion
    @Test
    public void should_throw_null_pointer_exception_when_productDto_isNull() {
        var exp = assertThrows(NullPointerException.class, () -> mapper.toProduct(null));

        // Comprueba que los dos mensajes sean iguales.
        assertEquals("The Product Dto should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapProductToResponseProductDto() {
        Product product = new Product(
                1,
                "Tablet",
                99.9,
                10,
                "Una columna");

        ProductResponseDTO responseDTO = mapper.toProductResponseDTO(product);

        assertEquals(responseDTO.name(), product.getName());
        assertEquals(responseDTO.serial(), product.getSerial());
    }


}