package cursoSpringBoot.error;

import cursoSpringBoot.error.pojo.ErrorDetails;
import cursoSpringBoot.exceptions.DeleteEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Permite una gestion global de las excepciones.
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());

        System.out.println(ex.getMessage());

        return errorResponse;
    }

    // Captura la excepcion DeleteEntityNotFoundException, es una
    // es una excepcion personalizada.
    @ExceptionHandler(DeleteEntityNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(DeleteEntityNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // ConstraintViolationException captura las excepciones lanzadas por las
    // restricciones (ej. @Size(min = 5, max = 20)) en la entidad, si no son manejadas antes de la insercion
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(
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
}

