package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import cursoSpringBoot.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:custom.app.properties")
})
public class ProductServiceImpl implements ProductService{



    Environment environment;
    @Value("${int.value}") // Toma el valor del archivo de configuracion "custom.app.properties" definido arriba.
    private Integer valueProperties;
    @Value("A value string properties") // Asigna un valor a la propiedad "stringValueProperties".
    private String stringValueProperties;
    @Autowired
//    @Qualifier("SecondBean")
    private Product product; // Spring se encarga de inicializar la instancia de Product.
    public static List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop", 799.9, 10, "some colum"),
            new Product(2, "smatphone", 399.3, 10, "some colum"),
            new Product(3, "tablet", 100.6,10, "some colum"),
            new Product(3, "watch", 100.6,10, "some colum")
    ));

//    @Autowired // Esta anotacion permite hacer la inyeccion de dependencia. La Inyeccion de Dependencia de constructor es una buena practica (Best Practice)
    public ProductServiceImpl( List<Product> products) {
        this.products = products;
    }


    @Override
    public List<Product> getProducts(){
        return products;
    }

    // Como fue inyectado, spring se encarga de inicializar el Product.
    public Product getProduct(){ return  this.product;}

    @Profile("dev")
    public String tellStory(){ return "A second Bean";}

    public String getStringValueProperties() {
        return stringValueProperties;
    }

    public Integer getValueProperties() {
        return valueProperties;
    }

    public Environment getEnvironment() {
        return environment;
    }


    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
