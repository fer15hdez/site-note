package cursoSpringBoot;

import ch.qos.logback.core.testUtil.StringListAppender;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.domain.ProductRepository;
import cursoSpringBoot.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Random;

@SpringBootApplication
public class CursoSpringBootApplication {

	public static void main(String[] args) {

		var app = new SpringApplication(CursoSpringBootApplication.class);
		//var app = SpringApplication.run(CursoSpringBootApplication.class, args);
		app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "prod"));
		var ctx = app.run(args);

		ProductServiceImpl productService = ctx.getBean(ProductServiceImpl.class);
		ApplicationConfig appConfig = ctx.getBean(ApplicationConfig.class);

		System.out.println(productService.tellStory());
		System.out.println("Value of properties in file configuration: " + productService.getValueProperties());
		System.out.println("Assign a value to a field in class: " + productService.getStringValueProperties());
		System.out.println(("My first bean: " + appConfig.MyFirstBean()));


		System.out.println("Properties from values.properties file: " + "int" );

		System.out.println(("My second bean: " + appConfig.productBeanToTest()));


	}

	@Autowired
	public Product useBeanPropetyQualifier(@Qualifier("SecondBean") Product product, @Value("${value.int}") Integer valueInt){
		System.out.println("@Value(\"${value.int}\"): " + valueInt.toString());
		System.out.println("My second Bean ");
		return new Product();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductRepository productRepository){
		int randomSerial = new Random().nextInt();
		if (randomSerial < 0) randomSerial = randomSerial * -1;
		int finalRandomSerial = randomSerial;
		return args -> {
			var product = Product.builder()
					.name("Iphone")
					.serial(finalRandomSerial)
					.stock(50)
					.build();
			productRepository.save(product);
		};
	}

}
