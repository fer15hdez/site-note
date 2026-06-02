package cursoSpringBoot;


import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@PropertySource("classpath:custom.app.properties")
@PropertySources({
        // La opcion (encoding = "UTF-8") permite poner incorporar caracteres especiales(ñ, vocales con tilde)
        @PropertySource(value = "classpath:values.properties", encoding = "UTF-8"),
// Se pueden agregar mas archivos de propiedad
})
public class ApplicationConfig {

    @Bean
    @Primary
    @Profile("dev")
    public ProductServiceImpl MyFirstBean(){
        return new ProductServiceImpl(Arrays.asList(
            new Product(1, "Laptop", 799.9, 10, "some colum"),
            new Product(2, "smatphone", 399.3, 10, "some colum"),
            new Product(3, "tablet", 100.6,10, "some colum"),
            new Product(3, "watch", 100.6,10, "some colum")
    ));

    }

    @Bean
    @Qualifier("SecondBean")
    public Product productBeanToTest(){
        return new Product();
    }
}
