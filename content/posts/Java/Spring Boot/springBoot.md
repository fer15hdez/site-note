# JPA: 
 - Es una API de java.
 - Java Persistence API, más conocida por sus siglas JPA, es una API de persistencia desarrollada para la plataforma Java EE. 
   Maneja datos relacionales en aplicaciones usando la Plataforma Java en sus ediciones Standard y Enterprise.
 - Proporciona un mecanismo para gestionar la persistencia y la correlación relacional de objetos y las funciones desde las especificaciones EJB 3.0 .    

# HIBERNATE (ORM)
 - Hibernate es una herramienta de mapeo objeto-relacional para la plataforma Java que facilita el mapeo de atributos entre una base de 
   datos relacional tradicional y el modelo de objetos de una aplicación.
 - Is a powerful object-relational mapping (ORM) framework for Java that simplifies the process of interacting with relational databases. 
   It automatically maps Java objects to database tables, eliminating the need for manual SQL queries and providing a more object-oriented 
   approach to data persistence.
 - Hibernate entonces es un **ORM que implementa JPA**. Como usaremos las interfaces de JPA, no habrá ninguna referencia directa en nuestro código a Hibernate.

# Structure of project
Controler: Is the layer(capa) of presentation. In this layer is where are the endpoint.    
Service: Is the layer of business logic.  
Domain (Dominio): Is this the layer where we place the POJO classes, meaning this is where our domain resides.  
Test: The place where the tests are. 

====================================
# Banner
Se crea un archivo banner.txt en la carpeta resources


====================================
# Configuration

**@Configuration** : Con esta anotacion en una clase le indica a Spring Boot que esta clase se usa para definir configuraciones.  
- Es buena practica crear una clase aparte a la clase principal de Spring Boot para definir configuraciones.  


**Application file**

  **ddl-auto property**
   - create – Hibernate first drops existing tables, then creates new tables
   - update – the object model created based on the mappings (annotations or XML) is compared with the existing schema, and then Hibernate 
     updates the schema according to the diff. It never deletes the existing tables or columns even if they are no more required by the application.
   - create-drop – similar to create, with the addition that Hibernate will drop the database after all operations are completed. Typically used 
     for unit testing.
   - validate – Hibernate only validates whether the tables and columns exist, otherwise it throws an exception
      none – this value effectively turns off the DDL generation


 ### Property
 **@PropertySource("classpath:fileProperty.name")**: Permite definir un nuevo archivo donde se definan las nuevas propiedades del proyecto.    
 - La anotacion se define en la clase principal de Spring (donde esta la anotacion @SpringBootApplication) y esta disponible en toda la app.
 - El archivo debe estar en la carpeta resource.  
 **@PropertySources**: Permite definir multiples archivos de configuracion.  
 @PropertySources({
  *La opcion (encoding = "UTF-8") permite poner incorporar caracteres especiales(ñ, vocales con tilde)*
    @PropertySource("value = classpath:fileProperty.name", encoding = "UTF-8"),
    @PropertySource("classpath:fileProperty.name1"),
 })

 **@Value("${name.property}")**: 
  - Permite asociar el valor de la propiedad al campo de una clase, Parámetros de constructores y en valor de parametros deMétodos 
  en un servicio u otro componente.
  - Spring Boot busca dentro de los archivos definido como archivos de configuracion (ej. mycustom.properties).  

  - Se puede tener varios archivos de configuracion de tipo "application.yml" o de tipo "application.propeties".  
  - Configuracion de las dependencias principales
  dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web' // Basico para la web.
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa' // Para base de datos.  
    implementation 'org.springframework.boot:spring-boot-starter-validation' // Para  la validadcion de datos en la database.  
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    runtimeOnly 'org.postgresql:postgresql'

    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
====================================

## Bean

Los **Bean** son simplemente objetos que son instanciado, configurado y administrado por el contenedor de Spring. 
  - Estos beans son los componentes fundamentales de una aplicación Spring, ya que representan cualquier cosa, desde servicios y 
  repositorios hasta controladores web. (ej. @service, @RestController, etc). 
  - **The @Bean annotation is used to declare methods that return objects to be managed by the Spring container.**     
