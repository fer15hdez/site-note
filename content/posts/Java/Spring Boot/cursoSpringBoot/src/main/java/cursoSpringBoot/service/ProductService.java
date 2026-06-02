package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ProductService {

    public List<Product> getProducts();
}
