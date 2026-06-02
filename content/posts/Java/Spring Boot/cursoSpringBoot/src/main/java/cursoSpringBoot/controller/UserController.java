package cursoSpringBoot.controller;

import cursoSpringBoot.domain.security.User;
import cursoSpringBoot.service.security.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<User> list(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if (result.hasFieldErrors()){
           return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    // Para crear un usuario admin hay que crear algunas reglas de seguridad, entonces nos aseguramos
    // que la creacion de un nuevo user sea con rol comun ("ROLE_USER")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        user.setAdmin(false);
        return create(user, result);
    }

    ResponseEntity<?> validate(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();

        if(bindingResult.hasFieldErrors()){
            bindingResult.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
            );
        }

        return ResponseEntity.badRequest().body(errors);
    }
}
