package cursoSpringBoot.controller;

import cursoSpringBoot.domain.Customer;
import cursoSpringBoot.domain.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController //Le dice a spring que debe tomar esta clase como un contralador
@RequestMapping("/clientes")
public class CustomerController {

    private final CustomerRepository customerRepository;

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Fernando", "fer", "123"),
            new Customer(124, "Antonio", "fer1", "123")

    ));

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Otra forma de especificar que es un endpoint y definition el método
    //@RequestMapping(method = RequestMethod.GET)
    /*
    @GetMapping
    public List<Customer> getCustomers(){

        return customers;
    }
    */


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED) //Si se ejecuta sin excepciones devuelve el http method 'ACCEPTED'
    public ResponseEntity<List<Customer>> getCustomers(){
        // La clase ResponseEntity es la encargada de devolver los códigos http
        return ResponseEntity.ok(customers);
    }

    //@RequestMapping(value = "/{username}", method = RequestMethod.GET) // Cuando se pasa valor por la ruta
    @GetMapping("/{user-name}") // Pasando valor por la ruta
    //@GetMapping("/clientes/{username}")
    // Esta ruta es la que permite el acceso al endpoint. Si la clase tiene
    // el decorador @RequestMapping("/ruta") con una ruta especificada ("/ruta"),
    // entonces la ruta sería '/ruta/{username}'
    // El decorador @PathVariable permite tomar el valor de la url '/ruta/{username}'. Debe coincidir el valor dentro {} con
    // la variable pasada por parametros. Si no coinciden hay que especificar el valor de la ruta {user-name} en el
    // decorador @PathVariable("user-name")
    public ResponseEntity<?> getCustomer(@PathVariable("user-name") String username){
        //   ResponseEntity<?>
        // Dentro de los <> se define el tipo de datos que devuelve. La clase ResponseEntity<> encapsula el tipo de dato que se devuelve.
        // Los símbolos se llaman corchetes angulares (mayor que y menor que)
        // Cuando se especifica el símbolo '?', se flexibiliza el valor que sé devuelve, se puede devolver cualquier tipo de dato

        // El decorador @PathVariable indica que el valor se toma de la ruta, en este caso es {username}

        for (Customer c: customers){
            if (c.getUsername().equalsIgnoreCase(username)){
                return ResponseEntity.ok(c); // Devuelve un estado 200 y el objeto 'c'
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: " + username);
    }

    @GetMapping("/param")
    //http://localhost:8080/sistema/api/v1/clientes/param?user-name=fernan&last-name=vel
    // @RequestParam("user-name") permite especificar el valor que se pasa por parametro
    public String paramVar(
            @RequestParam("user-name") String userName,
            @RequestParam("last-name") String lastNmae
    ){
        return "My value parameter are, " + "userName: " + userName;
    }

    // @RequestMapping(method = RequestMethod.POST) // Funciona de igual forma que @PostMapping
    @PostMapping
    // El decorador @RequestBody toma de las request post el valor enviado
    public Customer postClientes (@RequestBody Customer customer){
        customers.add(customer);
        return customer;
    }

    @PutMapping
    public ResponseEntity <?> putCliente (@RequestBody Customer customer){
        for (Customer c: customers) {
            if (c.getID() == customer.getID()){
              c.setName(customer.getName());
              c.setUsername(customer.getUsername());
              c.setPassword(customer.getPassword());

              return ResponseEntity.ok("Cliente modificado correctamente: " + customer.getID());
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con id: " + customer.getID());

    }

    public ResponseEntity<?> deleteCliente(int id){
        for (Customer c: customers){
            if (c.getID() == id){
                customers.remove(c);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado: " + id);
            }
        }

        //Respuesta con un mensaje en el cuerpo del return.
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el cliente: " + id);
        return ResponseEntity.noContent().build(); // Respuesta simplificada, no se devuelve un mensaje con el código.

    }

    @PostMapping("/db")
    public Customer createCustomer(
            @RequestBody Customer customer
    ){
        return customerRepository.save(customer);

    }



}