**@Bean("nameOfBean")**: Permite agragar un nombre al Bean y con la anotacion @Qualifier("nameOfBean") llamar al Bean deseado.  
- Es la forma que utiliza Spring Boot para denotar los componentes y asi poder utilizarlo en la inyeccion de dependencia.  
- La inyeccion de dependencia de spring boot es sinonimo de la dependiencia entre clases, pero para desaclopar el codigo y para que se encargue Spring Boot
se usan los Bean.  
**@Component**: Se aplica directamente a una clase para marcarla como un bean.  
  - En la clase donde se declare, esta es considerada como un componente de Spring. 
  - Las clase declaradas @Component son inicialidazas, configuradas y gestionadas por Spring. 
  - Spring gestiona la inyeccion de dependencia de la clase que se le aplico la notacion Component.  
  - Las anotaciones @Repository, @Service y @Controller son meta-anotaciones de @Component. Spring las trata como un Component. 
**@service** : Este decorador le indica a spring boot que debe tratar esta clase como un servicio, de esta 
forma spring gestionara automaticamente la creacion de esta clase cuando sea necesario.  
**@Autowired** : Esta anotacion es la encargada de crear la injeccion de dependencia.  Tambien llamada Field Injection, se 
recomienda hacer Contructor Injection.  
**@Qualifier**: Esta anotacion permite inyectar un Bean especifico, que pueden ser mas de un metodo dentro de la clase AppicationConfig y 
  despues poderlo especificar desde la llamada de un servicio, etc.  
 **@Primary**: Permite determinar una prioridad donde existan mas de un Bean.  

 ### Inyeccion de dependencias (Bean)
 1- Constructor Injection -> Practica recomenda.  
 2- Field Injection  
 3- Setter Injection  
 4- Configuration (Configuration Methods)
 5- Methods Injection.

### Bean SCOPING
1- Singleton: es el default scope of a Bean. Solo se crea una instancia del Bean solicitado.
2- Prototype
3- Request: Se crea un nuevo Bean por cada request que se realiza.  
4- Session: Solo es valido en la sesion http.  
5- Application
6- Websocket: Solo esta disponible en el nivel de websocket.  

**@Scope("prototype")**: Permite definir el scope del Bean. 
**@SessionScope**: Otra forma de definir el scope del Bean.  

====================================
  # PROFILES
  Los perfiles permiten hacer configuraciones para diferentes escenarios, ej. produccion, desarrollo, etc.  

  - Cuando se definen mas de un perfil, Spring Boot busca primero las propiedades en el archivo "application.properties" y 
  despues en los perfiles activos. Si una de las propiedades en los perfiles activos es similar a la de "application.properties" la sobrescribe.  
  **spring.profiles.active=dev,prod**: Esta caracteristica permite en el archivo "application.properties" definir cuales perfiles estan activos.  
  Si existe una misma propiedad en diferentes perfiles Spring Boot sobreescribe las anteriores y usa el valor del ultimo profile activo.  

  **@Profile("nameOfProfile")**: Permite definir para cual profile va estar disponible el Bean. Se definen en la clase de configuracion.  
   
====================================
# REST
## CONTROLLER

**@RestController**: Le dice a spring que debe tomar esta clase como un contralador.  
**@ResponseStatus(HttpStatus.ACCEPTED)**: Si se ejecuta sin excepciones devuelve el http method 'ACCEPTED'.  
**@GetMapping**: Identifica al EndPoint que va a responder ante una peticion de tipo GET.  GetMapping("/path"), se puede definir una url para 
acceder al EndPoint.  

- Los getter and setter son necesarios para la serialitation y el proceso inverso. Permiten el acceso a los campos privados de las clases.  

**@PathVariable**: Permite pasar un valor por la url. El valor de la url debe ser igual que el nombre del parametro.  
- url: "/hola/fernando"
```java
  @GetMapping({"/hello/{name}", "/hola/{name}"})
  public String greeting(@PathVariable String name){}  
```
  

**@RequestParam("user-name")**:  Permite especificar el valor que se pasa por parametro en la url. 
  http://localhost:8080/sistema/api/v1/clientes/param?user-name=fernan&last-name=vel

- Toda la logica se debe hacer en las clases servicios y los controllers solo para ser la puerta de entrada de las resquest.  

### URI
- URI: Identifica al recurso

## PAGINATION
- Paquetes a importar
` import org.springframework.data.domain.Page;`
` import org.springframework.data.domain.Pageable;`

