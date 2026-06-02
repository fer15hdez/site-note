package cursoSpringBoot.validation;

import cursoSpringBoot.service.security.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UserService userService;

   /* public ExistsByUsernameValidation(UserService userService) {
        this.userService = userService;
    }*/

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (userService == null){
            return true;
        }

        // Se implementa la logica de validacion
        // En este caso solo es llamar al metodo existsByUsername() para ver
        // si existe el nombre de usuario
        return !userService.existsByUsername(username);
    }
}
