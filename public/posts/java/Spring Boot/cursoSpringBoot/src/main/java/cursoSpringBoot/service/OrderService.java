package cursoSpringBoot.service;

import cursoSpringBoot.domain.Order;
import cursoSpringBoot.domain.OrderRepository;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.domain.ProductRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Order order ){
       /* List<Integer> idProducts = new ArrayList<>();
        for (Product p: order.getProducts()){
            idProducts.add(p.getId());
        }

        List<Product> products = productRepository.findAllById(idProducts);
        order.setProducts(products);*/
        return this.orderRepository.save(order); // Inserta el producto en la bd.

    }
}