- En el controller
```java
  @GetMapping("/db/prouducts/pageable")
    public ResponseEntity<Page<Product>> listProductPaginados(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size  ){

        PageRequest pageRequest = PageRequest.of(page, size); // PageRequest implementa la interfaz Pageable
        Page<Product> productosPaginados = productServiceBoualiali.listProductPaginados(pageRequest);
        return ResponseEntity.ok(productosPaginados);

    }
```    
- En el service
```java
public Page<Product> listProductPaginados(Pageable pageable){

        return this.productRepository.findAll(pageable);
    }    
```  

====================================
# DATA BASE

**@Entity**: Define la clase como una entidad.  
**@Table(name = "T_PRODUCT")**: Permite definir un nombre para la tabla. Sino se define la anotacion el nombre que adopta
  es el nombre de la clase (ej. Product). El valor de la propiedad "ddl-auto: update" esta en la configuracion del proyecto
  creara una nueva tabla, si es create solo se sobre escribe.  
**@Id**: Define el campo como pk e identificador en la tabla. Es recomendable usar wrapper (envoltura, ej. Integer), si se usan tipos primitivos (ej. int) el valor inicial es cero
  e hibernate tratara buscar un elemento en la tabla con ese valor, sin embargo cuando apunta a null hibernate intenta insertar el nuevo valor.  
**@GeneratedValue**: Se autoincrementa el valor. Solo se debe usar en Primary Key. No permite el uso en pk compuestas.
  El valor por defecto es AUTO. Se puede definir una estrategia de generacion del id.    
**@Column(unique = true)**: Configura el campo como un valor unico dentro de la BD.  
Column(
            name = "c_name", // Define el nombre del campo en la DB. Sino se define se toma como valor el  nombre del campo.
            length = 20 // define el numero de caracteres que va a tener el campo
            updatable = false // Define si el valor se puede actualizar o no.  
    )

**@Transient**: Especifica que no es un campo para persistir, es solo un campo de la clase.
(ej. @Transient
    private boolean admin;)

- Para hacer las consultas Se crea una interfaz que extiende de "JpaRepository<Product, Integer>". Para mejor organizacion el archivo debe nombrarse "NameEntityRepository".  
- Se le pasa a JpaRepository<Name_entity, data_type_pk>. (Nombre de la entidad y el tipo de datos de la llave primaria).  
- Para usar el recurso se crea una referencia de la interfaz "**private final ProductRepository productRepository;**".  
- Para insertar "**return productRepository.save(product);**"
- Delete: productRepository.deleteById(id). Con la notacion @DeleteMapping se define el controlador.  
- Para crear una consulta especifica (Ej. productRepository.findAllByNameContaining(name);), se el metodo en la clase Repository con el ?sufijo Containing?  

### Lombok dependecies (
  compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
)
 - `@Data`: Incluye las anotaciones `@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode`.  
 - `@Setter`
 - `@Getter`
 - `@NoArgsConstructor`: Crea el constructor sin parametros.  
 - `@AllArgsConstructor`: Crea el constructor con todos los parametros.  
 - `@Builder`:  Permite crear y diseannar objetos utilizando el patron de disenno Builder.  
 - En las entidades es necesario crear un constructor vacio.  

### Relationships
 - `@ManyToMany`: Se define cual de las dos entidades va ser la duenna de la relacion y en esa entidad se ponen las anotaciones principales. 
  - Se crea un atributo de tipo lista en cada una de las entidades que apunta hacia la otra entidad, esto permite que sea bidireccional.
  - Estas configuraciones van en la tabla duenno:
    ```java
    @JoinTable(
                name = "order_product",
                joinColumns = { @JoinColumn(name = "order_id")},/* Define la columna dentro de la tabla de union de la
                                                                    tabla duenna */
                inverseJoinColumns = { @JoinColumn(name = "product_id")} /* Define la columna de la otra tabla en la relacion
                                                                            en la tabla de union.*/
        )
    ```

