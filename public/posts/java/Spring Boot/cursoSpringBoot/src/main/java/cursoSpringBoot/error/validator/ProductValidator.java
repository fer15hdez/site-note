package cursoSpringBoot.error.validator;

import cursoSpringBoot.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
//        Las dos formas funcionan
        return Product.class.equals(clazz);
//        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        // Existen varias opciones en la clase ValidationUtils para validar los campos
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "Por favor rellene el campo nombre");

        if (product.getPrice() <= 50){
            errors.rejectValue("price", null,"debe mayor que 50");
        }

    }
}
