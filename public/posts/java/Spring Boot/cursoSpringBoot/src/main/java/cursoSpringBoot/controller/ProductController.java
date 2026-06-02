package cursoSpringBoot.controller;

import cursoSpringBoot.domain.*;
import cursoSpringBoot.error.validator.ProductValidator;
import cursoSpringBoot.service.ProductMapper;
import cursoSpringBoot.service.ProductService;
import cursoSpringBoot.service.ProductServiceBoualiali;
import cursoSpringBoot.service.ProductServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos") // This is the prefix to all the url on this class.
public class ProductController {

    private final ProductServiceBoualiali productServiceBoualiali;
    // Para llamar a las validaciones personalizadas
    private final ProductValidator validator;


    //ProductService productService = new ProductServiceImpl();
    // @Autowired // Esta anotacion es la encargada de crear la injeccion de dependencia.
    private ProductService  productService;
    private ProductServiceImpl productServiceImp;

    public ProductController(ProductServiceBoualiali productServiceBoualiali, ProductValidator validator) {
        this.productServiceBoualiali = productServiceBoualiali;
        this.validator = validator;
    }

//    Paginacion
    @GetMapping("/db/prouducts/pageable")
    public ResponseEntity<Page<Product>> listProductPaginados(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size  ){

        PageRequest pageRequest = PageRequest.of(page, size); // PageRequest implementa la interfaz Pageable
        Page<Product> productosPaginados = productServiceBoualiali.listProductPaginados(pageRequest);
        return ResponseEntity.ok(productosPaginados);

    }



    @PostMapping("/db/dto")
    public Product createDtoProduct(@Valid @RequestBody ProductRecordDto productRecordDto){

        return this.productServiceBoualiali.createDtoProduct(productRecordDto); // Inserta el producto en la bd.
    }

    @PostMapping("/db/dto/response")
    public ProductResponseDTO createDtoResponseProduct( // Una vez creada la entidad permite solo devolver los datos deseados.
            @RequestBody ProductRecordDto productDto
    ){
        return this.productServiceBoualiali.createResponseDtoProduct(productDto); // Inserta el producto en la bd.
    }

    @GetMapping("/start/with/{name}")
    public List<Product> startNameWith(@PathVariable("name") String name){
        return this.productServiceBoualiali.findAllProductsStartWith(name);
    }

    @GetMapping
    // Signo ? permite devolver cualquier tipo clase.
    public ResponseEntity<?> getProducts(){
        List<Product> products = productServiceImp.getProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{tell-story}")
    public String tellStoryService(@PathVariable("tell-story") String tellStory) {
        return "My tell story value: "  + tellStory;
    }

    @PostMapping("/db")
    // El orden de los parametros tiene que ser: @Valid, la entidad y BindingResult, para validar y capturar los errores.
    // BindingResult captura todos los error que estan por default en la entidad(ej. @NotBlank, @NotEmpty, etc).
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result){
        this.validator.validate(product, result);
        if (result.hasFieldErrors()){
            return validation(result);
        }
        // URI: Identifica al recurso
       /* URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/db/{id}") // El parametro debe ser la url que permite leer estos recursos
                .buildAndExpand(product.getId()) // Se encarga de insertar el valor que se da en la url y se inserta en path()
                .toUri(); // Construye la URI
        ResponseEntity.created(location).build(); // No devuelve info.
        ResponseEntity.created(location).body(product); // Devuelve toda la info del recurso creado.
*/
        // Inserta el producto en la bd y devuelve el ResponseEntity.
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productServiceBoualiali.createProduct(product));

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        //no esta validando los @NotBlank  @Size(min = 5, max = 20) para mostrar en el postman, solo los valida como error 500
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("/db/{id}")
    public Product getProduct(@PathVariable("id") Integer id){
        return this.productServiceBoualiali.getProduct(id);
    }

    @GetMapping("/db")
    public List<ProductResponseDTO> findAllProducts(){
        return this.productServiceBoualiali.findAllProducts();
    }
 /*   @GetMapping("/db/search/{name}")
    public List<Product> findAllProductsByName(@PathVariable("name") String name){
        return this.productServiceBoualiali.findAllProductsByName(name);
    }*/

    @DeleteMapping("/db/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(
            @PathVariable("id") Integer id
    ){
        this.productServiceBoualiali.deleteProduct(id);
    }

    @DeleteMapping("/db/del/exception/{id}")
    public void deleteProductHandleException(@PathVariable Integer id){
        this.productServiceBoualiali.deleteProductHandleException(id);
    }

    @DeleteMapping("/db/del/notfound/exception/{id}")
    public void deleteProductNotFoundException(@PathVariable Integer id){
        this.productServiceBoualiali.deleteProductHandleEntityNotFoundException(id);
    }

    // Este metodo maneja las excepciones del controlador.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException (
            MethodArgumentNotValidException  exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/db/find/product/{name}/{stock}")
    public List<Product> findAllProductByNameAndStock(
            @PathVariable("name") String name,
            @PathVariable("stock") Integer stock
                                                      ){
        return this.productServiceBoualiali.findAllProductByNameAndStock(name, stock);
    }


}