- `@ManyToOne`: En esta relacion siempre debe estar la notacion `@JoinColumn` para definir el campo
                que identifica la relacion.
                `@JoinColumn(name = "category_id")` Esta es la columna que se crea en la tabla
                para hacer refencia al campo "category".
  - Para que la relacion sea bidireccional debe tener:
    ```java
      @OneToMany(mappedBy = "category", // Indica que la relación es bidireccional
                                        // y que el mapeo de la relación se encuentra en el campo "category" de
                                        // la entidad Product.
              cascade = CascadeType.ALL, // Especifica que las operaciones de persistencia,
                                         // actualización y eliminación realizadas en la entidad Autor
                                         // se propagarán a las entidades relacionadas Libro
        )
    ```
      
  - De la parte del MUCHOS debe tener una lista de la otra entidad. 
  - En la entidad de UNO debe tener un atributo que sea del tipo de la otra entidad
  - Cuando se usan el patron DTO y los metodos "toNameEntity(entityDTO)", en la clase mappeer, para convertir el DTO en la entidad se debe crear en el DTO el campo Integer que representa la relacion (no del tipo de la entidad de la relacion) y en el metodo "toNameEntity(entityDTO)" se crea un objeto de la entidad que participa en la relacion utilizando el id que proporciona el DTO que se pasa por parametro y se utiliza para pasarlo al campo de la relacion (.setRelationNameEntity(entityRelation)).  
    **(Si no se hace da el error: "(although at least one Creator exists): no int/Int-argument constructor/factory method to deserialize from Number value")**
  - La unica solucion que encontre para la funcion update, en este tipo de relacion, es crear un constructor en la entidad OneToMany donde se cree solo con el parametro
    de ID (ej. public RoomType(Integer id){
        this.id = id;
    })
    
 - `@OneToOne`: @JoinColumn(name = "bar_code_id") // Esta columna se crea en la tabla para hacer referencia al campo 'barCode'.  
    - Para que la relacion sea bidireccionaal debe tener un campo en ambas tablas que identifique a la otra tabla.  
    `@JoinColumn(name = "product_id")` // Esta columna se crea en la tabla para hacer referencia al campo 'product'.  

####  Deserializacion Ciclica     
- Una solucion para evitar la **deserializacion ciclica** en el ManyToMany es crear un metodo toString() en la entidad
  no controladora de la relacion sin mostrar la lista de la otra entidad.
- Otra solucion para evitar la **deserializacion ciclica** en el ManyToMany
    `@JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})`
    `private List<Role> roles;`
    - Le decimos que cuando convierta la entidad en json que ignore el campo "users" en la entidad Role.  
    - Estos "handler", "hibernateLazyInitializer" los puede generar spring como parte del proceso de descerializacion y podrian dar error,
    por eso quizas sea una opcion incluirlos en las propiedades para ignorar.  
- `@JsonBackReference` esta va donde se define el `@JoinTable` de la relacion       
  ```java
    @ManyToMany(mappedBy = "areas")
    @JsonManagedReference // Esta va donde se define el "mappedBy"
    // @JsonIgnoreProperties({"coaches", "handler", "hibernateLazyInitializer"})
    private List<Coach> coaches;
  ```


### HERENCIA

**Clase Padre**
`@Data`
`@NoArgsConstructor`
`@AllArgsConstructor`
`@SuperBuilder` // Esta propiedad esta en modo experimental
`@MappedSuperclass` // Identifica la clase como una superclase. Esta clase solo va a estar en el codigo,
                  // no se crea una tabla. Tampoco se puede insertar esta clase en la DB, ni se pueden hacer consultas.  

En la **clase hijas** solo se extiende de la clase padre y ponen las siguientes anotaciones:
```java
    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder // Esta propiedad esta en modo experimental
    @Entity 
```
                   

#### Estrategia de Herencia
  `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`:  SINGLE_TABLE strategy crea una sola tabla con los atributos de
    las clases hijas y los de la clase padre. 
    - El nombre de la tabla es el de la clase padre.
    - La notacion se declara en la clase padre.  
    - En la tabla, en la base de datos, se crea un campo 'dtype' que identifica a que tabla pertenecen los datos insertados. 
    - Para cambiar el nombre del campo 'dtype' se usa la notacion @DiscriminatorValue("L"). Cuando se inserta una nueva fila 
      esta se identifica por la notacion @DiscriminatorValue("L") declarada en la clase hija, para saber a que clase pertenece la insercion.
    - Solo se puede hacer inserciones de los valores de cada clase (si es clase hija, los valores de la clase hija y de la clase padre, si es clase padre solo lo
      de la clase padre), los demas valores toman el valor null.  

**@Inheritance(strategy = InheritanceType.JOINED)**: (Clases en el codigo: Vehicle, Automobile, Truck) 
- Es recomendada para cuando existe un numero alto de subclases.  
- Esta estrategia declara en la PK de la clase hija como llave foranea el id de la clase padre.
- Si se insertan datos para un entidad hija automaticamente se hacen dos insersiones, una en la clase hija con los campos correspondientes y una en la clase padre con los campos pertenecientes a la clase padre.  
- Para cambiar nombre de la llave foranea en la clase hija se usa la notacion @PrimaryKeyJoinColumn(name = "vehicle_id").  

#### Llave Compuesta (clases en el codigo: OrderId, OrderExample)

- Se crea una clase con los atributos que conforman la llave compuesta. Esta lleva la anotacion **@Embeddable**
  Debe implementar la interfaz Serializable (**public class OrderId implements Serializable**).  
  No lleva la anotacion **@Entity**
- En la clase donde se va a usar la llave foranea se crea una instancia de la clase donde se encuentran los atributos definidos (**private OrderId orderId;**)  
  Esta instacia lleva la notacion **@EmbeddedId**.  
  Al ser la entidad que va a persistir en la BD debe llevar la notacion **@Entity**.  

### QUERY IN REPOSITORY

  **CREADNDO CONSULTAS PERSONALIZADAS EN EL REPOSITORIO**  
  **@Modifying** // La notacion @Query no soporta la sentencia update para que hacer que funcione debe incluir la 
//    notacion **@Modifying** y **@Transactional**.   
    **@Transactional**  
    **@Query**("update Product p set p.name = :name where p.id = :id ") // No soporta la query update. Debe tener las notaciones
//    @Modifying, @Transactional.  
    **int updateproduct(String name, Integer id);**.  

// **@NamedQuery** Se crea en la entidad (ej. Producto). Permite crear una consulta. Luego en el ProductRepository se usa: List<Product> findByNameQuery(@Param("stock") Integer stock);
@NamedQuery(
        name = "Product.findByNameQuery", 
        query = "select a from Product a where a.stock <= :stock"
)
//    Consulta creada en la entidad Producto con notacion @NamedQuery
    @Transactional
    List<Product> findByNameQuery(@Param("stock") Integer stock);

**Query Methods**
- **Convenciones de nomenclatura:** Spring Data JPA utiliza un conjunto de convenciones para inferir la lógica de las consultas a partir 
    de los nombres de los métodos en tus repositorios. Por ejemplo, si defines un método findByNombre, Spring Data asumirá que quieres buscar 
    entidades por su atributo "nombre".    
  - Se denominan "Query Methods".
  - Estos metodos se declaran en el repositorio que pertenece a la entidad.
  Los nombres de los métodos generalmente siguen este patrón:
    <findBy>: Indica que se busca por un atributo específico.
    <Nombre del atributo>: El atributo de la entidad por el que se filtrará.
    <Operadores>: Puedes usar operadores como Is, Containing, StartingWith, EndingWith, Before, After, etc., para refinar la búsqueda.

    ej. findByNombre(), indByEdadGreaterThan(), etc.



#### HACER CONSULTAS COMPLEJAS (Specification)
 - Se extiende en la interfaz Repository de JpaSpecificationExecutor. (ej. public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> )
 - Se crea una nueva clase donde se implementan los filtros, consultas. (ej. public class ProductSpecification).  
 - Los metodos donde se implementan los filtros tienen la estructura: 
    public static Specification<Product> hasStock(int stock) {
      return (
                Root<Product> root,
                CriteriaQuery<?> query,
                CriteriaBuilder builder
        ) -> {
            if (stock < 0) {
                return null;
            }
            return builder.equal(root.get("stock"), stock); // "stock" en root.get("stock") debe ser igual al campo 'stock'
                                                            // en la clase Product
        };
    }  
- Donde se usen los metodos que filtran:
    // Usando Specification tool
    public List<Product> findAllProductByNameAndStock(String name, Integer stock){
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasStock(stock)) // Se llama a la clase ProductSpecification para usar las
                                                             // las consultas precredas.
                .or(ProductSpecification.nameLike(name)) // Se pueden usar varias consultas
                ;

        return productRepository.findAll(specification); // Recibe como parametro un Specification type o null
    }

# DTO Pattern
" Es una clase que te separa de manipular directamente las entidades, te permite devolver solo los datos que se necesiten para el cliente. No se devuelven datos incesarios. Aumenta la seguridad.
Permite mayor flexibilidad. "
- Patron DTO (Crea una capa de abstraccion en el acceso a la entidad)
- Se pueden crear diferentes metodos con diferentes tipos de datos para exponer.
- La finalidad de este patron es exponer solo los datos que sean necesarios exponer.

- Al enviar los datos desde el formato de json para insertar una nueva entidad a traves de la clase DTO se deben usar los nombres de los campos de la clase DTO.  

- La clase public record ProductResponseDTO(
        Integer serial,
        String name,
        Double price,
        Integer stock
        ) {
}, 
  junto a los metodos toProductResponseDTO, los implementa el programador, permiten elegir cuales son los datos que se exponen una vez creado la entidad (ej. Producto)
  - Los metodos (toProductResponseDTO, toProduct, etc) se crean en una clase mapper (ej. ProductMapper).

====================================
# VALIDATION
- En las dependencias: "implementation 'org.springframework.boot:spring-boot-starter-validation' " // Para  la validadcion de datos en la database.  

**@Valid**: Esta anotacion valida el valor que le sigue. Utiliza las anotaciones de los campos del objeto a validar (ej. ProductRecordDto). 
    @PostMapping("/db/dto")
    public Product createDtoProduct(@Valid @RequestBody ProductRecordDto productRecordDto){

        return this.productServiceBoualiali.createDtoProduct(productRecordDto); // Inserta el producto en la bd.
    }

- El orden de los parametros tiene que ser: **@Valid, la entidad y BindingResult**, para validar y capturar los errores.
  **BindingResult** captura todos los error que estan por default en la entidad(ej. @NotBlank, @NotEmpty, etc).
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result){

    }
    - El **BindingResult** captura los errores de los datos que se envian. En el caso de la notacion **@Colum(parameter=value)** es
      una notacion que actua cuando se va a insertar en la bd, por lo que el Bindingresult no captura el error.
      Una solucion para que devuelva una respuesta al cliente y no de error 500, es crear una notacion personalizada.  

**@NotEmpty(message = "This field must be filled")**: Permite validar el campo del objeto.      

### Crear una anotacion personalizada

- Se crea una interfaz(debe tener el @ antes de la palabra interface) con las anotaciones: @Constraint, @Target, @Retention

    @Constraint(validatedBy = ExistsByUsernameValidation.class) "Se le pasa la clase que implementa la interfaz ConstraintValidator"
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExistsByUsername {
        String message() default "ya existe el usuario, elige otro username";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

- Se implementa la interfaz ConstraintValidator<LaInterfaz--es la interfaz ExistsByUsername, TipoDeDatoDelCampo Donde se aplica> de jakarta.validation.ConstraintValidator   
    @Component
    public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

        @Autowired
        private UserService userService;

        @Override
        public boolean isValid(String username, ConstraintValidatorContext context) {
         // Se implementa la logica de validacion
         // En este caso solo es llamar al metodo existsByUsername() en el service para ver
         // si existe el nombre de usuario

            // Se llama al metodo creado en el service que utiliza el metodo creado en el repositorio.
            return !userService.existsByUsername(username);
        }
    }

- Para validar, en este caso al usuario, se creo un metodo en el repositorio con la nomenclatura  <boolean existsByUsername(String username);>
- Se aplica la notacion creada **@ExistsByUsername** al campo de la entidad donde se necesita validar.  

- En caso de no usar la notacion @Valid para comprobar los datos que se van a insertar y teniendo las restricciones en la entidad
  se lanza la excepcion ConstraintViolationException con los mensajes de error. Esta excepcion se puede capturar en un handler de 
  excepciones globales. 

### Handler Exception
  Las anotaciones para manejar global handler son: **@ControllerAdvice y @RestControllerAdvice**  
<code> 
    @ExceptionHandler(ConstraintViolationException.class)  
        public ResponseEntity<?> handlerConstraintViolationException(  
                ConstraintViolationException ex, WebRequest request){

            Map<String, String> errors = new HashMap<>();

            ex.getConstraintViolations().forEach(violation -> {
                String fieldName = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.put(fieldName, message);
            });


            // Personaliza el mensaje general si lo deseas
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error de validación en los datos enviados.");
            response.put("errors", errors);
            response.put("timestamp", java.time.LocalDateTime.now());
            response.put("details", "Verifique los campos con errores.");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } 
</code> 

- La excepcion **EntityNotFoundException** cuando se lanza y existe un global handler para capturarla debe tener las anotaciones
  **@ResponseBody** en el metodo donde se captura o tener el **@RestControllerAdvice** a inicio de la clase.  
  Ej:
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        
    }

### Authentication -JWT (Json Web Token)
- El String de los roles en la BD deben ser de la forma **"ROLE_nombreRol"**.  
- Libreria:
    jjwt-root
    url github: https://github.com/jwtk/jjwt

====================================
# TESTING

- Recomendable en la carpeta de test crear una jerarquia de carpetas similar a la del proycto para poder encontrar con facilidad los test implementados y 
  a que clase se esta aplicando el test.  

- Hay que importar: "import org.junit.jupiter.api.*;" para usar los "assert".  
- Usando los assert se puede hacer las comparaciones de los paramatros que se quieren comprobar (ej. assertEquals(), assertNotNull(), etc)   

- Los Mock son objetos simulados a los objetos que imitan el comportamiento de objetos reales de una forma controlada.
- Con Mockito se puede hacer el uso de Mock para simular entidades y tambien se puede hacer uso para simular llamadas a metodos y comprobar
  si el comportamiento del codigo.  

==================================== 
# EXCEPTION

- Se pueden crear exception personalizadas. Se puede crear una carpeta que agrupe las exceptions.
- Se extiende de RuntimeException (ej. 
<public class DeleteEntityNotFoundException extends RuntimeException{
    public DeleteEntityNotFoundException(String message) {
        super(message);
    }
}
)>
- La exceptions se pueden llamar desde los servicios (ej. 
  <this.productRepository.findById(id).orElseThrow(
                () -> new DeleteEntityNotFoundException("Not found entity with id: " + id)
        );
        )>
- Sino se crea un **GlobalExceptionHandle** cuando se lanza la exception muestra el error en el servidor pero el cliente de la api no ve la respuesta,
  solo recive el error 500.
- **@ControllerAdvice**: Permite gestionar globalmente las excepciones. Cuando el sistema lanza una excepcion, en la clase donde se implementa la notacion
    @ControllerAdvice, debe haber un metodo que toma la excepcion como parametro y luego realiza las operaciones necesarias para devolver al cliente. 
    (ej. Esta en la clase: cursoSpringBoot.error.GlobalExceptionHandler) 
    El metodo que se lanza cuando una exception ocurre es el metodo que tiene como argumento la exception que se lanza. 
    (ej. **public ResponseEntity<?> handleUserNotFoundException(DeleteEntityNotFoundException ex, WebRequest request)**).
  
# SPRING SECURITY

- Se debe importar para el uso de Spring Security
    implementation 'org.springframework.boot:spring-boot-starter-security'
    testImplementation 'org.springframework.security:spring-security-test'

- Para el uso del JWT (Json Web Token)
  Se busca en el sitio de jwt, se filtra por java y se va al repositorio. Ahi estan las especificaciones de lo que se debe importar.
    implementation 'io.jsonwebtoken:jjwt-api:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6' // or 'io.jsonwebtoken:jjwt-gson:0.12.6' for gson

- Solo permite insertar valor. Como medida de seguridad no muestra el valor del campo cuando se devuelve la entidad en formato json.
    **@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)**
    **private String password;**

- Otra alternativa para ignorar el campo cuando devuelve la entidad. El problema es que esta anotacion excluye el 
  el valor tanto para escribir como para leer, entoces cuando creas un usuario nunca recibe el valor password.
  **@JsonIgnore**
  **private String password;**

- Se debe crear una clase de tipo @Configuration para establecer las configuraciones de Spring Security **(ej. public class SpringSecurityConfig)**.

## CREAR USUARIOS

## AUTENTICACION

- Se crea un metodo Bean en la clase de configuracion de Spring Security **(ej. public class SpringSecurityConfig)** donde se añaden los filtros.
    @Bean
    **SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {}**
- En este metodo se annaden los filtros que se crean para la autenticacion.

### Filtros
1- Se crea una clase que extiende de UsernamePasswordAuthenticationFilter.
    - Se sobre escriben los metodos:
      - **public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)**
      - protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                                Authentication authResult) throws IOException, ServletException**
      - protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                  AuthenticationException failed) throws IOException, ServletException                                             
2- Se crea otra clase que valida el token y extiende de BasicAuthenticationFilter (ej. public class JwtValidationFilter extends BasicAuthenticationFilter {})
    Este es el constructor:  public JwtValidationFilter(AuthenticationManager authenticationManager) {
                                super(authenticationManager);
                            }   
    - Se sobre escribe el metodo:
          @Override
          protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                  throws IOException, ServletException{}


